package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class RecipieDAOImpl implements RecipieDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}

	//create
	@Override
	public Long save(Recipie object) {
		
		return (Long)getSession().save(object);
	}
	
	
	//read
	@Override
	public Recipie findById(Long id) {
		
		return getSession().get(Recipie.class, id);
	}

	@Override
	public List<Recipie> findAll() {
		
		Query<Recipie> query = getSession().createQuery("from Recipie", Recipie.class);
		List<Recipie> all = query.getResultList();
		
		return all;
	}
	
	@Override
	public List<Recipie> findAllByShoppingList(ShoppingList list) {
		
		Query<Recipie> query = getSession().createQuery("FROM Recipie WHERE shoppingList = :list", Recipie.class);
		query.setParameter("list", list);
		
		return query.getResultList();

	}
	
	//update
	@Override
	public void update(Recipie object) {
		getSession().update(object);
		
	}

	//delete
	@Override
	public void delete(Recipie object) {
		
		getSession().delete(object);
		
	}

	@Override
	public void deleteByShoppingList(ShoppingList list) {
		
		Session session = getSession();
		
		List<Recipie> todelete = findAllByShoppingList(list);
		
		Query<?> query = session.createQuery("delete from Recipie r where r IN (:todelete)");
		query.setParameter("todelete", todelete);
		query.executeUpdate();
		// TODO Auto-generated method stub
		
	}


}
