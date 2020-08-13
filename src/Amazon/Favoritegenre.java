package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favoritegenre {

  /*
  userSongs = {
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
  },
  songGenres = {
     "Rock":    ["song1", "song3"],
     "Dubstep": ["song7"],
     "Techno":  ["song2", "song4"],
     "Pop":     ["song5", "song6"],
     "Jazz":    ["song8", "song9"]
  }

  Output: {
     "David": ["Rock", "Techno"],
     "Emma":  ["Pop"]
  }
   */


  public static void main(String[] args) {

    Map<String, List<String>> userSongs = new HashMap<String, List<String>>(){{
      put("David",new ArrayList<String>(){{add("song1");add("song2");add("song3");add("song4");add("song8");}});
      put("Emma", new ArrayList<String>(){{add("song5");add("song6");add("song7");}});
    }};

    Map<String, List<String>> songGenres = new HashMap<String,List<String>>(){{
      put("Rock",new ArrayList<String>(){{add("song1");add("song3");}});
      put("Dubstep", new ArrayList<String>(){{add("song7");}});
      put("Techno",new ArrayList<String>(){{add("song2");add("song4");}});
      put("Pop",new ArrayList<String>(){{add("song5");add("song6");}});
      put("Jazz",new ArrayList<String>(){{add("song8");add("song9");}});
    }};

    // make a hashmap of song = genre
    HashMap<String, String> songToGenre = new HashMap<>();
    songGenres.forEach((genre, songs) -> songs.forEach(song -> songToGenre.put(song,genre)));

    HashMap<String, List<String>> userMostLikedGenre = new HashMap<>();
    userSongs.forEach((user,songs) -> userMostLikedGenre.put(user, mostLikedSongs(songs, songToGenre)));
    System.out.println(userMostLikedGenre);


  }

  private static List<String> mostLikedSongs(List<String> songs, HashMap<String, String> songToGenre) {

    HashMap<String, Integer> genreCount = new HashMap<>();
    int max = 0;
    for (String song : songs) {
      String genre = songToGenre.get(song);
      genreCount.put(genre, genreCount.getOrDefault(genre,0)+1);
      max= Math.max(genreCount.get(genre),max);
    }

    ArrayList<String> result = new ArrayList<>();
    for(Map.Entry<String,Integer> genre : genreCount.entrySet()){
      if(genre.getValue() == max)
        result.add(genre.getKey());
    }

    return result;
  }


}
