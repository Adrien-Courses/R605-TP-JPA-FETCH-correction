package fr.adriencaubel;

import fr.adriencaubel.entity.Article;
import fr.adriencaubel.entity.Client;
import fr.adriencaubel.entity.Commande;
import fr.adriencaubel.repository.ClientDAO;
import fr.adriencaubel.service.ClientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

	/**
     * Initialize database with one client, 2 favorite articles, and 3 orders.
     */
    public static void initDatabase() {
        System.out.println("Initializing database...");

    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();  
    	
    	entityManager.getTransaction().begin();
        
        // Create a new client
        Client client = new Client();
        client.setNom("John Doe");
        client.setEmail("johndoe@example.com");

        // Create and add 2 favorite articles directly via Client method
        Article fav1 = new Article();
        fav1.setNom("Laptop");
        fav1.setPrix(1200.00);
        client.addFavoris(fav1); // Using entity method

        Article fav2 = new Article();
        fav2.setNom("Smartphone");
        fav2.setPrix(800.00);
        client.addFavoris(fav2); // Using entity method

        // Create and add 3 orders directly via Client method
        Commande order1 = new Commande();
        order1.setMontant(100.00);
        client.addCommande(order1);

        Commande order2 = new Commande();
        order2.setMontant(250.50);
        client.addCommande(order2); 
        
        Commande order3 = new Commande();
        order3.setMontant(50.75);
        client.addCommande(order3); 

        entityManager.persist(client); // Cascade.ALL
        
    	entityManager.getTransaction().commit();
    	entityManager.close();

        
        System.out.println("Database initialized successfully!");
    }
	
	public static void main(String[] args) {
		//initDatabase(); // Décommenter MAIS NE LANCER QU'UNE SEULE FOIS l'initialisation

		
		//clientService.getAllClients();
	}

}
