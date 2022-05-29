package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Chef;
public interface ChefRepository  extends CrudRepository<Chef, Long>{
	public List <Chef> findByNome(String nome);
	public List <Chef> findByNomeOrCognome(String nome,String cognome);
	public Optional<Chef>  findById(Long id);
	public void deleteById(Long id);
	public boolean existsByNomeAndCognomeAndNazionalita(String nome, String cognome, String nazionalita);
}

