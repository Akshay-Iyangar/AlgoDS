package graphs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Djikstra's algorithm to solve for the shortest distance between the source and destination.
 *
 * 1. make a graph
 * 2. Once the graph is present, select a node. mark it visited.
 * 3. Get all the adjacent vertex, compare the cost with it's current cost of the edge (weight + edge) < target node weight if less update the cost
 * 4. Once all the nodes of the adjacent vertex is traversed pick one vertex
 * 5. Repeat 2-4 until all nodes mark visited.
 *
 */

public class Djikstra {
//
//  public List<Vertex<Integer>> getShortestDistance(Graph<Integer> graph){
//    HashMap<Vertex<Integer>, Boolean> visited = new HashMap<>();
//    List<Vertex<Integer>> allVertex = graph.getAllVertex();
//    allVertex.forEach(a -> visited.put(a, false));
//
//    // get all the edges
//    List<Edge<Integer>> allEdges = graph.getAllEdges();
//
//
//    while (visited.containsValue(false)) {
//
//    }
//  }
//
//  public static void main(String[] args) {
//
//  }
//


  public static void main(String[] args) {
    int[] a = new int[4];
    System.out.println(Arrays.toString(a));

    List<Integer> ab = new ArrayList<>();
    HashSet<Integer> roomsVisited = new HashSet<>();
    System.out.println(7 / 2);
    List<String> list = new ArrayList<>();
    list.add("dog");

    char[] res = new char[3];

    String abc = "akshay";
    System.out.println(abc);
    abc.replace("k", "*");
    System.out.println(abc);



    Map<String, List<String>> map = new HashMap<>();
    List<String> intList = new LinkedList<>();


    String s = "akshay".substring(0, 0) + "*" + "akshay".substring(1, 6);
    System.out.println(s);

    for (String w : list) {
      char[] chars = w.toCharArray();
      for (char ch : chars) {
      }
    }



  }


}

class Pair<X,Y> {
  private final X x;
  private final Y y;
  Pair(X x, Y y) {
    this.x = x;
    this.y = y;
  }
  public X getKey() { return x;}
  public Y getValue() { return y;}
}


