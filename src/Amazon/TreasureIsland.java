package Amazon;


import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs.
 * Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters.
 * You must start from the top-left corner of the map and can move one block up, down, left or right at a time.
 * The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner.
 * Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
 * You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe.
 * Output the minimum number of steps to get to the treasure.
 *
 *
 * Example:
 *
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 */
public class TreasureIsland {
    static int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
    //static int count=0;

    private static int bfs(char[][]grid){

        Queue<int[]> queue = new LinkedList<>();
        int count=0;
        queue.add(new int[]{0,0});
        grid[0][0]='D'; //mark it visited.

        while (!queue.isEmpty()){
            int size = queue.size();
            //level
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                if(grid[point[0]][point[1]]=='X') return count;
                for (int[] dir : direction){
                    int x = point[0]+dir[0];
                    int y = point[1]+dir[1];
                    if(x>=grid.length || x<0 || y>=grid[0].length || y<0 || grid[x][y]=='D') continue;
                    queue.add(new int[]{x,y});
                    grid[x][y]='D';
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {

        char[][] grid = {
            {'O','O','O','O'},
            {'D','O','D','O'},
            {'O','O','O','D'},
            {'D','D','O','X'}
        };
        int count = bfs(grid);
        System.out.println("value of min: "+count);
    }
}
