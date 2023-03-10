package pkg_words;

import java.util.StringTokenizer;

/**
 * Classe du parser
 * @author Luca
 * @version 24/05/2022
 */

public class Parser 
{

    private CommandWords aCommandWords;  // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     * @param pInputLine String
     * @return Command
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (vWord1 != null) {
            Command vCommand = this.aCommandWords.get(vWord1.toLowerCase());
            if (vCommand != null) {
                vCommand.setSecondWord(vWord2);
            }
            return vCommand;
        }
        
        return null;
    } // getCommand(.)

    /**
     * Returns a String with valid command words.
     * @return String
     */
    public String getCommandString() // was showCommands()
    {
        return this.aCommandWords.getCommandList();
    } // getCommandString()

} // Parser
