package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * user_authテーブルの1レコードを管理する
 */
@Entity
@Table(name="user_auth")
@Data
public class UserAuth {
	/**
	 * フィールド
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;          // 認証ID
	private String name;     // ユーザ名
	private String email;    // メールアドレス
	private String password; // パスワード
}
