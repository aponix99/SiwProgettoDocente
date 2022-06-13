package it.uniroma3.siw.spring.controller.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.ChefService;

@Component
public class ChefValidator implements Validator {
	@Autowired
	private ChefService chefService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.chefService.alreadyExists((Chef)o)){
			errors.reject("chef.duplicato");
			}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Chef.class.equals(aClass);
	}
}



/*
    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        String nome = user.getNome().trim();
        String cognome = user.getCognome().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (cognome.isEmpty())
            errors.rejectValue("cognome", "required");
        else if (cognome.length() < MIN_NAME_LENGTH || cognome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("cognome", "size");
    } 
 */
 