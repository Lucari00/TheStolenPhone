package pkg_rooms;

import java.util.HashMap;
import pkg_items.Item;
import pkg_items.ItemList;




/**
 * Décrivez votre classe ShopRoom ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ShopRoom extends Room
{
    private ItemList aShopItems;
    private String aPassword;
    private boolean aHasPassword;
    
    public ShopRoom(final String pDescription, final String pImage, final boolean pHasPassword, final String pPassword) {
        super(pDescription, pImage); 
        this.aShopItems = new ItemList();
        this.aPassword = pPassword;
        this.aHasPassword = pHasPassword;
    }
    
    @Override
    public void addItem(final Item pItem) {
         this.aShopItems.addItem(pItem);   
    }
    
    @Override
    public void removeItem(final String pItemName) {
        this.aShopItems.removeItem(pItemName);
    }
    
    @Override
    public String getLongDescription() {
        return "You are " + this.getDescription() + "\n" + this.getItemList() + this.getStringChar() +"\n" + this.getExitString();
    }
    
    public String getItemList() {
         return this.aShopItems.getItemStringShop();  
    }
    
    @Override
    public boolean isShop() {
        return true;
    }
    
    @Override
    public Item getItem(final String pName) {
        return this.aShopItems.getItem(pName);
    }
    
    public String getPassword() {
        return this.aPassword;
    }
    
    public boolean hasPassword() {
        return this.aHasPassword;
    }
}
