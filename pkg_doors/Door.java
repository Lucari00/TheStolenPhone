package pkg_doors;


/**
 * Classe d'une porte
 *
 * @author Luca
 * @version 24/05/2022
 */
public class Door
{
    private boolean aIsLocked;
    
    public static final Door UNKNOWN_DIRECTION_DOOR = new Door(false);

    /**
     * Constructor for objects of class Door
     * @param pIsLocked boolean
     */
    public Door(final boolean pIsLocked) {
        this.aIsLocked = pIsLocked;
        
    }//Door()
    
    /**
     * Accesseur de si la porte est fermée ou non
     * @return boolean vrai si fermée, faux si ouvert
     */
    public boolean isLocked() {
        return aIsLocked;
    }//isLocked()
    
    /**
     * Getter pour changer l'état de la porte, ouvert ou fermée
     * @param pC boolean, true fermée, false ouvert
     */
    public void setLocked(final boolean pC) {
        this.aIsLocked = pC;
    }//setLocked()
}
