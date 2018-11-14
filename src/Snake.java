import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * container class for SnakeBody pieces
 * - keep track of position of each piece
 * - used for if you collide into yourself
 */
public class Snake
{
    private ArrayList<Tail> tail;
    private Move currentMove;
    private Move previousMove;
    
    public Snake(){}
    
    public Snake(int x, int y)
    {
        tail = new ArrayList<>();
        currentMove = new Move(x, y, Direction.UP);
        previousMove = currentMove.copy();
        
        // THIS IS WHY ONLY THE FIRST PIECE MOVES!
        // THIS CLASS UPDATES CURRENTMOVE AND THE FIRST PIECE HAS A DIRECT REFERENCE!!!!!!
        // AS TO WHY THE OTHER PIECES ARE PICKED UP WHEN THIS ONE PASSES BY... IDK?
        tail.add(new Tail(currentMove.copy()));
    }
    
    public void add(int x, int y)
    {
        tail.add(new Tail(new Move(x, y, Direction.UP)));
    }
    
    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame(int rangeX, int rangeY)
    {
        //update position of head of snake
        //temporary to test movement
        // - GameField will handle what happens when the head hits the border (i.e., game over)
        
        //THIS CHANGES THE POSITION OF THE PIECE???!!!?!?!?!!?!?!!!!!!
        if(currentMove.getDirection() == Direction.UP && currentMove.getY() > 0) {
            currentMove.setY(currentMove.getY() - 1);
        } else if(currentMove.getDirection() == Direction.DOWN && currentMove.getY() < (rangeY - 1)) {
            currentMove.setY(currentMove.getY() + 1);
        } else if(currentMove.getDirection() == Direction.LEFT && currentMove.getX() > 0) {
            currentMove.setX(currentMove.getX() - 1);
        } else if(currentMove.getDirection() == Direction.RIGHT && currentMove.getX() < (rangeX - 1)){
            currentMove.setX(currentMove.getX() + 1);
        }
        
        
        //list.get(0).updateFrame(0,0)
        //setCenterX(x * 20 + 10);
        //setCenterY(y * 20 + 10);
        
        //head.updateFrame(rangeX, rangeY);
        
        //[debugging]
        //System.out.println("x: " + currentMove.getX() + " y: " + currentMove.getY());
        
        //tail.get(0).setCurrentMove(currentMove);
        for(int i = 0; i < tail.size(); i++) {
            System.out.println("tail " + i);
            tail.get(i).updateFrame();
        }
    }
    
    public Tail getHead()
    {
        return tail.get(0);
    }
    
    public void changeDirection(Direction direction)
    {
        
        // stop player from going in opposite direction or changing to the current direction
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.UP && direction == Direction.DOWN) ||
           (currentMove.getDirection() == Direction.DOWN && direction == Direction.UP) ||
           (currentMove.getDirection() == Direction.LEFT && direction == Direction.RIGHT) ||
           (currentMove.getDirection() == Direction.RIGHT && direction == Direction.LEFT)) {
               return;  
        }
        
        currentMove.setDirection(direction);
        
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        System.out.println("##############################################################################");
        
        // add move to queue of all tail pieces
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).addNextMove(currentMove.copy());
        }
        
        previousMove = currentMove.copy();
        
        /*
        previousX = x;
        previousY = y;
        currentDirection = direction;
*/
        
    }
}
