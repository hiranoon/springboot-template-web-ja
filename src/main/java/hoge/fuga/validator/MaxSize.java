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
 * 最大文字数を検証するためのアノテーション.
 * @author hirano
 */
@Documented
@Constraint(validatedBy = { MaxSizeValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface MaxSize {

    /** 最大文字数. */
    int max() default Integer.MAX_VALUE;

    /** 出力するメッセージのデフォルト値. */
    String message() default "{MaxSize.message}";

    @SuppressWarnings("javadoc")
    Class<?>[] groups() default {};

    @SuppressWarnings("javadoc")
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @SuppressWarnings("javadoc")
    public @interface List {
        MaxSize[] value();
    }
}
