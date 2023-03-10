package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_characters.Character;
import pkg_characters.MovingCharacter;
import pkg_items.Item;
import pkg_doors.Door;

/**
 * Classe de la commande GiveCommand
 *
 * @author Luca
 * @version 24/05
 */
public class GiveCommand extends Command
{
    
    /**
     * Constructor for objects of class GiveCommand
     */
    public GiveCommand()
    {
    }

    /** 
     * Fonction de la commande GiveCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            aGui.println( "You can't have two or more words" );
            return false;
        }
        
        Character vChar = aPlayer.getCurrentRoom().getCharacter();
        if (vChar != null) {
            Item vCharItem = vChar.getItemNeed();
            if (vCharItem != null) {
                if (aPlayer.getItem(vCharItem.getName()) != null) {
                    aPlayer.removeItemPlayer(vCharItem.getName());
                    aPlayer.addItem(vChar.getItemGive());
                    aGui.println(vChar.getDialGive());
                    
                    if (vCharItem.getName().equals("KeyFBI")) { //si c'est le pnj du fbi
                        Door vDoor = (Door) pGameEngine.getRoom(4).getDoor("up"); //récupère la porte du fbi et la déverouille
                        vDoor.setLocked(false);
                        aGui.println( "The building of FBI is unlocked !" );
                        
                        vChar.clearDialogue();
                        vChar.addDialogue("Thanks you so much for helping me, come to the office when you want !");
                        MovingCharacter vMovChar = (MovingCharacter) vChar;
                        vMovChar.addRoom(pGameEngine.getRoom(5));
                        pGameEngine.showInventoryGUI();
                    }
                    
                    vChar.setItemNeed(null);
                    vChar.setItemGive(null);   
                } else {
                    aGui.println( "You don't have the item needed." );
                }
            } else {
                aGui.println( "There is nothing to give here." );
            }
        } else {
            aGui.println( "There is nothing to give here." );
        }
        return false;
    }
}
