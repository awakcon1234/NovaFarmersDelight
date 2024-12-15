@file:Suppress("unused")

package fr.ateastudio.farmersdelight.util

import fr.ateastudio.farmersdelight.NovaFarmersDelight

object Logger {
        fun error(message: String) {
                NovaFarmersDelight.logger.error(message)
        }
        fun warn(message: String) {
                NovaFarmersDelight.logger.warn(message)
        }
        fun debug(message: String) {
                NovaFarmersDelight.logger.debug(message)
        }
        fun info(message: String) {
                NovaFarmersDelight.logger.info(message)
        }
        fun trace(message: String) {
                NovaFarmersDelight.logger.trace(message)
        }
}