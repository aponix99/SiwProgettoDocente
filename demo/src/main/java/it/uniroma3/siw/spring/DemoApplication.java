package it.uniroma3.siw.spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgettoSiwDocente");
		EntityManager em = emf.createEntityManager();
		Chef chef1=new Chef("nome0", "cognome0", "nazionalita0");
		Buffet buffet1=new Buffet("buffet1", "descrizione1");
		Piatto piatto1=new Piatto("piatto1", "todo", buffet1);
		Ingrediente ingrediente1=new Ingrediente("ingrediente1", "origine1", "todo",piatto1);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(chef1);
		em.persist(buffet1);
		em.persist(piatto1);
		em.persist(ingrediente1);
		tx.commit();
		em.close();
		emf.close();

	}
}
