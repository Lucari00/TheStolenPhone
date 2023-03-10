package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande InventoryCommand
 *
 * @author Luca
 * @version 24/05
 */
public class InventoryCommand extends Command
{
    
    /**
     * Constructor for objects of class InventoryCommand
     */
    public InventoryCommand()
    {
    }

    /** 
     * Fonction de la commande InventoryCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (!hasSecondWord()) {
            aGui.println("Your inventory :");
            aGui.println(aPlayer.inventory());
        } else {
            aGui.println("The inventory doesn't take two word.");
        }
        return false;
    }
}
