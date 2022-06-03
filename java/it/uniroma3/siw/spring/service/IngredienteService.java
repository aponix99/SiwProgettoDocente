package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository ingredienteService;

	@Transactional
	public void save (Ingrediente ingrediente) {
		ingredienteService.save(ingrediente);
	}

	public Ingrediente findById(Long id) {
		return ingredienteService.findById(id).get();
	}


	public List<Ingrediente> findAll() {
		List<Ingrediente> piatti=new ArrayList<Ingrediente>();
		for(Ingrediente b:ingredienteService.findAll()) {
			piatti.add(b);
		}
		return piatti;
	}

	public boolean alreadyExists(Ingrediente o) {
		// TODO Auto-generated .method stub
		return ingredienteService.existsByNome(o.getNome());
	}

	public void deleteById(Long id) {
		ingredienteService.deleteById(id);

	}

	public List<Ingrediente> findByPiatto(Piatto piatto){
		List<Ingrediente> ingredienti=new ArrayList<Ingrediente>();
		for(Ingrediente i:ingredienteService.findAll()) {
			if(i.getPiatto()==piatto)
				ingredienti.add(i);
		}
		return ingredienti;
	}

}
