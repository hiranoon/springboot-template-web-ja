package hoge.fuga.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hoge.fuga.service.PlayerService;

/**
 * 未使用の背番号であるか検証するための Validator クラス.
 * @author hirano
 */
@Component
public class UnusedSquadNumberValidator implements ConstraintValidator<UnusedSquadNumber, Integer> {

    /** 選手の Service クラス. */
    @Autowired
    PlayerService playerService;

    /** 検証の要否. */
    private boolean needsValidation;

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(UnusedSquadNumber constraintAnnotation) {
        needsValidation = constraintAnnotation.needsValidation();
    }

    /*
     * (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 検証が不要ならチェックOKとします.
        if (!needsValidation) {
            return true;
        }
        // 未入力は必須チェックでエラーを判定させる想定のため、チェックOKとします.
        if (value == null) {
            return true;
        }
        // 背番号が利用されているか検証します.
        return playerService.isUnusedSquadNumber(value);
    }
}
