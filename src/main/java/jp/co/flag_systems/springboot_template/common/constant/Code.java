package jp.co.flag_systems.springboot_template.common.constant;

/**
 * コードであることを示すマーカーインターフェースです.
 * @author hirano
 */
public interface Code {

    /**
     * コードのIDを返却します.
     * @return コードのID
     */
    public String getCodeId();

    /**
     * コードのIDを値します.
     * @return コードの値
     */
    public String getCodeName();
}
