

Spring Boot や thymeleaf などの仕様に関する説明は、ソースコメントの先頭に `【解説】` と記載しています。



| controller | 画面単位 |
| web        | 画面単位 |
| service    | 画面単位 |
| repository | テーブル単位 |
| domain     | テーブル単位 |






### DBスキーマ

`players` 、 `nationarities` のように、複数形としています。
Ruby on Rails と同じような命名としました。

PK は `id` 、名称は `name` のように、汎用的な命名としています。
これは、プロパティにアクセスする際に `player.playerId` ではなく `player.id` とするためです。
なお、他のテーブルの `id` を表現する場合は、 `nationarity_id` のように、プレフィックスに他のテーブルの名称の単数形を付けるようにします。



### コード

A型: 1、B型: 2、O型: 3、AB型:4、のようなよくある区分はコードと呼ぶことにしています。

コードは `id` と `name` というプロパティを保持した `enum` で作成します。
`enum` は `CodeConsts` の中に定義します。
各コードは、 `Code` インターフェースを実装します。



