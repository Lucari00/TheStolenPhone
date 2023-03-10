package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_items.Beamer;

/**
 * Classe de la commande ChargeCommand
 *
 * @author Luca
 * @version 24/05
 */
public class ChargeCommand extends Command
{
    
    /**
     * Constructor for objects of class ChargeCommand
     */
    public ChargeCommand()
    {
    }

    /** 
     * Fonction de la commande ChargeCommand
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
            vB.charge(aPlayer.getCurrentRoom());
            aGui.println("You just charge this room, type 'fire' to teleport to it when you want.");
        } else {
            aGui.println("You don't have a beamer in your inventory.");
        }
        return false;
    }
}
