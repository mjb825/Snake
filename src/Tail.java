import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * The pieces that make up the Snake
 * @author Matthew Below
 */
public class Tail extends Circle
{
    private Move currentMove;
    private Move previousMove;
    
    public Tail(){}
    
    public Tail(Move currentMove, Color color, Double size, Color strColor, int strWidth)
    {
        // circle properties
        super(size);
        setStroke(strColor);
        setStrokeWidth(strWidth);
        setStrokeType(StrokeType.OUTSIDE);
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
    public Tail copy(Color color, Double size, Color strColor, int strWidth)
    {   
        return new Tail(currentMove.copy(), color, size, strColor, strWidth);
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

    public void setSize(Double size)
    {
        setRadius(size);
    }
    
}
