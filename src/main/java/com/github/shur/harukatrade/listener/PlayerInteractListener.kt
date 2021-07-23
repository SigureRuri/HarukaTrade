package com.github.shur.harukatrade.listener

import com.github.shur.harukatrade.HarukaTrade
import com.github.shur.harukatrade.ui.TradeUI
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class PlayerInteractListener : Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onInteractEntity(event: PlayerInteractEntityEvent) {
        val player = event.player

        HarukaTrade.tradeRegistry.getAll().forEach {
            if (!it.owners.contains(event.rightClicked.uniqueId)) return@forEach
            if (!it.canOpen(player)) return@forEach

            TradeUI(it).open(event.player)

            event.isCancelled = true
        }

    }

}