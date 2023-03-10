package pkg_words;

import java.util.HashMap;
import pkg_words.Command;
import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25 + 24/05/2022
 */
public class CommandWords
{
    // an HashMap that will hold all valid command words
    private HashMap<String, CommandWord> pValidCommands;
    private HashMap<CommandWord, Command> aCommands;

    /** 
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        this.pValidCommands = new HashMap<String, CommandWord>();
        for(CommandWord vCommand : CommandWord.values()) {
            if(vCommand != CommandWord.UNKNOWN) {
                pValidCommands.put(vCommand.getEnWord(), vCommand);
                pValidCommands.put(vCommand.getFrWord(), vCommand);
            } 
        }
        
        CommandWord[] vCom = CommandWord.values();

        this.aCommands = new HashMap<CommandWord, Command>();
        this.aCommands.put(vCom[0], new GoCommand());
        this.aCommands.put(vCom[1], new QuitCommand());
        this.aCommands.put(vCom[2], new HelpCommand(this.getCommandList())); 
        this.aCommands.put(vCom[4], new LookCommand());
        this.aCommands.put(vCom[5], new EatCommand());
        this.aCommands.put(vCom[6], new BackCommand());
        this.aCommands.put(vCom[7], new TestCommand());
        this.aCommands.put(vCom[8], new TakeCommand());
        this.aCommands.put(vCom[9], new DropCommand());
        this.aCommands.put(vCom[10], new InventoryCommand());
        this.aCommands.put(vCom[11], new RoomIsCommand());
        this.aCommands.put(vCom[12], new RoomHasCommand());
        this.aCommands.put(vCom[13], new RoomHasNotCommand());
        this.aCommands.put(vCom[14], new PlayerHasCommand());
        this.aCommands.put(vCom[15], new PlayerHasNotCommand());
        this.aCommands.put(vCom[16], new ChargeCommand());
        this.aCommands.put(vCom[17], new FireCommand());
        this.aCommands.put(vCom[18], new OpenCommand());
        this.aCommands.put(vCom[19], new CloseCommand());
        this.aCommands.put(vCom[20], new AleaCommand());
        this.aCommands.put(vCom[21], new TestModCommand());
        this.aCommands.put(vCom[22], new GiveCommand());
        this.aCommands.put(vCom[23], new FightCommand());
        this.aCommands.put(vCom[24], new BuyCommand());
        this.aCommands.put(vCom[25], new SayCommand());
    } // CommandWords()

    /**
     * Check whether a given String is a valid command word.
     * @param pString String
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        return pValidCommands.containsKey(pString);
    } // isCommand()
    
    public Command get(String pWord) {
        CommandWord vP = this.pValidCommands.get(pWord);
        return (Command)this.aCommands.get(vP);
    }
    
    /**
     * Find the CommandWord associated with a command word.
     * @param pCommandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(final String pCommandWord) {
        CommandWord vCommand = pValidCommands.get(pCommandWord);
        if (vCommand != null) {
            return vCommand;
        } else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * 
     * @return String of all commands
     */
    public String getCommandList() {
        StringBuilder vText = new StringBuilder();
        vText.append(" ");
        for (String vCommand : pValidCommands.keySet()) {
            if (vCommand.equals("roomHas") || vCommand.equals("roomHasNot") || vCommand.equals("playerHas") || vCommand.equals("playerHasNot") || vCommand.equals("roomIs")) {
                continue;
            }
            vText.append(vCommand + " ");
        }
        return vText.toString();
    }
} // CommandWords