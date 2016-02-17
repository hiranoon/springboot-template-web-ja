package hoge.fuga.common.token;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Token のチェックを行うためのアノテーション.
 * <p>
 * Controller クラスのメソッドで利用します.
 * 入力画面を表示するメソッドと、登録・更新を行うメソッドで対になるようセットしてください.
 * 入力画面を表示するメソッドでは {@link TransactionTokenType.PUBLISH} を、
 * 登録・更新を行うメソッドでは {@link TransactionTokenType.VALIDATE} を指定します.
 * 互いに対であることを示すためのマーカーとして、名前空間を指定してください.
 * Controller クラス内で一意である必要があります.
 * </p>
 * @author hirano
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionToken {

    /** 名前空間. */
    String value() default "";

    /** Token アノテーションの処理種別. */
    TransactionTokenType type() default TransactionTokenType.PUBLISH;
}
