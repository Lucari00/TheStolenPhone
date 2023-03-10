package pkg_rooms;

import java.util.HashMap;
import java.util.Set;
import pkg_items.Item;
import pkg_items.ItemList;
import pkg_doors.Door;
import pkg_characters.Character;

/**
 * Classe qui permet de créer et gérer les salles du jeu
 *
 * @author PALAYSI Luca
 * @version 24/05/2022
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private HashMap<String, Door> aDoors;
    public static final Room UNKNOWN_DIRECTION = new Room( "nowhere", "" );
    public static final Room NO_MOVE_LEFT = new Room( "nomove", "GameOver.gif" );
    public static final Room WIN = new Room( "win", "Win.gif" );
    public static final String[] KNOWN_DIRECTION = {"north", "east", "south", "west", "up", "down"};
    public static final Room LOCKED_DOOR = new Room( "lockeddoor", "" );
    private String aImageName;
    private ItemList aItems;
    private Character aCharacter;

    /**
     * Constructeur d'une room
     * @param pDescription String
     * @param pImage String
     */
    public Room(final String pDescription, final String pImage) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aDoors = new HashMap<String, Door>();
        this.aImageName = pImage;
        this.aItems = new ItemList();
        this.aCharacter = null;
    }// Room()
    
    public void setCharacter(final Character pCharacter) {
        this.aCharacter = pCharacter;
    }
    
    public Character getCharacter() {
        return this.aCharacter;
    }
    
    /**
     * Accesseur de la description
     * @return shortDescription
     */
    public String getShortDescription() {
        return this.aDescription;
    }//getDescription()
    
    /**
     * Accesseur de la description avec la sortie
     * @return String longDescription
     */
    public String getLongDescription() {
        return "You are " + this.aDescription + "\n" + this.aItems.getItemString() + this.getStringChar() +"\n" + this.getExitString();
    }//getLongDescription()
    
    /**
     * Accesseur sorties
     * @param  pDirection String de la direction souhaitée
     * @return  Room  La salle de la direction, null s'il n'y pas de salle, unknown direction si la direction est inconnue
     */
    public Room getExit(final String pDirection) {
        for (int i = 0; i < KNOWN_DIRECTION.length; i++) {
            if (KNOWN_DIRECTION[i].equals(pDirection)) {
                if (this.aDoors.get(pDirection) != null) {
                    if (!this.aDoors.get(pDirection).isLocked())
                        return this.aExits.get(pDirection);
                    else 
                        return LOCKED_DOOR;
                }
                    
            }
        }
        
        for (int i = 0; i < KNOWN_DIRECTION.length; i++) {
            if (KNOWN_DIRECTION[i].equals(pDirection)) {
                return null;
            }
        }
        
        return UNKNOWN_DIRECTION;
    }//getExit()
    
    public String getDescription() {
        return this.aDescription;
    }
    
    /**
     * Donne la porte en fonction de la direction
     * @param  pDirection String
     * @return Door la porte
     */
    public Object getDoor(String pDirection) {
        for (int i = 0; i < KNOWN_DIRECTION.length; i++) {
            if (KNOWN_DIRECTION[i].equals(pDirection)) {
                return this.aDoors.get(pDirection);
            }
        }
        
        return Door.UNKNOWN_DIRECTION_DOOR;
    }//getDoor()
    
    /**
     * Met les sorties possibles à une room
     * @param  pDirection String
     * @param pNeighbor Room (dans quelle direction est la salle)
     * @param pDoor Door
     */
    public void setExits(final String pDirection, final Room pNeighbor, final Door pDoor) {
        this.aExits.put(pDirection, pNeighbor);
        this.aDoors.put(pDirection, pDoor); 
    }//setExits()
    
    /**
     * Renvoie un string avec toutes les sorties
     * @return   String de toutes les sorties possibles dans la position du joueur
     */
    public String getExitString() {
        StringBuilder vText = new StringBuilder("Exits:");
        Set<String> keys = this.aExits.keySet();
        for (String vExits : keys) {
            vText.append(" " + vExits);
        }
        return vText.toString();
    }//getExitString()
    
    /**
     * Renvoie le nom de l'image
     * @return String Nom de l'image
     */
    public String getImageName() {
        return this.aImageName;
    }
    
    /**
     * Ajoute un Item à la room
     * @param pItem Item
     */
    public void addItem(final Item pItem) {
        this.aItems.addItem(pItem); //String en lower case comme ça plus facile à check pour le joueur
    }//setItem()
    
    /**
     * Retourne l'item de la room grâce à son nom
     * @param pName de l'item
     * @return Item
     */
    public Item getItem(final String pName) {
        return this.aItems.getItem(pName.toLowerCase());
    }//getItem()
    
    /**
     * Enlève l'item de la room
     * @param pName de l'item
     */
    public void removeItem(final String pName) {
        this.aItems.removeItem(pName.toLowerCase());
    }//removeItem()
    
    /**
     * Donne la direction de la salle donnée en paramètre
     * @param pRoom Room
     * @return String direction
     */
    public String getDirection(final Room pRoom) {
        for (String vExit : this.aExits.keySet()) {
            if (this.aExits.get(vExit) == pRoom) {
                return vExit;
            }
        }
        return null;
    }//getDirection()
    
    /**
     * Getter si la salle est un transporter
     * @return boolean false
     */
    public boolean isTransporter() {
        return false;
    }
    
    public boolean isShop() {
        return false;
    }
    
    public String getStringChar() {
        if (this.aCharacter != null) {
            return "\n" + this.aCharacter.getDialogue();
        } else {
            return "";
        }
    }
    
} // Room
