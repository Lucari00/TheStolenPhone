package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande PlayerHasCommand
 *
 * @author Luca
 * @version 24/05
 */
public class PlayerHasCommand extends Command
{
    
    /**
     * Constructor for objects of class PlayerHasCommand
     */
    public PlayerHasCommand()
    {
    }

    /** 
     * Fonction de la commande PlayerHasCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord() && pGameEngine.getTestMod()) {
            if (aPlayer.getItem(getSecondWord()) != null) {
                aGui.println("check");
                return false;
            } else {
                aGui.println("error, this item is not in the player inventory : " + getSecondWord());
                return true; 
            }
        } else {
            aGui.println("error, need second word or test mod");
            return true;
        }
    }
}
