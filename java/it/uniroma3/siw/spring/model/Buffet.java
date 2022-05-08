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
public class Buffet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	String nome;
	@Column
	String descrizione;
	@OneToMany(mappedBy="nome")
	private List<Chef> chefs;
	public Buffet(String nome, String descrizione) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
	}
	@OneToMany(mappedBy="nome")
	private List<Piatto> piatti;
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
	
}
