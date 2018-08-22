public class Node{
  private int x;
  private int y;
  //f, g, and h are A* variables: The total analysis, distance from start, and heuristic analysis respectively.
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private Node lastNode;
  private char currentChar;
  
  public Node(){
    
  }
  
  public void setLastNode(Node n){
    lastNode = n;
  }
  
  public Node getLastNode(){
    return lastNode;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public int getF(){
    return f;
  }
  
  public int getG(){
    return g;
  }
  
  public int getH(){
    return h;
  }
  
  public void setF(int newF){
    f = newF;
  }
  
  public void setG(int newG){
    g = newG;
  }
  
  public void setH(int newH){
    h = newH;
  }
  
  public void setChar(char c){
    currentChar = c;
  }
  
  public char getChar(){
    return currentChar;
  }
}