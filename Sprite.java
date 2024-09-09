import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Sprite extends JPanel implements Drawable {//Implements drawable interface
    protected Room currentRoom;
    protected ImageIcon image;

    public Sprite() { // Constructor to set to null
        this.currentRoom = null;
        this.image = null; 
    }

    public void setCurrentRoom(Room r) {
        this.currentRoom = r;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public void draw(Graphics g) {
        if (currentRoom != null && image != null) {
            Point pos = currentRoom.getPosition();
            int size = 50; 
            int imageWidth = image.getIconWidth();
            int imageHeight = image.getIconHeight();
            int x = pos.x + (size - imageWidth) / 2;
            int y = pos.y + (size - imageHeight) / 2;
            g.drawImage(image.getImage(), x, y, this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void moveSouth() {//if room has specific exit, update current room on the specific exit   
        if (currentRoom.hasSouthExit()) {
            currentRoom = currentRoom.getSouthExit();
        }
    }

    public void moveNorth() {
        if (currentRoom.hasNorthExit()) {
            currentRoom = currentRoom.getNorthExit();
        }
    }

    public void moveEast() {
        if (currentRoom.hasEastExit()) {
            currentRoom = currentRoom.getEastExit();
        }
    }

    public void moveWest() {
        if (currentRoom.hasWestExit()) {
            currentRoom = currentRoom.getWestExit();
        }
    }

    public static class Nephi extends Sprite {
        private boolean hasSword;//field   (2 points) Show where you declare a field to keep track of whether Nephi has the sword or not.
        private ImageIcon normalIcon;
        private ImageIcon armedIcon;

        public Nephi() {
            super();
            this.normalIcon = new ImageIcon("nephi.png");
            this.armedIcon = new ImageIcon("nephiarmed.png"); //image of nephi with sword
            this.image = normalIcon;
        }

        public void pickUpSword() {//check if nephi has sword, and change image
            this.hasSword = true;
            this.image = armedIcon;
        }

        public boolean isArmed() {//Returns if nephi has sword or not(t or f)
            return hasSword;
        }

        public void reset() {
            this.hasSword = false;
            this.image = normalIcon;
        }
    }

    public static class Sword extends Sprite {
        public Sword() {
            super(); 
            this.image = new ImageIcon("sword.png"); 
        }
    }

    public static class Plates extends Sprite {
        public Plates() {
            super(); 
            this.image = new ImageIcon("plates.png"); 
        }
    }

    public static class Laban extends Sprite {
        public Laban() {
            super(); 
            this.image = new ImageIcon("laban.png"); 
        }
    }
}
