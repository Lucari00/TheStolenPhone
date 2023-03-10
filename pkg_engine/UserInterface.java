package pkg_engine;

    import javax.swing.JFrame;
    import javax.swing.JTextField;
    import javax.swing.JTextArea;
    import javax.swing.JEditorPane;
    import javax.swing.JLabel;
    import javax.swing.ImageIcon;
    import javax.swing.JScrollPane;
    import javax.swing.JPanel;
    import javax.swing.JButton;
    import javax.swing.JProgressBar;
    import javax.swing.SwingConstants;
    import java.awt.Dimension;
    import java.awt.BorderLayout;
    import java.awt.Image;
    import java.awt.event.ActionListener;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    import java.awt.event.ActionEvent;
    import java.net.URL;
    import java.awt.image.*;
    
    /**
     * This class implements a simple graphical user interface with a text entry
     * area, a text output area and an optional image.
     * 
     * @author Michael Kolling
     * @version 1.0 (Jan 2003) DB edited (2019) + 24/05/2022
     */
    public class UserInterface implements ActionListener
    {
        private GameEngine aEngine;
        private JFrame     aMyFrame;
        private JTextField aEntryField;
        private JTextArea  aLog;
        private JLabel     aImage;
        private JLabel     aSkin;
        private JButton    aButton;
        private JProgressBar aProgressBar;
        private JEditorPane  aQuest;
        private JEditorPane  aInventory;
        private JEditorPane  aTime;
    
        /**
         * Construct a UserInterface. As a parameter, a Game Engine
         * (an object processing and executing the game commands) is
         * needed.
         * 
         * @param pGameEngine  The GameEngine object implementing the game logic.
         */
        public UserInterface( final GameEngine pGameEngine )
        {
            this.aEngine = pGameEngine;
            this.createGUI();
            this.showImage("AirportAmerica.jpg");
        } // UserInterface(.)
    
        /**
         * Print out some text into the text area.
         * @param pText String
         */
        public void print( final String pText )
        {
            this.aLog.append( pText );
            this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
        } // print(.)
    
        /**
         * Print out some text into the text area, followed by a line break.
         * @param pText String
         */
        public void println( final String pText )
        {
            this.print(pText + "\n" );
        } // println(.)
    
        /**
         * Show an image file in the interface.
         * @param pImageName String
         */
        public void showImage( final String pImageName )
        {
            String vImagePath = "Images/" + pImageName; // to change the directory
            URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
            if ( vImageURL == null )
                System.out.println( "Image not found : " + vImagePath );
            else {
                ImageIcon vIcon = new ImageIcon( vImageURL );
                this.aImage.setIcon( vIcon );
                //this.aMyFrame.pack();
            }
        } // showImage(.)
        
        public void showSkin( final String pImageName )
        {
            if (pImageName != null) {
                String vImagePath = "Images/" + pImageName; // to change the directory
                URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
                if ( vImageURL == null )
                    System.out.println( "Image not found : " + vImagePath );
                else {
                    ImageIcon vIcon = new ImageIcon( vImageURL );
                    this.aSkin.setIcon( vIcon );
                    //this.aMyFrame.pack();
                }
            } else {
                this.aSkin.setIcon( null );
                //this.aMyFrame.pack();
            }
            
        } // showImage(.)
    
        /**
         * Enable or disable input in the input field.
         * @param pOnOff boolean
         */
        public void enable( final boolean pOnOff )
        {
            this.aEntryField.setEditable( pOnOff ); // enable/disable
            if ( ! pOnOff ) { // disable
                this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
                this.aEntryField.removeActionListener( this ); // won't react to entry
            }
        } // enable(.)
    
        /**
         * Set up graphical user interface.
         */
        private void createGUI()
        {
            this.aMyFrame = new JFrame( "The Stolen Phone" ); // change the title
            this.aEntryField = new JTextField( 34 );
    
            String vImagePath = "Images/logo.png"; // to change the directory
            URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
            if ( vImageURL == null )
                System.out.println( "Image not found : " + vImagePath );
            else {
                ImageIcon vImg = new ImageIcon(vImageURL);
                this.aMyFrame.setIconImage(vImg.getImage());
            }
            
            this.aLog = new JTextArea();
            this.aLog.setEditable( false );
            JScrollPane vListScroller = new JScrollPane( this.aLog );
            vListScroller.setPreferredSize( new Dimension(200, 200) );
            vListScroller.setMinimumSize( new Dimension(100,100) );
    
            JPanel vPanel = new JPanel();
            this.aImage = new JLabel();
            
            BorderLayout vBorderL1 = new BorderLayout();
            vPanel.setLayout( vBorderL1 ); // ==> only five places
            vPanel.add( this.aImage, vBorderL1.NORTH );
            vPanel.add( vListScroller, vBorderL1.CENTER );
            vPanel.add( this.aEntryField, vBorderL1.SOUTH );
            
            this.aProgressBar = new JProgressBar(SwingConstants.VERTICAL,0,this.aEngine.getNumberOfMoveLeft());
            this.aProgressBar.setValue(this.aEngine.getNumberOfMoveLeft());
            this.aProgressBar.setStringPainted(true);
            vPanel.add(this.aProgressBar, vBorderL1.WEST);
            
            this.aButton = new JButton("Look");
            
            BorderLayout vBorderL2 = new BorderLayout();
            JPanel vPanel2 = new JPanel();
            
            BorderLayout vBorderL3 = new BorderLayout();
            JPanel vPanel3 = new JPanel();
            
            vPanel.add(vPanel2 ,vBorderL1.EAST);
    
            this.aMyFrame.getContentPane().add( vPanel, vBorderL1.CENTER );
            
            vPanel2.setLayout(vBorderL2);
            vPanel3.setLayout(vBorderL3);
            
            vPanel2.add(aButton,vBorderL2.SOUTH);
            

            vPanel2.add(vPanel3 ,vBorderL2.WEST);
            
            this.aSkin = new JLabel();
            vPanel2.add(this.aSkin ,vBorderL2.EAST);
            
            this.aQuest = new JEditorPane("text/html", "");
            this.aQuest.setEditable( false );
            //JScrollPane vListScroller3 = new JScrollPane( this.aQuest );
            //vListScroller3.setPreferredSize( new Dimension(150, 50) );
            //vListScroller3.setMinimumSize( new Dimension(150,50) );
            vPanel3.add(this.aQuest ,vBorderL3.NORTH);
            
            this.aInventory = new JEditorPane("text/html", "");
            this.aInventory.setEditable( false );
                //JScrollPane vListScroller2 = new JScrollPane( this.aInventory );
                //vListScroller2.setPreferredSize( new Dimension(150, 50) );
                //vListScroller2.setMinimumSize( new Dimension(150,50) );
            //vPanel3.add(this.aInventory ,vBorderL3.SOUTH);
            vPanel3.add(this.aInventory, vBorderL2.SOUTH);
            
            this.aTime = new JEditorPane("text/html", "");
            this.aTime.setEditable( false );
            vPanel3.add(this.aTime ,vBorderL3.CENTER);
    
            // add some event listeners to some components
            this.aEntryField.addActionListener( this );
            this.aButton.addActionListener(this);
    
            // to end program when window is closed
            this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
        } );
        this.aMyFrame.setPreferredSize(new Dimension(800, 1500));
        this.aMyFrame.pack();
        this.aMyFrame.setLocationRelativeTo(null);
        this.aMyFrame.setVisible( true );
        
        
        this.aEntryField.requestFocus();
    } // createGUI()
    
    public void setTime(final String pText) {
        aTime.setText(null);
        aTime.setText(pText);
    }
    
    public void setQuests(final String pText) {
        aQuest.setText(null);
        aQuest.setText(pText);
    }
    
    public void setInventory(final String pText) {
        aInventory.setText(null);
        aInventory.setText(pText);
    }
    
    /**
     * Permet de changer la barre de progression en fonction du nombre donné
     * @param pNum int nombre 
     */
    public void setProgressBar(int pNum) {
        this.aProgressBar.setValue(pNum);
    }//setProgressBar()

    /**
     * Actionlistener interface for entry textfield.
     * @param pE ActionEvent
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        if (pE.getActionCommand() == "Look") 
            this.aEngine.processCommand("look");
        else 
            this.processCommand();
         // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.processCommand( vInput );
    } // processCommand()
} // UserInterface 
