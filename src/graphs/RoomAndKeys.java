package graphs;
/*

# Question:
# You are a game developer working on a game that randomly generates levels. A level is an undirected graph of rooms, each connected by doors. The player starts in one room, and there is a treasure in another room. Some doors are locked, and each lock is opened by a unique key. A room may contain one of those unique keys, or the treasure, or nothing.
#
# Since you are generating levels randomly, you need to ensure the level is solvable. Otherwise, players will get upset. We need you to implement a function that takes your level and validates that it is solvable.
#
# +------------+------------------+--------------+
# |            |                  |              |
# |            |                  |              |
# | Player     |                  X              |
# |            |                  X  Treasure    |
# |      1     |      2           X    3         |
# |            |                  |              |
# +---XXXXXX--------XXXXXX----------------------->
# |            |                  |              |
# |      4     |      5           |    6         |
# |           XXX                XXX             |
# |  Key: A  CXXXC              BXXXB            |
# |           XXX                XXX             |
# |            |                  |     D        |
# |            |                  |    XXXX      |
# +--XXXXXX----------XXXXXX------------XXXX------>
# |            |                  |    XXXX      |
# |            |                  |     6        |
# |     7     XXX      8          |     9        |
# |          8XXX7                |   Key: B     |
# |           XXX                 |              |
# |            |                  |              |
# |            |                  |              |
# +------------+------------------+--------------+
#
*
* */


import java.util.List;

public class RoomAndKeys {


  public static void main(String[] args) {

  }

  // this will be the nodes of the graph.
  class Room {
    List<Integer> doors; //multiple doors. // these are the edges. // undirected.
    boolean isTreasure;
    boolean isStart;
    List<Integer> keys;
  }
}
