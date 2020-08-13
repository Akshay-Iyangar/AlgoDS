import java.util.HashMap;
import java.util.Map;

class LRUCache {

  int capacity;
  Map<Integer, Node> map = new HashMap<>();
  Node head=new Node(0,0);
  Node tail=new Node(0,0);


  public LRUCache(int capacity) {
    this.capacity = capacity;
    head.next = tail;
    tail.prev = head;
    head.prev = null;
    tail.next = null;
  }

  public int get(int key) {

    if(map.containsKey(key)) {
      Node node = map.get(key);
      //remove the node
      removeNode(node);
      //offer at last
      offerNode(node);
      return node.value;
    }
    else{
      return -1;
    }

  }

  public void put(int key, int value) {

    // Create a linked hashmap to maintain ordering.
    // check if capacity reached if yes evict the head of the DLL;
    if(map.containsKey(key)) {
      Node node = map.get(key);
      //change the value of the key ..
      node.value = value;
      // remove the node from DLL
      removeNode(node);
      // add it to the end / tail
      offerNode(node);
    } else {
      if(map.size()>capacity){
        //remove the head node;
        Node nodeDelete = head.next;
        removeNode(nodeDelete);
        //remove the key from the map as well.
        map.remove(nodeDelete.key);
      }
      Node node = new Node(key,value);
      offerNode(node);
      map.put(key,node);
    }
  }
  //remove from the DLL
  private void removeNode(Node node) {
    //check if it is tail node
    if(node.next == tail) {
      node.prev.next = tail;
      tail.prev = node.prev;
    } else if(node.prev == head ) { // check if it is head node
      head.next = node.next;
      node.next.prev = head;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
  }

  // add to the DLL
  private void offerNode(Node node) {

    if(head.next==null){
      head.next = node;
      node.prev = head;
    } else {
      tail.prev.next = node;
      node.prev = tail.prev;
    }
    node.next = tail;
    tail.prev = node;
  }

  public static void main(String[] args) {

    LRUCache lruCache = new LRUCache(2);
    lruCache.put(2,2);
  }

}

class Node {
  Node next;
  Node prev;
  int key;
  int value;

  Node(int key, int value) {
    this.key=key;
    this.value=value;
  }
}



/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */