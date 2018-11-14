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
    
    public Tail()
    {
    }
    
    public Tail(Move currentMove)
    {
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: green; -fx-stroke-width: 2;");
        nextMove = new LinkedList<>();
        this.currentMove = currentMove;
        //nextMove.add(new Move(20, 8, Direction.RIGHT));
    }

    public Tail(Queue<Move> nextMove, Move currentMove)
    {
        this.nextMove = nextMove;
        this.currentMove = currentMove;
    }
    
    public Move getCurrentMove()
    {
        return currentMove;
    }
    
    public void setCurrentMove(Move currentMove)
    {
        this.currentMove = currentMove;
    }
    
    public void addNextMove(Move nextMove)
    {
        this.nextMove.add(nextMove);
    }
    
    public void updateFrame()
    {
        
        if(nextMove.peek() != null) {
            
            //System.out.println("---------------------------------------");
            //System.out.println("nextMove not empty");
            //System.out.printf("currentX: %d currentY: %d nextX: %d nextY: %d\n", currentMove.getX(), currentMove.getY(), nextMove.peek().getX(), nextMove.peek().getY());
            
            //compare x,y of currentMove to x,y of nextMove
            //if they match x and y, currentMove is assigned nextMove and nextMove is removed from Queue
            if(currentMove.getX() == nextMove.peek().getX() &&
               currentMove.getY() == nextMove.peek().getY()) {
                currentMove = nextMove.remove();
                // NOT SURE IF THIS HAD ANY EFFECT
                // WILL BE OBSOLETE IF WE JUST MAKE A DEEP COPY OF QUEUE WHEN COPYING
                //currentMove = new Move(nextMove.remove());
                
                //System.out.println("---------------------------------------");
                //System.out.println("currentMove equals nextMove coordinates");
                //System.out.printf("currentX: %d currentY: %d\n", currentMove.getX(), currentMove.getY());
            }
        
        }
        
        // update x, y of piece        
        if(currentMove.getDirection() == Direction.UP) {
            currentMove.setY(currentMove.getY() - 1);
        } else if(currentMove.getDirection() == Direction.DOWN) {
            currentMove.setY(currentMove.getY() + 1);
        } else if(currentMove.getDirection() == Direction.LEFT) {
            currentMove.setX(currentMove.getX() - 1);
        } else if(currentMove.getDirection() == Direction.RIGHT) {
            currentMove.setX(currentMove.getX() + 1);
        }

        //System.out.println("---------------------------------------");
        //System.out.println("move piece to currentMove");
        //System.out.printf("currentX: %d currentY: %d\n", currentMove.getX(), currentMove.getY());
        
        // update position of piece
        setCenterX(currentMove.getX() * 20 + 10);
        setCenterY(currentMove.getY() * 20 + 10);
    }
    
    public Tail copy()
    {   
        return new Tail(this.nextMove, this.currentMove);
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
