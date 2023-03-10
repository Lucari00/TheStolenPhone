package pkg_rooms;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui donne une room aléatoire.
 *
 * @author Luca
 * @version 24/05/2022
 */
public class RoomRandomizer
{
    private ArrayList<Room> aRooms;
    private Random aRandom;
    private int aAlea;
    
    /**
     * Constructor for objects of class RoomRandomizer
     * @param pRooms ArrayList de Room
     */
    public RoomRandomizer(final ArrayList<Room> pRooms) {
        this.aRooms = pRooms;
        this.aRandom = new Random();
        this.aAlea = -1;
    }

    /**
     * Renvoie une salle pseudorandom
     * @return Room
     */
    public Room findRandomRoom() {
        if (this.aAlea == -1) {
            return this.aRooms.get(aRandom.nextInt(aRooms.size()));
        } else {
            int vA = this.aAlea;
            if (vA != 5) {
              return this.aRooms.get(vA);  
            } else {
                return this.aRooms.get(8);
            }
            
        }
    }
    
    /**
     * Setter alea
     * @param pNum int
     */
    public void setAlea(final int pNum) {
        this.aAlea = pNum;
    }
}
