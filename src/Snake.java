import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

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
    
    public Snake()
    {
    }
    
    public Snake(int x, int y, Direction direction)
    {
        tail = new ArrayList<>();
        currentMove = new Move(x, y, direction);
        previousMove = currentMove.copy();
        
        // add initial head piece to snake
        Tail head = new Tail(currentMove.copy(), "red");
        tail.add(head);
    }

    public void add(Tail piece)
    {
        tail.add(piece);
    }

    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame()
    {
        // update position of snake, which the head and tail of snake follow
        if(currentMove.getDirection() == Direction.N) {
            currentMove.setY(currentMove.getY() - 1);
        }
        else if(currentMove.getDirection() == Direction.S) {
            currentMove.setY(currentMove.getY() + 1);
        }
        else if(currentMove.getDirection() == Direction.E) {
            currentMove.setX(currentMove.getX() - 1);
        }
        else if(currentMove.getDirection() == Direction.W){
            currentMove.setX(currentMove.getX() + 1);
        }
        else if(currentMove.getDirection() == Direction.NE){
            currentMove.setY(currentMove.getY() - 1);
            currentMove.setX(currentMove.getX() - 1);
        }
        else if(currentMove.getDirection() == Direction.NW){
            currentMove.setY(currentMove.getY() - 1);
            currentMove.setX(currentMove.getX() + 1);
        }
        else if(currentMove.getDirection() == Direction.SE){
            currentMove.setY(currentMove.getY() + 1);
            currentMove.setX(currentMove.getX() - 1);
        }
        else if(currentMove.getDirection() == Direction.SW){
            currentMove.setY(currentMove.getY() + 1);
            currentMove.setX(currentMove.getX() + 1);
        }
        
        
        // update each piece of snake
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).updateFrame();
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
        
        // add move to queue of all tail pieces
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).addNextMove(currentMove.copy());
        }
    }
    
    public Tail getFirst()
    {
        return tail.get(0);
    }
    
    public Tail getLast()
    {
        return tail.get(tail.size() - 1);
    }
}
