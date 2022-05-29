package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona,Long>{
	public boolean existsByNomeAndCognomeAndEta(String nome,String cognome,Integer eta);
	public void deleteById(Long id);

}
