package pkg_rooms;

import java.util.ArrayList;

/**
 * Classe de Transporter Room
 *
 * @author Luca
 * @version 24/05/2022
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRoomR;
    
    /**
     * Constructeur d'une room
     * @param pDescription String
     * @param pImage String
     * @param pRooms ArrayList de Room
     */
    public TransporterRoom(final String pDescription, final String pImage, final ArrayList<Room> pRooms) {
        super(pDescription, pImage);
        this.aRoomR = new RoomRandomizer(pRooms); 
    }

    /**
     * renvoie la sortie qui est donnée par RoomRandomizer
     * @param pDirection String
     * @return Room
     */
    @Override
    public Room getExit(final String pDirection) {
        return this.aRoomR.findRandomRoom();
    }
    
    /**
     * renvoie si la room est un transporter 
     * @return boolean vrai
     */
    @Override
    public boolean isTransporter() {
        return true;
    }
    
    /**
     * met l'alea à une salle précise
     * @param pNum int de l'alea donc de la room de l'ArrayList
     */
    public void setAlea(final int pNum) {
        this.aRoomR.setAlea(pNum);
    }
}
