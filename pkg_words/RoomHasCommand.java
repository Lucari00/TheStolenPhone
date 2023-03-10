package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande RoomHasCommand
 *
 * @author Luca
 * @version 24/05
 */
public class RoomHasCommand extends Command
{
    
    /**
     * Constructor for objects of class RoomHasCommand
     */
    public RoomHasCommand()
    {
    }

    /** 
     * Fonction de la commande RoomHasCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord() && pGameEngine.getTestMod()) {
            if (aPlayer.getCurrentRoom().getItem(getSecondWord().toLowerCase()) != null) {
                aGui.println("check");
                return false;
            } else {
                aGui.println("error, this item is not in the room : " + getSecondWord());
                return true;
            }
        } else {
            aGui.println("error, need second word or test mod");
            return true;
        } 
    }
}
