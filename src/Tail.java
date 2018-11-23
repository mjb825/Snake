import javafx.scene.shape.Circle;

/**
 * The pieces that make up the Snake
 * @author Matthew Below
 */
public class Tail extends Circle
{
    private Move currentMove;
    private Move previousMove;
    
    public Tail()
    {
    }
    
    public Tail(Move currentMove)
    {
        // circle properties
        super(6);
        //[change] fun times
        //super(((int)(Math.random()*8)) + 4);
        setStyle("-fx-stroke: black; -fx-fill: blue; -fx-stroke-width: 2;");
        
        // piece properties
        this.currentMove = currentMove;
    }
    
    public Tail(Move currentMove, String color)
    {
        // circle properties
        super(6);
        setStyle("-fx-stroke: black; -fx-fill: " + color + "; -fx-stroke-width: 2;");
        
        // piece properties
        //[change] does it need to be currentMove.copy()
        this.currentMove = currentMove;
        
        // set initial position of piece
        setCenterX(this.currentMove.getX() * 20 + 10);
        setCenterY(this.currentMove.getY() * 20 + 10);
    }
    
    public void updateFrame(Tail prev)
    {
        previousMove = currentMove.copy();
        
        currentMove = prev.getPreviousMove().copy();
        
        // update position of piece
        setCenterX(currentMove.getX() * 20 + 10);
        setCenterY(currentMove.getY() * 20 + 10);
    }
    
    public void updateFrame(Move currentMove)
    {
        previousMove = this.currentMove.copy();
        
        this.currentMove = currentMove.copy();
        
        // update position of piece
        setCenterX(this.currentMove.getX() * 20 + 10);
        setCenterY(this.currentMove.getY() * 20 + 10);
    }
    
    // return copy of piece
    public Tail copy()
    {   
        return new Tail(currentMove.copy());
    }
    
    public Move getCurrentMove()
    {
        return currentMove;
    }
    
    public Move getPreviousMove()
    {
        return previousMove;
    }
}
