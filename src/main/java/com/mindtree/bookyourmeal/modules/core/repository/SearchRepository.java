
package com.mindtree.bookyourmeal.modules.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.Query;
import org.hibernate.search.exception.SearchException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.mindtree.bookyourmeal.modules.core.dto.FoodRestaurantSearchDto;
import com.mindtree.bookyourmeal.modules.core.entity.FoodRestaurantSearch;
import com.mindtree.bookyourmeal.modules.core.exception.CoreException;
import com.mindtree.bookyourmeal.modules.core.exception.NoFoodFoundException;

import com.mindtree.bookyourmeal.modules.restaurant.entity.Food;
import com.mindtree.bookyourmeal.modules.restaurant.entity.Restaurant;

@Repository
@Transactional
public class SearchRepository {

	@PersistenceContext
	private EntityManager entityManager;

	Query luceneProblemQuery;

	public FoodRestaurantSearch searchbykeyword(String searchedfood) throws CoreException {
		// TODO Auto-generated method stub
		FullTextQuery jpaQueryfood,jpaQueryrestaurant;
		List<Food> searchedfoodlist;
		List<Restaurant> searchedrestaurantlist;
		FoodRestaurantSearch result=new FoodRestaurantSearch();
		try {
			FullTextEntityManager fullTextEM = Search.getFullTextEntityManager(entityManager);
			QueryBuilder problemfoodQB = fullTextEM.getSearchFactory().buildQueryBuilder().forEntity(Food.class).get();
			luceneProblemQuery = problemfoodQB.keyword().onField("foodName").matching(searchedfood).createQuery();
			Builder foodbuilder = new Builder();
			foodbuilder.add(luceneProblemQuery, Occur.SHOULD);
			// builder.add(luceneIdeaQuery, Occur.SHOULD);
			jpaQueryfood = fullTextEM.createFullTextQuery(foodbuilder.build(), Food.class);
			searchedfoodlist = jpaQueryfood.getResultList();
			
			QueryBuilder problemrestaurantQB = fullTextEM.getSearchFactory().buildQueryBuilder().forEntity(Restaurant.class).get();
			luceneProblemQuery = problemrestaurantQB.keyword().onField("restaurantName").matching(searchedfood).createQuery();
			Builder restaurantbuilder = new Builder();
			restaurantbuilder.add(luceneProblemQuery, Occur.SHOULD);
			jpaQueryrestaurant = fullTextEM.createFullTextQuery(restaurantbuilder.build(), Restaurant.class);
			searchedrestaurantlist = jpaQueryrestaurant.getResultList();
			result.setFoods(searchedfoodlist);
			result.setRestaurants(searchedrestaurantlist);
			if (searchedrestaurantlist.isEmpty() && searchedfoodlist.isEmpty()) {
				System.out.println("empty list");//
				throw new NoFoodFoundException("No matching results found, try another keywords....");
			}
			entityManager.close();
		} catch (SearchException e) {
			throw new CoreException("Server Error!!");
		}
		return result;
	}	
 }

