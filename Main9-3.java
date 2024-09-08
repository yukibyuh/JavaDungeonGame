import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;

public class Main9 extends JPanel implements KeyListener {
    private Room r1, r2, r3, r4, r5;
    private Room r6, r7, r8, r9, r10;
    private Room r11, r12, r13, r14, r15;
    private Room r16, r17, r18, r19, r20;
    private Room r21, r22, r23, r24, r25;

    private Sprite.Nephi nephi; // Nephi field
    private Sprite.Sword sword;
    private Sprite.Plates plates;
    private Sprite.Laban laban;

    private ArrayList<Drawable> drawables;//Drawable
    private Timer labanTimer;//This ArrayList will store objects that implement the Drawable interface.
    private Room[] rooms; // Declare rooms as an instance variable

    public Main9() {
        // Set the background color to gray
        setBackground(Color.GRAY);

        drawables = new ArrayList<>();//make new array

        r1 = new Room(130, 10);
        r2 = new Room(190, 10);
        r3 = new Room(250, 10);

        r4 = new Room(70, 70);
        r5 = new Room(130, 70);
        r6 = new Room(190, 70);
        r7 = new Room(250, 70);
        r8 = new Room(310, 70);

        r9 = new Room(70, 130);
        r10 = new Room(130, 130);
        r11 = new Room(190, 130);
        r12 = new Room(250, 130);
        r13 = new Room(310, 130);

        r14 = new Room(10, 190);
        r15 = new Room(70, 190);
        r16 = new Room(130, 190);
        r17 = new Room(190, 190);
        r18 = new Room(250, 190);
        r19 = new Room(310, 190);
        r20 = new Room(370, 190);

        r21 = new Room(10, 250);
        r22 = new Room(70, 250);
        r23 = new Room(130, 250);

        r24 = new Room(10, 310);
        r25 = new Room(70, 310);

        // put all rooms in an array
        rooms = new Room[] {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25};
        for (Room room : rooms) {
            drawables.add(room);
        }

        r1.setEastExit(r2);
        r2.setWestExit(r1);
        r2.setEastExit(r3);
        r2.setSouthExit(r6);
        r3.setWestExit(r2);
        r3.setSouthExit(r7);

        r4.setEastExit(r5);
        r5.setWestExit(r4);
        r5.setSouthExit(r10);
        r6.setSouthExit(r11);
        r6.setNorthExit(r2);
        r7.setEastExit(r8);
        r7.setNorthExit(r3);
        r8.setSouthExit(r13);
        r8.setWestExit(r7);

        r9.setEastExit(r10);
        r9.setSouthExit(r15);
        r10.setNorthExit(r5);
        r10.setWestExit(r9);
        r11.setEastExit(r12);
        r11.setNorthExit(r6);
        r12.setSouthExit(r18);
        r12.setWestExit(r11);
        r13.setSouthExit(r19);
        r13.setNorthExit(r8);

        r14.setSouthExit(r21);
        r15.setSouthExit(r22);
        r15.setNorthExit(r9);
        r16.setSouthExit(r23);
        r16.setEastExit(r17);
        r17.setEastExit(r18);
        r17.setWestExit(r16);
        r18.setNorthExit(r12);
        r18.setWestExit(r17);
        r19.setEastExit(r20);
        r19.setNorthExit(r13);

        r21.setSouthExit(r24);
        r21.setNorthExit(r14);
        r22.setNorthExit(r15);
        r22.setSouthExit(r25);
        r22.setEastExit(r23);
        r23.setNorthExit(r16);
        r23.setWestExit(r22);

        r24.setEastExit(r25);
        r24.setNorthExit(r21);
        r25.setWestExit(r24);
        r25.setNorthExit(r22);

        nephi = new Sprite.Nephi();
        nephi.setCurrentRoom(r14); // Place Nephi in room 14

        sword = new Sprite.Sword();
        sword.setCurrentRoom(r1);

        plates = new Sprite.Plates();
        plates.setCurrentRoom(r20);

        laban = new Sprite.Laban();
        laban.setCurrentRoom(r19);

        drawables.add(nephi);
        drawables.add(sword);
        drawables.add(plates);
        drawables.add(laban);

        // Add key listener
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        initLabanTimer();
    }

