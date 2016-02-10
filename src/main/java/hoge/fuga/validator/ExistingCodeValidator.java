package hoge.fuga.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import hoge.fuga.common.constant.Code;
import hoge.fuga.common.constant.CodeUtils;

/**
 * 存在する {@link Code} であるか検証するための Validator クラス.
 * @author hirano
 */
@Component
public class ExistingCodeValidator implements ConstraintValidator<ExistingCode, String> {

    /** コードのクラス. */
    private Class<? extends Code> codeClass;

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(ExistingCode constraintAnnotation) {
        codeClass = constraintAnnotation.codeClass();
    }

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 未入力は必須チェックでエラーを判定させる想定のため、チェックOKとします.
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        // 存在するコードであるか検証します.
        return CodeUtils.contains(codeClass, value);
    }
}
