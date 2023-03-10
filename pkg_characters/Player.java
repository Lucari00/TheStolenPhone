package pkg_characters;

import java.util.Stack;
import pkg_words.Command;
import pkg_rooms.Room;
import pkg_items.Item;
import pkg_items.ItemList;

/**
 * Player class
 *
 * @author Luca
 * @version 24/05/2022
 */
public class Player
{
    private String aPlayerName;
    private Room aCurrentRoom;
    private Stack aLastRooms;
    private ItemList aItems;
    private int aMaxWeight;
    private int aWeight;
    private int aNumberOfMoveLeft;
    private int aWallet;

    /**
     * Constructor for objects of class Player
     * @param pCurrentRoom Room
     */
    public Player(final Room pCurrentRoom)
    {
        this.aPlayerName = "admin";
        this.aCurrentRoom = pCurrentRoom;
        this.aLastRooms = new Stack();
        this.aItems = new ItemList();
        this.aMaxWeight = 10000;
        this.aWeight = 0;
        this.aNumberOfMoveLeft = 50;
        this.aWallet = 100;
    }//Player()

    /**
     * Renvoie la Room actuelle
     * @return Room 
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }//getCurrentRoom()
    
    /**
     * Renvoie les dernières Room
     * @return Stack 
     */
    public Stack getLastRooms() {
        return this.aLastRooms;
    }//getLastRooms()
    
    public int getWallet() {
        return this.aWallet;
    }
    
    public void addWallet(final int pWallet) {
        this.aWallet = this.aWallet + pWallet;
    }
    
    public void setWallet(final int pWallet) {
        this.aWallet = pWallet;
    }
    
    /**
     * Renvoie le nombre de pas restant
     * @return int Nombre
     */
    public int getNumberOfMoveLeft() {
        return this.aNumberOfMoveLeft;
    }//getNumberOfMoveLeft()
    
    public void removeNumberOfMoveLeft(final int pInt) {
        this.aNumberOfMoveLeft -= pInt;
    }
    
    public void addNumberOfMoveLeft(final int pInt) {
        if (this.aNumberOfMoveLeft + pInt >= 50) {
            this.aNumberOfMoveLeft = 50;
        } else {
            this.aNumberOfMoveLeft += pInt;
        }
        
    }
    
