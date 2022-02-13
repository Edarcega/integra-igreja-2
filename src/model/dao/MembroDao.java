package model.dao;

import java.util.List;

import model.entities.Membro;

public interface MembroDao {
	
	void insert(Membro obj);

	void update(Membro obj);

	void deleteById(Integer id);

	Membro findById(Integer id);

	List<Membro> findByName(String Name);
	
	List<Membro> findByIgreja (Integer id);

	List<Membro> findAll();

	List<Membro> findByPGM(Integer id);

}
