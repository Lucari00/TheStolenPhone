package pkg_engine;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import pkg_words.Command;
import pkg_words.Parser;
import pkg_rooms.Room;
import pkg_rooms.TransporterRoom;
import pkg_rooms.ShopRoom;
import pkg_items.Item;
import pkg_items.ItemEatable;
import pkg_items.Beamer;
import pkg_doors.Door;
import pkg_doors.LockedDoor;
import pkg_characters.Player;
import pkg_characters.Character;
import pkg_characters.MovingCharacter;
import pkg_engine.Sound;

/**
 * Classe qui permet de gérer le jeu
 *
 * @author PALAYSI Luca
 * @version 24/05/2022
 */
public class GameEngine
{
    private Parser aParser; // permet de recup command
    private ArrayList<Room> aRooms; //permet de stocker les rooms et d'y avoir accès
    private ArrayList<MovingCharacter> aCharacters; // permet de stocker tous les characters et les faire bouger tous
    private UserInterface aGui;
    private Player aPlayer;
    private boolean aTestMod;
    private ArrayList<Room> aAllRooms;
    private HashMap<String, Quest> aQuests;
    private Sound aSound;
    private TimeFinish aTime;
    
    /**
     * Constructeur par défaut
     */
    public GameEngine() {
        this.aRooms = new ArrayList<Room>();// for Transporter Room
        this.aAllRooms = new ArrayList<Room>();// for needed room
        this.aCharacters = new ArrayList<MovingCharacter>();
        this.aQuests = new HashMap<String, Quest>();
        this.createRooms();
        this.aParser = new Parser();
        this.aTestMod = false;
        this.aSound = new Sound();
    }//GameEngine()
    
    /**
     * Crée le GUI
     * @param pUserInterface Classe qui gère l'interface.
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }//setGUI()
    
    public Room getASpecRoom(final int pNum) {
        return this.aAllRooms.get(pNum);
    }
    
    /**
     * Getter du mode de test
     * @return boolean vrai si mode de test, faux si en mode normal
     */
    public boolean getTestMod() {
        return this.aTestMod;
    }
    
    /**
     * Setter du mode de test
     * @param pB boolean vrai si mode de test, faux si en mode normal
     */
    public void setTestMod(final boolean pB) {
        this.aTestMod = pB;
    }
    
    /**
     * Getter de la transporter room (dernière de la liste)
     * @return TransporterRoom
     */
    public TransporterRoom getTransR() {
        return (TransporterRoom) this.aRooms.get(this.aRooms.size()-1);
    } 
    
    /**
     * Getter de la taille de la liste des rooms
     * @return int
     */
    public int getRoomsSize() {
        return this.aRooms.size();
    }
    
    /**
     * Getter de la room en fonction de son positionnement dans la liste
     * @param pNum int
     * @return Room
     */
    public Room getRoom(final int pNum) {
        return this.aRooms.get(pNum);
    }
    
