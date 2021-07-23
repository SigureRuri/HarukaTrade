package com.github.shur.harukatrade.command.argument

import com.github.shur.harukatrade.HarukaTrade
import com.github.shur.harukatrade.api.TradeId
import dev.jorel.commandapi.arguments.CustomArgument
import org.bukkit.command.CommandSender

object TradeArgument {

    fun tradeArgument(nodeName: String) =
        CustomArgument(nodeName) {
            val tradeId = try {
                TradeId(it)
            } catch (e: IllegalArgumentException) {
                throw CustomArgument.CustomArgumentException(
                    CustomArgument.MessageBuilder("Invalid tradeId: ").appendArgInput()
                )
            }

            HarukaTrade.tradeRegistry.get(tradeId) ?: throw CustomArgument.CustomArgumentException(
                CustomArgument.MessageBuilder("Unknown trade: ").appendArgInput()
            )
        }.overrideSuggestions { _: CommandSender ->
            HarukaTrade.tradeRegistry.getAll()
                .map { it.id.toString() }
                .toTypedArray()
        }

}