package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.model.Chef;
public interface IngredienteRepository  extends CrudRepository<Ingrediente, Long>{
	public List <Ingrediente> findByNome(String nome);
	public Optional<Ingrediente>  findById(Long id);
	public Optional<Ingrediente>  findByPiatto(Piatto piatto);
	public void deleteById(Long id);
	public boolean existsByNome(String nome);
}

