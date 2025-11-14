package fr.ateastudio.farmersdelight.block.behavior

import fr.ateastudio.farmersdelight.registry.Blocks
import fr.ateastudio.farmersdelight.util.relative
import org.bukkit.Tag.REPLACEABLE
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions.BlockPlace
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.integration.protection.ProtectionManager
import xyz.xenondevs.nova.util.BlockUtils.breakBlockNaturally
import xyz.xenondevs.nova.util.BlockUtils.placeBlock
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.behavior.BlockBehavior
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.format.WorldDataManager

object TatamiMatHead : BlockBehavior {
    
    override fun handlePlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>) {
        val face = state[DefaultBlockStateProperties.FACING]!!
        val footPos = pos.relative(face.oppositeFace)
        val footMat = Blocks.FULL_TATAMI_MAT_FOOT.defaultBlockState
            .with(DefaultBlockStateProperties.FACING, face.oppositeFace)
        val contextFoot = Context.intention(BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, footPos)
            .param(DefaultContextParamTypes.BLOCK_STATE_NOVA, footMat)
            .build()
        placeBlock(contextFoot)
    }
    
    override suspend fun canPlace(pos: BlockPos, state: NovaBlockState, ctx: Context<BlockPlace>): Boolean {
        if (!REPLACEABLE.isTagged(pos.block.type) || WorldDataManager.getBlockState(pos) != null) {
            return false
        }
        
        if (!ProtectionManager.canPlace(ctx))
            return false
        
        val face = state[DefaultBlockStateProperties.FACING]!!
        val footPos = pos.relative(face.oppositeFace)
        return !(!REPLACEABLE.isTagged(footPos.block.type) || WorldDataManager.getBlockState(footPos) != null)
    }
    
    override fun handleBreak(pos: BlockPos, state: NovaBlockState, ctx: Context<DefaultContextIntentions.BlockBreak>) {
        val face = state[DefaultBlockStateProperties.FACING]!!
        val footPos = pos.relative(face.oppositeFace)
        if (footPos.novaBlockState?.block == Blocks.FULL_TATAMI_MAT_FOOT) {
            val contextFoot = Context.intention(DefaultContextIntentions.BlockBreak)
                .param(DefaultContextParamTypes.BLOCK_POS, footPos)
                .param(DefaultContextParamTypes.SOURCE_ENTITY, ctx[DefaultContextParamTypes.SOURCE_PLAYER])
                .build()
            breakBlockNaturally(contextFoot)
        }
    }
    
    
}