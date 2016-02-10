package hoge.fuga.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 未使用の背番号であるか検証するためのアノテーション.
 * @author hirano
 */
@Documented
@Constraint(validatedBy = { UnusedSquadNumberValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface UnusedSquadNumber {

    /** 出力するメッセージのデフォルト値です. */
    String message() default "{UnusedSquadNumber.message}";

    /** チェック実行するかどうかの分割の為の定義です. */
    Class<?>[] groups() default {};

    /** チェック結果に情報を付加するための定義です. */
    Class<? extends Payload>[] payload() default {};

    /** 同一項目に複数の {@code @UnusedSquadNumber} アノテーションを指定する場合の為の定義です. */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        @SuppressWarnings("javadoc")
        UnusedSquadNumber[] value();
    }
}
