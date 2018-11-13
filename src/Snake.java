import java.util.ArrayList;
/**
 * container class for SnakeBody pieces
 * - keep track of position of each piece
 * - used for if you collide into yourself
 */
public class Snake
{
    private Head head;
    private ArrayList<Tail> tail;
    private Direction currentDirection;
    private int previousX;
    private int previousY;
    private int x;
    private int y;
    private Move currentMove;
    
    public Snake(){}
    
    public Snake(int x, int y)
    {
        tail = new ArrayList<>();
        head = new Head(x, y);
        currentMove = new Move(x, y, Direction.UP);
        tail.add(new Tail(currentMove));
    }
    
    public void updateFrame(int rangeX, int rangeY)
    {
                
        if(currentDirection == Direction.UP && y > 0) {
            y--;
        } else if(currentDirection == Direction.DOWN && y < (rangeY - 1)) {
            y++;
        } else if(currentDirection == Direction.LEFT && x > 0) {
            x--;
        } else if(currentDirection == Direction.RIGHT && x < (rangeX - 1)){
            x++;
        }
        
        //list.get(0).updateFrame(0,0)
        //setCenterX(x * 20 + 10);
        //setCenterY(y * 20 + 10);
        
        head.updateFrame(rangeX, rangeY);
    }
    
    public Tail getHead()
    {
        return tail.get(0);
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
