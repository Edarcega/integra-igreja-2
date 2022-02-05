package model.dao;

import java.util.List;

import model.entities.Pgm;

public interface PgmDao {
	
	void insert(Pgm obj);

	void update(Pgm obj);

	void deleteById(Integer id);

	Pgm findById(Integer id);

	Pgm findByName(String Name);

	List<Pgm> findAll();

}
