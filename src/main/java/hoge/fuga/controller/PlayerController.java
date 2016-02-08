package hoge.fuga.controller;

import java.util.List;

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

import hoge.fuga.common.constant.CodeConsts;
import hoge.fuga.domain.Nationality;
import hoge.fuga.domain.Player;
import hoge.fuga.service.NationalityService;
import hoge.fuga.service.PlayerService;
import hoge.fuga.web.PlayerForm;

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

    // 【解説】
    // @ModelAttribute があると @RequestMapping のメソッドの前に呼ばれます.
    // 返り値は自動で Model に追加されます.
    // model.addAttribute(new PlayerForm); が裏で実行されることを意味します.
    // 属性名を省略すると先頭小文字のキャメルケースになるので、「playerForm」として登録されます.
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
     * @param model {@link Model} オブジェクト
     * @return 選手の一覧画面
     */
    @RequestMapping(method = RequestMethod.GET)
    String index(
            // 【解説】
            // @PageableDefault で Pageable の初期値を設定できます.
            // ここでは、一覧に表示する件数、初期ページ番号、ソートの項目と方向を設定しています.
            // また、 Pageable へは以下の様な URL のクエリパラメータで設定がなされます.
            // /players?page=2&size=5&sort=squadNumber,desc
            // クエリパラメータの設定は @PageableDefault よりも優先されます.
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
     * @return 選手の一覧画面
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(
            @Validated PlayerForm form, // 【解説】@Validated を付与することで入力チェックが行われます. 結果は BindingResult に入ります.
            BindingResult result,       // 【解説】入力チェック結果です. 引数の順番が重要で、 @Validated が付いた引数の次にする必要があります.
            Model model
            ) {
        // エラーが有るときは登録画面に戻ります.
        if (result.hasErrors()) { // 【解説】BindingResult#hasErrors でエラーの有無を確認できます.
            return add(model);
        }
        // エラーがないときは選手を登録します.
        Player player = new Player();
        BeanUtils.copyProperties(form, player);
        playerService.create(player, form.getNationalityId());
        // 選手の一覧画面にリダイレクトします.
        return "redirect:/players"; // 【解説】リダイレクトをする場合は先頭に「redirect:」を付けます.
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
            @PathVariable("id") Integer id, // 【解説】パスパラメータは @PathVariable で取得できます.
            PlayerForm form,                // 【解説】引数で渡ってきた段階ではブランクです. メソッド内で設定し、画面に表示させます.
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
     * @return 選手の一覧画面
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(
            @Validated PlayerForm form, // 【解説】@Validated を付与することで入力チェックが行われます. 結果は BindingResult に入ります.
            BindingResult result,       // 【解説】入力チェック結果です. 引数の順番が重要で、 @Validated が付いた引数の次にする必要があります.
            Model model
            ) {
        // エラーが有るときは更新画面に戻ります.
        if (result.hasErrors()) {
            return edit(form.getId(), form, model);
        }
        // Form より Player オブジェクトを生成します.
        Player player = new Player();
        BeanUtils.copyProperties(form, player);
        // 選手を更新します.
// TODO 楽観的排他制御
        playerService.update(player, form.getNationalityId());
        // 選手の一覧画面にリダイレクトします.
        return "redirect:/players"; // 【解説】リダイレクトをする場合は先頭に「redirect:」を付けます.
    }

    /**
     * 選手を削除します.
     * @param id パスパラメータに設定された ID
     * @return 選手の一覧画面
     */
    @RequestMapping(value = "{id}/destroy", method = RequestMethod.POST)
    String destroy(
            @PathVariable("id") Integer id // 【解説】パスパラメータは @PathVariable で取得できます.
            ) {
        // 選手を削除します.
// TODO 楽観的排他制御
        playerService.delete(id);
        // 選手の一覧画面にリダイレクトします.
        return "redirect:/players"; // 【解説】リダイレクトをする場合は先頭に「redirect:」を付けます.
    }
}
