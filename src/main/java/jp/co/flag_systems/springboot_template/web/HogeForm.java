package jp.co.flag_systems.springboot_template.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * Hoge の Form クラス.
 * @author hirano
 */
@Data
public class HogeForm {
    /**
     * 背番号.
     */
    @NotNull // 必須チェックではなく、リクエストパラメータに存在するかチェックしています。
    @Min(1)
    @Max(99)
    private Integer squadNumber;

    /**
     * 名前.
     */
    @NotBlank // 空文字やスペースのみをエラーにします。
    @Size(min = 1, max = 50)
    private String name;

    /**
     * ポジションコード.
     */
    @NotNull
    @Min(1)
    @Max(4)
    private Integer positionCode;

    /**
     * 国籍.
     */
    @NotNull // 必須チェックではなく、リクエストパラメータに存在するかチェックしています。
    @Min(1)
    @Max(99)
    private Integer fugaId;
}
