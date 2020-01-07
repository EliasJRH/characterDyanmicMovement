package acharacterthatturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Bullet extends JPanel{

    TheCharacterThatMoves character = new TheCharacterThatMoves(); //creates an instance of the actual player class
     
    int x = 630, y = 340; //x and y coordinates to establish starting position
    Border blackline = BorderFactory.createLineBorder(Color.black);  //creating a border for debeugging and other stuff
    double rotationAngle = 0; //rotation angle double variable

    public Bullet() { 
        setSize(36, 36); //sets the size of the bullet
        setLocation(x, y); //sets the starting location of the bullet to the default x and y coordinates
        setOpaque(false); //sets it opaque to false to the object is see through 
        setBorder(blackline); //sets a blackline border around it for debugging
    } //bullet constructor

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //super component paint think idk, need to be in here
        BufferedImage bulletImg = null; //declares buffered image variable
        try { //try catch for file not found
            bulletImg = LoadImage("images/bulletReSized.png"); //calls the method to get this image file
        } catch (IOException ex) {
            Logger.getLogger(TheCharacterThatMoves.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2d = (Graphics2D) g; //graphics 2d declaration from graphics
        AffineTransform at = AffineTransform.getTranslateInstance(0,8); //declare affine transform and also moves the image a bit
        at.rotate(Math.toRadians(rotationAngle), bulletImg.getWidth() / 2, bulletImg.getHeight() / 2); //whenever the repaint method is called, it rotates the image by that angle from the center of the iamge
        g2d.drawImage(bulletImg, at, null); //draws the image with the rotations 
    } //Method to paint the image of the bullet, this called every time the mouse moves to also rotate the image of the bullet

    BufferedImage LoadImage(String FileName) throws IOException {
        BufferedImage img = null; //creates a buffered image variable
        img = ImageIO.read(new File(FileName)); //sets the variable to the image of the file that it was called
        return img; //returns the img variable
    } //method to load the image in the paint component

    public void getAngleAndLocation(double angleToSet, int mouseX, int mouseY) {
        rotationAngle = angleToSet; //gets the angle of rotation from the JFrame class
        this.repaint(); //calls the repaint method 
    }//method called from the frame class to change the rotation angle and to repaint the bullet image
}
