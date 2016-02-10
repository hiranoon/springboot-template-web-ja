package hoge.fuga.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hoge.fuga.service.NationalityService;

/**
 * 存在する国籍IDであるか検証するための Validator クラス.
 * @author hirano
 */
@Component
public class ExistingNationalityIdValidator implements ConstraintValidator<ExistingNationalityId, Integer> {

    /** 国籍の Service クラス. */
    @Autowired
    NationalityService nationalityService;

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(ExistingNationalityId constraintAnnotation) {
    }

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 未入力は必須チェックでエラーを判定させる想定のため、チェックOKとします.
        if (value == null) {
            return true;
        }
        // 背番号が利用されているか検証します.
        return nationalityService.isExistingNationalityId(value);
    }
}
