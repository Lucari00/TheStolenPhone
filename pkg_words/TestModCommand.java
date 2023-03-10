package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande TestModCommand
 *
 * @author Luca
 * @version 24/05
 */
public class TestModCommand extends Command
{
    
    /**
     * Constructor for objects of class TestModCommand
     */
    public TestModCommand()
    {
    }

    /** 
     * Fonction de la commande TestModCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            if (getSecondWord().equals("vrai")) {
               pGameEngine.setTestMod(true);
               aGui.println("Test mod activated.");
            } else if (getSecondWord().equals("faux")) {
               pGameEngine.setTestMod(false);
               aGui.println("Test mod desactivated.");
            } else {
               aGui.println("Vous devez mettre 'vrai' ou 'faux'.");
            }
        } else {
            aGui.println("Vous devez mettre 'vrai' ou 'faux'."); 
        }
        return false;
    }
}
