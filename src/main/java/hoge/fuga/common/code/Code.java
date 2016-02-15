package hoge.fuga.common.code;

/**
 * コードであることを示すインターフェースです.
 * <p>
 * {@link CodeConsts} 内で定義する enum で実装します.
 * </p>
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
