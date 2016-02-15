package hoge.fuga.controller;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hoge.fuga.common.code.CodeConsts;
import hoge.fuga.entity.Nationality;
import hoge.fuga.entity.Player;
import hoge.fuga.form.PlayerForm;
import hoge.fuga.form.PlayerForm.Insert;
import hoge.fuga.form.PlayerForm.Update;
import hoge.fuga.service.NationalityService;
import hoge.fuga.service.PlayerService;

/**
 * 選手の Controller クラス.
 * @author hirano
 */
@Controller
@RequestMapping("players")
public class PlayerController {

    /** 選手の Service クラス. */
    @Autowired
    PlayerService playerService;

    /** 国籍の Service クラス. */
    @Autowired
    NationalityService nationalityService;

    /** メッセージソース. */
    @Autowired
    MessageSource messageSource;

    /**
     * Form の初期化を行います.
     * @return {@link PlayerForm} オブジェクト
     */
    @ModelAttribute
    PlayerForm setUpForm() {
        return new PlayerForm();
    }

    /**
     * 選手の一覧画面を表示します.
     * @param pageable ページング情報
     * @param model {@link Model} オブジェクト
     * @return 選手の一覧画面
     */
    @RequestMapping(method = RequestMethod.GET)
    String index(
            @PageableDefault(
                size = 5,
                page = 0,
                sort = "squadNumber",
                direction = Direction.ASC
                ) Pageable pageable,
            Model model
            ) {
        // Player を取得します.
        Page<Player> page = playerService.findAll(pageable);
        model.addAttribute("page", page);
        // 選手の一覧画面を表示します.
        return "players/index";
    }

    /**
     * 選手の登録画面を表示します.
     * @param model {@link Model} オブジェクト
     * @return 選手の登録画面
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    String add(Model model) {
        // プルダウン用にすべての Nationality をセットします.
        List<Nationality> nationalities = nationalityService.findAll();
        model.addAttribute("nationalities", nationalities);
        // プルダウン用にすべてのポジションコードをセットします.
        model.addAttribute("positionClasses", CodeConsts.PositionCode.values());
        // 選手の一覧画面を表示します.
        return "players/add";
    }

    /**
     * 選手を登録します.
     * @param form 画面入力内容
     * @param result 入力チェック結果
     * @param model {@link Model} オブジェクト
     * @param redirectAttributes リダイレクト後のデータ連携用セッション一時領域.
     * @return 選手の一覧画面.
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(
            @Validated({Insert.class, Default.class}) PlayerForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            ) {
        // エラーが有るときは登録画面に戻ります.
        if (result.hasErrors()) {
            return add(model);
        }
        // エラーがないときは選手を登録します.
        Player player = new Player();
        BeanUtils.copyProperties(form, player);
        playerService.create(player, form.getNationalityId());
        // 選手の一覧画面にリダイレクトします.
        redirectAttributes.addFlashAttribute("info", messageSource.getMessage("info.create.complete", null, null));
        return "redirect:/players";
    }

    /**
     * 選手の更新画面を表示します.
     * @param id パスパラメータに設定された ID
     * @param form ブランクの {@link PlayerForm} オブジェクト
     * @param model {@link Model} オブジェクト
     * @return 選手の更新画面
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    String edit(
            @PathVariable("id") Integer id,
            PlayerForm form,
            Model model
            ) {
        // パスパラメータの ID より Player を検索します.
        Player player = playerService.findOne(id);
        // 検索結果を Form にコピーします.
        BeanUtils.copyProperties(player, form);
        form.setNationalityId(player.getNationality().getId());
        // プルダウン用にすべての Nationality をセットします.
        List<Nationality> nationalities = nationalityService.findAll();
        model.addAttribute("nationalities", nationalities);
        // プルダウン用にすべてのポジションコードをセットします.
        model.addAttribute("positionClasses", CodeConsts.PositionCode.values());
        // 更新画面を表示します.
        return "players/edit";
    }

    /**
     * 選手を更新します.
     * @param form 画面入力内容
     * @param result 入力チェック結果
     * @param model {@link Model} オブジェクト
     * @param redirectAttributes リダイレクト後のデータ連携用セッション一時領域.
     * @return 選手の一覧画面
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(
            @Validated({Update.class, Default.class}) PlayerForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            ) {
        // エラーが有るときは更新画面に戻ります.
        if (result.hasErrors()) {
            return edit(form.getId(), form, model);
        }
        // Form より Player オブジェクトを生成します.
        Player player = playerService.findOne(form.getId());
        BeanUtils.copyProperties(form, player);
        // 選手を更新します.
        try {
            playerService.update(player, form.getNationalityId());
        } catch (OptimisticLockingFailureException e) {
            // 楽観的排他制御エラーが発生した場合はエラーメッセージを設定し、選手の一覧画面にリダイレクトします.
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("error.optimisticlocking", null, null));
            return "redirect:/players";
        }
        // 選手の一覧画面にリダイレクトします.
        redirectAttributes.addFlashAttribute("info", messageSource.getMessage("info.update.complete", null, null));
        return "redirect:/players";
    }

    /**
     * 選手を削除します.
     * @param id パスパラメータに設定された ID
     * @param redirectAttributes リダイレクト後のデータ連携用セッション一時領域.
     * @return 選手の一覧画面
     */
    @RequestMapping(value = "{id}/destroy", method = RequestMethod.POST)
    String destroy(
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes
            ) {
        // 選手を削除します.
        playerService.delete(id);
        // 選手の一覧画面にリダイレクトします.
        redirectAttributes.addFlashAttribute("info", messageSource.getMessage("info.destroy.complete", null, null));
        return "redirect:/players";
    }
}
