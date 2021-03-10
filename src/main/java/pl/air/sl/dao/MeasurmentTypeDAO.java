package pl.air.sl.dao;

import java.util.List;

import pl.air.sl.model.*;

public interface MeasurmentTypeDAO {

	// create
	Long save(MeasurmentType object);

	// read
	MeasurmentType findById(Long id);

	List<MeasurmentType> findAll();

	// update
	void update(MeasurmentType object);

	// delete
	void delete(MeasurmentType object);
}
