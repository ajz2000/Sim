import java.util.*;

public class Entity{
  protected int health;
  protected int x;
  protected int y;
  protected Sim sim;
  
  protected ArrayList<Node> findPathTo(Entity e){
    //The final list of nodes to move to.
    ArrayList<Node> path = new ArrayList<Node>();
    
    //Shallow copy of world
    Node[][] map = new Node[sim.getWorld().length][];
    for(int i = 0; i < map.length; i++){
      map[i] = sim.getWorld()[i].clone();
    }
    
    //If the F value of the node is the max int, it's not on the open list.
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[i].length; j++){
        map[i][j].setF(Integer.MAX_VALUE);
      }
    }
    
    //Closed List is nodes already traversed
    boolean[][] closedList = new boolean[map.length][map[0].length];
    //Open List is nodes to be traversed
    ArrayList<Node> openList = new ArrayList<Node>();
    
    //Some Defaults for the beginning of the path.
    map[y][x].setLastNode(null);
    map[y][x].setF(0);
    map[y][x].setG(0);
    map[y][x].setH(0);
    openList.add(map[y][x]);
    
    //Temporary storage to compare with existing node values.
    int newF;
    int newG;
    int newH;
    
    //If destination is found.
    boolean found = false;
    
    //If the openList is Empty and the destination isn't found, it's impossible to reach.
    while(!openList.isEmpty()){
      //"Dequeue" the first element of the openlist.
      Node n = openList.get(0);
      openList.remove(0);
      
      //Check node to the North if it's in the array.
      if(isValid(map, n.getX(), n.getY() - 1)){
        //If this node is the destination, path found.
        if(n.getX() == e.getX() && n.getY() - 1 == e.getY()){
          //Link the end node to this one.
          map[n.getY() - 1][n.getX()].setLastNode(n);
          //Trace the links to build a path.
          path = traceBack(map[n.getY() - 1][n.getX()]);
          found = true;
          break;
        } else if(!closedList[n.getY() - 1][n.getX()]){
          //If the node isn't closed, do work.
          
          //Since all paths take the same "time" to move down, the distance from the start simply increases by 1.
          newG = n.getG() + 1;
          //Perform Heuristic analysis(That sounds really pretentious) to find approximate distance from end.
          newH = heuristics(map[n.getY() - 1][n.getX()], map[e.getY()][e.getX()]);
          //New "Score" = distance from start + distance from finish.
          newF = newG + newH;
          
          //If the node isn't open, make it open. Alternatively, if the node is open but this is a more efficient path, change it's status.
          if(map[n.getY() - 1][n.getX()].getF() == Integer.MAX_VALUE || map[n.getY() - 1][n.getX()].getF() > newF){
            map[n.getY() - 1][n.getX()].setF(newF);
            map[n.getY() - 1][n.getX()].setG(newG);
            map[n.getY() - 1][n.getX()].setH(newH);
            map[n.getY() - 1][n.getX()].setLastNode(n);
            
            if(openList.isEmpty()){
              openList.add(map[n.getY() - 1][n.getX()]);
            } else {
              int i = 0;
              while(newF >= openList.get(i).getF()){
                i++;
              }
              openList.add(i, map[n.getY() - 1][n.getX()]);
            }
          }
        }
      }
      
      //Repeat the process for South, East, and West Nodes.
      if(isValid(map, n.getX(), n.getY() + 1)){
        //If this node is the destination, path found.
        if(n.getX() == e.getX() && n.getY() + 1 == e.getY()){
          //Link the end node to this one.
          map[n.getY() + 1][n.getX()].setLastNode(n);
          //Trace the links to build a path.
          path = traceBack(map[n.getY() + 1][n.getX()]);
          found = true;
          break;
        } else if(!closedList[n.getY() + 1][n.getX()]){
          //If the node isn't closed, do work.
          
          //Since all paths take the same "time" to move down, the distance from the start simply increases by 1.
          newG = n.getG() + 1;
          //Perform Heuristic analysis(That sounds really pretentious) to find approximate distance from end.
          newH = heuristics(map[n.getY() + 1][n.getX()], map[e.getY()][e.getX()]);
          //New "Score" = distance from start + distance from finish.
          newF = newG + newH;
          
          //If the node isn't open, make it open. Alternatively, if the node is open but this is a more efficient path, change it's status.
          if(map[n.getY() + 1][n.getX()].getF() == Integer.MAX_VALUE || map[n.getY() + 1][n.getX()].getF() > newF){
            map[n.getY() + 1][n.getX()].setF(newF);
            map[n.getY() + 1][n.getX()].setG(newG);
            map[n.getY() + 1][n.getX()].setH(newH);
            map[n.getY() + 1][n.getX()].setLastNode(n);
            
            if(openList.isEmpty()){
              openList.add(map[n.getY() + 1][n.getX()]);
            } else {
              int i = 0;
              while(newF >= openList.get(i).getF()){
                i++;
              }
              openList.add(i, map[n.getY() + 1][n.getX()]);
            }
          }
        }
      }
      
      if(isValid(map, n.getX() + 1, n.getY())){
        //If this node is the destination, path found.
        if(n.getX() == e.getX() + 1 && n.getY() == e.getY()){
          //Link the end node to this one.
          map[n.getY()][n.getX() + 1].setLastNode(n);
          //Trace the links to build a path.
          path = traceBack(map[n.getY()][n.getX() + 1]);
          found = true;
          break;
        } else if(!closedList[n.getY()][n.getX() + 1]){
          //If the node isn't closed, do work.
          
          //Since all paths take the same "time" to move down, the distance from the start simply increases by 1.
          newG = n.getG() + 1;
          //Perform Heuristic analysis(That sounds really pretentious) to find approximate distance from end.
          newH = heuristics(map[n.getY()][n.getX() + 1], map[e.getY()][e.getX()]);
          //New "Score" = distance from start + distance from finish.
          newF = newG + newH;
          
          //If the node isn't open, make it open. Alternatively, if the node is open but this is a more efficient path, change it's status.
          if(map[n.getY()][n.getX() + 1].getF() == Integer.MAX_VALUE || map[n.getY()][n.getX() + 1].getF() > newF){
            map[n.getY()][n.getX() + 1].setF(newF);
            map[n.getY()][n.getX() + 1].setG(newG);
            map[n.getY()][n.getX() + 1].setH(newH);
            map[n.getY()][n.getX() + 1].setLastNode(n);
            
            if(openList.isEmpty()){
              openList.add(map[n.getY()][n.getX() + 1]);
            } else {
              int i = 0;
              while(newF >= openList.get(i).getF()){
                i++;
              }
              openList.add(i, map[n.getY()][n.getX() + 1]);
            }
          }
        }
      }
      
      if(isValid(map, n.getX() - 1, n.getY())){
        //If this node is the destination, path found.
        if(n.getX() == e.getX() - 1 && n.getY() == e.getY()){
          //Link the end node to this one.
          map[n.getY()][n.getX() - 1].setLastNode(n);
          //Trace the links to build a path.
          path = traceBack(map[n.getY()][n.getX() - 1]);
          found = true;
          break;
        } else if(!closedList[n.getY()][n.getX() - 1]){
          //If the node isn't closed, do work.
          
          //Since all paths take the same "time" to move down, the distance from the start simply increases by 1.
          newG = n.getG() + 1;
          //Perform Heuristic analysis(That sounds really pretentious) to find approximate distance from end.
          newH = heuristics(map[n.getY()][n.getX() - 1], map[e.getY()][e.getX()]);
          //New "Score" = distance from start + distance from finish.
          newF = newG + newH;
          
          //If the node isn't open, make it open. Alternatively, if the node is open but this is a more efficient path, change it's status.
          if(map[n.getY()][n.getX() - 1].getF() == Integer.MAX_VALUE || map[n.getY()][n.getX() - 1].getF() > newF){
            map[n.getY()][n.getX() - 1].setF(newF);
            map[n.getY()][n.getX() - 1].setG(newG);
            map[n.getY()][n.getX() - 1].setH(newH);
            map[n.getY()][n.getX() - 1].setLastNode(n);
            
            if(openList.isEmpty()){
              openList.add(map[n.getY()][n.getX() - 1]);
            } else {
              int i = 0;
              while(newF >= openList.get(i).getF()){
                i++;
              }
              openList.add(i, map[n.getY()][n.getX() - 1]);
            }
          }
        }
      }
    }
    
    //If the path was not found, throw an error to catch later, which can tell the creature to either move on or try to break through.
    if(!found){
      throw new Error("Could not find path");
    }
    
    //Give the object the list of nodes to follow.
    return path;
  }
  
  private ArrayList<Node> traceBack(Node end){
    ArrayList<Node> path = new ArrayList<Node>();
    path.add(end);
    
    Node currentNode = end;
    
    while (currentNode.getLastNode() != null){
      path.add(0, currentNode.getLastNode());
      
      currentNode = currentNode.getLastNode();
    }
    
    return path;
  }
  
  private boolean isValid(Node[][] n, int x, int y){
    return (x >= 0) && (y >= 0) && (x < n[0].length) && (y < n.length);
  }
  
  //Best heuristic(shortest theoretical path assuming no obstacles) for 4-directional movement between 2 nodes: "Manhattan Distance".
  private int heuristics(Node a, Node b){
    return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));
  }
  
  protected void goToNearest(Entity e){
    
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
}