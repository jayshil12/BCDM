package mypackage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import mypackage.model.Category;
import mypackage.model.Fooditem;
import mypackage.model.User;

public class FoodDao {
	/**
	 * @description This method builds food items info by joining two tables : fooditems and categories
	 */
	public static List<FoodItemInfoWrapper> getFoodItemInfos(String userId) {
		List<FoodItemInfoWrapper> foodItemInfos = new ArrayList<FoodItemInfoWrapper>();
		
		try {
			Map<Integer,String> categoryNameById = getCategoryInfo();
			List<Fooditem> foodItems = getFoodItems();
			
			for (Fooditem foodItem : foodItems) {
				FoodItemInfoWrapper foodItemInfo = new FoodItemInfoWrapper();
				foodItemInfo.foodName = foodItem.getName();
				foodItemInfo.description = foodItem.getDescription();
				foodItemInfo.price = foodItem.getPrice();
				foodItemInfo.categoryName = categoryNameById.get(foodItem.getCategoryId());
				foodItemInfo.foodId = foodItem.getId();
				foodItemInfos.add(foodItemInfo);
			}
			
			return foodItemInfos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * @description This method gets categories stored in database
	 */
	public static Map<Integer,String> getCategoryInfo() {
		Map<Integer,String> categoryNameById = new HashMap<Integer,String>();
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory(Category.class).openSession()) {
			transaction = session.beginTransaction();
            Query query = session.createQuery("from Category u", Category.class);
            
            @SuppressWarnings("unchecked")
			List<Category> categories = query.getResultList();
            
            if (categories.size() > 0) {
				for (Category category : categories) {
					categoryNameById.put(category.getId(), category.getName());
		    	}
			}

            transaction.commit();
        	session.close();
        } catch (Exception e) {
            e.printStackTrace();
            
            return categoryNameById;
        }
        
		return categoryNameById;
	}
	
	/**
	 * @description This method gets fooditems stored in database
	 */
	public static List<Fooditem> getFoodItems() {
		//List<Fooditem> foodItems = new ArrayList<Fooditem>();
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory(Fooditem.class).openSession()) {
			transaction = session.beginTransaction();
            Query query = session.createQuery("from Fooditem f where active = true", Fooditem.class);
            
            @SuppressWarnings("unchecked")
            List<Fooditem> foodItems = query.getResultList();
            
            transaction.commit();
        	session.close();
        	
            return foodItems;
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
	}
}
