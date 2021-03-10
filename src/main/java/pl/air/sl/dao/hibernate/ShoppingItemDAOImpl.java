package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class ShoppingItemDAOImpl implements ShoppingItemDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}


	//create
	@Override
	public Long save(ShoppingItem object) {
		
		return (Long)getSession().save(object);
	}

	//read
	@Override
	public ShoppingItem findById(Long id) {
		
		return getSession().get(ShoppingItem.class, id);
	}

	@Override
	public List<ShoppingItem> findAll() {
		
		Query<ShoppingItem> query = getSession().createQuery("from ShoppingItem", ShoppingItem.class);
		List<ShoppingItem> all = query.getResultList();
		
		return all;
	}
	
	//update
	@Override
	public void update(ShoppingItem object) {
		getSession().update(object);
		
	}
	//delete
	@Override
	public void delete(ShoppingItem object) {
		
		getSession().delete(object);
		
	}

}
