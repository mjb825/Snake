/**
 * Move for Snake pieces that holds Direction and x,y coordinates
 * @author Matthew Below
 */
public class Move
{
    private Direction direction;
    private double x;
    private double y;

    public Move()
    {  
    }
    
    public Move(double x, double y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    
    public Move(Move move)
    {
        this.x = move.x;
        this.y = move.y;
        this.direction = move.direction;
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
    
    public Move copy()
    {
        return new Move(this.x, this.y, this.direction);
    }
}
