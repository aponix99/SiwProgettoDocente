package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	private PiattoRepository piattoService;

	@Transactional
	public void save (Piatto Piatto) {
		piattoService.save(Piatto);
	}

	public Piatto findById(Long id) {
		return piattoService.findById(id).get();
	}


	public List<Piatto> findAll() {
		List<Piatto> piatti=new ArrayList<Piatto>();
		for(Piatto b:piattoService.findAll()) {
			piatti.add(b);
		}
		return piatti;
	}

	public boolean alreadyExists(Piatto o) {
		// TODO Auto-generated .method stub
		 return piattoService.existsByNome(o.getNome());
	}

	public void deleteById(Long id) {
		 piattoService.deleteById(id);
		
	}
	

	public List<Piatto> findByBuffet(Buffet buffet){
		List<Piatto> piatti=new ArrayList<Piatto>();
		for(Piatto b:piattoService.findAll()) {
			if(b.getBuffet()==buffet)
			piatti.add(b);
		}
		return piatti;
	}
}
