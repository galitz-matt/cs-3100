import java.util.Scanner;
import java.util.ArrayList;

public class MainDaycare {
	
	public static int extra = 0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int j = 0; j < 2; j++) {
			int numRooms = in.nextInt();
			ArrayList<Room> vacancy = new ArrayList<Room>();
			ArrayList<Room> incNC = new ArrayList<Room>();
			ArrayList<Room> sameNC = new ArrayList<Room>();
			ArrayList<Room> decNC = new ArrayList<Room>();
	 		
			for (int i = 0; i < numRooms; i++) {
				int C = in.nextInt();
				int NC = in.nextInt();
				Room newRoom = new Room(C, NC);
				if (C == NC) {
					sameNC.add(newRoom);
				}
				else if (C > NC) {
					decNC.add(newRoom);
				}
				else {
					incNC.add(newRoom);
				}
			}
			
			decNC.sort(new DecComparator());
			incNC.sort(new IncComparator());
			
			for (Room room : incNC) {
				moveKids(room, vacancy);
				room.C = room.NC;
				vacancy.add(room);
			}
			
			for (Room room : sameNC)  {
				moveKids(room, vacancy);
				vacancy.add(room);
			}
			
			for (Room room : decNC) {
				moveKids(room, vacancy);
				room.C = room.NC;
				vacancy.add(room);
			}
			
			System.out.println(extra);
			extra = 0;
		}
		in.close();
	}
	
	public static void moveKids(Room room, ArrayList<Room> vacancy) {
		for (int i = 0; i < vacancy.size(); i++) {
			int residual = vacancy.get(i).C - vacancy.get(i).K;
			if (residual > room.K) {
				vacancy.get(i).K += room.K;
				room.K = 0;
				return;
			}
			room.K -= residual;
			vacancy.get(i).K += residual;
		}
		extra += room.K;
		room.K = 0;
	}
}
