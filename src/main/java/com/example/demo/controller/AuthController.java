package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.business.AuthInfo;
import com.example.demo.entity.UserAuth;
import com.example.demo.repository.AuthRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	/**
	 * クラス定数
	 */
	// 画面タイトル文字列定数群
	private static final String SYSTEM_NAME = "顧客管理システム"; 
	private static final String LOGIN_PAGE_TITLE = "ログイン" + " - " + SYSTEM_NAME;
	private static final String COMPANY_SEARCH_PAGE_TITLE = "取引先検索" + " - " + SYSTEM_NAME;
	// エラーメッセージ文字列定数群
	private static final String ERR_MESSAGE = "メールアドレスとパスワードが一致しませんでした。";
	// スコープに登録するキー名文字列定数群
	private static final String KEY_PAGE_TITLE = "pageTitle";
	private static final String KEY_ERROR = "error";
	
	@Autowired
	HttpSession session;
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	AuthInfo authInfo;
	
	/**
	 * ログイン処理を実行する
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @param model    リクエストスコープに対応するストレージ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			Model model) {
		
		// リクエストパラメータをもとにして認証を実行
		UserAuth auth = authRepository.findByEmailAndPassword(email, password);
		
		// 取得した登録情報によって処理を分岐
		String nextTemplateName = "";
		if (auth == null) {
			// nullの場合：認証失敗のメッセージをスコープに登録して自画面遷移
			model.addAttribute(KEY_ERROR, ERR_MESSAGE);
			// スコープにページタイトルを登録
			model.addAttribute(KEY_PAGE_TITLE, LOGIN_PAGE_TITLE);
			// 遷移先Thymeleafテンプレート名の指定
			nextTemplateName = "login";
			// return "login";
		} else {
			// nullでない場合：セッションに認証情報を登録して次画面遷移（取引先一覧画面）
			authInfo.setParams(auth.getName(), auth.getEmail());
			// スコープにページタイトルを登録
			model.addAttribute(KEY_PAGE_TITLE, COMPANY_SEARCH_PAGE_TITLE);
			nextTemplateName = "companies";
		}
		
		// 次画面遷移
		return nextTemplateName;
	}
	
	/**
	 * ログイン画面を表示する
	 * @param model リクエストスコープに対応するストレージ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping({"/", "/customers", "/customers/login", "/logout"})
	public String index(Model model) {
		session.invalidate();
		model.addAttribute(KEY_PAGE_TITLE, LOGIN_PAGE_TITLE);
		return "login";
	}
	
}
