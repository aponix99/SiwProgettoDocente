package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column (nullable=false)
	String nome;
	@Column (nullable=false)
	String cognome;
	@Column (nullable=false)
	String nazionalità;
	@ManyToOne
	Buffet buffet;
	
	public Chef(String nome, String cognome, String nazionalità) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalità = nazionalità;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalità() {
		return nazionalità;
	}
	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}
	@Override
	public String toString() {
		return "Chef [nome=" + nome + ", cognome=" + cognome + ", nazionalità=" + nazionalità + "]";
	}

	
}
