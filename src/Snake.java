import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * container class for pieces of Snake
 * - keep track of position of each piece
 * - used for if you collide into yourself
 * @author Matthew Below
 */
public class Snake
{
    private ArrayList<Tail> tail;
    private Move currentMove;
    private Move previousMove;
    
    public Snake(){}
    
    public Snake(int x, int y)
    {
        tail = new ArrayList<>();
        currentMove = new Move(x, y, Direction.N);
        previousMove = currentMove.copy();
        

        tail.add(new Tail(currentMove.copy(), "red"));
    }
    
    public void add(int x, int y, Direction direction)
    {
        
        Tail piece = new Tail(new Move(x, y, direction));
        Queue next = tail.get(tail.size() - 1).getNextMove();
        piece.setNextMove(new LinkedList<>(next));
        
        tail.add(piece);
    }
    
    public void add(Tail piece)
    {
        tail.add(piece);
    }
    
    public void add()
    {
        Tail lastPiece = tail.get(tail.size() - 1).copy();
        Move move = new Move(lastPiece.x, lastPiece.y, lastPiece.direction);
        Direction direction = move.getDirection();
        if(direction == Direction.N)
            move.setY(move.getY() + 1); 
        else if(direction == Direction.S)
            move.setY(move.getY() - 1);
        else if(direction == Direction.E)
            move.setX(move.getX() + 1);
        else if(direction == Direction.W)
            move.setX(move.getX() - 1);
        
        
    
            
        tail.add(lastPiece);
        
        //return lastPiece;
    }
    
    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame()
    {
        //update position of head of snake
        //temporary to test movement
        // - GameField will handle what happens when the head hits the border (i.e., game over)
        
        /*
        //THIS CHANGES THE POSITION OF THE PIECE???!!!?!?!?!!?!?!!!!!!
        if(currentMove.getDirection() == Direction.UP && currentMove.getY() > 0) {
            currentMove.setY(currentMove.getY() - 1);
        } else if(currentMove.getDirection() == Direction.DOWN && currentMove.getY() < (rangeY - 1)) {
            currentMove.setY(currentMove.getY() + 1);
        } else if(currentMove.getDirection() == Direction.LEFT && currentMove.getX() > 0) {
            currentMove.setX(currentMove.getX() - 1);
        } else if(currentMove.getDirection() == Direction.RIGHT && currentMove.getX() < (rangeX - 1)){
            currentMove.setX(currentMove.getX() + 1);
        }
        */
        //THIS CHANGES THE POSITION OF THE PIECE???!!!?!?!?!!?!?!!!!!!
        if(currentMove.getDirection() == Direction.N) {
            currentMove.setY(currentMove.getY() - 1);
        }
        else if(currentMove.getDirection() == Direction.S) {
            currentMove.setY(currentMove.getY() + 1);
        }
        else if(currentMove.getDirection() == Direction.E) {
            currentMove.setX(currentMove.getX() - 1);
        }
        else if(currentMove.getDirection() == Direction.W){
            currentMove.setX(currentMove.getX() + 1);
        }
        
        // update each piece of snake
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).updateFrame();
        }
    }
    
    public void changeDirection(Direction direction)
    {
        
        // stop player from going in opposite direction or changing to the current direction
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.N && direction == Direction.S) ||
           (currentMove.getDirection() == Direction.S && direction == Direction.N) ||
           (currentMove.getDirection() == Direction.E && direction == Direction.W) ||
           (currentMove.getDirection() == Direction.W && direction == Direction.E)) {
               return;  
        }
        
        // update current and previous move of snake
        currentMove.setDirection(direction);
        previousMove = currentMove.copy();
        
        // add move to queue of all tail pieces
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).addNextMove(currentMove.copy());
        }

    }
    
    public Tail getFirst()
    {
        return tail.get(0);
    }
    
    public Tail getLast()
    {
        return tail.get(tail.size() - 1);
    }
}
