package pkg_items;

import pkg_rooms.Room;


/**
 * Classe de l'item beamer
 *
 * @author Luca
 * @version 24/05/2022
 */
public class Beamer extends Item
{
    private Room aChargedRoom;

    /**
     * Constructor for objects of class Beamer
     * @param pName String
     * @param pDescription String
     * @param pWeight int
     * @param pPrice int
     */
    public Beamer(final String pName,final String pDescription, final int pWeight, final int pPrice) {
        super(pName, pDescription, pWeight, pPrice);
        this.aChargedRoom = null;
    }//Beamer()
    
    /**
     * Accesseur si le beamer est chargé
     * @return boolean
     */
    public boolean isCharged() {
        return this.aChargedRoom != null;
    }//isCharged()
    
    /**
     * Commande pour charger le beamer de la salle
     * @param pRoom Room de la salle à charger
     */
    public void charge(final Room pRoom) {
        this.aChargedRoom = pRoom;
    }//charge()
    
    /**
     * Accesseur de la salle chargée
     * @return Room
     */
    public Room getChargedRoom() {
        return this.aChargedRoom;
    }//getChargedRoom()
}
