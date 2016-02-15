package hoge.fuga.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * コードの定数クラスです.
 * <p>
 * 追加する場合には以下の3点を遵守してください.
 * <ul>
 * <li>@{@link Getter} アノテーション、および @{@link AllArgsConstructor} アノテーションを付ける.</li>
 * <li>{@link Code} インターフェースを実装する.</li>
 * <li>コードID(private final String id;)、コード名称(private final String name;)プロパティを用意する.</li>
 * </ul>
 * これらが実装されていることを前提として、ユーティリティクラス等の機能が実装されています.
 * </p>
 * @author hirano
 */
public class CodeConsts {

    /**
     * 隠蔽されたコンストラクタ.
     */
    private CodeConsts() {
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
        private final String id;
        /** コード名称 */
        private final String name;
    }
}
