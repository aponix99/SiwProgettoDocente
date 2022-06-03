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
import it.uniroma3.siw.spring.controller.validator.BuffetValidator;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;



@Controller
public class BuffetController {
	@Autowired BuffetService buffetService;
	@Autowired BuffetValidator buffetValidator;
	@Autowired ChefService chefService;



	//aggiunge una buffet 
	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute ("buffet") Buffet buffet,BindingResult bindingResult,Model model) {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet",buffetService.findById(buffet.getId()));
			return "buffet.html";
		}
		return "buffetForm.html";

	}

	@GetMapping("chef/{id}/buffetForm")
	public String getBuffetForm(@PathVariable("id") Long id,Model model) {
		Chef chef=chefService.findById(id);
		model.addAttribute("buffet",new Buffet());
		model.addAttribute("listChefs",chef);
		System.out.println(id);
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
	


}

