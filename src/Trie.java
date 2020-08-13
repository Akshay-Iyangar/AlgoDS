import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Trie {
  private TrieNode node = new TrieNode();

  public void insert(String word){
    TrieNode current = node;
    for (int i = 0; i < word.length(); i++) {
      current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
    }
    current.setEndWord(true);
  }

  public boolean find(String word) {
    TrieNode current = node;
    for (int i = 0; i < word.length(); i++) {
      TrieNode node = current.getChildren().get(word.charAt(i));
      if (node == null)
        return false;
      current = node;
    }
    return current.isEndWord();
  }
  public boolean startsWith(String prefix) {
    TrieNode current = node;
    int i = 0;
    for (; i < prefix.length(); i++) {
      TrieNode trieNode = current.getChildren().get(prefix.charAt(i));
      if (trieNode == null)
        return false;
      current = trieNode;
    }
    return i == prefix.length();
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("ball");
    trie.insert("bags");
    trie.insert("doll");
    trie.insert("dogs");

    System.out.println(trie.find("dall"));
    System.out.println(trie.startsWith("balls"));

  }
}

class TrieNode {
  private Map<Character,TrieNode> children = new HashMap<>();
  private boolean endWord;

  Map<Character, TrieNode> getChildren() {
    return children;
  }

  boolean isEndWord() {
    return endWord;
  }

  void setEndWord(boolean endWord) {
    this.endWord = endWord;
  }
}
