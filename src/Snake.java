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
        previousMove = currentMove.clone();
        tail.add(new Tail(currentMove));
    }
    
    public void updateFrame(int rangeX, int rangeY)
    {
        //update position of head of snake
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
        
        tail.get(0).setCurrentMove(currentMove);
        tail.get(0).updateFrame();
    }
    
    public Tail getHead()
    {
        return tail.get(0);
    }
    
    public void setDirection(Direction direction)
    {
        //head.setDirection(direction);
        
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.UP && direction == Direction.DOWN) ||
           (currentMove.getDirection() == Direction.DOWN && direction == Direction.UP) ||
           (currentMove.getDirection() == Direction.LEFT && direction == Direction.RIGHT) ||
           (currentMove.getDirection() == Direction.RIGHT && direction == Direction.LEFT)) {
               return;  
        }
        
        currentMove.setDirection(direction);
        
        
        previousMove = currentMove.clone();
        
        /*
        previousX = x;
        previousY = y;
        currentDirection = direction;
*/
        
    }
}
