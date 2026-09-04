package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.block.BlockStateProperties
import org.bukkit.GameRule
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockPlace
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.util.BlockUtils.updateBlockState
import xyz.xenondevs.nova.util.below
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import kotlin.random.Random

/**
 * A crop two blocks tall, after Mysterious Mountain Lib's HighCropBlock, which Corn Delight's
 * corn is built on.
 *
 * Both halves are the same block and carry their own age. The lower half is planted; once it has
 * reached [growUpperAge] it grows a fresh upper half above itself, and from then on the two age
 * independently. The upper half only stands on a lower half of this crop that is old enough, so
 * breaking, harvesting or trampling the lower half takes the upper half with it, and the upper
 * half can never be planted by hand.
 */
abstract class TallCropBlock : CropBlock() {
    
    /**
     * The age the lower half must reach before an upper half appears above it.
     */
    protected open val growUpperAge: Int
        get() = 3
    
    protected fun isUpper(state: NovaBlockState): Boolean {
        return state[BlockStateProperties.UPPER] ?: false
    }
    
    override fun handleRandomTick(pos: BlockPos, state: NovaBlockState) {
        if (getRawBrightness(pos) >= 9) {
            val age = getAge(state)
            val tickSpeedMultiplier = (pos.world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED) ?: 3) / 3
            val growSpeed = growthSpeed(pos, state)
            
            repeat(tickSpeedMultiplier) {
                if (age < getMaxAge(state) && Random.nextInt(((25.0F / growSpeed) + 1).toInt()) == 0) {
                    growCrop(pos, state)
                }
            }
            
            // The lower half also tries to sprout its upper half, at the same odds as ageing.
            if (!isUpper(state) && age >= growUpperAge) {
                repeat(tickSpeedMultiplier) {
                    if (Random.nextInt(((25.0F / growSpeed) + 1).toInt()) == 0) {
                        growUpper(pos, state)
                    }
                }
            }
        }
        
        if (!canSurvive(state, pos)) {
            breakBlock(pos)
        }
    }
    
    override fun ticksRandomly(state: NovaBlockState): Boolean {
        // A ripe lower half still has to sprout its upper half, so only a ripe upper half rests.
        return !isMaxAge(state) || !isUpper(state)
    }
    
    override fun performBoneMeal(pos: BlockPos, state: NovaBlockState) {
        // Bone meal ripens the half it is used on first, then the other half, then sprouts the
        // upper half if there is none yet, in that order, so a single use always does something.
        if (!isMaxAge(state)) {
            super.performBoneMeal(pos, state)
            return
        }
        
        if (isUpper(state)) {
            val lower = pos.below.novaBlockState
            if (lower != null && lower.block == state.block && !isMaxAge(lower)) {
                super.performBoneMeal(pos.below, lower)
            }
            return
        }
        
        val upper = pos.add(0, 1, 0).novaBlockState
        if (upper == null) {
            growUpper(pos, state)
            showBoneMealParticle(pos)
        } else if (upper.block == state.block && !isMaxAge(upper)) {
            super.performBoneMeal(pos.add(0, 1, 0), upper)
        }
    }
    
    override fun canSurvive(state: NovaBlockState, pos: BlockPos): Boolean {
        if (isUpper(state)) {
            val lower = pos.below.novaBlockState ?: return false
            return lower.block == state.block
                && !isUpper(lower)
                && getAge(lower) >= growUpperAge
                && hasSufficientLight(pos)
        }
        
        return super.canSurvive(state, pos)
    }
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>) {
        if (isUpper(state)) {
            return
        }
        
        // The upper half cannot stand without its lower half, and neighbour updates would remove
        // it a tick later without drops; breaking it here drops its own harvest instead.
        val upper = pos.add(0, 1, 0).novaBlockState ?: return
        if (upper.block == state.block && isUpper(upper)) {
            val upperCtx = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, pos.add(0, 1, 0))
                .param(DefaultContextParamTypes.SOURCE_ENTITY, ctx[DefaultContextParamTypes.SOURCE_PLAYER])
                .param(DefaultContextParamTypes.BLOCK_BREAK_EFFECTS, true)
                .build()
            BlockUtils.breakBlockNaturally(upperCtx)
        }
    }
    
    private fun growUpper(pos: BlockPos, state: NovaBlockState) {
        val abovePos = pos.add(0, 1, 0)
        if (!abovePos.block.isEmpty || abovePos.novaBlockState != null) {
            return
        }
        
        val upperState = state.block.defaultBlockState
            .with(BlockStateProperties.UPPER, true)
            .with(BlockStateProperties.AGE, 0)
        val placeCtx = Context.intention(BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, abovePos)
            .param(DefaultContextParamTypes.BLOCK_STATE_NOVA, upperState)
            .param(DefaultContextParamTypes.BLOCK_PLACE_EFFECTS, false)
            .build()
        BlockUtils.placeBlock(placeCtx)
        
        // handlePlace on the new half sets MAX_AGE and BUDDING_AGE; the upper flag survives it
        // because Ageable only touches those two.
        val placed = abovePos.novaBlockState ?: return
        if (!isUpper(placed)) {
            updateBlockState(abovePos, placed.with(BlockStateProperties.UPPER, true))
        }
    }
    
    /**
     * Farmland is read below the lower half; the upper half borrows its lower half's soil so both
     * halves grow at the same rate.
     */
    private fun growthSpeed(pos: BlockPos, state: NovaBlockState): Float {
        val soilOwner = if (isUpper(state)) pos.below else pos
        return growthSpeedAt(soilOwner)
    }
    
}
