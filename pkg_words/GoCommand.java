package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import pkg_rooms.Room;

/**
 * Classe de la commande go
 *
 * @author Luca
 * @version 24/05
 */
public class GoCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public GoCommand()
    {
    }

    /** 
     * Fonction de la commande PlayerHasNotCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine)
    {
        //pas de second mot
        if (!hasSecondWord()) {
            aGui.println("Go where ?");
            return false;
        }
        if (aPlayer.getCurrentRoom().isTransporter()) {
            aPlayer.emptyLastRooms();
        }
        //next Room ?
        Room vNextRoom = aPlayer.goRoom(this.getSecondWord()); 
        
        if (vNextRoom == Room.UNKNOWN_DIRECTION) {
            aGui.println( "Unknown direction !" );
            return false;
        } else if (vNextRoom == null) {
            aGui.println( "There is no door !" );
            return false;
        } else if (vNextRoom == Room.NO_MOVE_LEFT) {
            aGui.println("You are too tired, you can't move anymore.");
            aGui.println("Game over");
            aGui.enable( false );
            if ( vNextRoom.getImageName() != null )
                aGui.showImage( vNextRoom.getImageName() );
            pGameEngine.playSound("GameOver");
            return false;
        } else if (vNextRoom == Room.LOCKED_DOOR) {
            aGui.println( "The door is locked !" );
            return false;
        } 
        
        //move pnj
        pGameEngine.moveCharacter();
        //affiche la description et les directions possibles
        pGameEngine.showInterface(vNextRoom);
        return false;
    }
}
