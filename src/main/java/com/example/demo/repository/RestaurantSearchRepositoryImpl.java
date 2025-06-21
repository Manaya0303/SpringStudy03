package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SearchedRestaurant;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RestaurantSearchRepositoryImpl implements RestaurantSearchRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SearchedRestaurant> selectByNameWildcard(String restaurantName) {
		
		String sql =
				"SELECT                                                  " +
				"	mr.restaurant_id,                                    " +
				"	mr.restaurant_name,                                  " +
				"	mr.catch_phrase,                                     " +
				"	COUNT(tr.comment) review_count                       " +
				"FROM                                                    " +
				"	m_restaurant mr                                      " +
				"	LEFT OUTER JOIN t_review tr                          " +
				"                ON mr.restaurant_id = tr.restaurant_id  " +
				"WHERE                                                   " +
				"	mr.restaurant_name LIKE ?                            " +
				"GROUP BY                                                " +
				"	mr.restaurant_id,                                    " +
				"	mr.restaurant_name,                                  " +
				"	mr.catch_phrase                                      " +
				"ORDER BY                                                " +
				"	mr.restaurant_id                                     ";
		
		String p = "%" + restaurantName + "%";	//プレースホルダーの値
		
		//SQLで検索 (プレースホルダー:p)
		List<Map<String, Object>> list
			=jdbcTemplate.queryForList(sql, p);
		
		//値の取得⇒結果の格納
		List<SearchedRestaurant> result = new ArrayList<SearchedRestaurant>();	//結果の初期化
		for (Map<String, Object> one : list) {
			SearchedRestaurant restaurant = new SearchedRestaurant();
			restaurant.setRestaurantId((int)one.get("restaurant_id"));
			restaurant.setRestaurantName((String)one.get("restaurant_name"));
			restaurant.setCatchPhrase((String)one.get("catch_phrase"));
			restaurant.setReviewCount(((Long)one.get("review_count")).intValue());
			result.add(restaurant);
		}
		
		return result;
	}

}
