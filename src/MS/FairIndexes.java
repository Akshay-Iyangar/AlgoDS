package MS;

public class FairIndexes {

  /*
  * Split two array such that they sum of all the split is equal
  *
  * If it is possible then how many different splits are possible
  *
  * If not possible return 0
  *
  * A = [4, -1, 0, 3]
  * B = [-2, 5, 0, 3]
  * 2 indexes are fair. (2 and 3)
  *
  * e.g:
  * sumA = [4, 3, 0, 6];
  * sumB = [-2, 3, 3, 6];
  *
  * Pretty similar to splitting the array into 2 subsets.
  *
  * So at any given time if we have a situation where the rolling sum is 2*totalSum.
  *
  * */

  public static void main(String[] args) {

    FairIndexes fairIndexes = new FairIndexes();
    int[] A = {4,-1,0,3};
    int[] B = {-2,5,0,3};
    int result = fairIndexes.countFairIndexes(A,B);
    System.out.println(result);

  }

  private int countFairIndexes(int[] A, int[] B){

    int sumA=0;
    int sumB=0;
    for(int i=0;i<A.length;i++){
      sumA += A[i];
      sumB += B[i];
    }

    int count=0;
    int tempA=0;
    int tempB=0;
    for (int i=0; i<A.length; i++){

      if(i > 0 && tempA==tempB && 2*tempA == sumA && 2*tempB == sumB) count++;
      tempA += A[i];
      tempB += B[i];
    }

    return count;
  }

}
