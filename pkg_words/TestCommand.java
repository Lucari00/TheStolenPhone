package pkg_words;

import pkg_characters.Player;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe de la commande TestCommand
 *
 * @author Luca
 * @version 24/05
 */
public class TestCommand extends Command
{
    
    /**
     * Constructor for objects of class TestCommand
     */
    public TestCommand()
    {
    }

    /** 
     * Fonction de la commande TestCommand
     * @param aPlayer Player
     * @param aGui UserInterface
     * @param pGameEngine GameEngine
     * 
     * return boolean vrai pour arrêter le jeu, faux pour continuer
     */
    public boolean execute(Player aPlayer, UserInterface aGui, GameEngine pGameEngine) {
        if (hasSecondWord()) {
            try {
                Scanner vSr = new Scanner(new File(getSecondWord() + ".txt"));
                while (vSr.hasNextLine()) {
                    String vLine = vSr.nextLine();
                    if (pGameEngine.processCommand(vLine)) {
                        return false;
                    }
                }
                vSr.close();
                aGui.println("\n\nAll tests successfully passed");
            } 
            catch (final FileNotFoundException pError) {
                aGui.println("File is not found.");
            }
        } else {
            aGui.println("You didn't put the name of the file.");
        }
        return false;
    }
}
