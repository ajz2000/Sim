import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Sim extends JFrame{
  private Node[][] worldArray = new Node[50][50];

  public static void main(String args[]){
    new Sim();
  }

  public Sim(){
    JPanel tempPanel = new JPanel();
    tempPanel.setPreferredSize(new Dimension(1920,1080));
    tempPanel.setBackground(new Color(35,35,35));
    add(tempPanel, BorderLayout.CENTER);
    
  setDefaultCloseOperation(EXIT_ON_CLOSE);
    setUndecorated(true);
    pack();
    setLocationRelativeTo(null);
    setTitle("Sim");
    setVisible(true);  
  }
  
  public Node[][] getWorld(){
    return worldArray;
  }
}