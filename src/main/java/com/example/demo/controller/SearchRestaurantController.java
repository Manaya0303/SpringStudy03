package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.SearchedRestaurant;
import com.example.demo.form.RestaurantSearchForm;
import com.example.demo.service.RestaurantListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchRestaurantController {
	
	private final RestaurantListService service;
	
	//最初のリクエスト
	@GetMapping("/search-top")
	private String searchTop(
			@ModelAttribute RestaurantSearchForm form) {
		return "search-restaurant";
	}

	//検索リクエスト
	@PostMapping("/search-restaurant")
	private String searchRestaurant(
			@ModelAttribute RestaurantSearchForm form, 
			Model model) {
		
		List<SearchedRestaurant> list
			=service.findByNameWildcard(form.getRestaurantName());
		
		model.addAttribute("restaurantList", list);
		
		return "search-restaurant";
	}
}
