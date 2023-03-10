package pkg_items;

/**
 * Classe qui définie un item
 *
 * @author Luca
 * @version 13/04/22
 */
public class Item
{
    private String aName;
    private String aDescription;
    private int aWeight;
    private int aPrice;
    private boolean isEatable;

    /**
     * Constructor for objects of class Item
     * @param pName String
     * @param pDescription String 
     * @param pWeight int 
     * @param pPrice int
     */
  public Item(final String pName,  final String pDescription, final int pWeight, final int pPrice)
    {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
        this.aPrice = pPrice;
        this.isEatable = false;
    }//Item()
    
    public boolean isEatable() {
        return false;
    }

    /**
     * Envoie la description de l'item
     * @return String nom et la description
     */
    public String getDescription() 
    {
        return this.aName + " : " + this.aDescription;
    }//getDescription()
    
    public String getDescriptionGUI() 
    {
        return "<b>" + this.aName + "</b>" + " : " + this.aDescription;
    }//getDescription()
    
    /**
     * Envoie le poids de l'item
     * @return int Poids
     */
    public int getWeight() {
        return this.aWeight;
    }//getWeight()
    
    /**
     * Envoie le prix de l'item
     * @return int Prix
     */
    public int getPrice() {
        return this.aPrice;
    }
    
    /**
     * Envoie le nom de l'item
     * @return String Nom
     */
    public String getName() {
        return this.aName;
    }//getName()
    
    /**
     * Envoie la description avec le nom, la description, le poids et le prix
     * @return String long description
     */
    public String getLongDescription() {
       return this.aName + " : " + this.aDescription + " His weight : " + this.aWeight + "g. His price : " + this.aPrice + "€.";
    }//getLongDescription()
    
}//Item
