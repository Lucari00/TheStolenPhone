package pkg_characters;

import pkg_rooms.Room;
import java.util.ArrayList;
import pkg_items.Item;

/**
 * Classe d'un character
 *
 * @author Luca
 * @version 24/05/2022
 */
public class MovingCharacter extends Character
{
    private ArrayList<Room> aRooms;
    private int aRoomComp;
    
    /**
     * Constructor for objects of class MovingCharacter
     * @param pName String
     * @param pCurrentRoom Room
     * @param pNeedItem Item
     * @param pGiveItem Item
     */
    public MovingCharacter(final String pName, final Room pCurrentRoom, final Item pNeedItem, final Item pGiveItem, final String pSkin) {
        super(pName, pCurrentRoom, pNeedItem, pGiveItem, pSkin);
        this.aRooms = new ArrayList<Room>();
    }
    
    /**
     * Ajout d'une room à la liste des rooms où le pnj se déplace
     * @param pRoom Room
     */
    public void addRoom(final Room pRoom) {
        this.aRooms.add(pRoom);
    }
    
    /**
     * Méthode qui fait avancer le pnj dans la pièce suivante
     */
    public void changeRoom() {
        this.aRoomComp++;
        
        if (this.aRoomComp == this.aRooms.size()) {
            this.aRoomComp = 0;
        }
        
        this.getCurrentRoom().setCharacter(null);
        this.setRoom(this.aRooms.get(this.aRoomComp));
        this.getCurrentRoom().setCharacter(this);
    }
}
