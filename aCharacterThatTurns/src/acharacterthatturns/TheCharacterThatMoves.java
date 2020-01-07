/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acharacterthatturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TheCharacterThatMoves extends JPanel{
    
    int x = 500, y = 250; //starting x and y coordinates 
    Border blackline = BorderFactory.createLineBorder(Color.black); //border variable for debugging
    double rotationAngle = 0; //double variable to store rotation angle

    public TheCharacterThatMoves() {
        setSize(120, 120); //sets the size
        setLocation(x, y); //sets the location
        setOpaque(false); //makes the charatcer see through
    } //constructor for player class

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage player1 = null; //declares buffered image variable
        try { //try catch for file not found
            player1 = LoadImage("images/myGuy2.gif"); //calls the method to get this image file
        } catch (IOException ex) {
            Logger.getLogger(TheCharacterThatMoves.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2d = (Graphics2D) g; //graphics 2d declaration from graphics
        AffineTransform at = AffineTransform.getTranslateInstance(0, 20); //declare affine transform and also moves the image a bit
        at.rotate(Math.toRadians(rotationAngle), player1.getWidth() / 2, player1.getHeight() / 2); //whenever the repaint method is called, it rotates the image by that angle from the center of the iamge
        g2d.drawImage(player1, at, null); //draws the image with the rotations 
    } //paints the image to the jpanel

    BufferedImage LoadImage(String FileName) throws IOException {
        BufferedImage img = null; //creates a buffered image variable
        img = ImageIO.read(new File(FileName)); //sets the variable to the image of the file that it was called
        return img; //returns the img variable
    }//method to load the image in the paint component

    public void getAngle(double angleToSet) {
        rotationAngle = angleToSet; //gets the angle of rotation from the JFrame class
        this.repaint(); //calls the repaint method 
    } //method called from the frame class to get the angle of rotation 
}
