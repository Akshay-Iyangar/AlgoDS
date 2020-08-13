import java.util.ArrayList;
import java.util.List;

public class Prac {
 List<List<Integer>> result = new ArrayList<>();
  public static void main(String[] args) {
    final Prac prac = new Prac();
    prac.backtracking(4,4,4, new ArrayList<>());
    prac.printCombination();
  }

  public void printCombination(){
    System.out.println(result);
  }

  //sum = 1, n = 4, [1,0,0,0]
  private void backtracking(int sum, int max, int n, List<Integer> list) {

    if (list.size() == n) {
      if (sum == 0){
        result.add(new ArrayList<>(list));

      }
      return;
    }
    //if (sum < 0) return;

    for (int i=0;i<=max; i++){
      list.add(i);
      backtracking(sum - i, max, n, list);
      list.remove(list.size()-1);
    }
  }

  public static int allSubstrings(String s, char[] alphabets) {
    int map[] = new int[128];
    int counter = alphabets.length;
    int res = 0, start = 0, end=0;
    for(char c : alphabets) {
      map[c]++;
    }
    while (end < s.length()) {
      char c = s.charAt(end);
      map[c]--;
      if (map[c] >= 0) counter--;

      while(counter == 0) {
        char c1 = s.charAt(start);
        map[c1]++;
        if(map[c1] >= 0) counter++;
        start++;
      }
      res += end-start+1;
      end++;
    }
    return res;
  }
}
