package pkg_items;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
 * Gestion des items
 *
 * @author Luca
 * @version 12/04/2022
 */
public class ItemList
{
    private HashMap<String, Item> aItems;

    /**
     * Constructor de ItemList
     */
    public ItemList() {
        this.aItems = new HashMap<String, Item>();
    }//ItemList()
    
    /**
     * Retourne l'item demandé
     * @param pName String de l'item
     * @return Item 
     */
    public Item getItem(final String pName) {
        return this.aItems.get(pName.toLowerCase());
    }//getItem()
    
    /**
     * Retourne si la HashMap est vide ou non
     * @return boolean, vrai si vide, faux si non vide
     */
    public boolean isEmpty() {
        return this.aItems.isEmpty();
    }//isEmpty()
    
    /**
     * Ajoute l'item à la HashMap
     * @param pItem Item
     */
    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName().toLowerCase(), pItem);
    }//addItem()
    
    /**
     * Enlève l'item de la HashMap
     * @param pName String de l'item
     */
    public void removeItem(final String pName) {
        this.aItems.remove(pName.toLowerCase());
    }//removeItem()
    
    /**
     * Envoie la description avec le nom, la description, le poids et le prix
     * @return String
     */
    public String getItemString() {
        StringBuilder vText = new StringBuilder("Items:\n");
        if (!this.aItems.isEmpty()) {
            Iterator<Item> vIt = this.aItems.values().iterator();
            while (vIt.hasNext()) {
                vText.append(vIt.next().getDescription());
                if (!vIt.hasNext()) {
                    break;
                }
                vText.append("\n");
            }
        } else {
            return "No item.";
        }
        
        return vText.toString();
    }//getItemString()
    
    public String getItemStringGUI() {
        StringBuilder vText = new StringBuilder("<u><b>Items</u></b>:<br>");
        if (!this.aItems.isEmpty()) {
            Iterator<Item> vIt = this.aItems.values().iterator();
            while (vIt.hasNext()) {
                vText.append(vIt.next().getDescriptionGUI());
                if (!vIt.hasNext()) {
                    break;
                }
                vText.append("<br>");
            }
        } else {
            return "You have no Item.";
        }
        
        return vText.toString();
    }//getItemString()
    
    public String getItemStringShop() {
        StringBuilder vText = new StringBuilder("Items to sell:\n");
        if (!this.aItems.isEmpty()) {
            Iterator<Item> vIt = this.aItems.values().iterator();
            while (vIt.hasNext()) {
                Item vItem = vIt.next();
                vText.append(vItem.getDescription() + " Price : " + vItem.getPrice() + "€");
                if (!vIt.hasNext()) {
                    break;
                }
                vText.append("\n");
            }
        } else {
            return "No item.";
        }
        
        return vText.toString();
    }
}