    /**
     * Crée toutes les rooms et place le joueur au point de départ
     */
    private void createRooms() {
        
        //Création des rooms America
        Room vAirportA = new Room("at the airport of America. To go to England, \nyou need to have a ticket and 'open' by south.", "AirportAmerica.jpg");
        this.aRooms.add(vAirportA);
        Room vBoulevardA = new Room("in a boulevard with a lot of people.", "BoulevardAmerica.jpg");
        this.aRooms.add(vBoulevardA);
        Room vAlley = new Room("in the alley close to your house.", "AlleyAmerica.jpg");
        this.aRooms.add(vAlley);
        Room vHouse = new Room("in front of your house.", "HouseAmerica.jpg");
        this.aRooms.add(vHouse);
        Room vHouseInside = new Room("in your house.", "HouseInside.jpg");
        Room vFBI = new Room("in front of the building of the FBI.", "FBIoutside.jpg");
        this.aRooms.add(vFBI);
        Room vFBIUp = new Room("in the office of the FBI.", "FBIinside.jpg");
        this.aRooms.add(vFBIUp);
        this.aRooms.add(vHouseInside);
        Room vTrapped = new Room("in small alley. Weird, they are some people \nblocking you from going back.", "SmallAlley.jpg");
        TransporterRoom vTrans = new TransporterRoom("in a labo with computers everywhere", "Labo.jpg", this.aRooms);
        ShopRoom vShop = new ShopRoom("in the shop !", "shop.jpg", true, "sun");
        ShopRoom vUnderShop = new ShopRoom("in the secret shop.", "UnderShop.jpg", false, "");
        this.aRooms.add(vUnderShop); //A AJOUTER EN AVANT AVANT DERNIER
        this.aRooms.add(vShop); //A AJOUTER EN AVANT DERNIER
        this.aRooms.add(vTrans); //A AJOUTER EN DERNIER
        
        //CREATION ENGLISH
        
        Room vAirportE = new Room("at the airport of London. To go to America, \nyou need to have a ticket and 'open' by north.", "AirportEnglish.jpg");
        this.aAllRooms.add(vAirportE);
        
        Room vStreetL = new Room("in the street in London.", "StreetLondon.jpg");
        
        Room vBigBen = new Room("in front of Big Ben.", "BigBen.jpg");
        
        Room vBigWheel = new Room("below the big wheel", "BigWheel.jpg");
        
        //Item
        ItemEatable vSandwich = new ItemEatable("Sandwich","A Sandwich with chicken and curry.", 200, 5, 3);
        ItemEatable vPizza = new ItemEatable("Pizza","A basic margarita", 250, 12, 5);
        ItemEatable vSushi = new ItemEatable("Sushis","Some beautiful sushis", 100, 8, 4);
        ItemEatable vBurger = new ItemEatable("Burger","A real american burger", 124, 15, 7);
        ItemEatable vChips = new ItemEatable("Crisps","A little packet of crisps", 50, 2, 2);
        
        Item vKeyFBI = new Item("KeyFBI", "Key of the building of FBI.", 20, 0);
        Item vProt = new Item("Prot", "A prot that seems special because it is shining.", 50, 15000);
        Item vKeyHouse = new Item("KeyHouse", "The key of your house.", 20, 0);
        Item vTicket = new Item("Ticket","Ticket to do unlimited round trip between <br>\nAmerica and England.", 5, 1000);
        Item vBeamer = new Beamer("Beamer", "Object which you can remember a room and teleport to it when you want.", 1500, 5000);
        Item vKnife = new Item("Knife", "A knife can intidimate people.", 300, 75);
        Item vWeapon = new Item("Weapon", "A nice way to intidimate people.", 2000, 8500);
        Item vShotgun = new Item("Shotgun", "An other nice way to intidimate people.", 3500, 15000);
        Item vSniper = new Item("Sniper", "You can be arrest by the cops if you have it.", 7000, 50000);
        Item vBazooka = new Item("Bazooka", "You will be arrest by the police.", 15000, 500000);
        
        //met les lieux en relation
        Door vDoorAirport = new LockedDoor(true, vTicket);
        vAirportA.setExits("north", vBoulevardA, new Door(false)); //faut relier à england
        vAirportA.setExits("south", vAirportE, vDoorAirport);
        
        vBoulevardA.setExits("north", vAlley, new Door(false)); 
        vBoulevardA.setExits("south", vAirportA, new Door(false));
        vBoulevardA.setExits("west", vFBI, new Door(false));
        vBoulevardA.setExits("east", vShop, new Door(false));
        
        Door vDoorHouse = new LockedDoor(true, vKeyHouse);
        vAlley.setExits("north", vHouse, new Door(false));
        vAlley.setExits("south", vBoulevardA, new Door(false));
        vAlley.setExits("east", vTrapped, new Door(false));
        vAlley.addItem(vKeyFBI);

        vHouse.setExits("south", vAlley, new Door(false));
        vHouse.setExits("north", vHouseInside, vDoorHouse);
        
        vHouseInside.setExits("south", vHouse, vDoorHouse);
        vHouseInside.addItem(vBeamer);
        
        Door doorFBI = new LockedDoor(true, vKeyFBI);
        vFBI.setExits("east", vBoulevardA, new Door(false));
        vFBI.setExits("up", vFBIUp, doorFBI);
        
        vFBIUp.setExits("down", vFBI, doorFBI);
        vFBIUp.addItem(vTicket);
        
        vTrapped.setExits("east", vTrans, new Door(false));
        vTrapped.setExits("west", vAlley, new Door(true));
        
        vTrans.setExits("north", null, new Door(false));
        vTrans.setExits("east", null, new Door(false));
        vTrans.setExits("south", null, new Door(false));
        vTrans.setExits("west", null, new Door(false));
        vTrans.setExits("up", null, new Door(false));
        vTrans.setExits("down", null, new Door(false));
        
        vAirportE.setExits("north", vAirportA, vDoorAirport);
        vAirportE.setExits("east", vStreetL, new Door(false));
        
        vStreetL.setExits("west", vAirportE, new Door(false));
        vStreetL.setExits("south", vBigBen, new Door(false));
        
        vBigBen.setExits("north", vStreetL, new Door(false));
        vBigBen.setExits("west", vBigWheel, new Door(false));
        
        vBigWheel.setExits("east", vBigBen, new Door(false));
        
        vShop.setExits("west", vBoulevardA, new Door(false));
        
        vUnderShop.setExits("up", vShop, new Door(false));
        
        //ajout des objets dans les boutiques
        vShop.addItem(vSandwich);
        vShop.addItem(vPizza);
        vShop.addItem(vSushi);
        vShop.addItem(vBurger);
        vShop.addItem(vChips);
        
        vUnderShop.addItem(vKnife);
        vUnderShop.addItem(vWeapon);
        vUnderShop.addItem(vShotgun);
        vUnderShop.addItem(vSniper);
        vUnderShop.addItem(vBazooka);
        
        //ajout des PNJ
        Character vTrapper = new Character("José", vAlley, null, null, "Jose.png");
        vAlley.setCharacter(vTrapper);
        vTrapper.addDialogue("Psss, you, yeah you, I think that if you go on your east, \nthere will be a surprise for you.");
        vTrapper.addDialogue("GO ON YOUR EAST NOW.");
        vTrapper.addDialogue("I SAID GO ON YOUR EAST.");
        vTrapper.addDialogue("The east is really cool.");
        
        Character vNeigh = new Character("Andre", vHouse, null, null, "Andre.png");
        vHouse.setCharacter(vNeigh);
        vNeigh.addDialogue("Hey ! I heard that something is happening in England.");
        vNeigh.addDialogue("I think that is about your music");
        
        MovingCharacter vFbiGuy = new MovingCharacter("Alex", vFBI, vKeyFBI, vProt, "Alex.png"); 
        vFBI.setCharacter(vFbiGuy);
        vFbiGuy.addRoom(vFBI);
        vFbiGuy.addRoom(vBoulevardA);
        vFbiGuy.addDialogue("OH NO ! I lost my key to enter in the FBI building.");
        vFbiGuy.addDialogue("If you find them, I would be so happy ! \nI will give you a gift.");
        vFbiGuy.addDialogue("There is a label on them : 'keyFBI'.");
        vFbiGuy.setDialGive("Thanks so much ! Take this, don't worry ! Eat it !");
        this.aCharacters.add(vFbiGuy);
        
        Character vAero = new Character("Pierre", vAirportE, null, null, "Pierre.png");
        vAirportE.setCharacter(vAero);
        vAero.addDialogue("Woow, did you hear guys ? There is a sell \nof a famous phone close the big wheel !");
        vAero.addDialogue("I think that phone can worth millions !");
        vAero.addDialogue("I heard that the phone was to someone famous !");
        vAero.addDialogue("It has some exclusive music on it !");
        
        Character vStreet = new Character("Marc", vStreetL, null, null, "Marc.png");
        vStreetL.setCharacter(vStreet);
        vStreet.addDialogue("The sell is close Big Ben !");
        vStreet.addDialogue("Go fast before it is sold !");
        
        Character vBadChar = new Character("Oscar", vBigWheel, null, null, "Oscar.png");
        vBigWheel.setCharacter(vBadChar);
        vBadChar.addDialogue("Ooooh, I know you, the phone was yours... no ? \nCome 'fight' to get it !");
        
        Character vShopGuy = new Character("Ava", vShop, null, null, "Ava.png");
        vShop.setCharacter(vShopGuy);
        vShopGuy.addDialogue("Hey, what can I do for you ?");
        
        Character vUnderShopGuy = new Character("Pedro", vUnderShop, null, null, "Pedro.png");
        vUnderShop.setCharacter(vUnderShopGuy);
        vUnderShopGuy.addDialogue("You find me ! All in here is to sell !");
        
        Character vSecretTeller = new Character("Roger", vBigBen, null, null, "Roger.png");
        vBigBen.setCharacter(vSecretTeller);
        vSecretTeller.addDialogue("I heard that if you say 'sun' at the shop, \nsomething happen.");
        vSecretTeller.addDialogue("Try to say 'sun' at the shop to see.");
        vSecretTeller.addDialogue("They love the 'sun' at the shop, you will see.");
        
        //crée les quests
        Quest vIntroQuest = new Quest("Introduction", "Go to your house !");
        vIntroQuest.setIsDoing(true);
        this.aQuests.put("Introduction", vIntroQuest);
        
        Quest vKeyFBIQuestp1 = new Quest("Key of FBI part 1", "Find the key of <br>the office of the FBI.");
        this.aQuests.put("Key of FBI part 1", vKeyFBIQuestp1);
        
        Quest vKeyFBIQuestp2 = new Quest("Key of FBI part 2", "Give the key of <br>the FBI to Alex.");
        this.aQuests.put("Key of FBI part 2", vKeyFBIQuestp2);
        
        Quest vFindFBI = new Quest("Find the FBI", "Find a way to <br>enter in the FBI's offices.");
        this.aQuests.put("Find the FBI", vFindFBI);
        
        Quest vGoToEngland = new Quest("Go to England", "Go to the airport <br>and go to England !<br>(open south)");
        this.aQuests.put("Go to England", vGoToEngland);
        
        Quest vLearnMore = new Quest("Learn about the sell", "<br>Navigate through the city to <br>learn about the sell.");
        this.aQuests.put("Learn about the sell", vLearnMore);
        
        Quest vTryToFight = new Quest("Try to fight the seller", "<br>Go to the big wheel and<br>try to fight the seller. (fight)");
        this.aQuests.put("Try to fight the seller", vTryToFight);
        
        Quest vIntidimate = new Quest("Intidimate the seller", "<br>Find a way to intidimate the seller,<br>maybe with a knife or other...");
        this.aQuests.put("Intidimate the seller", vIntidimate);
        
        Quest vFight = new Quest("Fight again the seller", "<br>Now that you have a weapon,<br>you can intidimate him.");
        this.aQuests.put("Fight again the seller", vFight);
        
        Quest vTakePhone = new Quest("Take the phone", "The phone is on the gound !");
        this.aQuests.put("Take the phone", vTakePhone);
        
        Quest vWin = new Quest("Drop your phone in your house", "<br>You have take back your phone ! <br>Go put it in safe play at your home.");
        this.aQuests.put("Drop your phone in your house", vWin);
        
        //start la clock
        this.aTime = new TimeFinish();
        
        //place le joueur au début
        this.aPlayer = new Player(vAirportA); //crée player + donne la room de départ
        this.aPlayer.addItem(vKeyHouse);
    }//createRooms()
    
