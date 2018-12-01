import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The pieces that make up the Snake
 * @author Matthew Below
 */
public class Tail extends Circle
{
    private Move currentMove;
    private Move previousMove;
    
    public Tail(){}
    
    public Tail(Move currentMove, Color color)
    {
        // circle properties
        super(6);
        setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
        setFill(color);
        
        // piece properties
        this.currentMove = currentMove;
        
        // set initial position of piece
        setCenterX(this.currentMove.getX() * 20 + 10);
        setCenterY(this.currentMove.getY() * 20 + 10);
    }
    
    public void updateFrame(Tail prev)
    {
        // update piece properties
        previousMove = currentMove.copy();
        currentMove = prev.getPreviousMove().copy();
        
        // update position of piece
        setCenterX(currentMove.getX() * 20 + 10);
        setCenterY(currentMove.getY() * 20 + 10);
    }
    
    public void updateFrame(Move currentMove)
    {
        // update piece properties
        previousMove = this.currentMove.copy();
        this.currentMove = currentMove.copy();
        
        // update position of piece
        setCenterX(this.currentMove.getX() * 20 + 10);
        setCenterY(this.currentMove.getY() * 20 + 10);
    }
    
    // return copy of piece
    public Tail copy(Color color)
    {   
        return new Tail(currentMove.copy(), color);
    }
    
    public Move getCurrentMove()
    {
        return currentMove;
    }
    
    public Move getPreviousMove()
    {
        return previousMove;
    }
    
    public void setColor(Color color)
    {
        setFill(color);
    }
}
