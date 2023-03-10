package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande RoomIsCommand
 *
 * @author Luca
 * @version 24/05
 */
public class RoomIsCommand extends Command
{
    
    /**
     * Constructor for objects of class RoomIsCommand
     */
    public RoomIsCommand()
    {
    }

    /** 
     * Fonction de la commande RoomIsCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord() && pGameEngine.getTestMod()) {
            if (getSecondWord().toLowerCase().equals(aPlayer.getCurrentRoom().getImageName().toLowerCase())) {
                aGui.println("check");
                return false;
            } else {
                aGui.println("error, we are not at : " + getSecondWord());
                return true;
            }
        } else {
            aGui.println("error, need second word or test mod");
            return true;
        }
    }
}