    public TimeFinish getTime() {
        return this.aTime;
    }
    
    public void setQuest() {
        this.aGui.setQuests(this.textQuest());
    }
    
    /**
     * Récupère le nombre de mouvements restants au joueur
     * @return int le nombre restant
     */
    public int getNumberOfMoveLeft() {
        return this.aPlayer.getNumberOfMoveLeft();
    }//getNumberOfMoveLeft()
    
    /**
     * méthode qui call la méthode qui bouge les MovingCharacter
     */
    public void moveCharacter() {
        for (MovingCharacter vChar : this.aCharacters) {
            vChar.changeRoom();
        }
    }
    
    public void progressBar() {
        setProgressBar(this.aPlayer.getNumberOfMoveLeft());
    }
    
    public void showInventoryGUI() {
        this.aGui.setInventory(this.textInventory());
    }
    
    public String textInventory() {
        return this.aPlayer.inventoryGUI();
    }
    
    /**
     * Met en place l'interface graphique du joueur 
     * @param  pRoom Room de la salle qu'il faut afficher
     */
    public void showInterface(final Room pRoom) {
        this.printLocationInfo();
        setProgressBar(this.aPlayer.getNumberOfMoveLeft());
        if ( pRoom.getImageName() != null )
                this.aGui.showImage( pRoom.getImageName() );
        if (pRoom.getCharacter() != null) {
                this.aGui.showSkin(pRoom.getCharacter().getImage());
        } else {
            this.aGui.showSkin(null);
        }
    }//showInterface()
    
