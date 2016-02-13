springboot-template-web-ja
==========================

はじめに
--------

このプロジェクトは画面のあるアプリケーションの雛形として利用されることを想定して作成しています。

### 実装していること。

- CRUD操作。
  特定のテーブル(選手テーブル)のデータのCRUD(一覧表示、登録、更新、削除)が行えます。
- ページネーション。
  一覧表示部分にはページネーションが付いています。
- テーブル連結。
  複数のテーブル(選手テーブルと国籍テーブル)を連結して画面表示しています。
  JPA の遅延ロードを利用しています。
- JPA での LocalDateTime の使用。
- コードの enum 管理。
  1: A, 2: B, 3: O, 4: AB 、のようなものをコードと呼ぶことにします。
  このコードを enum で管理する仕組みを用意しています。
  コードのプルダウンなども作成しやすいよう考慮しています。
- Heroku へのデプロイ。
  Heroku へデプロイできるようにしています。

### 諦めていること。

- thymeleaf は html で記述できるのでそのままブラウザで確認できます。
  が、部品化やレイアウト機能(thymeleaf layout dialect)を使っていることで、
  ブラウザでそのまま表示させることはできなくなっています。
  (これを実現するための Thymol というライブラリもあるようですが、利用していません。)
- i18n(多言語化)は行っていません。
  世界に公開するようなアプリケーションよりも、
  特定の人たちに利用してもらうアプリケーションでの利用の方が多いと思ってのことです。
  i18n を実現するためにコードが複雑化(難読化)することを避けるという目的もあります。

### 注意事項

Spring Boot や thymeleaf などの仕様に関する説明は、ソースコメントの先頭に `【解説】` と記載しています。
本来はコメントする必要のないものと思っています。

利用ライブラリ
--------------

```gradle
dependencies {
    // Spring
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compile('org.springframework.boot:spring-boot-starter-security')
    compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.springframework.boot:spring-boot-configuration-processor')
    // Others
    compile('org.projectlombok:lombok:1.16.6')
    compile('net.sourceforge.nekohtml:nekohtml:1.9.22')
    compile('org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16')
    compile('org.flywaydb:flyway-core')
    // DataBase
    runtime('com.h2database:h2')
    runtime('mysql:mysql-connector-java')
    // WebJars
    compile('org.webjars:bootstrap:4.0.0-alpha.2')
    compile('org.webjars:jquery:2.1.4')
    compile('org.webjars:font-awesome:4.5.0')
    // Test
    testCompile('org.springframework.boot:spring-boot-starter-test')
}
```



パッケージ構成
--------------

主なパッケージは以下になります。

| パッケージ  | 単位 |
| ---------- | ---- |
| controller | 画面単位 |
| web        | 画面単位 |
| service    | 画面単位 |
| repository | テーブル単位 |
| domain     | テーブル単位 |




DB アクセス
-----------

### 命名

`players` 、 `nationarities` のように、複数形としています。
Ruby on Rails と同じような命名としました。

PK は `id` 、名称は `name` のように、汎用的な命名としています。
プロパティにアクセスする際に `player.playerId` ではなく `player.id` となり、より自然に読むことができるようにするためです。

他のテーブルの `id` を表現する場合は、 `nationarity_id` のように、プレフィックスに他のテーブルの名称の単数形を付けるようにします。

### Date and Time API の利用

JPA は、 `LocalDateTime` などの Date and Time API には対応していません。
ですが、 `@Entity` を付与したクラスでも Date and Time API を使いたいです。

これを解決するため、 `LocalDateTimeAttributeConverter` を用意し、 `LocalDateTime` と `Timestamp` を自動変換するようにしています。
この `LocalDateTimeAttributeConverter` 以外に `Timestamp` が登場することがないため、思う存分 Date and Time API に依存したコードを書くことができるようになります。

参考）
[How to persist LocalDate and LocalDateTime with JPA](http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/)


### コード

A型: 1、B型: 2、O型: 3、AB型:4、のようなよくある区分はコードと呼ぶことにしています。

コードは `id` と `name` というプロパティを保持した `enum` で作成します。
`enum` は `CodeConsts` の中に定義します。
各コードは、 `Code` インターフェースを実装します。




Thymeleaf
---------

### 共通レイアウト

Thymeleaf Layout Dialect を使っています。

shared/layout.html を用意し、`<head>` の中身やヘッダー部品の取込なども含めた基本レイアウトとしています。
このレイアウト側の方を、 Decolator と呼びます。
レイアウトを導入する各画面の方を、 Content と呼びます。

各画面(Content)では、 `<html>` タグでレイアウト(Decolator)を指定します。

```html
<html lang="ja"
  xmlns:th="http://www.thymeleaf.org"
  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
  xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4"
  layout:decorator="layouts/main"> <!-- ←ここ！ -->
```

### 部品化

画面の部品は fragments.html に集約しています。
全画面で共通の部品は、 shared/fragments.html に、
各機能ごと、例えば players 内で共通の部品であれば、 players/fragments.html を用意します。

この中に、 `<nav th:fragment="pagenation(path)">...</nav>` のように部品が定義されています。

fragments.html では、IDEのエラーがでないよう下記のように `<html>` タグだけ書くようにしています。

```html
<!DOCTYPE html>
<html lang="ja" xmlns:th="http://www.thymeleaf.org">

<!-- ここに部品を記述 -->

</html>
```



