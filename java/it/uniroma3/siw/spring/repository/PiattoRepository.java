package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
public interface PiattoRepository  extends CrudRepository<Piatto, Long>{
	public List <Piatto> findByNome(String nome);
	public Optional<Piatto>  findById(Long id);
	public Optional<Piatto>  findByBuffet(Buffet buffet);
	public void deleteById(Long id);
	public boolean existsByNome(String nome);
}

