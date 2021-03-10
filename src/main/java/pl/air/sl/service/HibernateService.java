package pl.air.sl.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateService {
	
	private static SessionFactory sessionFactory;
	private static Validator validator;
	

	
	/* session factory */
	public static void startup() {
		
		 sessionFactory = new Configuration().configure().buildSessionFactory();
		 
		 ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		 validator = validatorFactory.getValidator();
	}
	
	public static void shutdown() {
		sessionFactory.close();
	}
	
	/* session */
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeSession() {
		getSession().flush();
		getSession().close();
	}
	
	// transaction
	
	public static Transaction beginTransaction() {
		Transaction tx = getSession().beginTransaction();
		return tx;
	}
	
	public static void commitTransaction() {
		getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}
	
	// validation
	public static <T> List<String> validate(T object) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
		
		if(constraintViolations.size() == 0) {
			return null;
		}
		
		List<String> errorList = new ArrayList<>();
		
		for(ConstraintViolation<T> violation : constraintViolations) {
			String msg = violation.getMessage();
			errorList.add(msg);
			
		}
		return errorList;
	}
	
	// config option
	public static boolean isCreate() {
		Map<String, Object> props = sessionFactory.getProperties();
		String value = (String) props.get("hbm2ddl.auto");
		return "create".equals(value);
	}

}
