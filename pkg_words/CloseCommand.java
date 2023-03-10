package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_doors.Door;
import pkg_doors.LockedDoor;

/**
 * Classe de la commande CloseCommand
 *
 * @author Luca
 * @version 24/05
 */
public class CloseCommand extends Command
{
    
    /**
     * Constructor for objects of class CloseCommand
     */
    public CloseCommand()
    {
    }

    /** 
     * Fonction de la commande CloseCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (!hasSecondWord()) {
            aGui.println("I need to know which door to open.");
            return false;
        }
        
        //récupère la salle actuelle, puis récupère les portes, pour récupérer celle de la direction qu'on cherche
        Object vObj = aPlayer.getCurrentRoom().getDoor(getSecondWord().toLowerCase());

        Door vDoor = (Door) vObj;
        if (vDoor == Door.UNKNOWN_DIRECTION_DOOR) {
            aGui.println( "Unknown direction !" );
            return false;
        } else if (vDoor == null) {
            aGui.println( "There is no door !" );
            return false;
        } else if (!vDoor.isLocked()) {
            //si le joueur a la clé sur lui
            try {
                LockedDoor vLockedDoor = (LockedDoor) vObj;
                if (vLockedDoor.getKey() != null) {
                    if (aPlayer.getItem(vLockedDoor.getKey().getName()) == vLockedDoor.getKey()) {
                        vDoor.setLocked(true);
                        aGui.println( "The door has been locked !" );
                    } else {
                        aGui.println( "You need to have the hey to locked the door." );
                    }
                } else {
                    aGui.println( "You don't have the key to lock the door." );
                }
            } catch (ClassCastException ex) {
                aGui.println( "You don't have the key to lock the door." );
            }
            return false;
        } else {
            aGui.println( "The door is already locked !" );
            return false;
        }
    }
}
