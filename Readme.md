

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


### HTML

Thymeleaf Layout Dialect を使っています。

layout.html を用意し、`<head>` の中身やヘッダー部品の取込なども含めた基本レイアウトとしています。
このレイアウト側の方を、 Decolator と呼びます。
レイアウトを導入する各画面のことを、 Content と呼びます。

各画面(Content)では、 `<html>` タグでレイアウト(Decolator)を指定します。

```html
<html lang="ja"
  xmlns:th="http://www.thymeleaf.org"
  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
  xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4"
  layout:decorator="layout"> <!-- ←ここ！ -->
```

画面の部品は fragments.html に集約しています。
この中に、 `<nav th:fragment="pagenation(path)">...</nav>` のように部品が定義されています。