    /**
     * Envoie le message d'accueil
     */
    private void printWelcome() {
        //message d'accueil
        this.aGui.println("Welcome to the Stolen Phone and to the United-States also !");
        this.aGui.println("You will live an incredible adventure ! But before that go back to your home.");
        this.aGui.println("The bar on the left is your fatigue bar, when it reaches 0, ou are too tired to move.");
        this.aGui.println("Type 'help' if you need help.");
        this.aGui.println("You have the key of your house on you.");
        this.aGui.println("To win, you need to drop your phone in your house.");
        this.aGui.println("\n");//saut de ligne
        
        //message descp et sorties
        this.printLocationInfo();
    }//printWelcome()

    /**
     * Envoie la localisation du joueur et les différentes sorties possibles
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        //ICI
    }//printLocationInfo()
    
    public void printExits() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getExitString());
    }
    
    /**
     * Détermine la commande passée et l'exécute
     * @param  pCommandLine String général
     * @return    True pour quitter le jeu, false si la Commande est pour autre chose
     */
    public boolean processCommand(final String pCommandLine) {
        this.aGui.println( "\n> " + pCommandLine );
        Command vCommand = this.aParser.getCommand(pCommandLine); 
        
        if (vCommand == null) {
            this.aGui.println("I don't know what you mean...");
            return false;
        } else {
            if (vCommand.execute(this.aPlayer, this.aGui, this)) {
                this.endGame();
                return true;
            } else {
                this.checkQuest();
                this.setTimeGUI();
                return false;
            }
        }
    }// processCommand()
    
