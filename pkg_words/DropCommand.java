package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_engine.TimeFinish;
import pkg_rooms.Room;


/**
 * Classe de la commande DropCommand
 *
 * @author Luca
 * @version 24/05
 */
public class DropCommand extends Command
{
    
    /**
     * Constructor for objects of class DropCommand
     */
    public DropCommand()
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
            String vItemName = aPlayer.drop(getSecondWord());
            if (vItemName != null) {
                if (vItemName.toLowerCase().equals("phone") && aPlayer.getCurrentRoom().getImageName().toLowerCase().equals("houseinside.jpg")) {
                    aGui.println("Wow you win this game !");
                    aGui.println("BRAVO!");
                    aGui.enable( false );
                    Room vWin = Room.WIN;
                    if ( vWin.getImageName() != null )
                        aGui.showImage( vWin.getImageName() );
                    pGameEngine.playSound("GameWin");
                    TimeFinish vTime = pGameEngine.getTime();
                    vTime.takeTime();
                    aGui.println("Your time : " + vTime.getString());
                    vTime.getDifferenceInTxt();
                } else {
                    aGui.println("You drop " + vItemName + ".");
                    pGameEngine.showInventoryGUI();
                }
            } else {
                aGui.println("You don't have this item in you inventory.");
            }
        } else {
            aGui.println("drop what ?");
        }
        return false;
    }
}
