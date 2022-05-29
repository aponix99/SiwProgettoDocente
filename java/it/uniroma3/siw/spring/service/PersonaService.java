package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.repository.PersonaRepository;

@Service
public class PersonaService {
	@Autowired
	private PersonaRepository personaService;

	@Transactional
	public void save (Persona persona) {
		personaService.save(persona);
	}

	public Persona findById(Long id) {
		return personaService.findById(id).get();
	}


	public List<Persona> findAll() {
		List<Persona> persone=new ArrayList<Persona>();
		for(Persona p:personaService.findAll()) {
			persone.add(p);
		}
		return persone;
	}

	public boolean alreadyExists(Persona o) {
		// TODO Auto-generated .method stub
		 return personaService.existsByNomeAndCognomeAndEta(o.getNome(),o.getCognome(),o.getEta());
	}

	public void deleteById(Long id) {
		 personaService.deleteById(id);
		
	}

}
