package fr.adriencaubel.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Ne charge pas automatiquement les commandes
    private List<Commande> commandes = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Ne charge pas automatiquement les favoris
    private List<Article> favoris = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void addCommande(Commande commande) {
		commande.setClient(this);
		this.commandes.add(commande);
	}

	public List<Article> getFavoris() {
		return favoris;
	}   
	
	public void addFavoris(Article favoris) {
		favoris.setClient(this);
		this.favoris.add(favoris);
	}
}
