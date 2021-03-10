package pl.air.sl.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pl.air.sl.service.HibernateService;
import pl.air.sl.dao.*;
import pl.air.sl.model.*;

public class MeasurmentTypeDAOImpl implements MeasurmentTypeDAO{
	
	private Session getSession() {
		return HibernateService.getSession();
	}


	//create
	@Override
	public Long save(MeasurmentType object) {
		
		return (Long)getSession().save(object);
	}

	//read
	@Override
	public MeasurmentType findById(Long id) {
		
		return getSession().get(MeasurmentType.class, id);
	}

	@Override
	public List<MeasurmentType> findAll() {
		
		Query<MeasurmentType> query = getSession().createQuery("from MeasurmentType", MeasurmentType.class);
		List<MeasurmentType> all = query.getResultList();
		
		return all;
	}
	
	//update
	@Override
	public void update(MeasurmentType object) {
		getSession().update(object);
		
	}
	
	//delete
	@Override
	public void delete(MeasurmentType object) {
		
		getSession().delete(object);
		
	}

}
