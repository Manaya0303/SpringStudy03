package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.SearchedRestaurant;

public interface RestaurantSearchRepository {

	List<SearchedRestaurant> selectByNameWildcard(String restaurantName);
}
