package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_items.ItemEatable;

/**
 * Classe de la commande EatCommand
 *
 * @author Luca
 * @version 24/05
 */
public class EatCommand extends Command
{
    
    /**
     * Constructor for objects of class EatCommand
     */
    public EatCommand()
    {
    }

    /** 
     * Fonction de la commande EatCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            if (getSecondWord().toLowerCase().equals("prot")) {
                if (aPlayer.getItem("prot") != null) {
                    aPlayer.doubleMaxWeight(); 
                    aPlayer.removeItemPlayer(getSecondWord());
                    aGui.println("Oh the prot you just eat gave you double max weight !");
                    pGameEngine.showInventoryGUI();
                } else { 
                    aGui.println("You don't have prot in your inventory.");
                }
                
            } else if (aPlayer.getItem(getSecondWord()) != null) {
                if (aPlayer.getItem(getSecondWord()).isEatable()) {
                    ItemEatable vI = (ItemEatable) aPlayer.getItem(getSecondWord());
                    aPlayer.addNumberOfMoveLeft(vI.getNumberOfMoveProvided());
                    aPlayer.removeItemPlayer(getSecondWord());
                    aGui.println("You ate " + vI.getName() + ".");
                    pGameEngine.progressBar();
                    pGameEngine.showInventoryGUI();
                } else {
                    aGui.println("You can't eat this item.");
                }
            } else {
                aGui.println("You don't have this item.");
            }
        } else {
            aGui.println("Eat what ?");
        }
        return false;
    }
}
