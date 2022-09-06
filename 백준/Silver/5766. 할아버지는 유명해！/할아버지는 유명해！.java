import java.util.*;

public class Main {
	static Map<Integer, Integer> countMap;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int week = 0;
		int personCnt = 0;
		
		while(true) {
			int[] firstInput = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			week = firstInput[0];
			personCnt = firstInput[1];
			
			if(week == 0 && personCnt == 0) break;
			
			countMap = new HashMap<>();
			
			for (int i = 0; i < week; i++) {
				int[] players = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				countPlayer(players);
			}
			int firstValue = Collections.max(countMap.values());
			int firstKey = countMap.entrySet().stream().filter(entry -> entry.getValue() == firstValue).findFirst().get().getKey();
			countMap.remove(firstKey);
			
			int secondValue = Collections.max(countMap.values());
			countMap.entrySet().stream()
				.filter(entry -> entry.getValue() == secondValue)
				.mapToInt(entry -> entry.getKey())
				.sorted()
				.forEach((i)-> sb.append(i + " "));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
	
	private static void countPlayer(int[] players) {
		Set<Integer> keys = countMap.keySet();
		for (int i = 0; i < players.length; i++) {
			if(keys.contains(players[i])) countMap.put(players[i], countMap.get(players[i]) + 1);
			else countMap.put(players[i], 1);
		}
	}
}