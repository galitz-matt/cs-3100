import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class MainMovingBoxes {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numTC = in.nextInt();
		ArrayList<ArrayList<Price>> result = new ArrayList<ArrayList<Price>>();
		
		for (int k = 0; k < numTC; k++) {
			int b = in.nextInt();
			int m = in.nextInt();
			int c = in.nextInt();
			HashMap<String, Integer[]> info = new HashMap<String, Integer[]>();
			result.add(new ArrayList<Price>());
			
			for (int i = 0; i < c; i++) {
				String name = in.next();
				int s1 = in.nextInt();
				int s2 = in.nextInt();
				info.put(name, new Integer[] {s1, s2});
			}

			for (String company : info.keySet()) {
				int toMove = b;
				int cost = 0;
				int s1Cost = info.get(company)[0];
				int s2Cost = info.get(company)[1];
				while (toMove > m) {
					int halfOfBoxes = (toMove + 1) / 2;
					
					if (s1Cost * halfOfBoxes < s2Cost || toMove - halfOfBoxes < m) {	
						toMove--;
						cost += s1Cost;
					}
					else {
						toMove -= halfOfBoxes;
						cost += s2Cost;
					}
				}
				result.get(k).add(new Price(company, cost));
			}
		}
		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("Case " + String.valueOf(i + 1));
			result.get(i).sort(new PriceComparator());
			for (Price p : result.get(i)) {
				System.out.println(p);
			}
		}
		
		in.close();
	}
}