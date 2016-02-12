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

    /** 検証の要否. */
    boolean needsValidation() default false;

    /** 出力するメッセージのデフォルト値. */
    String message() default "{UnusedSquadNumber.message}";

    @SuppressWarnings("javadoc")
    Class<?>[] groups() default {};

    @SuppressWarnings("javadoc")
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @SuppressWarnings("javadoc")
    public @interface List {
        UnusedSquadNumber[] value();
    }
}
