/**
 * Move for Snake pieces that holds Direction and x,y coordinates
 * @author Matthew Below
 */
public class Move
{
    private Direction direction;
    private int x;
    private int y;


    public Move()
    {
        
    }
    
    public Move(int x, int y, Direction direction)
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
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
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
