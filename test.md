# テスト方式設計書

## はじめに

本ドキュメントは、serioのFW移行プロジェクトにおいて、新serioアプリケーション(以下、新serio)のテスト方式を示したもの。

本ドキュメントを元に、各開発者はテストケースの作成、テストの実装、実行をおこなう。

## テストの定義

新serioのテストとして、テストのカバー範囲に応じて以下の3つが考えられる。

- 単体テスト：クラス/メソッドの振る舞いを検証する
- 結合テスト：新serioのAPIの振る舞いを検証する
- E2Eテスト：ユーザーの実際の操作に応じたシステム全体の振る舞いを検証する

このうち、本ドキュメントでは**結合テスト**を対象とする。

## テストの目的

- 新serioの動きが、移行前と変わらないことを確認する
- テストをコード化することで、アプリケーションの保守性を向上させる

## テストの実装

### 技術スタック

新serioのフレームワークである`Spring Boot`はアプリケーションをテストするときに役立つ多くのユーティリティとアノテーションをサポートしている。

具体的には、`spring-boot-starter-test`を依存関係に含めることで、`JUnit`などのライブラリが使えるようになる。

詳細は[公式ドキュメント](https://spring.pleiades.io/spring-boot/docs/current/reference/html/features.html#features.testing)を参照すること。

また、新serioはDB(MySQL)を使用しているため、`DBUnit`も合わせて導入する

```gradle:buld.gradle
dependencies {
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'com.github.springtestdbunit:spring-test-dbunit:1.3.0'
	testImplementation 'org.dbunit:dbunit:2.7.3'
}
```

### 外部サービスのモック

`Spring Boot`のDI機能を使って、モックオブジェクトを注入することで、外部サービス呼び出しをテストする。

// TODO:使用している外部サービスが判明次第、具体的なコード

### APIレスポンスが正しいことの確認方法

新serioはAPIレスポンスとして、HTML/XML形式を返す。そのため、レスポンスが期待通りかどうかは次の2点を確認する必要がある。

- HTML/XML構造が正しいこと
- 埋め込まれているデータが正しいこと

// TODO: 具体的なコード
