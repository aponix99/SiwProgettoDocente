package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Piatto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	String nome;
	@Column
	String descrizione;
	@ManyToOne
	Buffet buffet;
	@OneToMany (mappedBy="nome")
	private List<Ingrediente> ingredienti;
	public Piatto(String nome, String descrizione, Buffet buffet) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.buffet = buffet;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Buffet getBuffet() {
		return buffet;
	}
	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}
	
}
