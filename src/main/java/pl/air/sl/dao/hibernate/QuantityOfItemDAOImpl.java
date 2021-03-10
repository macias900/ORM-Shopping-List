package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class QuantityOfItemDAOImpl implements QuantityOfItemDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	//create
	@Override
	public Long save(QuantityOfItem object) {
		
		return (Long)getSession().save(object);
	}
	
	
	//read
	@Override
	public QuantityOfItem findById(Long id) {
		
		return getSession().get(QuantityOfItem.class, id);
	}

	@Override
	public List<QuantityOfItem> findAll() {
		
		Query<QuantityOfItem> query = getSession().createQuery("from QuantityOfItem", QuantityOfItem.class);
		List<QuantityOfItem> all = query.getResultList();
		
		return all;
	}
	
	public List<QuantityOfItem> findAllByShoppingList(ShoppingList one){
		
	
		Query<QuantityOfItem> query = getSession().createQuery("FROM QuantityOfItem WHERE shoppingList = :one ", QuantityOfItem.class);
		query.setParameter("one", one);
		List<QuantityOfItem> list = query.getResultList();
		return list;
	}
	
	@Override
	public List<QuantityOfItem> findAllByShoppingItem(ShoppingItem one) {
		
		Query<QuantityOfItem> query = getSession().createQuery("FROM QuantityOfItem WHERE shoppingItem = :one ", QuantityOfItem.class);
		query.setParameter("one", one);
		List<QuantityOfItem> list = query.getResultList();
		return list;
	}
	
	//update
	@Override
	public void update(QuantityOfItem object) {
		getSession().update(object);
		
	}

	//delete
	@Override
	public void delete(QuantityOfItem object) {
		
		getSession().delete(object);
		
	}

	@Override
	public void deleteByShoppingList(ShoppingList one) {
		
		Session session = getSession();
		
		List<QuantityOfItem> list = findAllByShoppingList(one);
		Query<?> query = session.createQuery("delete from QuantityOfItem q where q IN (:list)");
		query.setParameter("list", list);
		query.executeUpdate();
		
	}


	@Override
	public void deleteByShoppingItem(ShoppingItem one) {
		
		Session session = getSession();
		
		List<QuantityOfItem> list =findAllByShoppingItem(one);
		Query<?> query = session.createQuery("delete from QuantityOfItem q where q IN (:list)");
		query.setParameter("list", list);
		query.executeUpdate();
		
	}

}
