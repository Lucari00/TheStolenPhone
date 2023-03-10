package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande go
 *
 * @author Luca
 * @version 24/05
 */
public class TakeCommand extends Command
{
    
    /**
     * Constructor for objects of class GoCommand
     */
    public TakeCommand()
    {
    }

    /** 
     * Fonction de la commande go
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            String vItemName = aPlayer.take(getSecondWord());
            if (vItemName != null && !vItemName.equals("TooMuch")) {
                aGui.println("You took " + vItemName + " !");
                pGameEngine.showInventoryGUI();
            } else if (vItemName != null && vItemName.equals("TooMuch")) {
                aGui.println("You are too heavy, you can't take that item. You can try to drop some items if you want it.");
            } else {
                aGui.println("This item is not in the room.");
            }
        } else {
            aGui.println("take what ?");
        }
        return false;
    }
}
