package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SearchedRestaurant;
import com.example.demo.repository.RestaurantSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantListServiceImpl implements RestaurantListService {
	
	private final RestaurantSearchRepository repository;
	@Override
	public List<SearchedRestaurant> findByNameWildcard(String restaurantName) {
		
		List<SearchedRestaurant> list
			= repository.selectByNameWildcard(restaurantName);
		
		return list;
	}

}
