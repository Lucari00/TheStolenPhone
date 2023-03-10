package pkg_characters;

import pkg_rooms.Room;
import java.util.ArrayList;
import pkg_items.Item;
import pkg_engine.Quest;

/**
 * Classe d'un character
 *
 * @author Luca
 * @version 24/05/2022
 */
public class Character
{
    private String aName;
    private ArrayList<String> aDialogue;
    private String aDialGive;
    private Room aCurrentRoom;
    private int aCompteur;
    private Item aNeedItem; //Item qu'il veut
    private Item aGiveItem; //Item qu'il donne
    private String aSkin;
    
    /**
     * Constructor for objects of class Character
     * @param pName String
     * @param pCurrentRoom Room
     * @param pNeedItem Item
     * @param pGiveItem Item
     */
    public Character(final String pName, final Room pCurrentRoom, final Item pNeedItem, final Item pGiveItem, final String pSkin) {
        this.aName = pName;
        this.aCurrentRoom = pCurrentRoom;
        this.aDialogue = new ArrayList<String>();
        this.aCompteur = 0;
        this.aNeedItem = pNeedItem;
        this.aGiveItem = pGiveItem;
        this.aSkin = pSkin;
    }
    
    public String getImage() {
        return this.aSkin;
    }
    
    /**
     * Getter du nom du PNJ
     * @return String
     */
    public String getName() {
        return this.aName;
    }
    
    /**
     * Setter de la room actuelle du PNJ
     * @param pRoom Room
     */
    public void setRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }
    
    /**
     * Setter de la ligne de dialogue quand le personnage donne l'objet
     * @param pS String
     */
    public void setDialGive(final String pS) {
        this.aDialGive = pS;
    }
    
    /**
     * Getter de la ligne de dialogue
     * @return String
     */
    public String getDialGive() {
        return this.aDialGive;
    }
    
    /**
     * Ajout d'une ligne de dialogue
     * @param pS String
     */
    public void addDialogue(final String pS) {
        this.aDialogue.add(pS);
    }
    
    /**
     * Getter d'une ligne de dialogue
     * @return String
     */
    public String getDialogue() {
        String vText = this.aName + " : " + this.aDialogue.get(this.aCompteur);
        
        this.aCompteur++;
        
        if (this.aCompteur == this.aDialogue.size()) {
            this.aCompteur = 0;
        }

        return vText;
    }
    
    /**
     * Getter de la room actuelle
     * @return Room
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }
    
    /**
     * Getter de l'item demandé
     * @return Item
     */
    public Item getItemNeed() {
        return this.aNeedItem;
    }
    
    /**
     * Getter de l'item donné
     * @return Item
     */
    public Item getItemGive() {
        return this.aGiveItem;
    }
    
    /**
     * Setter de l'item demandé
     * @param pItem Item
     */
    public void setItemNeed(final Item pItem) {
        this.aNeedItem = pItem;
    }
    
    /**
     * Setter de l'item donné
     * @param pItem Item
     */
    public void setItemGive(final Item pItem) {
        this.aGiveItem = pItem;
    }
    
    /**
     * Clear toutes les lignes de dialogue
     */
    public void clearDialogue() {
        this.aDialogue.clear();
        this.aCompteur = 0;
    }
}
