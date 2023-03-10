package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande LookCommand
 *
 * @author Luca
 * @version 24/05
 */
public class LookCommand extends Command
{
    
    /**
     * Constructor for objects of class LookCommand
     */
    public LookCommand()
    {
    }

    /** 
     * Fonction de la commande LookCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            if (aPlayer.getCurrentRoom().getItem(getSecondWord().toLowerCase()) != null) {
                aGui.println(aPlayer.getCurrentRoom().getItem(getSecondWord().toLowerCase()).getLongDescription());
            } else {
                aGui.println("This item is not in the room.");
            }
        } else {
            pGameEngine.moveCharacter();
            pGameEngine.showInterface(aPlayer.getCurrentRoom());
        }
        
        
        return false;
    }
}
