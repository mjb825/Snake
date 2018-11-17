import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * container class for SnakeBody pieces
 * - keep track of position of each piece
 * - used for if you collide into yourself
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
        currentMove = new Move(x, y, Direction.UP);
        previousMove = currentMove.copy();
        

        tail.add(new Tail(currentMove.copy(), "red"));
    }
    
    public void add(int x, int y, Direction direction)
    {
        // SETTING THIS TO DIRECTION.UP CAUSED THE BUG!!!
        Tail piece = new Tail(new Move(x, y, direction));
        // THIS SOLVED THE INFAMOUSE MOVEMENT BUG
        //[REMOVE] this wasn't even needed!!!
        //if(piece.getNextMove().peek() != null)
        //    piece.setCurrentMove(piece.getNextMove().remove());
        Queue next = tail.get(tail.size() - 1).getNextMove();
        piece.setNextMove(new LinkedList<>(next));
        System.out.println(next.size());
        System.out.println(tail.get(tail.size() - 1));
        //while(next.peek() != null)
            //next.remove();
        //piece.setNextMove(next);
        tail.add(piece);
    }
    
    public void add()
    {
        Tail lastPiece = tail.get(tail.size() - 1).copy();
        Move move = new Move(lastPiece.x, lastPiece.y, lastPiece.direction);
        Direction direction = move.getDirection();
        if(direction == Direction.UP)
            move.setY(move.getY() + 1); 
        else if(direction == Direction.DOWN)
            move.setY(move.getY() - 1);
        else if(direction == Direction.LEFT)
            move.setX(move.getX() + 1);
        else if(direction == Direction.RIGHT)
            move.setX(move.getX() - 1);
        
        
    
            
        tail.add(lastPiece);
        
        //return lastPiece;
    }
    
    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame(int rangeX, int rangeY)
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
        if(currentMove.getDirection() == Direction.UP) {
            currentMove.setY(currentMove.getY() - 1);
        } else if(currentMove.getDirection() == Direction.DOWN) {
            currentMove.setY(currentMove.getY() + 1);
        } else if(currentMove.getDirection() == Direction.LEFT) {
            currentMove.setX(currentMove.getX() - 1);
        } else if(currentMove.getDirection() == Direction.RIGHT){
            currentMove.setX(currentMove.getX() + 1);
        }
        
        //tail.get(0).setCurrentMove(currentMove);
        for(int i = 0; i < tail.size(); i++) {
            tail.get(i).updateFrame();
        }
    }
    
    public void changeDirection(Direction direction)
    {
        
        // stop player from going in opposite direction or changing to the current direction
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.UP && direction == Direction.DOWN) ||
           (currentMove.getDirection() == Direction.DOWN && direction == Direction.UP) ||
           (currentMove.getDirection() == Direction.LEFT && direction == Direction.RIGHT) ||
           (currentMove.getDirection() == Direction.RIGHT && direction == Direction.LEFT)) {
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
}
