
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 *  
 *  To play this game, create an instance of this class.
 * 
 *  This main class creates the necessary implementation objects and starts the game off.
 * 
 * @author  Michael Kolling and David J. Barnes, Luca
 * @version 2.0 (Jan 2003) DB edited (2019), 23/03/22
 */

public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map. Create the inerface and link to it.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
        this.aEngine.setQuest();
        this.aEngine.showInventoryGUI();
        this.aEngine.setTimeGUI();
    }
    
    public static void main(String args[]) {
        new Game();
    }
}
