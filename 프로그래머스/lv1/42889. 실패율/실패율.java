import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int N, int[] stages) {
        Arrays.sort(stages);
        Queue<Integer> qu = Arrays.stream(stages).sorted().boxed()
        		.collect(Collectors.toCollection(LinkedList::new));
        Map<Integer, Double> hash = new HashMap<>();
        
        double size = 0;
        double cnt = 0;
        for (int i = 1; i <= N; i++) {
        	size = qu.size();
        	cnt = 0;
        	while(!qu.isEmpty() && qu.peek() == i) {
        		qu.poll();
        		cnt++;
        	}
        	if(size == 0)
        		hash.put(i, (double)0);
        	else
        		hash.put(i, cnt/size);
		}
        
        return hash.entrySet().stream()
        		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        		.map(e -> e.getKey()).mapToInt(Integer::valueOf)
        		.toArray();
    }
}