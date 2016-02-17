package hoge.fuga.common.token;

/**
 * Token アノテーションの処理種別を表す列挙型.
 * @author hirano
 */
public enum TransactionTokenType {
    /** Token を発行します. */
    PUBLISH,
    /** Token の検証をします. */
    VALIDATE,
}
