package mypackage.controller;
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

import mypackage.model.User;

public class UserDao {
	
	/**
	 * @description This method insert user into the database
	 */
	public static String saveUser(User user) {
		Transaction transaction = null;
		
        try (Session session = HibernateUtil.getSessionFactory(User.class).openSession()) {
            transaction = session.beginTransaction(); //start the transaction
            session.save(user); //save the user
            transaction.commit(); //commit changes to the database
            session.close();
            
            return Constants.SUCCESS;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            e.printStackTrace();
            
            return e.getMessage();
        }
	}
	
	/**
	 * @description This method get user from the database
	 * @param bronco_id Bronco student Id
	 */
	public static User getUser(Integer bronco_id) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory(User.class).openSession()) {
			transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where bronco_id = :bronco_id", User.class);
            query.setParameter(Constants.BRONCO_ID, bronco_id);
            
            @SuppressWarnings("unchecked")
			List<User> users = query.getResultList();
            
            if (users.size() > 0) {
            	transaction.commit();
            	session.close();
            	
            	return users.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
        
		return null;
	}
	
	/**
	 * @description This method checks if user exists in the system and password matches
	 */
	public static String checkIfUserExists(Integer bronco_id) {
        try {
        	User user = getUser(bronco_id);
        	
            if (user != null) {
            	return Constants.USER_EXISTS;
            }
            
            return Constants.SUCCESS;
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return e.getMessage();
        }
	}
	
	/**
	 * @description This method login user
	 */
	public static Map<String, User> loginUser(Integer bronco_id, String password) {
		
        try {
        	User user = getUser(bronco_id);
        	Map<String, User> resultMap = new HashMap<>();
        	
        	if (user != null) {
        		if (user.getPassword().equalsIgnoreCase(password)) {
        			resultMap.put(Constants.LOGIN_SUCCESSFUL, user);
        			return resultMap;
        			
        		} else {
        			resultMap.put(Constants.PASSWORD_INCORRECT, user);
        			return resultMap;
        		}
        	} else {
        		resultMap.put(Constants.USER_NOT_EXISTS, user);
    			return resultMap;
        	}
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return null;
        }
	}
}
