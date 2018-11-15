import javafx.scene.shape.Circle;
import java.util.Queue;
import java.util.LinkedList;
/**
 * The tail of the snake that follows the head
 */
public class Tail extends Circle
{

    private Queue<Move> nextMove; //the next move this tail is going to make
    private Move currentMove; //contains current x, y, and direction of piece
    public int x;
    public int y;
    public Direction direction;
    
    public Tail()
    {
    }
    
    public Tail(Move currentMove)
    {
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: green; -fx-stroke-width: 2;");
        nextMove = new LinkedList<>();
        //this.currentMove = currentMove;
        x = currentMove.getX();
        y = currentMove.getY();
        direction = currentMove.getDirection();
        //nextMove.add(new Move(20, 8, Direction.RIGHT));
    }

    public Tail(Queue<Move> nextMove, int x, int y, Direction direction)
    {
        this.nextMove = nextMove;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public Move getCurrentMove()
    {
        return currentMove;
    }
    
    public void setCurrentMove(Move currentMove)
    {
        //this.currentMove = currentMove;
        x = currentMove.getX();
        y = currentMove.getY();
        direction = currentMove.getDirection();
    }
    
    public void addNextMove(Move nextMove)
    {
        this.nextMove.add(nextMove);
    }
    
    public String toString()
    {
        if(nextMove.peek() != null)
            return "x: "+ x + " y: " + y + " direction: " + direction +
                "\n nextX: " + nextMove.peek().getX() + " nextY: " + nextMove.peek().getY() + " nextDirection: " + nextMove.peek().getDirection();
        return "x: "+ x + " y: " + y + " direction: " + direction;
    }
    
    public void updateFrame()
    {
        
        if(nextMove.peek() != null) {
            
            //System.out.println("---------------------------------------");
            //System.out.println("nextMove not empty");
            //System.out.printf("currentX: %d currentY: %d nextX: %d nextY: %d\n", currentMove.getX(), currentMove.getY(), nextMove.peek().getX(), nextMove.peek().getY());
            
            //compare x,y of currentMove to x,y of nextMove
            //if they match x and y, currentMove is assigned nextMove and nextMove is removed from Queue
            if(x == nextMove.peek().getX() &&
               y == nextMove.peek().getY()) {
                setCurrentMove(nextMove.remove());
                // NOT SURE IF THIS HAD ANY EFFECT
                // WILL BE OBSOLETE IF WE JUST MAKE A DEEP COPY OF QUEUE WHEN COPYING
                //currentMove = new Move(nextMove.remove());
                
                //System.out.println("---------------------------------------");
                //System.out.println("currentMove equals nextMove coordinates");
                //System.out.printf("currentX: %d currentY: %d\n", currentMove.getX(), currentMove.getY());
            }
        
        }
        
        // update x, y of piece        
        if(direction == Direction.UP) {
            y--;
        } else if(direction == Direction.DOWN) {
            y++;
        } else if(direction == Direction.LEFT) {
            x--;
        } else if(direction == Direction.RIGHT) {
            x++;
        }

        //System.out.println("---------------------------------------");
        //System.out.println("move piece to currentMove");
        //System.out.printf("currentX: %d currentY: %d\n", currentMove.getX(), currentMove.getY());
        
        // update position of piece
        setCenterX(x * 20 + 10);
        setCenterY(y * 20 + 10);
    }
    
    public Tail copy()
    {   
        return new Tail(this.nextMove, x, y, direction);
    }
    
    public void setNextMove(Queue<Move> nextMove)
    {
        this.nextMove = nextMove;
    }
    
    public Queue<Move> getNextMove()
    {
        return nextMove;
    }
}
