# HarukaTrade
An Easy-to-edit Trade Plugin for SpigotMC

## Dripとの違い
[Drip](https://github.com/SigureRuri/Drip) は、プレイヤーがゲーム内からトレードを扱うことを目的として設計されています。  
HarukaTradeでは、Dripからゲーム内からのトレード編集を削除し、プログラムからトレードを扱うようにしました。

## Commands

HarukaTradeはコマンドシステムに [CommandAPI](https://commandapi.jorel.dev/) を使用しています。そのため、Vanillaの`/execute`コマンドを介して実行することができます。


---

```
/drip open <Trade>
```

必要な権限 `drip.command.open`

実行者に対しトレードを開きます。  
このコマンドは一般のユーザーが使用することを目的として作成されておらず、管理者が特定のユーザーに対して`execute`コマンドを用いて使われることを目的としています。  
例: `/execute as @a[tag=Winner] run drip open trade_for_winners`

-- 

## Trade

プレイヤーは、それぞれのトレードに設定されているUUIDに対応するエンティティをクリックするとトレード画面を開くことができます。

### トレード画面

トレードには、

- 取引が可能な「商品」
- トレード画面の装飾として存在する「バックパネル」

が存在します。  
前者にカーソルを合わせ右クリックで商品の情報を確認することができます。
左クリックで購入確認画面を開きます。また、Shift + 左クリックで、購入確認画面なしで商品を購入します。

## Drip連携

HarukaTradeは、Dripとの連携をサポートしています。  
`/drip export harukatrade [all | trade <TradeID>]`でエクスポートしたファイルを、`plugins/HarukaTrade/drip`以下に配置することで読み込むことができます。

### Dripから読み込んだトレードの権限
`drip.trade.*` - すべてのトレードを開くことができます。
`drip.trade.<TradeID>` - トレードIDに対応するトレードを開くことができます。

この権限は、`/drip open`コマンドには適応されません。

## 開発者向けドキュメント

執筆中

## 将来的に実装される可能性があるもの

- Vaultと連携した通貨を利用した取引