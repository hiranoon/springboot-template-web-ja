package jp.co.flag_systems.springboot_template.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.flag_systems.springboot_template.domain.Hoge;
import jp.co.flag_systems.springboot_template.service.HogeService;
import jp.co.flag_systems.springboot_template.web.HogeForm;

/**
 * Hoge の Controller クラス.
 * @author hirano
 */
@Controller
@RequestMapping("hoges")
public class HogeController {

    /** Hoge の Service クラス. */
    @Autowired
    HogeService hogeService;

    // @ModelAttribute があると @RequestMapping のメソッドの前に呼ばれます。
    // 返り値は自動で Model に追加されます。
    // model.addAttribute(new HogeForm); が裏で実行されることを意味します。
    // 属性名を省略すると先頭小文字のキャメルケースになるので、「hogeForm」として登録されます。
    /**
     * Form の初期化を行います.
     * @return {@link HogeForm} オブジェクト
     */
    @ModelAttribute
    HogeForm setUpForm() {
        return new HogeForm();
    }

    /**
     * Hoge の一覧画面を表示します.
     * @param model {@link Model} オブジェクト
     * @return Hoge の一覧画面
     */
    @RequestMapping(method = RequestMethod.GET)
    String index(
            @PageableDefault(size = 5,
                page = 0,
                sort = "squadNumber",
                direction = Direction.ASC
                ) Pageable pageable,
            Model model
            ) {
        // Hoge を全件取得します.
        Page<Hoge> page = hogeService.findAll(pageable);
        model.addAttribute("page", page);
        // Hoge の一覧画面を表示します.
        return "hoges/index";
    }

    /**
     * Hoge の登録画面を表示します.
     * @param model {@link Model} オブジェクト
     * @return Hoge の登録画面
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    String add(Model model) {
        // Hoge の一覧画面を表示します.
        return "hoges/add";
    }

    /**
     * Hoge を登録します.
     * @param form 画面入力内容
     * @param result 入力チェック結果
     * @param model {@link Model} オブジェクト
     * @return Hoge の一覧画面
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(
            @Validated HogeForm form, // 入力チェックを行います。結果は BindingResult に入ります。
            BindingResult result,
            Model model
            ) {
        // エラーが有るときは登録画面に戻ります。
        if (result.hasErrors()) {
            return add(model);
        }
        // エラーがないときはホゲを追加します。
        Hoge hoge = new Hoge();
        BeanUtils.copyProperties(form, hoge);
        hogeService.create(hoge, form.getFugaId());
        // Hoge の一覧画面にリダイレクトします。
        return "redirect:/hoges"; // リダイレクトをする場合は先頭に「redirect:」を付けます。
    }

    /**
     * Hoge の更新画面を表示します.
     * @param hogeId パスパラメータに設定された ID
     * @param form ブランクの {@link HogeForm} オブジェクト
     * @return Hoge の更新画面
     */
    @RequestMapping(value = "{hogeId}/edit", method = RequestMethod.GET)
    String edit(
            @PathVariable("hogeId") Integer hogeId,
            HogeForm form) {
        // パスパラメータの ID よりホゲを検索します。
        Hoge hoge = hogeService.findOne(hogeId);
        // 検索結果を Form にコピーします。
        BeanUtils.copyProperties(hoge, form);
        form.setFugaId(hoge.getFuga().getFugaId());
        // 更新画面を表示します。
        return "hoges/edit";
    }

    /**
     * Hoge を更新します.
     * @param hogeId リクエストパラメータ（hidden）の hogeId
     * @param form 画面入力内容
     * @param result 入力チェック結果
     * @return Hoge の一覧画面
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(
            @RequestParam Integer hogeId,
            @Validated HogeForm form,
            BindingResult result
            ) {
        // エラーが有るときは更新画面に戻ります。
        if (result.hasErrors()) {
            return edit(hogeId, form);
        }
        // Form より Hoge オブジェクトを生成します。
        Hoge hoge = new Hoge();
        BeanUtils.copyProperties(form, hoge);
        hoge.setHogeId(hogeId);
        // Hoge を更新します。
// TODO 楽観的排他制御
        hogeService.update(hoge, form.getFugaId());
        // Hoge の一覧画面にリダイレクトします。
        return "redirect:/hoges"; // リダイレクトをする場合は先頭に「redirect:」を付けます。
    }

    /**
     * Hoge を削除します.
     * @param hogeId パスパラメータに設定された ID
     * @return Hoge の一覧画面
     */
    @RequestMapping(value = "{hogeId}/destroy", method = RequestMethod.POST)
    String destroy(@PathVariable("hogeId") Integer hogeId) {
        // Hoge を削除します。
// TODO 楽観的排他制御
        hogeService.delete(hogeId);
        // Hoge の一覧画面にリダイレクトします。
        return "redirect:/hoges"; // リダイレクトをする場合は先頭に「redirect:」を付けます。
    }
}
