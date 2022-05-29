package it.uniroma3.siw.spring.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.service.PersonaService;

@Component
public class PersonaValidator implements Validator {
	@Autowired
	private PersonaService personaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.personaService.alreadyExists((Persona)o)){
			errors.reject("persona.duplicato");
			}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Persona.class.equals(aClass);
	}
}

