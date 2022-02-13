package model.dao;

import java.util.List;

import model.entities.Igreja;

public interface IgrejaDao {

	void insert(Igreja obj);

	void update(Igreja obj);

	void deleteById(Integer id);

	Igreja findById(Integer id);

	List<Igreja> findByName(String Name);

	List<Igreja> findAll();

}
