package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SearchedRestaurant;

public interface RestaurantListService {

	List<SearchedRestaurant> findByNameWildcard(String restaurantName);
}
