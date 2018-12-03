import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * container class for pieces of Snake
 * - keep track of position of each piece
 * - used for if you collide into yourself
 * @author Matthew Below
 */
public class Snake
{
    private ArrayList<Tail> tail;
    private Move currentMove;
    private Move previousMove;
    
    private ArrayList<Color> headColors;// = new ArrayList<Color>();
    private ArrayList<Color> tailColors;// = new ArrayList<Color>();
    private ArrayList<Double> headSizes;//1 = new Array
    private ArrayList<Double> tailSizes;
    
    
    private int snakeColorPos = 0;
    private int tailColorPos = 0;
    
    private boolean headUnique;
    private boolean sequence;
    
    
    public Snake()
    {
    }
    
    public Snake(double x, double y, Direction direction, MainMenu menu)
    {
        // get head and tail colors and sizes from settings and boolean values from settings
        headColors = menu.getSettings().getHeadColors();
        tailColors = menu.getSettings().getTailColors();
        headSizes = menu.getSettings().getHeadSizes();
        tailSizes = menu.getSettings().getTailSizes();
        
        // get game options from settings
        headUnique = menu.getSettings().headUnique();
        sequence = menu.getSettings().sequence();
        
        tail = new ArrayList<>();
        currentMove = new Move(x, y, direction);
        previousMove = currentMove.copy();
        
        // add initial head piece to snake
        Tail head = new Tail(currentMove.copy(), determineColor());
        tail.add(head);
        
    }

    // add piece to Snake's tail
    public void add(Tail piece)
    {
        tail.add(piece);
    }

    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame(double movement)
    {
        // update position of snake, which the head and tail of snake follow
        if(currentMove.getDirection() == Direction.N) {
            currentMove.setY(currentMove.getY() - movement);
        }
        else if(currentMove.getDirection() == Direction.S) {
            currentMove.setY(currentMove.getY() + movement);
        }
        else if(currentMove.getDirection() == Direction.E) {
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.W){
            currentMove.setX(currentMove.getX() + movement);
        }
        else if(currentMove.getDirection() == Direction.NE){
            currentMove.setY(currentMove.getY() - movement);
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.NW){
            currentMove.setY(currentMove.getY() - movement);
            currentMove.setX(currentMove.getX() + movement);
        }
        else if(currentMove.getDirection() == Direction.SE){
            currentMove.setY(currentMove.getY() + movement);
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.SW){
            currentMove.setY(currentMove.getY() + movement);
            currentMove.setX(currentMove.getX() + movement);
        }
        
        updateColors();
        
        // update head of snake
        getFirst().updateFrame(currentMove);
        
        // update each tail piece of snake
        for(int i = 1; i < tail.size(); i++) {
            tail.get(i).updateFrame(tail.get(i - 1));
        }
    }
    
    public void updateColors()
    {
        // snakeColors and tailColors will at least have 1 color,
        // but maybe not if we set a default value if user got rid of all colors
        // if snakeColors.size() == 0; Color.GRAY;
        

        if(headUnique) {
            
            tail.get(0).setColor(headColors.get(snakeColorPos));
            
            if(sequence) {
                
                // i = snake pieces
                for(int i = 1; i < tail.size(); i++) {
                    
                    // j = number of available colors
                    for(int j = 0; j < tailColors.size(); j++) {
                        
                        if(i % tailColors.size() == j) {
                            
                            //[funny bug]
                            //tail.get(i).setColor(snakeColors.get((tailColorPos + j) % tailColors.size()));
                            tail.get(i).setColor(tailColors.get((tailColorPos + j) % tailColors.size()));
                            // stop looking through colors because snake piece won't match anymore
                            break;
                            
                        }  
                        
                    }
                    
                }
                
            }
            
            else {
                
                for(int i = 1; i < tail.size(); i++) {
                    tail.get(i).setColor(tailColors.get(tailColorPos));
                }

            }
            
            // update position for snake and tail color
            snakeColorPos = (snakeColorPos + 1) % headColors.size();
            tailColorPos = (tailColorPos + 1) % tailColors.size();
        }
           
        else {
            
            if(sequence) {
                
                // i = snake pieces
                for(int i = 0; i < tail.size(); i++) {
                    
                    // j = number of available colors
                    for(int j = 0; j < headColors.size(); j++) {
                        
                        if(i % headColors.size() == j) {

                            tail.get(i).setColor(headColors.get((snakeColorPos + j) % headColors.size()));
                            // stop looking through colors because snake piece won't match anymore
                            break;
                            
                        }  
                        
                    }
                    
                }
                
            }
            
            else {
                
                for(int i = 0; i < tail.size(); i++) {
                    tail.get(i).setColor(headColors.get(snakeColorPos));
                }
                
            }
            
            // update position for snake color
            snakeColorPos = (snakeColorPos + 1) % headColors.size();
        }
        
    }
    
    public Color determineColor()
    {
        
        // updateColors will update pos of colors
        if(headUnique) {
            return tailColors.get(tailColorPos);
        }
        else {
            return headColors.get(snakeColorPos);
        }
        
    }
    
    public void changeDirection(Direction direction)
    {
        // stop player from going in opposite direction or changing to the current direction
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.N && direction == Direction.S) ||
           (currentMove.getDirection() == Direction.S && direction == Direction.N) ||
           (currentMove.getDirection() == Direction.E && direction == Direction.W) ||
           (currentMove.getDirection() == Direction.W && direction == Direction.E) ||
           (currentMove.getDirection() == Direction.NE && direction == Direction.SW) ||
           (currentMove.getDirection() == Direction.NW && direction == Direction.SE) ||
           (currentMove.getDirection() == Direction.SE && direction == Direction.NW) ||
           (currentMove.getDirection() == Direction.SW && direction == Direction.NE)) {
               return;  
        }
        
        // update current and previous move of snake
        currentMove.setDirection(direction);
        previousMove = currentMove.copy();
    }
    
    // get first piece of snake
    public Tail getFirst()
    {
        return tail.get(0);
    }
    
    // get last piece of snake
    public Tail getLast()
    {
        return tail.get(tail.size() - 1);
    }
}
