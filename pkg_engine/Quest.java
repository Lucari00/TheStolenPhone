package pkg_engine;


/**
 * Décrivez votre classe Quest ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Quest
{
    private String aName;
    private String aDescription;
    private boolean aFound;
    private boolean aIsDoing;
    
    /**
     * Constructeur d'objets de classe Quest
     */
    public Quest(final String pName, final String pDescription) 
    {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aFound = false;
    }
    
    public String getName() {
        return this.aName;
    }
    
    public String getDescription() {
        return this.aDescription;
    }
    
    public void setFound() {
        this.aFound = true;
    }
    
    public boolean isFound() {
        return this.aFound;
    }
    
    public void setIsDoing(final boolean pDoing) {
        this.aIsDoing = pDoing;
    }
    
    public boolean getIsDoing() {
        return this.aIsDoing;
    }
    
    public String getLongDescription() {
        return "<b>" + this.aName + "</b> : " + this.aDescription;
    }
}
