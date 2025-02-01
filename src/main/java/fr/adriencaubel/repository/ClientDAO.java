package fr.adriencaubel.repository;

import fr.adriencaubel.entity.Client;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

public class ClientDAO {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

	
    public List<Client> getAllClients() {
    	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();        
    	List<Client> clients = null;
        try {
            TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
            clients = query.getResultList();
        } finally {
            entityManager.close();
        }
        return clients;
    }

	public Client getClientById(int clientId) {
    	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();        
    	Client client = null;
        try {
            client = entityManager.find(Client.class, clientId);
        } finally {
            entityManager.close();
        }
        return client;
    }  
    
    public Client getClientByIdWithDetailsHibernateInitiaze(int clientId) {
    	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();        
    	Client client = null;
    	try {
            client = entityManager.find(Client.class, clientId);
            Hibernate.initialize(client.getCommandes());
            Hibernate.initialize(client.getFavoris());
        } finally {
            entityManager.close();
        }
        return client;
    }
    
    public Client getClientByIdWithDetailsJoinFetch(int clientId) {
    	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();        
    	Client client = null;
    	try {
            TypedQuery<Client> query = entityManager.createQuery(
                    "SELECT c FROM Client c " +
                    "LEFT JOIN FETCH c.commandes " +
                    "LEFT JOIN FETCH c.favoris " +
                    "WHERE c.id = :clientId", 
                    Client.class
                );
            query.setParameter("clientId", clientId);
            client = query.getResultStream().findFirst().orElse(null);
        } finally {
            entityManager.close();
        }
        return client;
    }
    
    public Client getClientWithDetailsEntityGraph(int clientId) {
    	EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();    
    	Client client = null;
        try {
            // Load EntityGraph
            EntityGraph entityGraph = entityManager.getEntityGraph("Client.detail");

            // Query with EntityGraph to fetch related collections
            Map hints = new HashMap<>();
            hints.put("javax.persistence.fetchgraph", entityGraph);
            
            client = entityManager.find(Client.class, clientId, hints);

        } finally {
            entityManager.close();
        }

        return client;
    }
}
