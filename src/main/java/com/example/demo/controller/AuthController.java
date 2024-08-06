package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
	private static final String AUTH_ENTRY_PAGE_TITLE = "ユーザ登録" + " - " + SYSTEM_NAME;
	private static final String AUTH_UPDATE_PAGE_TITLE = "ユーザ登録修正" + " - " + SYSTEM_NAME;
	private static final String AUTH_CONFIRM_PAGE_TITLE = "ユーザ登録確認" + " - " + SYSTEM_NAME;
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
	 * 登録内容確認画面を表示する
	 * @param mode     処理モード：クエリ文字列
	 * @param name     ユーザ名
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @param model    スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/signup")
	public String signup(
			@RequestParam("mode") String mode,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		
		// リクエストパラメータをもとに認証情報のインスタンスを生成
		UserAuth userAuth = new UserAuth(name, email, password);
		model.addAttribute("entry", userAuth);
		String nextTymeleafTemplate = "";
		
		switch (mode) {
		case "confirm": // 確認画面遷移
			// 入力値チエックの準備
			List<String> errorList = new ArrayList<>();
			// ユーザ名の入力値チェック
			if (name == null || name.isEmpty()) {
				errorList.add("ユーザ名は必須です。");
			} else if (name.length() > 100) {
				errorList.add("ユーザ名は100文字以下です。");
			}
			// メールアドレスの入力チェック
			if (email == null || email.isEmpty()) {
				errorList.add("メールアドレスは必須です。");
			} else if (email.length() > 255) {
				errorList.add("メールアドレスは255文字以下です。");
			} else {
				// メールアドレスの重複チェック
				UserAuth target = authRepository.findByEmail(email);
				if (target != null) {
					errorList.add("メールアドレスはすでに登録されています。");
				}
			}
			// パスワードの入力チェック
			if (password == null || password.isEmpty()) {
				errorList.add("パスワードは必須です。");
			} else if (password.length() > 255) {
				errorList.add("パスワードは255文字以下です。");
			}
			// エラーの有無で処理を分岐
			if (errorList.size() > 0) {
				model.addAttribute("errorList", errorList);
				nextTymeleafTemplate = "/auth/signup";
				break;
			}
			// 画面タイトルと遷移先URLの設定
			model.addAttribute(KEY_PAGE_TITLE, AUTH_CONFIRM_PAGE_TITLE);
			nextTymeleafTemplate = "/auth/confirm";
			break;
		case "update": // 修正のための差し戻し
			model.addAttribute("message", "修正をしてください。");
			model.addAttribute(KEY_PAGE_TITLE, AUTH_UPDATE_PAGE_TITLE);
			nextTymeleafTemplate = "/auth/signup";
			break;
		case "register":
			// 認証情報の登録
			authRepository.save(userAuth);
			nextTymeleafTemplate = "redirect:/login";
			break;
		default:
			break;
		}
		return nextTymeleafTemplate;
	}
	
	/**
	 * ユーザ登録画面を表示する
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping("/auth/signup")
	public String  signup(Model model) {
		UserAuth entry = new UserAuth("", "", "");
		model.addAttribute("entry", entry);
		model.addAttribute("message", "入力する項目はすべて必須です。");
		model.addAttribute(KEY_PAGE_TITLE, AUTH_ENTRY_PAGE_TITLE);
		return "/auth/signup";
	}
	
	/**
	 * ログイン処理を実行する
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @param model    リクエストスコープ
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
	 * @param model リクエストスコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping({"/", "/customers", "/customers/login", "/login", "/logout"})
	public String index(Model model) {
		session.invalidate();
		model.addAttribute(KEY_PAGE_TITLE, LOGIN_PAGE_TITLE);
		return "login";
	}
	
}
