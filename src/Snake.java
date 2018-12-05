import java.util.ArrayList;
import javafx.scene.paint.Color;

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
    
    private ArrayList<Color> headColors;// = new ArrayList<Color>();
    private ArrayList<Color> tailColors;// = new ArrayList<Color>();
    private ArrayList<Double> headSizes;//1 = new Array
    private ArrayList<Double> tailSizes;
    
    
    private int headColorPos = 0;
    private int tailColorPos = 0;
    private int headSizePos = 0;
    private int tailSizePos = 0;
    
    private boolean headUnique;
    private boolean sequence;
    private boolean frozen;
    private boolean headUniqueSize;
    private boolean sequenceSize;
    private boolean frozenSize;
    
    
    public Snake()
    {
    }
    
    public Snake(double x, double y, Direction direction, MainMenu menu)
    {
        
        // get head and tail colors and sizes from settings and boolean values from settings
        // assign default valuses if none is set
        
        // head colors
        if(menu.getSettings().getHeadColors().isEmpty()) {
            headColors = new ArrayList<Color>();
            headColors.add(Color.RED);
        } else headColors = menu.getSettings().getHeadColors();

        // tail colors
        if(menu.getSettings().getTailColors().isEmpty()) {
            tailColors = new ArrayList<Color>();
            tailColors.add(Color.BLUE);
        } else tailColors = menu.getSettings().getTailColors();
        
        // head sizes
        if(menu.getSettings().getHeadSizes().isEmpty()) {
            headSizes = new ArrayList<Double>();
            headSizes.add(6.0);
        } else headSizes = menu.getSettings().getHeadSizes();
        
        // tail sizes
        if(menu.getSettings().getTailSizes().isEmpty()) {
            tailSizes = new ArrayList<Double>();
            tailSizes.add(6.0);
        } else tailSizes = menu.getSettings().getTailSizes();
        
        
        // get game options from settings
        headUnique = menu.getSettings().headUnique();
        sequence = menu.getSettings().sequence();
        frozen = menu.getSettings().frozen();
        headUniqueSize = menu.getSettings().headUniqueSize();
        sequenceSize = menu.getSettings().sequenceSize();
        frozenSize = menu.getSettings().frozenSize();
        
        tail = new ArrayList<>();
        currentMove = new Move(x, y, direction);
        previousMove = currentMove.copy();
        
        // add initial head piece to snake
        Tail head = new Tail(currentMove.copy(), headColors.get(0), headSizes.get(0), menu.getSettings().strColor, menu.getSettings().strWidth);
        
        // fix so first TAIL piece doesn't start off with color/size gotten by HEAD piece
        if(frozen && !headUnique)
            headColorPos = (headColorPos + 1) % headColors.size();
        if(frozenSize && !headUniqueSize)
            headSizePos = (headSizePos + 1) % headSizes.size();
        
        tail.add(head);
        
        
    }

    // add piece to Snake's tail
    public void add(Tail piece)
    {
        tail.add(piece);
    }

    public ArrayList<Tail> getTail()
    {
        return tail;
    }
    
    public void updateFrame(double movement)
    {
        // update position of snake, which the head and tail of snake follow
        if(currentMove.getDirection() == Direction.N) {
            currentMove.setY(currentMove.getY() - movement);
        }
        else if(currentMove.getDirection() == Direction.S) {
            currentMove.setY(currentMove.getY() + movement);
        }
        else if(currentMove.getDirection() == Direction.E) {
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.W){
            currentMove.setX(currentMove.getX() + movement);
        }
        else if(currentMove.getDirection() == Direction.NE){
            currentMove.setY(currentMove.getY() - movement);
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.NW){
            currentMove.setY(currentMove.getY() - movement);
            currentMove.setX(currentMove.getX() + movement);
        }
        else if(currentMove.getDirection() == Direction.SE){
            currentMove.setY(currentMove.getY() + movement);
            currentMove.setX(currentMove.getX() - movement);
        }
        else if(currentMove.getDirection() == Direction.SW){
            currentMove.setY(currentMove.getY() + movement);
            currentMove.setX(currentMove.getX() + movement);
        }

        updateColors();
        updateSizes();
        
        // update head of snake
        getFirst().updateFrame(currentMove);
        
        // update each tail piece of snake
        for(int i = 1; i < tail.size(); i++) {
            tail.get(i).updateFrame(tail.get(i - 1));
        }
    }
    
    public void updateColors()
    {

        if(headUnique) {
            
            tail.get(0).setColor(headColors.get(headColorPos));
            
            if(!frozen) {
                
                if(sequence) {

                    // i = snake pieces
                    for(int i = 1; i < tail.size(); i++) {

                        // j = number of available colors
                        for(int j = 0; j < tailColors.size(); j++) {

                            if(i % tailColors.size() == j) {

                                //tail.get(tail.size() - i - 1).setColor(tailColors.get((tailColorPos + j) % tailColors.size()));
                                tail.get(i).setColor(tailColors.get((tailColorPos + j) % tailColors.size()));
                                // stop looking through colors because snake piece won't match anymore
                                break;

                            }  

                        }

                    }

                }

                else {

                    for(int i = 1; i < tail.size(); i++) {
                        //tail.get(tail.size() - i - 1).setColor(tailColors.get(tailColorPos));
                        tail.get(i).setColor(tailColors.get(tailColorPos));
                    }

                }
                
                // update position for tail color if isn't frozen, in which case determineColor will update
                tailColorPos = (tailColorPos + 1) % tailColors.size();
            }
            
            // update position for head color
            headColorPos = (headColorPos + 1) % headColors.size();
        }
           
        else {
            
            if(!frozen) {
            
                if(sequence) {

                    // i = snake pieces
                    for(int i = 0; i < tail.size(); i++) {

                        // j = number of available colors
                        for(int j = 0; j < headColors.size(); j++) {

                            if(i % headColors.size() == j) {

                                //tail.get(tail.size() - i - 1).setColor(headColors.get((snakeColorPos + j) % headColors.size()));
                                tail.get(i).setColor(headColors.get((headColorPos + j) % headColors.size()));
                                // stop looking through colors because snake piece won't match anymore
                                break;

                            }  

                        }

                    }

                }

                else {

                    for(int i = 0; i < tail.size(); i++) {
                        //tail.get(tail.size() - i - 1).setColor(headColors.get(headColorPos));
                        tail.get(i).setColor(headColors.get(headColorPos));
                    }

                }
                // update position for head color if isn't frozen, in which case determineColor will update
                headColorPos = (headColorPos + 1) % headColors.size();
            }
            
        }
        
    }
    
    // determine color of newly added tail piece
    public Color determineColor()
    {
        
        Color color = headUnique ? tailColors.get(tailColorPos) : headColors.get(headColorPos);
        
        // if it isn't frozen, updateColors will change appropriate ColorPos
        if(frozen && headUnique)
            tailColorPos = (tailColorPos + 1) % tailColors.size();
        else if(frozen) 
            headColorPos = (headColorPos + 1) % headColors.size();
        
        return color;
        
    }
    

    public void updateSizes()
    {
      
        if(headUniqueSize) {
            
            tail.get(0).setSize(headSizes.get(headSizePos));
            
            if(!frozenSize) {
            
                if(sequenceSize) {

                    // i = snake pieces
                    for(int i = 1; i < tail.size(); i++) {

                        // j = number of available sizes
                        for(int j = 0; j < tailSizes.size(); j++) {

                            if(i % tailSizes.size() == j) {

                                //tail.get(tail.size() - i - 1).setSize(tailSizes.get((tailSizePos + j) % tailSizes.size()));
                                tail.get(i).setSize(tailSizes.get((tailSizePos + j) % tailSizes.size()));
                                // stop looking through sizes because snake piece won't match anymore
                                break;

                            }  

                        }

                    }

                }

                else {

                    for(int i = 1; i < tail.size(); i++) {
                        //tail.get(tail.size() - i - 1).setSize(tailSizes.get(tailSizePos));
                        tail.get(i).setSize(tailSizes.get(tailSizePos));
                    }

                }
            
                // update position for tail size if isn't frozen, in which case determineSize will update
                tailSizePos = (tailSizePos + 1) % tailSizes.size();
            }
            
            // update position for head size
            headSizePos = (headSizePos + 1) % headSizes.size();
        }
           
        else {
            
            if(!frozenSize) {
            
                if(sequenceSize) {

                    // i = snake pieces
                    for(int i = 0; i < tail.size(); i++) {

                        // j = number of available sizes
                        for(int j = 0; j < headSizes.size(); j++) {

                            if(i % headSizes.size() == j) {

                                //tail.get(tail.size() - i - 1).setSize(headSizes.get((snakeSizePos + j) % headSizes.size()));
                                tail.get(i).setSize(headSizes.get((headSizePos + j) % headSizes.size()));
                                // stop looking through sizes because snake piece won't match anymore
                                break;

                            }  

                        }

                    }

                }

                else {

                    for(int i = 0; i < tail.size(); i++) {
                        //tail.get(tail.size() - i - 1).setSize(headSizes.get(headSizePos));
                        tail.get(i).setSize(headSizes.get(headSizePos));
                    }

                }
            
                // update position for head size if isn't frozen, in which case determineSize will update
                headSizePos = (headSizePos + 1) % headSizes.size();
            }
            
        }
        
    }
    
    // determine size of newly added tail piece
    public Double determineSize()
    {

        Double size = headUniqueSize ? tailSizes.get(tailSizePos) : headSizes.get(headSizePos);
        
        // if it isn't frozen, updateSizes will change appropriate SizePos
        if(frozenSize && headUniqueSize)
            tailSizePos = (tailSizePos + 1) % tailSizes.size();
        else if(frozenSize)
            headSizePos = (headSizePos + 1) % headSizes.size();
        
        return size;
        
    }
    
    
    public void changeDirection(Direction direction)
    {
        // stop player from going in opposite direction or changing to the current direction
        if((currentMove.getDirection() == direction) ||
           (previousMove.getX() == currentMove.getX() && previousMove.getY() == currentMove.getY()) ||
           (currentMove.getDirection() == Direction.N && direction == Direction.S) ||
           (currentMove.getDirection() == Direction.S && direction == Direction.N) ||
           (currentMove.getDirection() == Direction.E && direction == Direction.W) ||
           (currentMove.getDirection() == Direction.W && direction == Direction.E) ||
           (currentMove.getDirection() == Direction.NE && direction == Direction.SW) ||
           (currentMove.getDirection() == Direction.NW && direction == Direction.SE) ||
           (currentMove.getDirection() == Direction.SE && direction == Direction.NW) ||
           (currentMove.getDirection() == Direction.SW && direction == Direction.NE)) {
               return;  
        }
        
        // update current and previous move of snake
        currentMove.setDirection(direction);
        previousMove = currentMove.copy();
    }
    
    // get first piece of snake
    public Tail getFirst()
    {
        return tail.get(0);
    }
    
    // get last piece of snake
    public Tail getLast()
    {
        return tail.get(tail.size() - 1);
    }
}
