import java.util.*;

/*Manages all of the information relevant to a given board location
includes:
tile : tile placed,
connections: side types*/

public class Slot{

  public static final int NUM_SIDES = 4; //it's a square
  private int meeplePlacement; //to be fixed later
  private Tile tile; //the tile placed in this slot
  protected SlotConnection[] connections; //the side types of this slot (set when tile is placed OR when this is connected to another tile)

  //initializes empty Slot with blank connections and no meeple
  public Slot(){
      meeplePlacement = 0;
      connections = new SlotConnection[NUM_SIDES];
      for (int i = 0; i < NUM_SIDES; i++) {
          connections[i] = new SlotConnection(i);
      }
  }

  //returns true if this tile can fit with the given rotation
  public boolean canFit(Tile tile, int rotationAmt){

    for(int side = 0; side<NUM_SIDES; side++){

        //if this side is connected to something, and the tile with that rotation doesn't match, it can't fit
        if(connections[side].type != '!' && connections[side].type != tile.getSide((rotationAmt + side) % NUM_SIDES)){
            return false;
        }

    }

    return true;
  }

  //sets the tile, inits connection points based on tile
  public void setTile(Tile tile, int rotationAmt){
      this.tile = tile;

      //make all of the connection types align with the tile
      for(int side = 0; side<NUM_SIDES; side++){
        connections[side].type = tile.getSide((side+rotationAmt) % NUM_SIDES);
      }
  }

  //to manage the interfacing between each Slot
  //each has an integer to denote which side it's on and a char to denote the type of connection
  private class SlotConnection{

    protected int side;
    protected char type;

      //originally initialized typeless
      public SlotConnection(int side) {
          this.side = side;
          this.type = '!';
      }

    //sets the character of this slotConnection based on the given side
    //and a (completed) connections slot
    protected void connect(Slot adjSlot){
      this.type = adjSlot.connections[opposite(side)].type;
    }
  }

  //connects this slot to the parameterized slot on the given side
  public void connect(Slot slot, int side){
    connections[side].connect(slot);
  }

  //to be changed later
  public void placeMeeple (int meeplePlacement) {
      this.meeplePlacement = meeplePlacement;
  }

  //the opposite side of this tile
  private static int opposite(int i){ return (i+2)%4; }

  public String toString() {
      // Need to print connections (with their tiles), the tile in this slot, and meeple placement in this slot.

      return (this.tile != null)?("tile: "+this.tile):"(empty)";

  }
}
