import javafx.scene.shape.Circle;
import java.util.Queue;
import java.util.LinkedList;

/**
 * The pieces that make up the Snake
 * @author Matthew Below
 */
public class Tail extends Circle
{
    public double x;
    public double y;
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

    public Tail(Queue<Move> nextMove, double x, double y, Direction direction)
    {
        // circle properties
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: blue; -fx-stroke-width: 2;");
        
        // piece properties
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.nextMove = new LinkedList<>(nextMove);
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
    
    public void updateFrame(double movement)
    {
        
        if(nextMove.peek() != null) {
            
            //compare x,y of currentMove to x,y of nextMove
            //if they match x and y, currentMove is assigned nextMove and nextMove is removed from Queue
            if(x == nextMove.peek().getX() && y == nextMove.peek().getY()) {
                setCurrentMove(nextMove.remove());
            }
        
        }
        
        // update x, y of piece        
        if(direction == Direction.N) {
            y = y - movement;
        }
        else if(direction == Direction.S) {
            y = y + movement;
        }
        else if(direction == Direction.E) {
            x = x - movement;
        } 
        else if(direction == Direction.W) {
            x = x + movement;
        }
        else if(direction == Direction.NE) {
            y = y - movement;
            x = x - movement;
        }
        else if(direction == Direction.NW) {
            y = y - movement;
            x = x + movement;
        }
        else if(direction == Direction.SE) {
            y = y + movement;
            x = x - movement;
        }
        else if(direction == Direction.SW) {
            y = y + movement;
            x = x + movement;
        }

        // update position of piece
        setCenterX(x * 20 + 10);
        setCenterY(y * 20 + 10);
    }
    
    // return copy of piece
    public Tail copy()
    {   
        return new Tail(nextMove, x, y, direction);
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
    
    public double getX()
    {
        return x;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public Direction getDirection()
    {
        return direction;
    }
    
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
}
