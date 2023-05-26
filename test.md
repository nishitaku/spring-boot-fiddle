# テスト方式設計書

新serioのテスト方式設計を記述したもの。

## テストの目的

- 品質が担保されていることを確認したい
- プログラム修正時のリグレッションテスト

## テスト範囲

結合テスト(Integration Test)
入力・出力値に対して、仕様通りにAPIが振る舞うかを確認する

## テスト方法

### 技術スタック

- spring-boot-starter-test
  - JUnit5
  - AssertJ
  - Hamcrest
  - Mockito
- DBUnit
