package acharacterthatturns;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WhereTheCharacterMoves extends JFrame implements MouseMotionListener, KeyListener {

    TheCharacterThatMoves thePlayer = new TheCharacterThatMoves(); //creates an instace of the player class
    Bullet bullet1 = new Bullet(); //creates an instance of the bullet class
    double rotationAngle; //double variable for the rotation angle to rotate the character and bullet based on mouse position

    public WhereTheCharacterMoves() {
        setTitle("Har0ld 2"); //sets the title of the jframe
        setLayout(null); //ALWAYS DO THIS, sets the layout to null so you can freely put jpanels anywhere
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets the screen size of the monitor 
        int width = (int) screenSize.getWidth(); //sets int width to the width of the screen
        int height = (int) screenSize.getHeight(); //sets int height to the height of the screen
        setSize(width, height); //sets the size of the frame  
        setResizable(true); //makes it so that the frame isnt resizable
        setVisible(true); //sets the frame to make it visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the frame so the program stops when its closed 
        addMouseMotionListener(this); //adds the mouse motion listener and the key listener
        addKeyListener(this); 
        add(thePlayer); //adds the player and the bullet
        add(bullet1);
        setFocusable(true); //allows the jframe to be focussed
        requestFocus(); //focusses the jframe
    } //constructor for the jframe 

    @Override
    public void mouseDragged(MouseEvent arg0) {
        //DO NOTHING
    }//a useless method that i can't get rid of

    @Override
    public void mouseMoved(MouseEvent arg0) {
        int x = arg0.getX(); //Gets the x and y coordinates of the mouse position on the screen
        int y = arg0.getY();
        //System.out.println(x + "," + y);
        float xDistance = x - (thePlayer.getLocation().x + (thePlayer.getWidth() / 2)); //Gets the x and y distances from the mouse pointer relative to the center of the character
        float yDistance = y - (thePlayer.getLocation().y + (thePlayer.getHeight() / 2));
        rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance)); //Gets the angle of the line created from the x and y distances relative to an imaginary x axis
        thePlayer.getAngle(rotationAngle); //calls the a method in thePlayer class to rotate it 
        if (x <= thePlayer.getLocation().x) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            bullet1.setLocation(thePlayer.getLocation().x - 30, bullet1.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (x >= thePlayer.getLocation().x) { //if the mouse is on the far right side of the character
            bullet1.setLocation(thePlayer.getLocation().x + thePlayer.getWidth() + 10, bullet1.getLocation().y);
        }
        if (y <= thePlayer.getLocation().y) { //if the mouse is above the character
            bullet1.setLocation(bullet1.getLocation().x, thePlayer.getLocation().y - 30);
        }
        if (y >= thePlayer.getLocation().y) { //if the mouse is below the character
            bullet1.setLocation(bullet1.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight() + 10);
        }
        if (x >= thePlayer.getLocation().x && x <= thePlayer.getLocation().x + thePlayer.getWidth()) { //if the mouse is in between the far left and ride side of the charatcer
            bullet1.setLocation(x, bullet1.getLocation().y);
        }
        if (y >= thePlayer.getLocation().y && y <= thePlayer.getLocation().y + thePlayer.getHeight()) { //if the mouse in between the top and bottom of the character
            bullet1.setLocation(bullet1.getLocation().x, y);
        }
        bullet1.getAngleAndLocation(rotationAngle, x, y); //calls the method in the bullet class to rotate it's image

    } //method that occurs every time the mouse moves 

    @Override
    public void keyTyped(KeyEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } //another useless method that i can't get rid of 

    @Override
    public void keyPressed(KeyEvent arg0) {
        int code = arg0.getKeyCode(); //gets the int value of the key that was pressed
        if (code == KeyEvent.VK_W) { //If the "w" key is pressed, move everything up
            thePlayer.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y - 5);
            bullet1.setLocation(bullet1.getLocation().x, bullet1.getLocation().y - 5);
        }
        if (code == KeyEvent.VK_A) { //if the "a" key is pressed, move everything down
            thePlayer.setLocation(thePlayer.getLocation().x - 5, thePlayer.getLocation().y);
            bullet1.setLocation(bullet1.getLocation().x - 5, bullet1.getLocation().y);
        }
        if (code == KeyEvent.VK_S) { //if the "s" key is pressed move everything to the left
            thePlayer.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y + 5);
            bullet1.setLocation(bullet1.getLocation().x, bullet1.getLocation().y + 5);
        }
        if (code == KeyEvent.VK_D) { //if the "d" key is pressed move everything to the right
            thePlayer.setLocation(thePlayer.getLocation().x + 5, thePlayer.getLocation().y);
            bullet1.setLocation(bullet1.getLocation().x + 5, bullet1.getLocation().y);
        }
    } //key listeners to move the objects

    @Override
    public void keyReleased(KeyEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//more useless method

}
