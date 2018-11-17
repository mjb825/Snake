import javafx.scene.shape.Circle;
import java.util.Queue;
import java.util.LinkedList;
/**
 * The pieces that make up the Snake
 */
public class Tail extends Circle
{

    public int x;
    public int y;
    public Direction direction;
    private Queue<Move> nextMove; //the next moves this tail is going to make
    
    public Tail()
    {
    }
    
    public Tail(Move currentMove)
    {
        // circle properties
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: none; -fx-stroke-width: 2;");
        
        // piece properties
        x = currentMove.getX();
        y = currentMove.getY();
        direction = currentMove.getDirection();
        nextMove = new LinkedList<>();
    }
    
    public Tail(Move currentMove, String color)
    {
        // circle properties
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: " + color + "; -fx-stroke-width: 2;");
        
        // piece properties
        x = currentMove.getX();
        y = currentMove.getY();
        direction = currentMove.getDirection();
        nextMove = new LinkedList<>();
        
        // set initial position of piece
        setCenterX(x * 20 + 10);
        setCenterY(y * 20 + 10);
    }

    public Tail(Queue<Move> nextMove, int x, int y, Direction direction)
    {
        this.nextMove = nextMove;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    // set current x, y, and direction of piece
    public void setCurrentMove(Move currentMove)
    {
        x = currentMove.getX();
        y = currentMove.getY();
        direction = currentMove.getDirection();
    }
    
    // add a move to nextMove Queue
    public void addNextMove(Move nextMove)
    {
        this.nextMove.add(nextMove);
    }
    
    // return stats of piece for debugging purposes
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
            
            //compare x,y of currentMove to x,y of nextMove
            //if they match x and y, currentMove is assigned nextMove and nextMove is removed from Queue
            if(x == nextMove.peek().getX() && y == nextMove.peek().getY()) {
                setCurrentMove(nextMove.remove());
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

        // update position of piece
        setCenterX(x * 20 + 10);
        setCenterY(y * 20 + 10);
    }
    
    // return copy of piece
    //[remove] this isn't necessary imo, just have getDirection, getX, getY, getNextMove
    public Tail copy()
    {   
        return new Tail(this.nextMove, x, y, direction);
    }
    
    // set nextMove Queue of piece
    public void setNextMove(Queue<Move> nextMove)
    {
        this.nextMove = nextMove;
    }
    
    // get nextMove Queue of piece
    public Queue<Move> getNextMove()
    {
        return nextMove;
    }
    
}
