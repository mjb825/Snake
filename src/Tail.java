import javafx.scene.shape.Circle;
import java.util.Queue;
/**
 * The tail of the snake that follows the head
 */
public class Tail extends Circle
{

    private Queue<Move> nextMoves; //stores all of the moves the head makes
    private Move nextMove; //the next move this tail is going to make
    private Move currentMove; //contains current x, y, and direction of piece
    
    public Tail()
    {
    }
    
    public Tail(Move currentMove)
    {
        super(8);
        setStyle("-fx-stroke: black; -fx-fill: green; -fx-stroke-width: 2;");
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
    
    public void updateFrame()
    {
        //compare currentMove to nextMove
        
        //if they match x and y, set direction of nextMove to currentMove
        
        // if nextMoves is not empty, pop into nextMove
        // else, set nextMove to null?
        
        // update x, y of piece
        /*
        if(currentMove.getDirection() == Direction.UP) {
            currentMove.setY(currentMove.getY() - 1);
        } else if(currentMove.getDirection() == Direction.DOWN) {
            currentMove.setY(currentMove.getY() + 1);
        } else if(currentMove.getDirection() == Direction.LEFT) {
            currentMove.setX(currentMove.getX() - 1);
        } else if(currentMove.getDirection() == Direction.RIGHT) {
            currentMove.setX(currentMove.getX() + 1);
        }
        */
        
        // update position of piece
        setCenterX(currentMove.getX() * 20 + 10);
        setCenterY(currentMove.getY() * 20 + 10);
    }
}
