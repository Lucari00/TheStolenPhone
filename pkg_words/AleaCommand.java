package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_rooms.TransporterRoom;

/**
 * Classe de la commande AleaCommand
 *
 * @author Luca
 * @version 24/05
 */
public class AleaCommand extends Command
{
    
    /**
     * Constructor for objects of class AleaCommand
     */
    public AleaCommand()
    {
    }

    /** 
     * Fonction de la commande AleaCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (pGameEngine.getTestMod()) {
            TransporterRoom vRoomT = pGameEngine.getTransR(); 
            if (hasSecondWord()) {
                try {
                    int vNum = Integer.parseInt(getSecondWord());
                    if (vNum > 0 && vNum < pGameEngine.getRoomsSize()) {
                        vRoomT.setAlea(vNum);
                        aGui.println("Succesful.");
                    } else {
                        aGui.println("Your number must be between 0 and the number of room (" + pGameEngine.getRoomsSize() + ", not included).");
                    } 
                } catch (final NumberFormatException NFE) {
                    aGui.println("After alea, you need to write a number");
                }
            } else {
                vRoomT.setAlea(-1);
                aGui.println("Succesful.");
            }
        } else {
            aGui.println( "That's cheating !!!!!!!!!!!" );
        }
        return false;
    }
}
