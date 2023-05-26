# テスト方式設計書

新serioのテスト方式設計を記述したもの。

## 対象読者

テスト開発者

## テストの目的

- 移行の品質を担保したい
  - 移行前後で動作が変わらないことを確認した
  - 移行前のテストパターンが、移行後も通ることを確認したい
- プログラム修正時のリグレッションテスト

## テスト対象
新serioアプリケーション。
Spring Bootで構築されたAPI。

## テスト範囲

単体テストは優先度が低い。
理由は、現状そのクラス、メソッドが動作しているかどうかが判断できないため。

E2Eテストは、手動で実施する。

結合テスト(Integration Test)を対象とする。
入力・出力値に対して、仕様通りにAPIが振る舞うかを確認する


## テスト方法


### 技術スタック

- spring-boot-starter-test
  - JUnit5
  - AssertJ
  - Hamcrest
  - Mockito
- DBUnit

## テスト準備

- MySQL
- DynamoDB
  - DBUnit


### モック
- S3
- 別サービスのAPIコール
  - 呼び出しているサービスをモックする
  - `when(demoApplicationService.getAnimal()).thenReturn("cat");` 

### アサーション

- XML構造の確認
- XMLデータバインディングの確認 x バリエーション