    /**
     * Met la salle actuelle par rapport à la salle en paramètre
     * @param pRoom Room
     */
    public void setRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }//setRoom()
    
    /**
     * Vide les salles enregistrées
     */
    public void emptyLastRooms() {
        this.aLastRooms.clear();
    }//emptyLastRooms()
    
    /**
     * Change la Room actuelle en la Room où le joueur veut se diriger
     * @param pDirection Command
     * @return Room nouvelle, null s'il n'y a pas de porte, Room.UNKNOWN_DIRECTION si la direction que le Player veut prendre est inconnu
     */
    public Room goRoom(final String pDirection) {
        Room vNextRoom = aCurrentRoom.getExit(pDirection);
        if (vNextRoom == Room.UNKNOWN_DIRECTION || vNextRoom == null || vNextRoom == Room.LOCKED_DOOR) {
            return vNextRoom; 
        }
        
        if (!hasMoveLeft()) {
            return Room.NO_MOVE_LEFT;
        }
        //ajoute la room à la liste des rooms
        if (!this.aCurrentRoom.isTransporter()) {
            this.aLastRooms.push(this.aCurrentRoom);
        }
        
        //met le joueur dans la nouvelle room
        this.aCurrentRoom = vNextRoom;
        this.aNumberOfMoveLeft--;
        
        return this.aCurrentRoom;
    }//goRoom()
    
    /**
     * Change la Room actuelle en la dernière Room où le Player s'est déplacé
     * @return Room
     */
    public Room back() {
        //recup et suppr le dernier objet du stack qu'on transforme en room car on sait que ça en ait un
        if (!hasMoveLeft()) {
            return Room.NO_MOVE_LEFT;
        } else if (!isOpen(getDirection((Room) this.aLastRooms.lastElement()))) {
            return Room.LOCKED_DOOR;
        } else {
             this.aCurrentRoom = (Room) this.aLastRooms.pop();
             this.aNumberOfMoveLeft--;
             return this.aCurrentRoom;
        }

    }//back()
    
    /**
     * Envoie la direction de la salle
     * @param pRoom Room
     * @return String 
     */
    public String getDirection(final Room pRoom) {
        return this.aCurrentRoom.getDirection(pRoom);
    }//getDirection()
    
    /**
     * Envoie si la direction est ouverte ou non
     * @param pDirection String
     * @return boolean si ouvert true, false si fermé 
     */
    public boolean isOpen(final String pDirection) {
        Room vNextRoom = aCurrentRoom.getExit(pDirection);
        return !(vNextRoom == Room.LOCKED_DOOR);
    }//isOpen()
    
    /**
     * Envoie s'il reste des pas restants
     * @return boolean vrai s'il en reste, faux s'il n'en reste pas 
     */
    private boolean hasMoveLeft() {
        return !(aNumberOfMoveLeft == 0);
    }//hasMoveLeft()
    
    /**
     * Ajoute l'Item, que le Player veut récupérer, à son inventaire ainsi que l'enlever de la Room
     * @param pCommand String
     * @return String 
     */
    public String take(final String pCommand) {
        Item vItem = this.aCurrentRoom.getItem(pCommand.toLowerCase());
        if (vItem != null) {
            if (this.aWeight + vItem.getWeight() <= this.aMaxWeight) {
                this.addItemPlayer(vItem.getName());
                this.aCurrentRoom.removeItem(vItem.getName());
                return vItem.getName().toLowerCase();
            } else {
                return "TooMuch";
            }
        } else {
            return null;
        }
    }//take()
    
    /**
     * Enlève l'Item, que le joueur veut mettre par terre, de son inventaire ainsi que l'ajouter à la Room
     * @param pCommand Command
     * @return String
     */
    public String drop(final String pCommand) {
        if (!this.aItems.isEmpty()) {
            if (this.aItems.getItem(pCommand) != null) {
                Item vItem = this.aItems.getItem(pCommand);
                this.aCurrentRoom.addItem(vItem);
                this.removeItemPlayer(pCommand);
                return pCommand.toLowerCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
        
    }//drop()
    
    /**
     * Ajoute l'Item à son inventaire ainsi qu'ajouter le poids de l'Item au Player
     * @param pName de l'Item
     */
    public void removeItemPlayer(final String pName) {
        Item vItem = this.aItems.getItem(pName);
        this.aItems.removeItem(pName);
        this.aWeight -= vItem.getWeight();
    }//removeItemPlayer()
    
    /**
     * Enlève l'Item de son inventaire ainsi qu'enlever le poids de l'Item au Player
     * @param pName de l'item
     */
    public void addItemPlayer(final String pName) {
        Item vItem = this.aCurrentRoom.getItem(pName);
        this.aItems.addItem(vItem);
        this.aWeight += vItem.getWeight();
    }//addItemPlayer()
    
    public void addItem(final Item pItem) {
        this.aItems.addItem(pItem);
        this.aWeight += pItem.getWeight();
    }
    
    /**
     * Retourne l'Item donné par son nom
     * @param pCommand String
     * @return Item
     */
    public Item getItem(final String pCommand) {
        return this.aItems.getItem(pCommand);
    }//getItem()
    
    /**
     * Retourne la description longue de tous les Item de l'inventaire avec le poids du Player et le poids total que le Player peut avoir
     * @return String
     */
    public String inventory() {
        return this.aItems.getItemString() + "\nYour weight : " + this.aWeight + "/" + this.aMaxWeight + "g." + "\nYour wallet : " + this.aWallet + "€.";
    }//inventory()
    
    public String inventoryGUI() {
        return this.aItems.getItemStringGUI() + "<br><b>Your weight</b> : " + this.aWeight + "/" + this.aMaxWeight + "g." + "<br><b>Your wallet</b> : " + this.aWallet + "€.";
    }
    
    /**
     * Mettre le poids maximal au paramètre donné
     * @param pMaxWeight int
     */
    private void setMaxWeight(final int pMaxWeight) {
        this.aMaxWeight = pMaxWeight;
    }//setMaxWeight()
    
    /**
     * Mettre le poids maximal fois 2
     */
    public void doubleMaxWeight() {
        this.setMaxWeight(this.aMaxWeight*2);
    }//doubleMaxWeight()
}
