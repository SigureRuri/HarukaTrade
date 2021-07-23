package com.github.shur.harukatrade

import com.github.shur.harukatrade.api.Trade
import com.github.shur.harukatrade.api.TradeId
import com.github.shur.harukatrade.api.TradeRegistry

class HarukaTradeRegistry : TradeRegistry {

    private val trades: MutableMap<TradeId, Trade> = mutableMapOf()

    override fun get(id: TradeId): Trade? =
        trades[id]

    override fun getAll(): List<Trade> =
        trades.values.toList()

    override fun has(id: TradeId): Boolean =
        trades.contains(id)

    override fun register(trade: Trade) {
        if (trades.contains(trade.id)) throw IllegalArgumentException()

        trades[trade.id] = trade
    }

    override fun unregister(id: TradeId) {
        trades.remove(id)
    }

}