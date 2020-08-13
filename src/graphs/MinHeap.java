package graphs;

import java.util.Arrays;

/**
 * The parent node is always smaller than it's child.
 * There is no basis for child's magnitude as in left > right or vice-versa.
 * Tree is filled left to right.
 * leftchild = 2*i +1;
 * rightChild = 2*i+2;
 * parent = (i-1)/2
 */
public class MinHeap {

  private int capacity = 10;
  private int size = 0;
  private int[] items = new int[capacity];

  private int getLeftChildIndex(int index){
    return (2*index+1);
  }
  private int getRightChildIndex(int index){
    return (2*index+2);
  }
  private int getParentIndex(int index){
    return (index-1)/2;
  }

  private boolean hasLeftChild(int index){
    return getLeftChildIndex(index) < size;
  }

  private boolean hasRightChild(int index){
    return getRightChildIndex(index)<size;
  }

  private boolean hasParent(int index){
    return getParentIndex(index) >= 0;
  }

  //swap
  private void swap(int index1, int index2){
    int temp = items[index1];
    items[index1] = items[index2];
    items[index2] = temp;
  }
  //AddExtraCapacity
  private void addExtraCapacity(){
    if (size == capacity) {
      items = Arrays.copyOf(items, 2 * capacity);
      capacity *= 2;
    }
  }

  //peek
  public int peek(){
    if (size==0) throw new IllegalStateException();
    return items[0];
  }
  //poll : Remove the smallest element replace last element to 0 and then heapifyDown
  public int poll() {
    if (size==0) throw new IllegalStateException();
    int minElement = items[0];
    items[0] = items[size - 1];
    size--;
    heapifyDown();
    return minElement;
  }

  //add : Add element to the end and heapifyUp
  public void add(int item){
    addExtraCapacity();
    items[size] = item;
    size++;
    heapifyUp();
  }

  //heapifyUp
  /**
   * take the last element and while it has a parent and if it's parent is greater than child than swap;
   */
  private void heapifyUp() {
    int index = size - 1;
    while (hasParent(index) && items[getParentIndex(index)] > items[index]) {
      swap(getParentIndex(index), index);
      index = getParentIndex(index);
    }
  }
  //heapifyDown
  /**
   * take the first element and check if it has a leftChild.
   * Then determine the smaller of the two child.
   * and if the smaller of the two child's value < parent we swap
   * else we good.
   */
  private void heapifyDown() {
    int index = 0;
    while (hasLeftChild(index)) {
      // determine smaller of the two child.
      int childIndex = getLeftChildIndex(index);
      if (hasRightChild(index) && items[getRightChildIndex(index)] < items[childIndex]) {
        childIndex = getRightChildIndex(index);
      }
      if (items[childIndex] < items[index]) {
        swap(childIndex,index);
        index = childIndex;
      } else {
        break;
      }
    }
  }

}
