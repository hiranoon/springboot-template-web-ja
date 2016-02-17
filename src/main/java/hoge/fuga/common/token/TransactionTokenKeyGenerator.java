package hoge.fuga.common.token;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import hoge.fuga.common.security.SecurityUtils;

/**
 * Token 保持用のキー文字列を生成するクラスです.
 * @author hirano
 */
public class TransactionTokenKeyGenerator {

    /**
     * Token 保持用のキー文字列を生成します.
     * <p>
     * キー文字列のルールは「ユーザー名_コントローラのクラス名_名前空間」になります.
     * </p>
     * @param handlerMethod Controller の {@link HandlerMethod}
     * @return Token 保持用のキー文字列
     */
    public static String generate(HandlerMethod handlerMethod) {
        // アノテーションを取得します.
        TransactionToken annotation = handlerMethod.getMethodAnnotation(TransactionToken.class);
        // Token 格納用のキー(ユーザー名_クラス名_名前空間)を生成します.
        return generate(handlerMethod.getMethod().getDeclaringClass().getSimpleName(), annotation.value());
    }

    /**
     * Token 保持用のキー文字列を生成します.
     * <p>
     * キー文字列のルールは「ユーザー名_コントローラ名_名前空間」になります.
     * コントローラ名は、先頭小文字で、末尾の "Controller" を取り除いた文字列で生成します.
     * 当該メソッドに対する指定は、先頭文字種類、末尾の "Controller" の有無は問いません.
     * </p>
     * @param controllerClassName コントローラクラス名
     * @param namespace 名前空間
     * @return Token 保持用のキー文字列
     */
    public static String generate(String controllerClassName, String namespace) {
        // コントローラ名を、先頭小文字で、末尾の "Controller" を取り除いた文字列に編集します.
        String controllerName = StringUtils.uncapitalize(controllerClassName);
        controllerName = StringUtils.delete(controllerName, "Controller");
        // Token 格納用のキー(ユーザー名_クラス名_名前空間)を生成します.
        StringBuilder sb = new StringBuilder();
        sb.append(SecurityUtils.getUserDetails().map(x -> x.getUsername()).orElse("annonymous"));
        sb.append("_");
        sb.append(controllerName);
        sb.append("_");
        sb.append(namespace);
        return sb.toString();
    }
}
