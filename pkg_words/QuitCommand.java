package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande QuitCommand
 *
 * @author Luca
 * @version 24/05
 */
public class QuitCommand extends Command
{
    
    /**
     * Constructor for objects of class QuitCommand
     */
    public QuitCommand()
    {
    }

    /** 
     * Fonction de la commande QuitCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (this.hasSecondWord()) {
            aGui.println( "Quit what?" );
            return false;
        } else {
            return true;
        }
    }
}
