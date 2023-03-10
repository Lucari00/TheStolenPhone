package pkg_doors;

import pkg_items.Item;


/**
 * Classe des locked doors. Ceux sont les seules portes qui peuvent être
 * ouvertes grâce à une clé
 *
 * @author Luca
 * @version 24/05/2022
 */
public class LockedDoor extends Door
{
    // instance variables - replace the example below with your own
    private Item aKey;

    /**
     * Constructor for objects of class Door
     * @param pIsLocked boolean
     * @param pKey Item
     */
    public LockedDoor(final boolean pIsLocked, final Item pKey) {
        super(pIsLocked);
        this.aKey = pKey;
    }

    /**
     * Accesseur de l'Item demandée pour ouvrir et fermer la porte
     * @return Item 
     */
    public Item getKey() {
        return this.aKey;
    }//getKey()
}
