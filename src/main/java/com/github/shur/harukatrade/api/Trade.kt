package com.github.shur.harukatrade.api

import org.bukkit.entity.Player
import java.util.*

interface Trade {

    val id: TradeId

    val name: String

    val owners: Set<UUID>

    val contents: Map<Int, TradeContent>

    fun canOpen(player: Player): Boolean

}