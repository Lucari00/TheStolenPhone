package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_rooms.Room;

/**
 * Classe de la commande go
 *
 * @author Luca
 * @version 24/05
 */
public class BackCommand extends Command
{
    
    /**
     * Constructor for objects of class BackCommand
     */
    public BackCommand()
    {
    }

    /** 
     * Fonction de la commande BackCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            aGui.println("I don't know how to go back in a particular way.");
            return false;
        } else if (aPlayer.getLastRooms().empty()) {
            aGui.println("You didn't move back further, so you can't go back.");
            return false;
        }
        
        Room vCurrentRoom = aPlayer.back();
        
        if (vCurrentRoom == Room.NO_MOVE_LEFT) {
            aGui.println("You are too tired, you can't move anymore.");
            aGui.println("Game over");
            aGui.enable( false );
        } else if (vCurrentRoom == Room.LOCKED_DOOR) {
            aGui.println( "The door is locked !" );
            return false;
        }
        
        pGameEngine.moveCharacter();
        pGameEngine.showInterface(vCurrentRoom);
        return false;
    }
}
