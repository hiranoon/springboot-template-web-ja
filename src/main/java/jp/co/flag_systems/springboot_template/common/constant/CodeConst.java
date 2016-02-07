package jp.co.flag_systems.springboot_template.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * コードの定数クラス.
 * @author hirano
 */
public class CodeConst {

    /**
     * 隠蔽されたコンストラクタ.
     */
    private CodeConst() {
    }

    /** ポジションコード */
    @Getter
    @AllArgsConstructor
    public enum PositionCode implements Code {

        /** ゴールキーパー */
        GK("1", "ゴールキーパー"),

        /** ディフェンダー */
        DF("2", "ディフェンダー"),

        /** ミッドフィルダー */
        MF("3", "ミッドフィルダー"),

        /** フォワード */
        FW("4", "フォワード"),
        ;

        /** コードID */
        private final String codeId;
        /** コード値 */
        private final String codeName;
    }
}
