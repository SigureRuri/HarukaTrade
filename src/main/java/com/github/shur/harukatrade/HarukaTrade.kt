package com.github.shur.harukatrade

import com.github.shur.harukatrade.api.TradeRegistry
import com.github.shur.harukatrade.command.HarukaTradeCommand
import com.github.shur.harukatrade.listener.PlayerInteractListener
import com.github.shur.harukatrade.tradeloader.TradeLoader
import com.github.shur.harukatrade.tradeloader.drip.DripTradeLoader
import org.bukkit.plugin.java.JavaPlugin

class HarukaTrade : JavaPlugin() {

    override fun onEnable() {
        instance = this

        tradeRegistry = HarukaTradeRegistry()

        tradeLoaders.add(DripTradeLoader())
        loadTrades()

        HarukaTradeCommand.registerCommand()

        server.pluginManager.registerEvents(PlayerInteractListener(), this)
    }

    fun loadTrades() {
        tradeLoaders.forEach {
            it.load()
        }
    }

    companion object {

        lateinit var instance: HarukaTrade
            private set

        lateinit var tradeRegistry: TradeRegistry
            private set

        val tradeLoaders: MutableList<TradeLoader> = mutableListOf()

    }

}