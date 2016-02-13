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

import hoge.fuga.common.code.Code;

/**
 * 存在する {@link Code} であるか検証するためのアノテーション.
 * @author hirano
 */
@Documented
@Constraint(validatedBy = { ExistingCodeValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ExistingCode {

    /** コードのクラス. */
    Class<? extends Code> codeClass();

    /** 出力するメッセージのデフォルト値. */
    String message() default "{ExistingCode.message}";

    @SuppressWarnings("javadoc")
    Class<?>[] groups() default {};

    @SuppressWarnings("javadoc")
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @SuppressWarnings("javadoc")
    public @interface List {
        ExistingCode[] value();
    }
}
