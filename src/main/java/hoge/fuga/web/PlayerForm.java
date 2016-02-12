package hoge.fuga.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import hoge.fuga.common.constant.CodeConsts;
import hoge.fuga.validator.ExistingCode;
import hoge.fuga.validator.ExistingNationalityId;
import hoge.fuga.validator.MaxSize;
import hoge.fuga.validator.UnusedSquadNumber;
import lombok.Data;

/**
 * 選手の Form クラス.
 * @author hirano
 */
@Data
public class PlayerForm {

    /** 登録グループ. <p>Validator の groups を指定するためのマーカーです.</p> */
    public static interface Insert {
    };

    /** 更新グループ. <p>Validator の groups を指定するためのマーカーです.</p> */
    public static interface Update {
    };

    /**
     * ID.
     * <p>
     * 更新時に指定されます. 登録時には指定されません.
     * </p>
     */
    private Integer id;

    /** 背番号. */
    @NotNull // 【解説】必須チェックではなく、リクエストパラメータに存在するかチェックしています.
    @Min(1)
    @Max(99)
    // 【解説】
    // 独自に用意した Validator です.
    // groups を指定することで検証条件を切り替えています.
    // この切替のマーカーとして Interface を用意しています.
    @UnusedSquadNumber.List({
        @UnusedSquadNumber(needsValidation = true, groups = Insert.class),
        @UnusedSquadNumber(needsValidation = false, groups = Update.class)
    })
    private Integer squadNumber;

    /** 名前. */
    @NotBlank          // 【解説】空文字やスペースのみをエラーにします.
    @MaxSize(max = 50) // 【解説】独自に用意した Validator です.
    private String name;

    /** ポジションコード. */
    @NotBlank                                                // 【解説】空文字やスペースのみをエラーにします.
    @ExistingCode(codeClass = CodeConsts.PositionCode.class) // 【解説】独自に用意した Validator です.
    private String positionCode;

    /** 国籍のID. */
    @NotNull               // 【解説】必須チェックではなく、リクエストパラメータに存在するかチェックしています.
    @ExistingNationalityId // 【解説】独自に用意した Validator です.
    private Integer nationalityId;
}
