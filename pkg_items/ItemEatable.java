package pkg_items;


/**
 * Décrivez votre classe ItemEatable ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ItemEatable extends Item
{
    private int aNumberOfMoveProvided;

    /**
     * Constructeur d'objets de classe ItemEatable
     */
    public ItemEatable(final String pName,  final String pDescription, final int pWeight, final int pPrice, final int pNumberOfMoveProvided)
    {
        super(pName, pDescription, pWeight, pPrice);
        this.aNumberOfMoveProvided = pNumberOfMoveProvided;
    }
    
    public int getNumberOfMoveProvided() {
        return this.aNumberOfMoveProvided;
    } 
    
    public void setNumberOfMoveProvided(final int pNumberOfMoveProvided) {
        this.aNumberOfMoveProvided = pNumberOfMoveProvided;
    }
    
    @Override
    public boolean isEatable() {
        return true;
    }

}
