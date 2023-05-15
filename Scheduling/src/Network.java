import java.util.ArrayList;

public class Network {
		
		int size;
		int[][] ADJM;
		ArrayList<Integer> seen1 = new ArrayList<>();
		ArrayList<Integer> seen2 = new ArrayList<>();
		
		public Network(int s) {
			size = s;
			ADJM = new int[size][size];
		}

		public void newEdge(int u, int v, int val) {
		   ADJM[u][v] = val;
		}
		
		public int dfs() {
			seen2.add(0);
			dfsVisit(0);
			return seen1.contains(ADJM.length - 1) ? 1 : 0;
		}
		
		public void dfsVisit(int u) {
			seen1.add(u);
			
			for(int i = 0; i < ADJM.length; i++)  {
				if(seen1.contains(ADJM.length - 1)) {
					return;
				}
				if(ADJM[u][i]!=0) {
					if(!(seen1.contains(i))) {
						seen2.add(ADJM[u][i]);
						dfsVisit(i);
					}
				}
			}
			if(seen1.contains(ADJM.length-1)) {
				return;
			}
			
			seen1.remove(seen1.size() - 1);
			seen2.remove(seen2.size() - 1);
		}
		
		public void fordFolkerson(Network residual) {
			int fl = residual.seen2.get(1);
			
			for(int i = 0; i < residual.seen2.size() - 1; i++) {
					if(residual.seen2.get(i + 1) < fl) {
						fl = residual.seen2.get(i + 1);
					}
			}
			
			for(int i = 0; i < residual.seen1.size() - 1; i++) {
				ADJM[residual.seen1.get(i)][residual.seen1.get(i + 1)] += fl;
			}
			
			for(int i = 0; i < residual.seen1.size() - 1; i++) {
				residual.ADJM[residual.seen1.get(i)][residual.seen1.get(i + 1)] -= fl;
				residual.ADJM[residual.seen1.get(i + 1)][residual.seen1.get(i)] += fl;

			}
		}
	}
