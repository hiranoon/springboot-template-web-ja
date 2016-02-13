package hoge.fuga.common.code;

/**
 * コードであることを示すマーカーインターフェースです.
 * @author hirano
 */
public interface Code {

    /**
     * コードIDを返却します.
     * @return コードのID
     */
    public String getId();

    /**
     * コード名称を返却します.
     * @return コードの値
     */
    public String getName();
}
