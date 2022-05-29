package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetService;

	@Transactional
	public void save (Buffet buffet) {
		buffetService.save(buffet);
	}

	public Buffet findById(Long id) {
		return buffetService.findById(id).get();
	}


	public List<Buffet> findAll() {
		List<Buffet> buffets=new ArrayList<Buffet>();
		for(Buffet b:buffetService.findAll()) {
			buffets.add(b);
		}
		return buffets;
	}

	public boolean alreadyExists(Buffet o) {
		// TODO Auto-generated .method stub
		 return buffetService.existsByNome(o.getNome());
	}

	public void deleteById(Long id) {
		 buffetService.deleteById(id);
		
	}

}
