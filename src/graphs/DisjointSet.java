package graphs;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {


  private Map<Long, Node> allNodes = new HashMap<>();

  /**
   * Union method is used to basically join two sets into one and assign it a parent based on rank.
   * If the rank is same choose any set we will choose left.
   * @param left - Set left of the union that needs to be merged.
   * @param right - Set right of the union that needs to be merged.
   */
  public boolean union(long left, long right) {
    Node leftParent = find(left);
    Node rightParent = find(right);

    Node leftNode = allNodes.get(left);
    Node rightNode = allNodes.get(right);

    if (leftParent == rightParent)
      return false;

    if(leftNode.getRank() >= rightNode.getRank()) {
      // make the parent of right equal to the parent of left.
      rightParent.setParent(leftParent);
      leftParent.setRank(leftParent.getRank() + 1);
    } else {
      leftParent.setParent(rightParent);
      rightParent.setRank(leftParent.getRank() + 1);
    }
    return true;
  }

  /**
   * Used to find the Node's parent and return. Also will perform path compression to
   * make the child node point to the parent node directly.
   * @param data - Node's who parent need to be found.
   */
  public Node find(long data){
    Node actualNode = allNodes.get(data);
    Node node = actualNode;
    while (node!=node.getParent()) {
      node = node.getParent();
    }
    actualNode.setParent(node);
    return node;
  }

  /**
   * Make set operation is an operation to make a Node for a given value. Each node is it's own set initially.
   * @param data - value of the node
   * @return Node for the given value
   */
  public void makeSet(long data) {
    Node node = new Node();
    node.setValue(data);
    node.setRank(0);
    node.setParent(node);
    allNodes.put(data,node);
  }
}


class Node {
  private long value;
  private int rank;
  private Node parent;

  void setValue(long value) {
    this.value = value;
  }

  void setRank(int rank) {
    this.rank = rank;
  }

  void setParent(Node parent) {
    this.parent = parent;
  }

  long getValue() {
    return value;
  }

  int getRank() {
    return rank;
  }

  Node getParent() {
    return parent;
  }
}
