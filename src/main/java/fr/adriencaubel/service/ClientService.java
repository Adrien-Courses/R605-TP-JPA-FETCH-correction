package fr.adriencaubel.service;


import java.util.List;

import fr.adriencaubel.entity.Client;
import fr.adriencaubel.entity.Commande;
import fr.adriencaubel.repository.ClientDAO;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
	
    /**
     * Dans une réelle application cette méthode serait appelée par un controlleur (+ un endpoint API)
     * Par exemple GET /clients
     * Ici nous nous contenterons d'un affichage dans la console
     */
    public void getAllClients() {
        List<Client> clients = clientDAO.getAllClients();
        for(Client client : clients) {
        	System.out.println(client.getEmail() + " " + client.getNom());
        }
    }
    
    public void getFicheClient(int clientId) {
    	Client client = clientDAO.getClientWithDetailsEntityGraph(clientId);
    	System.out.println(client.getEmail() + " " + client.getNom());
    	System.out.println(client.getCommandes());
    	System.out.println(client.getFavoris());
    }
    
    public void addCommandeToClient(int clientId, Commande commande) {
        Client client = clientDAO.getClientWithDetailsEntityGraph(clientId); // Attention à récupérer Client et toutes ses associations

        client.addCommande(commande); 
        clientDAO.saveOrUpdateClient(client); // Persist client (cascade saves orders)
    }
}
