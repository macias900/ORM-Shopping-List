package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class ShoppingListDAOImpl implements ShoppingListDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	//create
	@Override
	public Long save(ShoppingList object) {
		
		return (Long)getSession().save(object);
	}
	
	
	//read
	@Override
	public ShoppingList findById(Long id) {
		
		return getSession().get(ShoppingList.class, id);
	}

	@Override
	public List<ShoppingList> findAll() {
		
		Query<ShoppingList> query = getSession().createQuery("from ShoppingList", ShoppingList.class);
		List<ShoppingList> all = query.getResultList();
		
		return all;
	}
	
	public List<ShoppingList> findAllByShoppingItem(ShoppingItem one){
		
		Query<ShoppingList> query = getSession().createQuery("FROM ShoppingList WHERE :one MEMBER OF shoppingList ", ShoppingList.class);
		query.setParameter("one", one);
		List<ShoppingList> list = query.getResultList();
		
		return list;
		
	}
	
	
	//update
	@Override
	public void update(ShoppingList object) {
		getSession().update(object);
		
	}

	//delete
	@Override
	public void delete(ShoppingList object) {
		
		getSession().delete(object);
		
	}

	@Override
	public void deleteByShoppingItem(ShoppingItem one) {
		
		Session session = getSession();
		
		List<ShoppingList> list = findAllByShoppingItem(one);
		Query<?> query = session.createQuery("delete from ShoppingList sl where sl IN (:list)");
		query.setParameter("list", list);
		query.executeUpdate();
		
	}
	
	

}
