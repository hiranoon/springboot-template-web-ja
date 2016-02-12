package hoge.fuga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ユーザーの Controller クラス.
 * @author hirano
 */
@Controller
@RequestMapping("users")
public class UserController {

    /**
     * ログイン画面を表示します.
     * @return ログイン画面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    String index() {
        // ログイン画面を表示します.
        return "users/login";
    }
}
