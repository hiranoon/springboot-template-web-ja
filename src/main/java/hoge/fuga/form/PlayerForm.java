package hoge.fuga.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import hoge.fuga.common.code.CodeConsts;
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
     * <p>更新時に指定されます. 登録時には指定されません.</p>
     */
    private Integer id;

    /** 背番号. */
    @NotNull
    @Min(1)
    @Max(99)
    // 登録、更新で検証の要否を切り替えるためマーカーインターフェースを指定しています.
    @UnusedSquadNumber.List({
        @UnusedSquadNumber(needsValidation = true, groups = Insert.class),
        @UnusedSquadNumber(needsValidation = false, groups = Update.class)
    })
    private Integer squadNumber;

    /** 名前. */
    @NotBlank
    @MaxSize(max = 50)
    private String name;

    /** ポジションコード. */
    @NotBlank
    @ExistingCode(codeClass = CodeConsts.PositionCode.class)
    private String positionCode;

    /** 国籍のID. */
    @NotNull
    @ExistingNationalityId
    private Integer nationalityId;

    /**
     * バージョン.
     * <p>更新時に指定されます. 登録時には指定されません.</p>
     */
    private Integer version;
}
