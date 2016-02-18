package hoge.fuga.common.token;

/**
 * Token が一致しなかった場合の Exception.
 * @author hirano
 */
public class InvalidTransactionTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ.
     * @param expectedToken セッションに保持していた Token
     * @param actualToken 画面に指定されていた Token
     */
    public InvalidTransactionTokenException(String expectedToken, String actualToken) {
        super("Invalid Token '" + actualToken + "' was found. Expected Token was '" + expectedToken + "'.");
    }
}
