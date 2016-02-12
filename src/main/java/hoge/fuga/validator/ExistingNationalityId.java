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
 * 存在する国籍IDであるか検証するためのアノテーション.
 * @author hirano
 */
@Documented
@Constraint(validatedBy = { ExistingNationalityIdValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ExistingNationalityId {

    /** 出力するメッセージのデフォルト値. */
    String message() default "{ExistingNationalityId.message}";

    @SuppressWarnings("javadoc")
    Class<?>[] groups() default {};

    @SuppressWarnings("javadoc")
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @SuppressWarnings("javadoc")
    public @interface List {
        ExistingNationalityId[] value();
    }
}
