package mypackage.controller;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	public static SessionFactory getSessionFactory(Class<?> className) {
		try {
			Configuration config = new Configuration().configure();
			config.addAnnotatedClass(className);
			ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sessionFactory = config.buildSessionFactory(servReg);
			
			return sessionFactory;
		} catch (Exception e) {
			
		}
		
		return null;
	}
}