    private void initLabanTimer() {// initialize laban timer
        if (labanTimer == null) {
            int delay = 1000;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    moveLabanIntelligently();
                }
            };
            labanTimer = new Timer(delay, taskPerformer);
            labanTimer.start();
        }
    }

    private void moveLabanIntelligently() {
        Room labanRoom = laban.getCurrentRoom();
        Room nephiRoom = nephi.getCurrentRoom();
        Room closestRoom = findShortestPathRoom(labanRoom, nephiRoom);

        if (closestRoom != null) {
            laban.setCurrentRoom(closestRoom);
            repaint();
        }

        if (nephi.getCurrentRoom() == laban.getCurrentRoom()) {// check if nephi is in laban room
            if (nephi.isArmed()) {
                Sound.playSound("attack.wav");
                laban.setCurrentRoom(null);
            } else {
                Sound.playSound("over.wav");
                JOptionPane.showMessageDialog(this, "Nephi! Laban got you! Try again");// output message
                reset();
            }
        }
    }

    private Room findShortestPathRoom(Room start, Room goal) {
        if (start == goal) {
            return start;
        }

        Queue<Room> queue = new LinkedList<>();
        Map<Room, Room> predecessors = new HashMap<>();
        Set<Room> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Room current = queue.poll();

            for (Room neighbor : getAdjacentRooms(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);

                    if (neighbor == goal) {
                        return getFirstMove(predecessors, start, goal);
                    }
                }
            }
        }

        return null;
    }

    private List<Room> getAdjacentRooms(Room room) {
        List<Room> adjacentRooms = new ArrayList<>();
        if (room.hasNorthExit()) {
            adjacentRooms.add(room.getNorthExit());
        }
        if (room.hasSouthExit()) {
            adjacentRooms.add(room.getSouthExit());
        }
        if (room.hasEastExit()) {
            adjacentRooms.add(room.getEastExit());
        }
        if (room.hasWestExit()) {
            adjacentRooms.add(room.getWestExit());
        }
        return adjacentRooms;
    }

    private Room getFirstMove(Map<Room, Room> predecessors, Room start, Room goal) {
        Room step = goal;
        while (predecessors.get(step) != start) {
            step = predecessors.get(step);
        }
        return step;
    }

    private void stopLabanTimer() {
        if (labanTimer != null) {
            labanTimer.stop();
            labanTimer = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Drawable drawable : drawables) {// draw rooms using drawable
            drawable.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {// enable keyboard
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:// when up key is pressed, nephi goes north
                nephi.moveNorth();
                Sound.playSound("step.wav");
                break;
            case KeyEvent.VK_DOWN:
                nephi.moveSouth();
                Sound.playSound("step.wav");
                break;
            case KeyEvent.VK_LEFT:
                nephi.moveWest();
                Sound.playSound("step.wav");
                break;
            case KeyEvent.VK_RIGHT:
                nephi.moveEast();
                Sound.playSound("step.wav");
                break;
        }
        // if nephi is in the sword room
        if (nephi.getCurrentRoom() == sword.getCurrentRoom()) {//Explain how you detect collisions between Nephi and the sword.
            nephi.pickUpSword();
            Sound.playSound("sword.wav");
            sword.setCurrentRoom(null);//Explain how you make the sword disappear when Nephi touches it.
            // Set the sword's location to null so it won't get drawn anymore
        }

        if (nephi.getCurrentRoom() == plates.getCurrentRoom()) {// check if nephi is in plate room
            Sound.playSound("goal.wav");//Explain how you detect collisions between Nephi and the plates.
            laban.setCurrentRoom(null);
            JOptionPane.showMessageDialog(this, "Congratulations Nephi! You found the brass plates!");// output message

            reset();
        }

        if (nephi.getCurrentRoom() == laban.getCurrentRoom()) {// check if nephi is in laban room
            if (nephi.isArmed()) {  // Explain how you detect collisions between Nephi and Laban.
                Sound.playSound("attack.wav");
                laban.setCurrentRoom(null);
            } else {
                Sound.playSound("over.wav");
                JOptionPane.showMessageDialog(this, "Nephi! Laban got you! Try again");// output message
                reset();
            }
        }

        repaint();// refresh window each time Nephi moves.
    }

    // include allKeylistener methods because it is interface, you need to provide code for all of them
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private Room getRandomRoom(Set<Room> occupiedRooms) {
        Random random = new Random();
        Room randomRoom;
        do {
            randomRoom = rooms[random.nextInt(rooms.length)];
        } while (occupiedRooms.contains(randomRoom));
        return randomRoom;
    }

    private void reset() {// reset method to set all sprites to original place
        Set<Room> occupiedRooms = new HashSet<>();//so that sprites don't overlap
        
        Room nephiRoom = getRandomRoom(occupiedRooms);//get random room
        nephi.setCurrentRoom(nephiRoom);//set nephi to random room
        occupiedRooms.add(nephiRoom);
        nephi.reset();// reset nephi from hasSword to false, image to normalIcon

        Room swordRoom = getRandomRoom(occupiedRooms);
        sword.setCurrentRoom(swordRoom);
        occupiedRooms.add(swordRoom);

        Room platesRoom = getRandomRoom(occupiedRooms);
        plates.setCurrentRoom(platesRoom);
        occupiedRooms.add(platesRoom);

        Room labanRoom = getRandomRoom(occupiedRooms);
        laban.setCurrentRoom(labanRoom);
        occupiedRooms.add(labanRoom);

        // Ensure the timer continues to run after reset
        stopLabanTimer();
        initLabanTimer();

        repaint(); // Make sure to repaint the screen after resetting
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Room Grid");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(430, 430);
        window.setContentPane(new Main9());
        window.setVisible(true);
    }
}
