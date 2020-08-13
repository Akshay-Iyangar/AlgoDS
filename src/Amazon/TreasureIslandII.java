package Amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a map that marks the locations of treasure islands.
 * Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in.
 * There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters.
 * You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time.
 * The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
 * You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.
 *
 * Example:
 *
 * Input:
 * [['S', 'O', 'O', 'S', 'S'],
 *  ['D', 'O', 'D', 'O', 'D'],
 *  ['O', 'O', 'O', 'O', 'X'],
 *  ['X', 'D', 'D', 'O', 'O'],
 *  ['X', 'D', 'D', 'D', 'O']]
 *
 * Output: 3
 * Explanation:
 * You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0). Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).
 */
public class TreasureIslandII {
    static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
    private static int bfs(char[][] grid){
        Queue<int[]> queue = addAllSources(grid);
        int count=0;
        //we will need to use 'Z' to mark everything visited.
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                if(grid[point[0]][point[1]]=='X') return count;
                for (int[] dir : direction){
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    if (x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y]=='D') continue;
                    queue.add(new int[]{x,y});
                    grid[point[0]][point[1]]='D';
                }
            }
            count++;
        }
        return -1;
    }

    private static Queue<int[]> addAllSources(char[][] grid){
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='S'){
                    queue.add(new int[]{i,j});
                    grid[i][j]='D';
                }
            }
        }
        return queue;
    }

    public static void main(String[] args) {

        char[][] grid = {
                {'S','O','O','S','S'},
                {'D','O','D','O','D'},
                {'O','O','O','O','X'},
                {'X','D','D','O','O'},
                {'X','D','D','D','O'}
        };

        int count = bfs(grid);
        System.out.println(count);

    }
}
