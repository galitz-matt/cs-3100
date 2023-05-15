import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class MainDrainage {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numCases = in.nextInt();
		
		for (int iter = 0; iter < numCases; iter++) {
			String cityName = in.next();
			int rows = in.nextInt();
			int columns = in.nextInt();
			int[][] grid = new int[rows][columns];
			Entry[] linearGrid = new Entry[rows * columns];
			
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					grid[r][c] = in.nextInt();
					linearGrid[columns*r + c] = new Entry(grid[r][c], r, c);
				}
			}
			
			Arrays.sort(linearGrid, new EntryComparator());
			Entry base = linearGrid[0];
			int[][] dp = new int[rows][columns];
			dp[base.index[0]][base.index[1]] = 1;
			
			int maxRun = 0;
			for (Entry e : linearGrid) {
				int localMaxRun = findRun(dp, grid, e.index);
				dp[e.index[0]][e.index[1]] = localMaxRun;
				maxRun = Math.max(maxRun, localMaxRun);
			}
			System.out.println(cityName + ": " + maxRun);
		}
		in.close();
	}
	
	public static int findRun(int[][] dp, int[][] grid, Integer[] index) {
		int r = index[0];
		int c = index[1];
		int val = grid[r][c];
		int lmr = 1;
		
		if (r > 0 && grid[r-1][c] < val) {
			lmr = Math.max(dp[r-1][c] + 1, lmr);
		}
		if (r < grid.length - 1 && grid[r+1][c] < val) {
			lmr = Math.max(dp[r+1][c] + 1, lmr);
		}
		if (c > 0 && grid[r][c-1] < val) {
			lmr = Math.max(dp[r][c-1] + 1, lmr);
		}
		if (c < grid[r].length - 1 && grid[r][c+1] < val) {
			lmr = Math.max(dp[r][c+1] + 1, lmr);
		}
		
		return lmr;
	}
}