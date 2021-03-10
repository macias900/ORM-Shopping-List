package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class StockDAOImpl implements StockDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	//create
	@Override
	public Long save(Stock object) {
		
		return (Long)getSession().save(object);
	}
	
	
	//read
	@Override
	public Stock findById(Long id) {
		
		return getSession().get(Stock.class, id);
	}

	@Override
	public List<Stock> findAll() {
		
		Query<Stock> query = getSession().createQuery("from Stock", Stock.class);
		List<Stock> all = query.getResultList();
		
		return all;
	}
	
	@Override
	public Stock findyByShoppingItem(ShoppingItem one) {
		
		Query<Stock> query = getSession().createQuery("FROM Stock where shopItem = :one", Stock.class);
		query.setParameter("one", one);
		Stock stock = query.getSingleResult();
		
		return stock;
	}
	
	
	
	//update
	@Override
	public void update(Stock object) {
		getSession().update(object);
		
	}

	//delete
	@Override
	public void delete(Stock object) {
		
		getSession().delete(object);
		
	}


}
