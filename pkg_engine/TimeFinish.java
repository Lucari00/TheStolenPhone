package pkg_engine;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Décrivez votre classe TimeFinish ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class TimeFinish
{
    private LocalDateTime aStart;
    private LocalDateTime aFinish;
    private LocalDateTime aInter;

    /**
     * Constructeur d'objets de classe TimeFinish
     */
    public TimeFinish()
    {
         this.aStart = LocalDateTime.now();
    }

    public void takeTime() {
        this.aFinish = LocalDateTime.now();
    }
    
    public void takeTimeInter() {
        this.aInter = LocalDateTime.now();
    }
    
    public void getDifferenceInTxt() {
        long vTimeH = ChronoUnit.HOURS.between(this.aStart, this.aFinish);
        long vTimeM = ChronoUnit.MINUTES.between(this.aStart, this.aFinish);
        long vTimeS = ChronoUnit.SECONDS.between(this.aStart, this.aFinish);
        long vTimeMI = ChronoUnit.MILLIS.between(this.aStart, this.aFinish);
        
        vTimeMI -= 1000*vTimeS;
        vTimeS -= vTimeM*60;
        
        try {
              File vR = new File("time.txt");
              
              String vText = "";
              Scanner myReader = new Scanner(vR);
                  while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    vText += data;
                    vText += "\n";
                  }
              myReader.close();

              FileWriter vF = new FileWriter("time.txt");
              
              vF.write(vText + " : " + vTimeH + "h" + vTimeM + "m" + vTimeS + "s" + vTimeMI + "ms");
              vF.close();
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }
    
    public String getString() {
        long vTimeH = ChronoUnit.HOURS.between(this.aStart, this.aFinish);
        long vTimeM = ChronoUnit.MINUTES.between(this.aStart, this.aFinish);
        long vTimeS = ChronoUnit.SECONDS.between(this.aStart, this.aFinish);
        long vTimeMI = ChronoUnit.MILLIS.between(this.aStart, this.aFinish);
        
        vTimeMI -= 1000*vTimeS;
        vTimeS -= vTimeM*60;
        
        return "" + vTimeH + " hours, " + vTimeM + " minutes, " + vTimeS + " seconds, " + vTimeMI + " milliseconds.";
    }
    
    public String getStringGUI() {
        long vTimeH = ChronoUnit.HOURS.between(this.aStart, this.aInter);
        long vTimeM = ChronoUnit.MINUTES.between(this.aStart, this.aInter);
        long vTimeS = ChronoUnit.SECONDS.between(this.aStart, this.aInter);
        long vTimeMI = ChronoUnit.MILLIS.between(this.aStart, this.aInter);
        
        vTimeMI -= 1000*vTimeS;
        vTimeS -= vTimeM*60;
        
        return "" + vTimeH + "h " + vTimeM + "m " + vTimeS + "s " + vTimeMI + "ms";
    }
}
