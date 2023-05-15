import java.util.*;

public class MainScheduling {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int r = in.nextInt();
			int n = in.nextInt();
			int c = in.nextInt();
			if(r == 0 && n == 0 && c == 0) {
				break;
			}
			HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
			ArrayList<String> sL = new ArrayList<String>();
			ArrayList<String> cL = new ArrayList<String>();
			
			for (int i = 0; i < r; i++) {
				String s1 = in.next();
				String s2 = in.next();
				hm.putIfAbsent(s1,new ArrayList<>());
				if(!hm.get(s1).contains(s2)) {
					hm.get(s1).add(s2);
				}
				if(!sL.contains(s1)) {
					sL.add(s1);
				}
				if(!cL.contains(s2)) {
					cL.add(s2);
				}
			}
			Network network = new Network(sL.size() + n + 2);
			Network resNetwork = new Network(sL.size() + n + 2);
			
			for(String s : hm.keySet()) {
				int idxS = sL.indexOf(s) + 1;
				network.newEdge(0, idxS, 0);
				resNetwork.newEdge(0, idxS, c);
				resNetwork.newEdge(idxS, 0, 0);
				for(String t : hm.get(s)) {
					int idxT = network.ADJM.length - cL.indexOf(t) - 2;
					network.newEdge(idxS, idxT, 0);
					resNetwork.newEdge(idxS, idxT, 1);
					resNetwork.newEdge(idxT, sL.indexOf(t) + 1, 0);
				}
			}
			
			for (String s : cL) {
				network.newEdge(network.ADJM.length-cL.indexOf(s)-2, network.ADJM.length-1, 0);
				resNetwork.newEdge(network.ADJM.length-cL.indexOf(s)-2, network.ADJM.length-1,0);
				resNetwork.newEdge(network.ADJM.length-1, network.ADJM.length-cL.indexOf(s)-2,0);
			}
			
			for (int i = 0; i < n; i++) {
				String name = in.next();
				int capacity = in.nextInt();
				for(String s : cL) {
					if(s.equals(name)) {
						resNetwork.ADJM[network.ADJM.length - cL.indexOf(s) - 2][network.ADJM.length - 1] = capacity;
					}
				}
			}
			
			while(resNetwork.dfs() == 1) {
				network.fordFolkerson(resNetwork);
				resNetwork.seen1 = new ArrayList<Integer>();
				resNetwork.seen2 = new ArrayList<Integer>();
	
			}
			
			int flow = 0;
			
			for(int i : network.ADJM[0]) {
				flow += i;
			}
			if(flow==c*sL.size()) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
		in.close();
	}
}


