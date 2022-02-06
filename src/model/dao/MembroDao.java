package model.dao;

import java.util.List;

import model.entities.Igreja;
import model.entities.Membro;
import model.entities.Pgm;

public interface MembroDao {
	
	void insert(Membro obj);

	void update(Membro obj);

	void deleteById(Integer id);

	Membro findById(Integer id);

	Membro findByName(String Name);
	
	List<Membro> findByPGM (Pgm pgm);
	
	List<Membro> findByIgreja (Igreja igreja);

	List<Membro> findAll();

}
