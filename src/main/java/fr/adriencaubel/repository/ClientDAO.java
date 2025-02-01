package fr.adriencaubel.repository;

import fr.adriencaubel.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClientDAO {

    public List<Client> getAllClients() {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();        
    	List<Client> clients = null;
        try {
            TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
            clients = query.getResultList();
        } finally {
            entityManager.close();
        }
        return clients;
    }
}
