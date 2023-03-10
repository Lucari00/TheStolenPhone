package pkg_words;

import pkg_characters.Player;
import pkg_words.CommandWord;
import pkg_engine.UserInterface;
import pkg_engine.GameEngine;

/**
 * Classe qui permet de créer une commande
 *
 * @author PALAYSI Luca
 * @version 24/05/2022
 */
public abstract class Command
{
    private CommandWord aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur d'une commande
     */
    public Command() {
        this.aSecondWord = null;
    }//Command()
    
    /**
     * Accesseur du premier mot de la commande passée 
     * @return CommandWord
     */
    public CommandWord getCommandWord() {
        return this.aCommandWord;
    }//getCommandWord()
    
    /**
     * Accesseur du second mot de la commande passée 
     * @return String
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }//getSecondWord()
    
    public void setSecondWord(final String pSecondWord) {
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * Détermine si la commande passée a un second mot
     * @return boolean
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }//hasSecondWord()
    
    /**
     * Détermine si la commande est connue ou non
     * @return boolean
     */
    public boolean isUnknown() {
        return this.aCommandWord == CommandWord.UNKNOWN;
    }//isUnknown()
    
    public abstract boolean execute(Player player, UserInterface aGui, GameEngine pGameEngine);
} // Command
