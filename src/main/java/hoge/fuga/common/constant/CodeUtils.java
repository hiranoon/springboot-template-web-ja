package hoge.fuga.common.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * コードの操作を行うユーティリティクラスです.
 * @author hirano
 */
public class CodeUtils {

    /**
     * 隠蔽されたコンストラクタ.
     */
    private CodeUtils() {
    }

    /**
     * 指定されたコードのクラスとコードIDからコード名称を取得します.
     * <p>
     * <blockquote><pre>
     * // 以下の例では "DF" が取得されます.
     * CodeUtil.getCodeName(CodeConst.PositionCode.class, "2");
     * </pre></blockquote>
     * </p>
     * <p>
     * コードには同じコードIDが存在していないことを前提としています.
     * 存在しないコードIDが指定された場合は空文字を返却します.
     * </p>
     * @param codeClass コードのクラス
     * @param id コードID
     * @return コード名称. 存在しないコードIDの場合は空文字.
     */
    public static String getCodeName(Class<? extends Code> codeClass, String id) {
        // コードのクラスの中から id が合致する要素を抽出します.
        Optional<String> codeName = Arrays.stream(codeClass.getEnumConstants())
                .filter(x -> x.getId().equals(id)) // コードIDが合致する Code を抽出します.
                .map(x -> x.getName())             // Code から Code の name に変換します.
                .findFirst();                      // 最初の要素を返却していますが、仕様上一意に決まる想定です.
        // コード名称を返却します.
        return codeName.orElse("");
    }

    /**
     * 指定されたコードのクラスの中に、指定されたコードIDが含まれるかチェックします.
     * @param codeClass コードのクラス
     * @param id コードID
     * @return 存在するコードIDか. true: 存在する. false: 存在しない.
     */
    public static boolean contains(Class<? extends Code> codeClass, String id) {
        // コードのクラスの中から id が合致する要素が存在するかチェックします.
        return Arrays.stream(codeClass.getEnumConstants())
                .anyMatch(x -> x.getId().equals(id));
    }
}
