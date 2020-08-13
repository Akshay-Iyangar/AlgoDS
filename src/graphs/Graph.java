package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We associate each of the graph using a id;
 * @param <P> - Data associated with the graph if present
 */
public class Graph<P> {

  private final boolean isDirected;
  private final Map<Long, Vertex<P>> allVertex;
  private final List<Edge<P>> allEdges;

  public Graph(boolean isDirected) {

    this.isDirected = isDirected;
    allEdges = new ArrayList<>();
    allVertex = new HashMap<>();
  }

  public void addEdges(long id1, long id2) {
    addEdges(id1, id2, 0);
  }

  public void addEdges(long id1, long id2, int weight) {

    Vertex<P> vertex1 = allVertex.containsKey(id1) ? allVertex.get(id1) : new Vertex<>(id1);
    Vertex<P> vertex2 = allVertex.containsKey(id2) ? allVertex.get(id2) : new Vertex<>(id2);
    allVertex.put(id1, vertex1);
    allVertex.put(id2, vertex2);
    // add to the edges;
    Edge<P> edge = new Edge<>(vertex1, vertex2, isDirected, weight);
    allEdges.add(edge);
    //add to the adjacency list
    vertex1.addAdjacentVertex(edge, vertex2);
    // If it is a undirected graph we need to add vertex1 to vertex2.
    if (!isDirected) {
      vertex2.addAdjacentVertex(edge, vertex2);
    }
  }

  public Vertex<P> addVertex(long id) {
    if (allVertex.containsKey(id)) {
      return allVertex.get(id);
    }
    Vertex<P> vertex = new Vertex<>(id);
    return allVertex.put(id, vertex);
  }

  public List<Vertex<P>> getAllVertex() {
    return new ArrayList<Vertex<P>>(allVertex.values());
  }

  public List<Edge<P>> getAllEdges() {
    return allEdges;
  }

  public Vertex<P> getVertex(long id) {
    return allVertex.get(id);
  }

  public void setVertexData(long id, P data) {
    if (allVertex.containsKey(id)) {
      Vertex<P> vertex = allVertex.get(id);
      vertex.setData(data);
    }
    else {
      System.out.println("Please add the vertex to set data");
    }
  }
}

class Edge<P> {

  private Vertex<P> vertex1;
  private Vertex<P> vertex2;
  private boolean isDirected;
  private int weight;

  // If graph is a weighted graph.
  public Edge(
      Vertex<P> vertex1,
      Vertex<P> vertex2,
      boolean isDirected,
      int weight
  ) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.isDirected = isDirected;
    this.weight = weight;
  }

  //If graph is a unweighted graph.
  public Edge(
      Vertex<P> vertex1,
      Vertex<P> vertex2,
      boolean isDirected
  ) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.isDirected = isDirected;
  }

  public Edge(
      Vertex<P> vertex1,
      Vertex<P> vertex2
  ) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
  }

  public boolean isDirected() {
    return isDirected;
  }

  public Vertex<P> getVertex1(){
    return vertex1;
  }

  public Vertex<P> getVertex2(){
    return vertex2;
  }

  public int getWeight(){
    return weight;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;

    if (!(o instanceof Edge))
      return false;

    Edge e = (Edge) o;
   return e.vertex1.equals(vertex1) && e.vertex2.equals(vertex2);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash * 31 + vertex1.hashCode() + vertex2.hashCode();
  }

  @Override
  public String toString() {
    return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
        + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
  }
}

class Vertex<P> {

  private List<Vertex<P>> adjacentVertx = new ArrayList<>();
  private List<Edge<P>> edges = new ArrayList<>();
  private P data;
  private long id;

  public Vertex(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setData(P data) {
    this.data = data;
  }
  public P getData() {
    return data;
  }

  /**
   * Used to add adjacent vertex's for a particular vertex especially useful if you want to
   * create a adjacency matrix for all the vertex's
   * @param edge
   * @param vertex
   */
  public void addAdjacentVertex(Edge<P> edge, Vertex<P> vertex) {
    edges.add(edge);
    adjacentVertx.add(vertex);
  }

  public List<Vertex<P>> getAdjacentVertex(){
    return adjacentVertx;
  }

  public List<Edge<P>> getEdges() {
    return edges;
  }

  public int getDegree() {
    return edges.size();
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;

    if (!(o instanceof Vertex))
      return false;
    Vertex v = (Vertex) o;
    return v.data.equals(data) && v.id == id;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return  31 * hash + Long.hashCode(id) + ((data == null) ? 0 : data.hashCode());
  }

  @Override
  public String toString() {
    return "Vertex:[data="+ data + ",id="+id+"]";
  }
}
