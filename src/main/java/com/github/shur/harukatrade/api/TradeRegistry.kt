package com.github.shur.harukatrade.api

interface TradeRegistry {

    fun get(id: TradeId): Trade?

    fun getAll(): List<Trade>

    fun has(id: TradeId): Boolean

    fun register(trade: Trade)

    fun unregister(id: TradeId)

}