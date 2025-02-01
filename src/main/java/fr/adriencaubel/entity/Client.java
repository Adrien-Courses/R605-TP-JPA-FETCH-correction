package fr.adriencaubel.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;

@Entity
@NamedEntityGraph(
name = "Client.detail",
    attributeNodes = {
        @NamedAttributeNode("commandes"),
        @NamedAttributeNode("favoris")
    }
)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Ne charge pas automatiquement les commandes
    private Set<Commande> commandes = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Ne charge pas automatiquement les favoris
    private Set<Article> favoris = new HashSet<>();

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

	public Set<Commande> getCommandes() {
		return commandes;
	}

	public void addCommande(Commande commande) {
		commande.setClient(this);
		this.commandes.add(commande);
	}

	public Set<Article> getFavoris() {
		return favoris;
	}   
	
	public void addFavoris(Article favoris) {
		favoris.setClient(this);
		this.favoris.add(favoris);
	}
}
