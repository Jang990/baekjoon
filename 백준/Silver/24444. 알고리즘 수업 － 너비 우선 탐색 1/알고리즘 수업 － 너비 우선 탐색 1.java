import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
	
	static int N, M, R;
	static Map<Integer, List<Integer>> map;
	static boolean[] visited;
	static int[] count;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		R = Integer.valueOf(st.nextToken());
		
		visited = new boolean[N+1];
		count = new int[N+1];
		map = new HashMap<>();
		
		int n1,n2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.valueOf(st.nextToken());
			n2 = Integer.valueOf(st.nextToken());
			
			
			if(!map.containsKey(n1)) {
				List<Integer> arr = new ArrayList<>();
				map.put(n1, arr);
			}
			if(!map.containsKey(n2)) {
				List<Integer> arr = new ArrayList<>();
				map.put(n2, arr);
			}
			
			map.get(n1).add(n2);
			map.get(n2).add(n1);
		}
		
		map.keySet().forEach((k) ->
			map.replace(k, map.get(k).stream().sorted().collect(Collectors.toList()))
		);
		
		BFS(R);
		
		for (int i = 1; i < count.length; i++) {
			sb.append(count[i] + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}

	private static void BFS(int startIdx) {
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(startIdx);
		visited[startIdx] = true;
		int cnt = 1;
		count[startIdx] = cnt++;
		
		int n;
		while(!qu.isEmpty()) {
			n = qu.poll();
			List<Integer> list = map.get(n);
			if(list == null) {
				continue;
			}
			
			for (int i = 0; i < list.size(); i++) {
				if(!visited[list.get(i)]) {
					qu.offer(list.get(i));
					visited[list.get(i)] = true;
					count[list.get(i)] = cnt++;
				}
			}
		}
		
	}

}