package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository chefService;

	@Transactional
	public void save (Chef persona) {
		chefService.save(persona);
	}

	public Chef findById(Long id) {
		return chefService.findById(id).get();
	}


	public List<Chef> findAll() {
		List<Chef> chefs=new ArrayList<Chef>();
		for(Chef p:chefService.findAll()) {
			chefs.add(p);
		}
		return chefs;
	}

	public boolean alreadyExists(Chef o) {
		// TODO Auto-generated .method stub
		 return chefService.existsByNomeAndCognomeAndNazionalita(o.getNome(),o.getCognome(),o.getNazionalita());
	}

	public void deleteById(Long id) {
		 chefService.deleteById(id);
		
	}

}
