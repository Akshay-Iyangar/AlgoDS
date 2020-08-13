package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Tarjan's algorithm
 *
 *
 * steps
 * 1. Simply do a DFS and maintain
 *  - current time for the vertex
 *  - low time for the vertex.
 *  - Parent of the current vertex
 *
 * 2. Articulation point is a point which dissconnects the graph into two.
 *   i) Vertex is a root of the graph and has 2 independent children.(By independent it means they are
 *    not connected to each other except via this vertex). This condition is needed because if we
 *    started from corner vertex it will meet condition ii) but still is not an articulation point. To filter
 *    out those vertices we need this condition.
 *   ii) It is not root of DFS and if visitedTime of vertex <= lowTime of any adjacent vertex then its articulation point.
 */

public class ArticulationPoint<T> {

  private int time;
  public static void main(String[] args) {
      Graph<Integer> graph = new Graph<>(false);
      graph.addEdges(1, 2);
      graph.addEdges(2, 3);
      graph.addEdges(1, 3);
      graph.addEdges(1, 4);
      graph.addEdges(4, 5);
      graph.addEdges(5, 6);
      graph.addEdges(6, 7);
      graph.addEdges(7, 5);
      graph.addEdges(6, 8);

      //bigger example
        /*
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(4, 2);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(6, 3);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 9);
        graph.addEdge(9, 10);
        graph.addEdge(8, 10);*/

      ArticulationPoint<Integer> ap = new ArticulationPoint<>();
      Set<Vertex<Integer>> aPoints = ap.findarticulationPoints(graph);
      aPoints.forEach(System.out::println);

    }

  private Set<Vertex<T>> findarticulationPoints(Graph<T> graph) {
    /*write the code here*/
    time = 0;
    Set<Vertex<T>> visited = new HashSet<>();
    Set<Vertex<T>> articulationPoints = new HashSet<>();
    Vertex<T> startVertex = graph.getAllVertex().iterator().next();

    Map<Vertex<T>, Integer> visitedTime = new HashMap<>();
    Map<Vertex<T>, Integer> lowTime = new HashMap<>();
    Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

    DFS(visited,articulationPoints,startVertex, visitedTime, lowTime, parent);
    return articulationPoints;
  }

  private void DFS(Set<Vertex<T>> visited,
                   Set<Vertex<T>> articulationPoints, Vertex<T> vertex,
                   Map<Vertex<T>, Integer> visitedTime,
                   Map<Vertex<T>, Integer> lowTime, Map<Vertex<T>, Vertex<T>> parent) {
    visited.add(vertex);
    visitedTime.put(vertex, time);
    lowTime.put(vertex, time);
    time++;
    int childCount =0;
    boolean isArticulationPoint = false;
    for(Vertex<T> adj : vertex.getAdjacentVertex()){
      //if adj is same as parent then just ignore this vertex.
      if(adj.equals(parent.get(vertex))) {
        continue;
      }
      //if adj has not been visited then visit it.
      if(!visited.contains(adj)) {
        parent.put(adj, vertex);
        childCount++;
        DFS(visited, articulationPoints, adj, visitedTime, lowTime, parent);

        if(visitedTime.get(vertex) <= lowTime.get(adj)) {
          isArticulationPoint = true;
        } else {
          //below operation basically does lowTime[vertex] = min(lowTime[vertex], lowTime[adj]);
          lowTime.compute(vertex, (currentVertex, time) ->
              Math.min(time, lowTime.get(adj))
          );
        }

      } else { //if adj is already visited see if you can get better low time.
        //below operation basically does lowTime[vertex] = min(lowTime[vertex], visitedTime[adj]);
        lowTime.compute(vertex, (currentVertex, time) ->
            Math.min(time, visitedTime.get(adj))
        );
      }
    }

    //checks if either condition 1 or condition 2 meets). If yes then it is articulation point.
    if((parent.get(vertex) == null && childCount >= 2) || parent.get(vertex) != null && isArticulationPoint ) {
      articulationPoints.add(vertex);
    }

  }
}
