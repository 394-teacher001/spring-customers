package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserAuth;

public interface AuthRepository extends JpaRepository<UserAuth, Integer> {

	/**
	 * メールアドレスとパスワードから登録された認証情報を取得する
	 * @param email
	 * @param password
	 * @return UserAuthクラスのインスタンスまたはnull
	 */
	UserAuth findByEmailAndPassword(String email, String password);

	/**
	 * メールアドレスに合致する認証情報を崇德する
	 * @param email メールアドレス
	 * @return UserAuthクラスのインスタンスまたはnull
	 */
	UserAuth findByEmail(String email);

}
