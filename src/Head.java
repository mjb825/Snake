import javafx.scene.shape.Circle;
import java.util.Queue;
/**
 * Body piece (head or tail) of the Snake
 */
public class Head extends Circle
{
    protected Direction currentDirection;
    private Queue<Move> nextMoves;
    private int x;
    private int y;
    
    private int previousX;
    private int previousY;

    public Head()
    {
        
    }
    
    public Head(int x, int y)
    {
        super(8);
        currentDirection = Direction.RIGHT;
        this.x = x;
        this.y = y;
        setStyle("-fx-stroke: black; -fx-fill: green; -fx-stroke-width: 2;");
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
        
        
        setCenterX(x * 20 + 10);
        setCenterY(y * 20 + 10);
    }
    
    public void setDirection(Direction direction)
    {
        
        
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
    }
}
