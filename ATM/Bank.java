package assignment4;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Client> clients; // client list of the bank
    private String name; // nam of the bank

    /**
     * No parameter Bank constructor
     */
    public Bank() {
        this.clients = new ArrayList<Client>();
        this.name = "Bank of Marist";
    }

    /**
     * Parameter constructor of the Bank class
     * 
     * @param clients is an ArrayList of clients for the bank
     * @param name    is the name of the bank
     */
    public Bank(ArrayList<Client> clients, String name) {
        if (checkClientsIDs(clients)) {
            for (int i = 0; i < clients.size(); i++) {
                this.clients.add(clients.get(i));
            }
        } else {
            System.out.println("There is a problem with the ID numbers being input");
        }
        this.name = name;
    }

    /**
     * Adds a client to the client list of the current bank
     * 
     * @param client is the client that needs to be added to the clientlist
     */
    public void addClient(Client client) {
        this.clients.add(client);
    }

    /**
     * Name setter
     * 
     * @param name sets the current name to this parameter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Clients getter
     * 
     * @return returns the client list of the bank which is of the typ
     *         ArrayList<Client>
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Name getter
     * 
     * @return returns the name of the bank which is a String
     */
    public String getName() {
        return name;
    }

    public boolean checkClientsIDs(ArrayList<Client> c) {
        for (int i = 0; i < c.size(); i++) {
            Client temp = c.get(i);
            for (int j = i + 1; j < c.size(); j++) {
                if (temp.getID() == c.get(j).getID()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method is here to shoe that the interest of each account can be updated
     * whenever necessary but since we are not using the ATM for a significant
     * amount of time we are not calling it but still leaving it here
     */
    public void updateInterest() {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getCheckingAccount() != null) {
                clients.get(i).getCheckingAccount().calculateSimpleInterest();
            }
            if (clients.get(i).getSavingsAccount() != null) {
                clients.get(i).getSavingsAccount().calculateSimpleInterest();
            }
        }
    }

    /**
     * Overridden toString for the Bank class
     */
    public String toString() {
        String returnString = "Bank: " + name + "\nClients: \n";
        for (int i = 0; i < clients.size(); i++) {
            returnString = returnString + "-" + clients.get(i).toString() + "\n";
        }
        return returnString;
    }
}