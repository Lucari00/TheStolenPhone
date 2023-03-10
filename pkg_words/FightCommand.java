package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_items.Item;
import pkg_characters.Character;

/**
 * Classe de la commande QuitCommand
 *
 * @author Luca
 * @version 24/05
 */
public class FightCommand extends Command
{
    
    /**
     * Constructor for objects of class QuitCommand
     */
    public FightCommand()
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
            aGui.println( "I don't know fight what ?" );
            return false;
        }
        
        Character vChar = aPlayer.getCurrentRoom().getCharacter();
        if (vChar != null) {
            if (vChar.getName().equals("Oscar")) { 
                if (aPlayer.getItem("Knife") != null) {
                    aGui.println("Oscar : aaaaah you got me, you are too strong with this knife, I let you the phone.");
                    aGui.println("The phone drops on the ground.");
                    aPlayer.getCurrentRoom().addItem(new Item("Phone", "You finally find your phone !", 10, 0));
                    aPlayer.getCurrentRoom().setCharacter(null);
                    pGameEngine.showInterface(aPlayer.getCurrentRoom());
                } else {
                    aGui.println("Oscar : ahahah it was easy ! And don't come back !");
                    aPlayer.removeNumberOfMoveLeft(10);
                    aPlayer.setRoom(pGameEngine.getASpecRoom(0));
                    pGameEngine.showInterface(aPlayer.getCurrentRoom());
                    aGui.println("You find yourself at the Infirmary of the Airport.");
                    aGui.println("Maybe, next time, before going to fight, take a weapon.");
                    
                }
            } else {
                aGui.println("What do you want to fight ??? There is nothing to fight here.");
            }
        } else { 
            aGui.println("What do you want to fight ??? There is nothing to fight here.");
        }
        return false;
    }
}
