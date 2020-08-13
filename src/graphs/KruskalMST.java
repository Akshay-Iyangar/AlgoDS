package graphs;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {

  public List<Edge<Integer>> getMST(Graph<Integer> graph) {

    List<Edge<Integer>> allEdges = graph.getAllEdges();
    allEdges.sort(Comparator.comparingInt(Edge::getWeight));

    List<Vertex<Integer>> allVertex = graph.getAllVertex();
    DisjointSet disjointSet = new DisjointSet();
    allVertex.forEach(a -> disjointSet.makeSet(a.getId()));
    List<Edge<Integer>> results = new ArrayList<>();
    for(Edge e: allEdges) {
      Vertex vertex1 = e.getVertex1();
      Vertex vertex2 = e.getVertex2();
      Node left = disjointSet.find(vertex1.getId());
      Node right = disjointSet.find(vertex2.getId());
      if (!(left.getValue() == right.getValue())) {
        results.add(e);
        disjointSet.union(left.getValue(), right.getValue());
      }
    }
    return results;
  }

  public static void main(String[] args) {

    Graph<Integer> graph = new Graph<>(false);
    graph.addEdges(1, 2, 1);
    graph.addEdges(1, 5, 4);
    graph.addEdges(1, 6, 8);
    graph.addEdges(5, 6, 5);
    graph.addEdges(6, 2, 6);
    graph.addEdges(2, 3, 2);
    graph.addEdges(7, 2, 6);
    graph.addEdges(6, 7, 1);
    graph.addEdges(7, 3, 2);
    graph.addEdges(3, 4, 3);
    graph.addEdges(3, 8, 1);
    graph.addEdges(7, 8, 1);
    graph.addEdges(4, 8, 4);


    KruskalMST kruskalMST = new KruskalMST();
    List<Edge<Integer>> mst = kruskalMST.getMST(graph);
    mst.forEach(System.out::println);

  }

}
