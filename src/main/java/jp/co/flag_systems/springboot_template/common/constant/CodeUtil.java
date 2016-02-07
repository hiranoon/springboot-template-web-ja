package jp.co.flag_systems.springboot_template.common.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * コードの操作を行うユーティリティクラスです.
 * @author hirano
 */
public class CodeUtil {

    /**
     * 隠蔽されたコンストラクタ.
     */
    private CodeUtil() {
    }

    /**
     * 指定されたコードのクラスとコードIDからコード名称を取得します.
     *
     * 存在しないコードIDが指定された場合は空文字を返却します.
     * <blockquote><pre>
     * // 以下の例では "DF" が取得されます.
     * CodeUtil.getCodeName(CodeConst.PositionCode.class, "2");
     * </pre></blockquote>
     *
     * また、指定されるクラスは {@link Code} を実装した enum であること、
     * コードには同じコードIDが存在していないことを前提としています.
     *
     * @param codeClass コードのクラス
     * @param id コードID
     * @return コード名称. 存在しないコードIDの場合は空文字.
     */
    public static String getCodeName(Class<? extends Enum<?>> codeClass, String id) {
        // コードのクラスの中から id が合致する要素を抽出します.
        Optional<String> codeName = Arrays.stream(codeClass.getEnumConstants())
                .map(x -> (Code)x)                 // Code が指定されることを前提としてキャストします.
                .filter(x -> x.getId().equals(id)) // コードIDが合致する Code を抽出します.
                .map(x -> x.getName())             // Code から Code の name に変換します.
                .findFirst();                      // 最初の要素を返却していますが、仕様上一意に決まる想定です.
        // コードの name を取得して返却します.
        return codeName.orElse("");
    }
}
