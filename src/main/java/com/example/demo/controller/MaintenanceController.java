package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantRegistForm;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MaintenanceController {
	
	private final RegistService service;

	/*--- メンテナンスTOP画面表示リクエスト ---*/
	@GetMapping("/mt-top")
	private String mtTop() {
		
		return "maintenance-top";
	}
	
	/*--- 店舗登録画面表示リクエスト ---*/
	@GetMapping("/show-store-form")
	private String showStoreForm(@ModelAttribute RestaurantRegistForm store) {
		
		return "regist-store";
	}
	
	/*--- 店舗登録画面表示リクエスト（確認画面からの戻り） ---*/
	@PostMapping("/show-store-form-ret")
	private String showStoreFormRet(@ModelAttribute RestaurantRegistForm store) {
		
		return "regist-store";
	}
	
	/*--- 店舗登録リクエスト（登録画面より） ---*/
	@PostMapping("/regist-store")
	public String registStore(
			@Validated @ModelAttribute RestaurantRegistForm store,
			BindingResult result) {
		
		//入力エラーがある場合には、店舗登録画面に戻す
		if (result.hasErrors()) {
			return "regist-store";
		}
		
		//正常な場合に店舗登録確認画面に遷移する
		return "confirm-regist-store";
	}
	
	/*--- 店舗登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-regist-store")
	public String confirmRegistStore(
			@Validated RestaurantRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		//入力エラーがある場合には、レビュー登録画面に戻す
		if(result.hasErrors()) {
			return "regist-store";
		}
		
		Restaurant s = new Restaurant();
		s.setRestaurantName(form.getRestaurantName());
		s.setCatchPhrase(form.getCatchPhrase());
		service.regist(s);
		
		redirectAttributes.addFlashAttribute("msg", "(店舗登録)");
		
		return "redirect:/complete";
	}
	
}
