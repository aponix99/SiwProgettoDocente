package it.uniroma3.siw.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.spring.controller.validator.PiattoValidator;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.PiattoService;



@Controller
public class PiattoController {
	@Autowired PiattoService piattoService;
	@Autowired PiattoValidator piattoValidator;
	@Autowired BuffetService buffetService;



	//aggiunge una piatto 
	@PostMapping("/piatto")
	public String addPiatto(@Valid @ModelAttribute ("piatto") Piatto piatto,BindingResult bindingResult,Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto",piattoService.findById(piatto.getId()));
			return "piatto.html";
		}
		Long id=piatto.getBuffet().getId();
		Buffet buffet=buffetService.findById(id);
		model.addAttribute("buffet",buffet);
		model.addAttribute("piatto",piatto);
		return "piattoForm.html";

	}

//	@GetMapping("buffet/{id}/piattoForm")
//	public String piattoForm(@PathVariable ("id") Long id,Model model) {
//		Buffet buffet=buffetService.findById(id);
//		model.addAttribute("buffets",buffet);
//		model.addAttribute("piatto",new Piatto());
//		return "piattoForm.html";
//	}

	
	//richiede tutti i piatti (di tutti gli chef)
	@GetMapping("/piatti")
	public String getPiatti(Model model) {
		List<Piatto> piatto=piattoService.findAll();
		model.addAttribute("piatti", this.piattoService.findAll());
		return "piatti.html";
	}
	
	//richiede il piatto con id passato nel parametro
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatto.html";
	}

	//richiede tutti i piatti del buffet
	@GetMapping("/buffet/{id}/piatti")
	public String getPiattiByBuffet(Model model,@PathVariable ("id") Long id) {
		Buffet buffet=buffetService.findById(id);
		List<Piatto> piatti=piattoService.findByBuffet(buffet);
		model.addAttribute("piatti",piatti);
		return "piatti.html";
	}
	
	@GetMapping("/buffet/{id}/piattoForm")
	public String getPiatto(Model model,@PathVariable ("id") Long id) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("piatto",new Piatto());
		return "piattoForm.html";
	}
}


