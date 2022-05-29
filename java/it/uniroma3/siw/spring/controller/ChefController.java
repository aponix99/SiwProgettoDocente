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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.ChefValidator;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.ChefService;



@Controller
public class ChefController {
	@Autowired ChefService chefService;
	@Autowired ChefValidator chefValidator;



	//aggiunge una chef 
	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute ("chef") Chef chef,BindingResult bindingResult,Model model) {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef",chefService.findById(chef.getId()));
			return "chef.html";
		}
		return "chefForm.html";

	}
	//prende chef con id passato come parametro
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chef.html";
	}




	//richiede tutte le chefs
	@GetMapping("/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs=chefService.findAll();
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefs.html";
	}
	
	@GetMapping("/admin/chefs")
	public String getChefsAdmin(Model model) {
		List<Chef> chefs=chefService.findAll();
		model.addAttribute("chefs", this.chefService.findAll());
		return "/admin/chefs.html";
	}

	@GetMapping("/chefForm")
	public String getChef(Model model) {
		model.addAttribute("chef",new Chef());
		return "chefForm.html";
	}

	/*Funzione che rimanda a una pagina html di conferma per rimuovere una chef*/
	@GetMapping("/toDeleteChef/{id}")
	public String toDeletechef(@PathVariable ("id") Long id,Model model) {
		model.addAttribute("chef",this.chefService.findById(id));
		return "confirmDeleteChef.html";
	}
	
	@Transactional
	@GetMapping("deleteChef/{id}")
	public String deletechef(@PathVariable ("id") Long id,Model model) {
//		if(action.equals("Elimina")) {
//			this.chefService.deleteById(id);
//		}
		chefService.deleteById(id);
		model.addAttribute("chefs",chefService.findAll());
		return "chefs.html";
	}

}
