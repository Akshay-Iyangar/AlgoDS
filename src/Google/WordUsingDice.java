package Google;


/*

https://leetcode.com/discuss/interview-question/267985/google-interview-construct-a-word-using-dice


Given n blocks and n length word, can you rearrange blocks so as to make word?

If yes, than in how many ways.?

Input:
word = "hello"
dice = [[a, l, c, d, e, f], [a, b, c, d, e, f], [a, b, c, h, e, f], [a, b, c, d, o, f], [a, b, c, l, e, f]]
Output: true
Explanation: dice[2][3] + dice[1][4] + dice[0][1] + dice[4][3] + dice[3][4]



Input:
word = "hello"
dice = [[a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f], [a, b, c, d, e, f]]
Output: false


Input:
word = "aaaa"
dice = [[a, a, a, a, a, a], [b, b, b, b, b, b], [a, b, c, d, e, f], [a, b, c, d, e, f]]
Output: false


 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordUsingDice {



  private void bactracking(String word,
                              List<List<Character>> dice,
                              int index,
                              List<Integer> visited,
                              List<List<Integer>> result){

    if(visited.size() == word.length()){
      result.add(new ArrayList<>(visited));
    }


    for (int i=index; i<word.length();i++){
      for (int j=0;j<dice.size();j++){
        if(!visited.contains(j)){
          if(dice.get(j).contains(word.charAt(i))){
            visited.add(j);
            bactracking(word,dice,i+1,visited,result);
            visited.remove(visited.size() - 1);
          }
        }
      }
    }
  }

  public static void main(String[] args) {

    String word = "hello";
    //[[a, l, c, d, e, f], [a, b, c, d, e, f], [a, b, c, h, e, f], [a, b, c, d, o, f], [a, b, c, l, e, f]]
    Character[][] dice = {
        {'a','l','c','d','e','f'},
        {'a','b','c','d','e','f'},
        {'a','b','c','h','e','f'},
        {'a','b','c','d','o','f'},
        {'a','b','c','l','e','f'},
    };


    List<List<Character>> diceList = Arrays.stream(dice)
        .map(Arrays::asList)
        .collect(Collectors.toList());


    WordUsingDice wordUsingDice = new WordUsingDice();
    List<List<Integer>> result = new ArrayList<>();
    wordUsingDice.bactracking(word,diceList,0,new ArrayList<>(),result);

    System.out.println(result);


  }
}
