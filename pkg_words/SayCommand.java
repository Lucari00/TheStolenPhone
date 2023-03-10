package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_rooms.Room;
import pkg_rooms.ShopRoom;
import pkg_doors.Door;

/**
 * Classe de la commande FireCommand
 *
 * @author Luca
 * @version 24/05
 */
public class SayCommand extends Command
{
    
    /**
     * Constructor for objects of class FireCommand
     */
    public SayCommand()
    {
    }

    /** 
     * Fonction de la commande FireCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            Room vR = aPlayer.getCurrentRoom();
            if (vR.isShop()) {
                ShopRoom vS = (ShopRoom) vR;
                if (vS.hasPassword() && vS.getPassword().equals(getSecondWord())) {
                    aGui.println("Ava : Oooh I understand, go there, there is Pedro wainting for you.");
                    Room vShop = pGameEngine.getRoom(pGameEngine.getRoomsSize() - 2);
                    Room vUnderShop = pGameEngine.getRoom(pGameEngine.getRoomsSize() - 3);
                    vShop.setExits("down", vUnderShop, new Door(false));
                    pGameEngine.printExits();
                } else {
                    aGui.println("I can't do anything for you, sorry");
                }
            } else {
                aGui.println("You have nothing to say here.");
            }
        } else {
            aGui.println("Say what ?");
        }
        return false;
    }
}
