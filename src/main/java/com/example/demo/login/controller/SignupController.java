package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;

@Controller
public class SignupController 
{
	// ラジオボタンの実装
	private Map<String, String> radioMarriage;
	
	// ラジオボタンの初期化メソッド
	private Map<String, String> initRadioMarriage()
	{
		Map<String, String> radio = new LinkedHashMap<>();
		
		// 既婚、未婚をMapに格納
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
	// ユーザー登録画面のGETメソッド用コントローラー
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model)
	{
		// ラジオボタンの初期化メソッドの呼び出し
		radioMarriage = initRadioMarriage();
		
		// ラジオボタン用のMapをModelに登録
		model.addAttribute("radioMarriage", radioMarriage);
		
		// signup.htmlに画面遷移
		return "login/signup";
	}
	
	// ユーザー登録画面のPOSTメソッド用コントローラー
	// データバインドの結果の受け取り
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Valid SignupForm form, 
			BindingResult bindingResult,
			Model model)
	{
		// データバインドに何らかのエラーが存在する場合
		if (bindingResult.hasErrors()) {
			// GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻る
			return getSignUp(form, model);
		}
		
		// formの中身をコンソールに出力
		System.out.println(form);
		
		// login.htmlにリダイレクト
		return "redirect:/login";
	}

}
