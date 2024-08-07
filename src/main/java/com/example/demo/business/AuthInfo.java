package com.example.demo.business;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

/**
 * セッションに登録する認証情報
 */
@Component
@SessionScope
@Data
public class AuthInfo {
	/**
	 * フィールド
	 */
	private String name;  // 画面表示名：デフォルトはユーザ名
	private String email; // メールアドレス
	
	/**
	 * すべてのフィールドを設定する
	 * @param name  ユーザ名
	 * @param email メールアドレス
	 */
	
	public void setParams(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
