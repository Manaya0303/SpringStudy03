package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditServiceImpl implements EditService {

	private final RestaurantRepository repository;
	
	@Override
	public void edit(Restaurant restaurant) {
		
		repository.update(restaurant);
		
	}

}
