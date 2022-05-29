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

import it.uniroma3.siw.spring.controller.validator.PersonaValidator;
import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.service.PersonaService;

@Controller
public class PersonaController {
	@Autowired PersonaService personaService;
	@Autowired PersonaValidator personaValidator;



	//aggiunge una persona 
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute ("persona") Persona persona,BindingResult bindingResult,Model model) {
		this.personaValidator.validate(persona, bindingResult);
		if(!bindingResult.hasErrors()) {
			personaService.save(persona);
			model.addAttribute("persona",personaService.findById(persona.getId()));
			return "persona.html";
		}
		return "personaForm.html";

	}
	//prende persona con id passato come parametro
	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model) {
		model.addAttribute("persona", this.personaService.findById(id));
		return "persona.html";
	}




	//richiede tutte le persone
	@GetMapping("/persone")
	public String getPersone(Model model) {
		List<Persona> persone=personaService.findAll();
		model.addAttribute("persone", this.personaService.findAll());
		return "persone.html";
	}
	
	@GetMapping("/admin/persone")
	public String getPersoneAdmin(Model model) {
		List<Persona> persone=personaService.findAll();
		model.addAttribute("persone", this.personaService.findAll());
		return "/admin/persone.html";
	}

	@GetMapping("/personaForm")
	public String getPersona(Model model) {
		model.addAttribute("persona",new Persona());
		return "personaForm.html";
	}

	/*Funzione che rimanda a una pagina html di conferma per rimuovere una persona*/
	@GetMapping("/toDeletePersona/{id}")
	public String toDeletePersona(@PathVariable ("id") Long id,Model model) {
		model.addAttribute("persona",this.personaService.findById(id));
		return "confirmDeletePerson.html";
	}
	
	@Transactional
	@GetMapping("deletePersona/{id}")
	public String deletePersona(@PathVariable ("id") Long id,Model model) {
//		if(action.equals("Elimina")) {
//			this.personaService.deleteById(id);
//		}
		personaService.deleteById(id);
		model.addAttribute("persone",personaService.findAll());
		return "persone.html";
	}

}

