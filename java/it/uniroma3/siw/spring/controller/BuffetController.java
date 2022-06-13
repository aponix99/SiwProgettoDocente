package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.uniroma3.siw.spring.controller.validator.BuffetValidator;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.PiattoService;
import it.uniroma3.siw.spring.service.IngredienteService;


@Controller
public class BuffetController {
	@Autowired BuffetService buffetService;
	@Autowired BuffetValidator buffetValidator;
	@Autowired ChefService chefService;
	@Autowired PiattoService piattoService;
	@Autowired IngredienteService ingredienteService;



	//aggiunge una buffet 
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute ("buffet") Buffet buffet,BindingResult bindingResult,Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet",buffetService.findById(buffet.getId()));
			return "buffet.html";
		}
		//qui ho errori
		Long id=buffet.getChef().getId();
		Chef chef=chefService.findById(id);
		model.addAttribute("buffet",buffet);
		model.addAttribute("listChefs",chef);
		return "buffetForm.html";
		
	}

	@GetMapping("chef/{id}/buffetForm")
	public String getBuffetForm(@PathVariable("id") Long id,Model model) {
		Chef chef=chefService.findById(id);
		model.addAttribute("buffet",new Buffet());
		model.addAttribute("listChefs",chef);
		return "buffetForm.html";
	}
	
	@GetMapping("/buffetForm")
	public String getBuffetFormStandard(@PathVariable("id") Long id,Model model) {
		Chef chef=chefService.findById(id);
		model.addAttribute("buffet",new Buffet());
		model.addAttribute("listChefs",chef);
		return "buffetForm.html";
	}
	



	//richiede tutte le chefs
	@GetMapping("/buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets=buffetService.findAll();
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets.html";
	}


	//prende chef con id passato come parametro 
	@GetMapping("buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "buffet.html";
	}

	//richiede tutti i buffet dello chef selezionato
	@GetMapping("chef/{id}/buffets")
	public String getBuffetsByChef(@PathVariable("id") Long id,Model model) {
		Chef chef=chefService.findById(id);
		//		model.addAttribute("listChefs",chef);
		List<Buffet> buffets=buffetService.findByChef(chef);
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}

	/*Funzione che rimanda a una pagina html di conferma per rimuovere una chef*/
	@GetMapping("/toDeleteBuffet/{id}")
	public String toDeleteBuffet(@PathVariable ("id") Long id,Model model) {
		model.addAttribute("buffet",this.buffetService.findById(id));
		return "confirmDeleteBuffet.html";
	}

	@Transactional
	@GetMapping("deleteBuffet/{id}")
	public String deleteBuffet(@PathVariable ("id") Long id,Model model) {
		//		if(action.equals("Elimina")) {
		//			this.chefService.deleteById(id);
		//		}
		Buffet buffet=buffetService.findById(id);
		List <Piatto> piatti=piattoService.findByBuffet(buffet);
		if(piatti!=null) {
			for(Piatto p:piatti) {
				List<Ingrediente> ingredienti=ingredienteService.findByPiatto(p);
				if(ingredienti!=null){
					for(Ingrediente i:ingredienti) {
						Long idIngredienteCorrente=i.getId();
						ingredienteService.deleteById(idIngredienteCorrente);
					}
				}
				Long idPiattoCorrente=p.getId();
				piattoService.deleteById(idPiattoCorrente);
			}
		}

		buffetService.deleteById(id);
		Chef chef=buffet.getChef();
		model.addAttribute("buffets",buffetService.findByChef(chef));
		return "buffets.html";
	}


}

