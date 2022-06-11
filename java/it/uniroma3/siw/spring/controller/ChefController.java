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

import it.uniroma3.siw.spring.controller.validator.ChefValidator;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.IngredienteService;
import it.uniroma3.siw.spring.service.PiattoService;



@Controller
public class ChefController {
	@Autowired ChefService chefService;
	@Autowired ChefValidator chefValidator;
	@Autowired CredentialsService credentialsService;
	@Autowired BuffetService buffetService;
	@Autowired PiattoService piattoService;
	@Autowired IngredienteService ingredienteService;



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
		Chef chef=chefService.findById(id);
		List <Buffet> buffets=buffetService.findByChef(chef);
		if(buffets!=null) {
			for(Buffet b:buffets) {
				List <Piatto> piatti=piattoService.findByBuffet(b);
				if(piatti!=null) {
					for(Piatto p:piatti) {
						List<Ingrediente> ingredienti=ingredienteService.findByPiatto(p);
						if(ingredienti!=null){
							for(Ingrediente i:ingredienti) {
								Long id2=i.getId();
								ingredienteService.deleteById(id2);
							}
						}
						Long id1=p.getId();
						piattoService.deleteById(id1);
					}
				}
				buffetService.deleteById(b.getId());
			}
	
		}

		Credentials credentials=credentialsService.getCredentials(chef);
		Long id1=credentials.getId();
		credentialsService.deleteById(id1);
		//		chefService.deleteById(id);
		model.addAttribute("chefs",chefService.findAll());
		return "/admin/chefs.html";
	}

	@GetMapping("/ilMioProfilo/{id}")
	public String ilMioProfilo(@PathVariable ("id") Long id, Model model ) {
		Credentials credentials=credentialsService.getCredentials(id);
		Chef chef=credentials.getChef();
		model.addAttribute("chef", chef);
		return "chef.html";
	}
}

