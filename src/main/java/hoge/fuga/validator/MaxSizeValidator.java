package hoge.fuga.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 最大文字数を検証するための Validator クラス.
 * @author hirano
 */
@Component
public class MaxSizeValidator implements ConstraintValidator<MaxSize, String> {

    /** 最大文字数です. */
    private int max;

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(MaxSize constraintAnnotation) {
        max = constraintAnnotation.max();
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
        // 最大文字数を超えているか検証します.
        return value.length() <= max;
    }

}
