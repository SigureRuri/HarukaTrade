package com.github.shur.harukatrade.tradeloader.drip

import com.github.shur.harukatrade.HarukaTrade
import com.github.shur.harukatrade.tradeloader.TradeLoader
import java.io.File

class DripTradeLoader : TradeLoader {

    override fun load() {
        val dataFolder = File("${HarukaTrade.instance.dataFolder.absolutePath}/drip")
        if (!dataFolder.canRead()) throw IllegalStateException()
        if (!dataFolder.exists() || !dataFolder.isDirectory) dataFolder.mkdirs()

        dataFolder.listFiles()?.forEach {
            if (!it.canRead()) return@forEach
            if (!it.isFile) return@forEach
            if (it.extension != "yml") return@forEach

            val trade = DripTrade(it)
            if (HarukaTrade.tradeRegistry.has(trade.id)) {
                HarukaTrade.instance.logger.info("${trade.id} could not be registered because ${trade.id} has already registered")
            } else {
                HarukaTrade.tradeRegistry.register(trade)
            }
        }

    }
}