package com.github.shur.harukatrade.tradeloader.drip

import com.github.shur.harukatrade.api.BackPanel
import com.github.shur.harukatrade.api.Product
import com.github.shur.harukatrade.api.Trade
import com.github.shur.harukatrade.api.TradeContent
import com.github.shur.harukatrade.api.TradeId
import org.bukkit.configuration.MemoryConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.util.*

class DripTrade(file: File) : Trade {

    val config = YamlConfiguration().apply { load(file) }

    override val id: TradeId = TradeId(file.nameWithoutExtension)

    override val name: String = config.getString("name")!!

    override val owners: Set<UUID> = config.getStringList("owners").map { UUID.fromString(it) }.toSet()

    override val contents: Map<Int, TradeContent> = mutableMapOf<Int, TradeContent>().apply {
        val contentsConfig = config.getConfigurationSection("contents") ?: MemoryConfiguration()
        contentsConfig.getKeys(false).forEach { contentsKey ->
            val keyInt = contentsKey.toIntOrNull() ?: return@forEach
            val contentConfig = contentsConfig.getConfigurationSection(contentsKey)!!
            val content: TradeContent = when (contentConfig.getString("type")!!) {
                "backPanel" -> {
                    val panel = contentConfig.getItemStack("panel")!!
                    BackPanel(panel)
                }
                "product" -> {
                    val sell = contentConfig.getItemStack("sell")!!
                    val buys = contentConfig.getConfigurationSection("buy")!!.let { buyConfig ->
                        buyConfig.getKeys(false)
                            .map { buyConfig.getItemStack(it)!! }
                            .toMutableList()
                    }
                    Product(sell, buys)
                }
                else -> return@forEach
            }

            put(keyInt, content)
        }
    }

    override fun canOpen(player: Player) = player.hasPermission("drip.trade.*") || player.hasPermission("drip.trade.${id}")

}