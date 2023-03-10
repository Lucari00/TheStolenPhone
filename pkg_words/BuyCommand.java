package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_rooms.Room;
import pkg_items.Item;

/**
 * Classe de la commande DropCommand
 *
 * @author Luca
 * @version 24/05
 */
public class BuyCommand extends Command
{
    
    /**
     * Constructor for objects of class DropCommand
     */
    public BuyCommand()
    {
    }

    /** 
     * Fonction de la commande DropCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            if (aPlayer.getCurrentRoom().isShop()) {
                Item vItem = aPlayer.getCurrentRoom().getItem(getSecondWord());
                if (vItem != null) {
                    if (vItem.getPrice() <= aPlayer.getWallet()) {
                        aPlayer.setWallet(aPlayer.getWallet() - vItem.getPrice());
                        aPlayer.addItem(vItem);
                        aPlayer.getCurrentRoom().removeItem(getSecondWord());
                        aGui.println("You acquired " + vItem.getName().toLowerCase() + ".");
                        aGui.println("You now have " + aPlayer.getWallet() + "€.");
                        pGameEngine.showInventoryGUI();
                    } else {
                        aGui.println("You do not have the money to buy this item.");
                    }
                } else {
                    aGui.println("This item is not in the shop.");
                }
            } else {
                aGui.println("You need to be in a store to buy something.");
            }
        } else {
            aGui.println("Buy what ?");
        }
        return false;
    }
}
