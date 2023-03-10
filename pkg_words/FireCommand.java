package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_items.Beamer;

/**
 * Classe de la commande FireCommand
 *
 * @author Luca
 * @version 24/05
 */
public class FireCommand extends Command
{
    
    /**
     * Constructor for objects of class FireCommand
     */
    public FireCommand()
    {
    }

    /** 
     * Fonction de la commande FireCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            aGui.println("I don't know how to charge something in particular.");
            return false;
        }
        
        Beamer vB = (Beamer) aPlayer.getItem("beamer");
        if (vB != null) {
            if (vB.isCharged()) {
                aPlayer.setRoom(vB.getChargedRoom());
                pGameEngine.showInterface(vB.getChargedRoom());
                aPlayer.removeItemPlayer("beamer");
                aPlayer.emptyLastRooms();
                aGui.println("You just teleport to the room charged !");
                pGameEngine.showInventoryGUI();
            } else {
                aGui.println("You need to charge your beamer before using it.");
            }    
        } else {
            aGui.println("You don't have a beamer in your inventory.");
        }
        return false;
    }
}
