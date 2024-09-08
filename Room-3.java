import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class Room implements Drawable {//Implements drawable interface
    public static final int SIZE = 50; // Use static field
    private Point pos;   //Field
    private Room exitEast;
    private Room exitWest;
    private Room exitNorth;
    private Room exitSouth;

    public Room(int x, int y) {   //Constructor to initialize all the variables
        this.pos = new Point(x, y);
        this.exitEast = null;
        this.exitWest = null; 
        this.exitNorth = null;
        this.exitSouth = null;
    }

    public void setEastExit(Room r) {//Method to set the exit 
        this.exitEast = r;
        r.exitWest = this;
    }

    public void setNorthExit(Room r) {
        this.exitNorth = r;
        r.exitSouth = this;
    }

    public void setWestExit(Room r) {
        this.exitWest = r;
        r.exitEast = this;
    }

    public void setSouthExit(Room r) {
        this.exitSouth = r;
        r.exitNorth = this;
    }

    public Point getPosition() {
        return pos;
    }

    public void draw(Graphics g) {  //Method to draw each room
        int x = pos.x;  //X and Y will be the ones I put in Room constructor
        int y = pos.y;
        int size = 50;
        int hallwayWidth = 10;

       //draw exit and way
       if (exitWest != null && exitSouth != null && exitEast != null) {
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
            g.drawLine(pos.x, pos.y, pos.x, y + size / 2 - hallwayWidth / 2); //West Exit
            g.drawLine(pos.x, y + size / 2 + hallwayWidth / 2, pos.x, pos.y + size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, y + size / 2 - hallwayWidth / 2);//East exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
            g.drawLine(x, y + size / 2 - hallwayWidth / 2, x - hallwayWidth, y + size / 2 - hallwayWidth / 2); // west hallway top
            g.drawLine(x, y + size / 2 + hallwayWidth / 2, x - hallwayWidth, y + size / 2 + hallwayWidth / 2); // west hallway bottom
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
       }
       else if (exitNorth != null && exitSouth != null && exitEast != null) {  
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size); //left
            g.drawLine(pos.x, pos.y, x + size / 2 - hallwayWidth / 2, pos.y );//North exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y , pos.x + size, pos.y);
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, y + size / 2 - hallwayWidth / 2);//East exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y, x + size / 2 - hallwayWidth / 2, y - hallwayWidth); // north hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y, x + size / 2 + hallwayWidth / 2, y - hallwayWidth); // north hallway right
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
       }
       else if (exitWest != null && exitSouth != null) {
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);//upper
            g.drawLine(pos.x + size, pos.y, pos.x + size , pos.y+size);//right
            g.drawLine(pos.x, pos.y, pos.x, y + size / 2 - hallwayWidth / 2); //West Exit
            g.drawLine(pos.x, y + size / 2 + hallwayWidth / 2, pos.x, pos.y + size);
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
            g.drawLine(x, y + size / 2 - hallwayWidth / 2, x - hallwayWidth, y + size / 2 - hallwayWidth / 2); // west hallway top
            g.drawLine(x, y + size / 2 + hallwayWidth / 2, x - hallwayWidth, y + size / 2 + hallwayWidth / 2); // west hallway bottom
       }
       else if (exitNorth != null && exitSouth != null) {
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size); //left
            g.drawLine(pos.x + size, pos.y, pos.x + size , pos.y+size);//right
            g.drawLine(pos.x, pos.y, x + size / 2 - hallwayWidth / 2, pos.y );//North exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y , pos.x + size, pos.y);
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y, x + size / 2 - hallwayWidth / 2, y - hallwayWidth); // north hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y, x + size / 2 + hallwayWidth / 2, y - hallwayWidth); // north hallway right
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
       }
       else if (exitNorth != null &&  exitEast != null) {
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size); //left
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y + size);//bottom
            g.drawLine(pos.x, pos.y, x + size / 2 - hallwayWidth / 2, pos.y );//North exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y , pos.x + size, pos.y);
            g.drawLine(pos.x + size , pos.y , pos.x + size , y + size / 2 - hallwayWidth / 2); //East Exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y, x + size / 2 - hallwayWidth / 2, y - hallwayWidth); // north hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y, x + size / 2 + hallwayWidth / 2, y - hallwayWidth); // north hallway right
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
       }
       else if (exitSouth != null && exitEast != null) { 
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);//upper
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size); //left
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, y + size / 2 - hallwayWidth / 2);//East exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
       }
       else if (exitWest != null && exitNorth != null) {
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y + size);//bottom
            g.drawLine(pos.x + size, pos.y, pos.x + size , pos.y+size);//right
            g.drawLine(pos.x, pos.y, x + size / 2 - hallwayWidth / 2, pos.y );//North exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y , pos.x + size, pos.y);
            g.drawLine(pos.x, pos.y , pos.x, y + size / 2 - hallwayWidth / 2); //West Exit
            g.drawLine(pos.x, y + size / 2 + hallwayWidth / 2, pos.x, pos.y + size);
            g.drawLine(x + size / 2 - hallwayWidth / 2, y, x + size / 2 - hallwayWidth / 2, y - hallwayWidth); // north hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y, x + size / 2 + hallwayWidth / 2, y - hallwayWidth); // north hallway right
            g.drawLine(x, y + size / 2 - hallwayWidth / 2, x - hallwayWidth, y + size / 2 - hallwayWidth / 2); // west hallway top
            g.drawLine(x, y + size / 2 + hallwayWidth / 2, x - hallwayWidth, y + size / 2 + hallwayWidth / 2); // west hallway bottom
       }
       else if (exitWest != null && exitEast != null) {
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);//upper
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y + size);//bottom
            g.drawLine(pos.x, pos.y , pos.x, y + size / 2 - hallwayWidth / 2); //West Exit
            g.drawLine(pos.x, y + size / 2 + hallwayWidth / 2, pos.x, pos.y + size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, y + size / 2 - hallwayWidth / 2);//East exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
            g.drawLine(x, y + size / 2 - hallwayWidth / 2, x - hallwayWidth, y + size / 2 - hallwayWidth / 2); // west hallway top
            g.drawLine(x, y + size / 2 + hallwayWidth / 2, x - hallwayWidth, y + size / 2 + hallwayWidth / 2); // west hallway bottom
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
       }
       else if (exitNorth != null) {
            g.drawLine(x + size / 2 - hallwayWidth / 2, y, x + size / 2 - hallwayWidth / 2, y - hallwayWidth); // north hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y, x + size / 2 + hallwayWidth / 2, y - hallwayWidth); // north hallway right
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size); //left
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y + size);//bottom
            g.drawLine(pos.x + size, pos.y, pos.x + size , pos.y+size);//right
            g.drawLine(pos.x, pos.y, x + size / 2 - hallwayWidth / 2, pos.y );//North exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y , pos.x + size, pos.y);
       }
       else if (exitSouth != null) {
            g.drawLine(x + size / 2 - hallwayWidth / 2, y + size, x + size / 2 - hallwayWidth / 2, y + size + hallwayWidth); // south hallway left
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, x + size / 2 + hallwayWidth / 2, y + size + hallwayWidth); // south hallway right
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);//upper
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, pos.y+size);
            g.drawLine(pos.x, pos.y + size, x + size / 2 - hallwayWidth / 2, pos.y + size);//South exit
            g.drawLine(x + size / 2 + hallwayWidth / 2, y + size, pos.x + size, pos.y + size);
       }
       else if (exitWest != null) {
            g.drawLine(x, y + size / 2 - hallwayWidth / 2, x - hallwayWidth, y + size / 2 - hallwayWidth / 2); // west hallway top
            g.drawLine(x, y + size / 2 + hallwayWidth / 2, x - hallwayWidth, y + size / 2 + hallwayWidth / 2); // west hallway bottom
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);
            g.drawLine(pos.x + size, pos.y, pos.x + size , pos.y+size);
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y + size);
            g.drawLine(pos.x, pos.y , pos.x, y + size / 2 - hallwayWidth / 2); //West Exit
            g.drawLine(pos.x, y + size / 2 + hallwayWidth / 2, pos.x, pos.y + size);
       }
       else if (exitEast != null) {
            g.drawLine(x + size, y + size / 2 - hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 - hallwayWidth / 2); // east hallway top
            g.drawLine(x + size, y + size / 2 + hallwayWidth / 2, x + size + hallwayWidth, y + size / 2 + hallwayWidth / 2); // east hallway bottom
            g.drawLine(pos.x, pos.y, pos.x + size, pos.y);
            g.drawLine(pos.x, pos.y, pos.x, pos.y + size);
            g.drawLine(pos.x, pos.y + size, pos.x + size, pos.y+size);
            g.drawLine(pos.x + size, pos.y, pos.x + size, y + size / 2 - hallwayWidth / 2);//East exit
            g.drawLine(pos.x + size, y + size / 2 + hallwayWidth / 2, pos.x + size, pos.y + size);
       }
    }

    public boolean hasNorthExit() {//return false if room has no northexit
        return exitNorth != null;
    }
    
    public boolean hasSouthExit() {
        return exitSouth != null;
    }

    public boolean hasEastExit() {
        return exitEast != null;
    }

    public boolean hasWestExit() {
        return exitWest != null;
    }

    public Room getNorthExit() {//Just get exitnorth
        return exitNorth;
    }

    public Room getSouthExit() {
        return exitSouth;
    }

    public Room getEastExit() {
        return exitEast;
    }

    public Room getWestExit() {
        return exitWest;
    }

    public void clearExits() {//clear exits for next random exit
        exitNorth = null;
        exitSouth = null;
        exitEast = null;
        exitWest = null;
    }
}
