package jp.co.flag_systems.springboot_template.common.constant;

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
