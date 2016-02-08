package hoge.fuga.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 選手の Form クラス.
 * @author hirano
 */
@Data
public class PlayerForm {
    /**
     * 背番号.
     */
    @NotNull // 必須チェックではなく、リクエストパラメータに存在するかチェックしています.
    @Min(1)
    @Max(99)
    private Integer squadNumber;

    /**
     * 名前.
     */
    @NotBlank // 空文字やスペースのみをエラーにします.
    @Size(min = 1, max = 50)
    private String name;

    /**
     * ポジションコード.
     */
    @NotBlank // 空文字やスペースのみをエラーにします.
    @Size(max = 1)
    private String positionCode;

    /**
     * 国籍のID.
     */
    @NotNull // 必須チェックではなく、リクエストパラメータに存在するかチェックしています.
    @Min(1)
    @Max(99)
    private Integer nationalityId;
}
