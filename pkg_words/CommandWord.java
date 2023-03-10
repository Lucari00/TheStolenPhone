package pkg_words;

/**
 * Representations for all the valid command words for the game.
 * 
 * @author Luca
 * @version 24/05/2022
 */
public enum CommandWord {
    // A value for each command word, plus one for unrecognised
    // commands.
    GO("go", "aller"), QUIT("quit", "quitter"), HELP("help", "aide"), UNKNOWN("?", "?"),
    LOOK("look", "regarder"), EAT("eat", "manger"), BACK("back", "retour"),
    TEST("test", "test"), TAKE("take", "prendre"), DROP("drop", "jeter"), 
    INVENTORY("inventory", "inventaire"), ROOMIS("roomis", "roomis"), 
    ROOMHAS("roomhas", "roomhas"), ROOMHASNOT("roomhasnot", "roomhasnot"), 
    PLAYERHAS("playerHas", "playerhas"), PLAYERHASNOT("playerhasnot", "playerhasnot"),
    CHARGE("charge", "charger"), FIRE("fire", "tirer"), OPEN("open", "ouvrir"),
    CLOSE("close","fermer"), ALEA("alea", "alea"), TESTMOD("testmod", "testmod"),
    GIVE("give", "donner"), FIGHT("fight", "battre"), BUY("buy", "acheter"), SAY("say", "dire");
    
    private String aEn; //command en anglais
    private String aFr; //command en français
    
    /**
     * Initialise with the corresponding command word and the translate in fr.
     * @param pEn The command string 
     * @param pFr The command string in Fr.
     */
    CommandWord(final String pEn, final String pFr) {
        this.aEn = pEn;
        this.aFr = pFr;
    }//CommandWord()
    
    /**
     * @return The command word as a string in english.
     */
    public String getEnWord()
    {
        return aEn;
    }//getEnWord()
    
    /**
     * @return The command word as a string in french.
     */
    public String getFrWord()
    {
        return aFr;
    }//getFrWord()
}
