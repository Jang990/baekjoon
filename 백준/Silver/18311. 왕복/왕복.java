import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long[] input = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
		long cos = input[0];
		long moved = input[1];
		
		long[] distance = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray(); 
		
		for (int i = 0; i < distance.length; i++) {
			if(moved < distance[i]) {
				System.out.println(String.valueOf(i+1));
				return;
			}
			moved -= distance[i];
		}
		
		for (int i = distance.length-1; i >= 0 ; i--) {
			if(moved < distance[i]) {
				System.out.println(String.valueOf(i+1));
				return;
			}
			moved -= distance[i];
		}
	}

}