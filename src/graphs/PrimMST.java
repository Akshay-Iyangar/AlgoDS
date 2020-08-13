package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimMST {

  public List<Edge<Integer>> getMST(Graph<Integer> graph) {

    //Sort the edges so the smallest edge is the first edge always.
    List<Edge<Integer>> allEdges = graph.getAllEdges();
    allEdges.sort(Comparator.comparingInt(Edge::getWeight));
    // Create a Priority queue for min heap
    PriorityQueue<Edge<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
    Map<Vertex<Integer>, Boolean> visitedVertex = new HashMap<>();
    List<Vertex<Integer>> allVertex = graph.getAllVertex();
    allVertex.forEach(a -> visitedVertex.put(a, false));
    ArrayList<Edge<Integer>> mst = new ArrayList<>();

    // Add first edge.
    priorityQueue.add(allEdges.get(0));

    while (!priorityQueue.isEmpty()) {
      Edge<Integer> peek = priorityQueue.poll();
      if (visitedVertex.get(peek.getVertex1()) && visitedVertex.get(peek.getVertex2())) continue;
      visitedVertex.put(peek.getVertex1(), true);

      // Add all the ajacent edges for both the vertexes.
      List<Edge<Integer>> adjacentEdges = peek.getVertex2().getEdges();
      adjacentEdges.addAll(peek.getVertex1().getEdges());

      for (Edge<Integer> edge : adjacentEdges) {
        if (!visitedVertex.get(edge.getVertex2())) {
          priorityQueue.add(edge);
        }
      }
      visitedVertex.put(peek.getVertex2(), true);
      mst.add(peek);
    }
    return mst;
  }

  public static void main(String[] args) {

    Graph<Integer> graph = new Graph<>(false);
    graph.addEdges(3, 7, 8);
    graph.addEdges(1, 2, 4);
    graph.addEdges(1, 3, 1);
    graph.addEdges(4, 7, 2);
    graph.addEdges(3, 4, 5);
    graph.addEdges(2, 5, 1);
    graph.addEdges(2, 6, 3);
    graph.addEdges(2, 4, 2);
    graph.addEdges(6, 5, 2);
    graph.addEdges(6, 4, 3);

    PrimMST primMST = new PrimMST();
    List<Edge<Integer>> mst = primMST.getMST(graph);
    mst.forEach(System.out::println);

  }

}
