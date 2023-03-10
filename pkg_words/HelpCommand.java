package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe de la commande HelpCommand
 *
 * @author Luca
 * @version 24/05
 */
public class HelpCommand extends Command
{
    private String aParser;
    
    /**
     * Constructor for objects of class HelpCommand
     * @param pParser String
     */
    public HelpCommand(String pParser)
    {
        this.aParser = pParser;
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
        aGui.println("You are at the airport ready to take your plane,");
        aGui.println("but you just see that you lost your phone ! Go to your home !");
        aGui.println("The bar on the left is your fatigue bar, when it reaches 0, ou are too tired to move.");
        aGui.println("So don't forget to eat ! If you have nothing to eat, take a break to the shop !");
        aGui.println("It means, it is game over.");
        
        aGui.println("Your command words are :" + this.aParser);
        return false;
    }
}
