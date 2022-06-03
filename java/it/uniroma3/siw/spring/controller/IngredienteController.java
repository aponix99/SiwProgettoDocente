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

import it.uniroma3.siw.spring.controller.validator.IngredienteValidator;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.IngredienteService;
import it.uniroma3.siw.spring.service.PiattoService;



@Controller
public class IngredienteController {
	@Autowired IngredienteService ingredientiService;
	@Autowired IngredienteValidator ingredienteValidator;
	@Autowired PiattoService piattoService;



	//aggiunge una Ingrediente 
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute ("Ingrediente") Ingrediente Ingrediente,BindingResult bindingResult,Model model) {
		this.ingredienteValidator.validate(Ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			ingredientiService.save(Ingrediente);
			model.addAttribute("ingrediente",ingredientiService.findById(Ingrediente.getId()));
			return "ingrediente.html";
		}
		return "ingredienteForm.html";

	}
	//prende Ingrediente con id passato come parametro
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Ingrediente", this.ingredientiService.findById(id));
		return "ingrediente.html";
	}




	//richiede tutte le ingredienti
	@GetMapping("/ingredienti")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti=ingredientiService.findAll();
		model.addAttribute("ingredienti", this.ingredientiService.findAll());
		return "ingredienti.html";
	}
	
	@GetMapping("/admin/ingredienti")
	public String getingredientiAdmin(Model model) {
		List<Ingrediente> ingredienti=ingredientiService.findAll();
		model.addAttribute("ingredienti", this.ingredientiService.findAll());
		return "/admin/ingredienti.html";
	}

	@GetMapping("/piatto/{id}/ingredienteForm")
	public String getIngredienteForm(@PathVariable ("id") Long id,Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		model.addAttribute("ingrediente",new Ingrediente());
		return "ingredienteForm.html";
	}

	/*Funzione che rimanda a una pagina html di conferma per rimuovere una Ingrediente*/
	@GetMapping("/toDeleteIngrediente/{id}")
	public String toDeleteIngrediente(@PathVariable ("id") Long id,Model model) {
		model.addAttribute("Ingrediente",this.ingredientiService.findById(id));
		return "confirmDeleteIngrediente.html";
	}
	
	@Transactional
	@GetMapping("deleteIngrediente/{id}")
	public String deleteIngrediente(@PathVariable ("id") Long id,Model model) {
//		if(action.equals("Elimina")) {
//			this.ingredientiervice.deleteById(id);
//		}
		ingredientiService.deleteById(id);
		model.addAttribute("ingredienti",ingredientiService.findAll());
		return "ingredienti.html";
	}
	
	@GetMapping("/piatto/{id}/ingredientiDelPiatto")
	public String getIngredientiDelPiatto(@PathVariable("id") Long id,Model model) {
		Piatto piatto=piattoService.findById(id);
		List<Ingrediente> ingredienti=ingredientiService.findByPiatto(piatto);
		model.addAttribute("piatto",piatto);
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}

}

