package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
public interface BuffetRepository  extends CrudRepository<Buffet, Long>{
	public List <Buffet> findByNome(String nome);
	public Optional<Buffet>  findById(Long id);
	public void deleteById(Long id);
	public boolean existsByNome(String nome);
}

