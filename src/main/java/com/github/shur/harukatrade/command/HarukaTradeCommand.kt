package com.github.shur.harukatrade.command

import com.github.shur.harukatrade.api.Trade
import com.github.shur.harukatrade.command.argument.TradeArgument
import com.github.shur.harukatrade.ui.TradeUI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.command.Command

object HarukaTradeCommand {

    private val open = CommandAPICommand("open")
        .withPermission("harukatrade.command.open")
        .withArguments(TradeArgument.tradeArgument("trade"))
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val trade = args[0] as Trade

            TradeUI(trade).open(sender)

            Command.broadcastCommandMessage(sender, "Opened trade [${trade.id}]")
        })

    private val drip = CommandAPICommand("harukatrade")
        .withAliases("htrade")
        .withSubcommand(open)

    internal fun registerCommand() {
        drip.register()
    }

}