    public void setTimeGUI() {
        this.aTime.takeTimeInter();
        this.aGui.setTime("<u><b>Your time</b></u> : <br>" + this.aTime.getStringGUI());
    }
    
    /**
     * Change la barre de progression en fonction du nombre de pas restant
     * @param  pNum int le nombre de pas
     */
    public void setProgressBar(int pNum) {
        this.aGui.setProgressBar(pNum);
    }//setProgressBar()
    
    /**
     * Ferme le jeu
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }//endGame()
    
    public String textQuest() {
        String vText = "<u><b>Quests</u></b> : <br>";
        for (Quest vQ : this.aQuests.values()) {
            if (vQ.getIsDoing()) {
                vText += vQ.getLongDescription();
                vText += "<br>";
            }
        }
        return vText;
    }
    
    public void playSound(final String pName) {
        this.aSound.play(pName);
    }
    
    public void checkQuest() {
        ArrayList<String> vQuestNames = new ArrayList<String>();

        for (Quest vQ : this.aQuests.values()) {
            if (vQ.getIsDoing()) {
                vQuestNames.add(vQ.getName());
            }
        }
        
        for (String vNames : vQuestNames) {
             switch(vNames) {
                 case "Introduction":
                     Quest vQuest = this.aQuests.get("Introduction");
                     this.Introduction(vQuest);
                     break;
                 case "Key of FBI part 1":
                     vQuest = this.aQuests.get("Key of FBI part 1");
                     this.KeyFBIp1(vQuest);
                     break;
                 case "Key of FBI part 2":
                     vQuest = this.aQuests.get("Key of FBI part 2");
                     this.KeyFBIp2(vQuest);
                     break;
                 case "Find the FBI":
                     vQuest = this.aQuests.get("Find the FBI");
                     this.FindFBI(vQuest);
                     break;
                 case "Go to England":
                     vQuest = this.aQuests.get("Go to England");
                     this.GoToEngland(vQuest);
                     break;
                 case "Learn about the sell":
                     vQuest = this.aQuests.get("Learn about the sell");
                     this.LearnMore(vQuest);
                     break;
                 case "Try to fight the seller":
                     vQuest = this.aQuests.get("Try to fight the seller");
                     this.TryToFight(vQuest);
                     break;
                 case "Intidimate the seller":
                     vQuest = this.aQuests.get("Intidimate the seller");
                     this.Intidimate(vQuest);
                     break;
                 case "Fight again the seller":
                     vQuest = this.aQuests.get("Fight again the seller");
                     this.FightAgain(vQuest);
                     break;
                 case "Take the phone":
                     vQuest = this.aQuests.get("Take the phone");
                     this.TakePhone(vQuest);
                     break;
                 case "Drop your phone in your house":
                     vQuest = this.aQuests.get("Drop your phone in your house");
                     this.WinQuest(vQuest);
                     break;
             }
        }
        
        Quest vK = this.aQuests.get("Key of FBI part 1");
        if (!vK.getIsDoing() && !vK.isFound()) {
            if (this.aPlayer.getCurrentRoom().getImageName().equals("BoulevardAmerica.jpg")) {
                vK.setFound();
                vK.setIsDoing(true);
                this.aGui.println("You have a new quest !");
            }
        }
        
        this.aGui.setQuests(this.textQuest());
    }
    
    public void printQuestInfo(final String pNameQuest) {
        Quest vK = this.aQuests.get(pNameQuest);
        vK.setIsDoing(true);
        this.aPlayer.addWallet(50);
        this.aGui.println("You have a new quest !");
    }
    
    public void Introduction(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getImageName().equals("HouseInside.jpg")) {
             this.aGui.println("You finished the quest 'Introduction' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Find the FBI");
        }
    }
    
    public void KeyFBIp1(final Quest pQuest) {
        if (this.aPlayer.getItem("keyfbi") != null) {
             this.aGui.println("You finished the quest 'Key of FBI part 1' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Key of FBI part 2");
        }
    }
    
    public void KeyFBIp2(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getCharacter() != null) {
             Character vC = this.aPlayer.getCurrentRoom().getCharacter();
             if (vC.getName().equals("Alex") && vC.getItemNeed() == null) {
                 this.aGui.println("You finished the quest 'Key of FBI part 2' !");
                 pQuest.setIsDoing(false);
             }
        }
    }
    
    public void FindFBI(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getImageName().equals("FBIinside.jpg")) {
             this.aGui.println("You finished the quest 'Find the FBI' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Go to England");
        }
    }
    
    public void GoToEngland(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getImageName().equals("AirportEnglish.jpg")) {
             this.aGui.println("You finished the quest 'Go to England' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Learn about the sell");
        }
    }
    
    public void LearnMore(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getImageName().equals("StreetLondon.jpg")) {
             this.aGui.println("You finished the quest 'Learn about the sell' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Try to fight the seller");
        }
    }
    
    public void TryToFight(final Quest pQuest) {
        if (!this.aPlayer.getLastRooms().isEmpty()) {
            Room vR = (Room) this.aPlayer.getLastRooms().peek();
            if (this.aPlayer.getCurrentRoom().getImageName().equals("AirportEnglish.jpg") && vR.getImageName().equals("BigBen.jpg")) {
                 this.aGui.println("You finished the quest 'Try to fight the seller' !");
                 pQuest.setIsDoing(false);
                 this.printQuestInfo("Intidimate the seller");
            }
        }
    }
    
    public void Intidimate(final Quest pQuest) {
        if (this.aPlayer.getItem("knife") != null || this.aPlayer.getItem("weapon") != null || this.aPlayer.getItem("shotgun") != null) {
             this.aGui.println("You finished the quest 'Intidimate the seller' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Fight again the seller");
        }
    }
    
    public void FightAgain(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getItem("Phone") != null) {
            this.aGui.println("You finished the quest 'Fight again the seller' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Take the phone");
        }
    }
    
    public void TakePhone(final Quest pQuest) {
        if (this.aPlayer.getItem("Phone") != null) {
            this.aGui.println("You finished the quest 'Take the phone' !");
             pQuest.setIsDoing(false);
             this.printQuestInfo("Drop your phone in your house");
        }
    }
    
    public void WinQuest(final Quest pQuest) {
        if (this.aPlayer.getCurrentRoom().getImageName().equals("HouseInside.jpg") && this.aPlayer.getCurrentRoom().getItem("Phone") != null) {
            this.aGui.println("You finished the quest 'Drop your phone in your house' !");
             pQuest.setIsDoing(false);
        }
    }

} // Game
