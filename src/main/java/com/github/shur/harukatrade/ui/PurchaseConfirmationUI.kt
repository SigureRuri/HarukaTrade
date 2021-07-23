package com.github.shur.harukatrade.ui

import com.github.shur.harukatrade.api.Product
import com.github.shur.harukatrade.api.Trade
import com.github.shur.harukatrade.util.TradeUtil
import com.github.shur.whitebait.dsl.window
import com.github.shur.whitebait.inventory.InventoryUI
import com.github.shur.whitebait.inventory.window.TypedWindowOption
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType

class PurchaseConfirmationUI(
    val trade: Trade,
    val product: Product
) : InventoryUI {

    override val window by window(TypedWindowOption(InventoryType.HOPPER)) {

        title = "${ChatColor.DARK_PURPLE}${ChatColor.BOLD}購入確認"

        slot(0) {
            icon {
                type = Material.GOLD_NUGGET
                name = "${ChatColor.GOLD}${ChatColor.BOLD}購入"
            }
            onClickFilterNotDoubleClick {
                TradeUtil.buy(product, player)

                TradeUI(trade).openLater(player)
            }
        }

        slot(2) {
            icon(product.sell) { }
        }

        slot(4) {
            icon {
                type = Material.BARRIER
                name = "${ChatColor.RED}${ChatColor.BOLD}キャンセル"
            }
            onClickFilterNotDoubleClick {
                TradeUI(trade).openLater(player)
            }
        }

    }

}