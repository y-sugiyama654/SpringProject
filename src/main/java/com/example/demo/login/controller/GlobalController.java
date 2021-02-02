package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalController 
{
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model)
	{
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー(DB):ExceptionHandler");
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model)
	{
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー:ExceptionHandler");
		model.addAttribute("message", "SignupControllerでExceptionが発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

}
