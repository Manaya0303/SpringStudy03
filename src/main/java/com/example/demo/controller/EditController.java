package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Restaurant;
import com.example.demo.form.RestaurantEditForm;
import com.example.demo.service.EditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {
	
	private final EditService service;
	
	/*--- 店舗編集画面表示リクエスト ---*/
	@PostMapping("/show-edit-form")
	private String showEditForm(@ModelAttribute RestaurantEditForm form) {
		
		return "edit-restaurant";
	}
	
	/*--- 店舗編集リクエスト（編集画面より） ---*/
	@PostMapping("/edit-restaurant")
	public String editRestaurant(
			@Validated @ModelAttribute RestaurantEditForm form,
			BindingResult result) {
		
		//入力エラーがある場合には、店舗登録画面に戻す
		if (result.hasErrors()) {
			return "edit-restaurant";
		}
		
		//正常な場合に店舗登録確認画面に遷移する
		return "confirm-edit-restaurant";
	}
	
	/*--- 店舗編集リクエスト（編集確認画面より） ---*/
	@PostMapping("/confirm-edit-restaurant")
	public String confirmEditRestaurant(
			@Validated RestaurantEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		//入力エラーがある場合には、レビュー登録画面に戻す
		if(result.hasErrors()) {
			return "edit-restaurant";
		}
		
		Restaurant r = new Restaurant();
		r.setRestaurantId(form.getRestaurantId());
		r.setRestaurantName(form.getRestaurantName());
		r.setCatchPhrase(form.getCatchPhrase());
		
		service.edit(r);
		
		redirectAttributes.addFlashAttribute("msg", "(店舗更新)");
		
		return "redirect:/complete";
	}

	
}
