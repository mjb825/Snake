import java.util.List;
/**
 * container class for SnakeBody pieces
 * - keep track of position of each piece
 * - used for if you collide into yourself
 */
public class Snake
{
    private Head head;
    private List<Tail> tail;
    private Direction currentDirection;
    private int previousX;
    private int previousY;
    private int x;
    private int y;
    
    public Snake(int x, int y)
    {
        head = new Head(x, y);
    }
    
    public void updateFrame(int rangeX, int rangeY)
    {
        head.updateFrame(rangeX, rangeY);
    }
    
    public Head getHead()
    {
        return head;
    }
    
    public void setDirection(Direction direction)
    {
        head.setDirection(direction);
        /*
        if((currentDirection == direction) ||
           (previousX == x && previousY == y) ||
           (currentDirection == Direction.UP && direction == Direction.DOWN) ||
           (currentDirection == Direction.DOWN && direction == Direction.UP) ||
           (currentDirection == Direction.LEFT && direction == Direction.RIGHT) ||
           (currentDirection == Direction.RIGHT && direction == Direction.LEFT)) {
               return;  
        }
        
        previousX = x;
        previousY = y;
        currentDirection = direction;
        */
    }
}
