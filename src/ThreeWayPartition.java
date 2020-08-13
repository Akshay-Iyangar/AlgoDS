import java.util.Arrays;

public class ThreeWayPartition {
  void partion(int[] nums, int target)
  {

    int n = nums.length;
    int start = 0;
    int end = n - 1;

    for(int i=0;i<n;i++) {
      if(nums[i] < target){

        int temp = nums[start];
        nums[start] = nums[i];
        nums[i] = temp;
        start++;

      }
      else if (nums[i] > target){

        int temp = nums[end];
        nums[end] = nums[i];
        nums[i] = temp;
        end--;
      }
      else {
        continue;
      }
    }
    System.out.println(Arrays.toString(nums));
  }


  public static void main(String[] args) {


    ThreeWayPartition threeWayPartition = new ThreeWayPartition();
    int [] num = {2,1,2,5,6,-1,0};
    threeWayPartition.partion(num,-1 );

  }
}