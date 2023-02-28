import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {
/*
4 3
0
2 1 2
1 3
3 2 3 4

4 5
1 1
1 1
1 2
1 3
1 4
2 4 1
 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		int person = NM[0];
		int party = NM[1];
		boolean[][] graph = new boolean[person+1][person+1];
		boolean[] visited = new boolean[person+1];
		Set<Integer> known = new HashSet<>();
		
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		for (int i = 1; i < arr.length; i++) {
			known.add(arr[i]);
		}
		
		List<List<Integer>> guestList = new ArrayList<>();
		for (int i = 0; i < party; i++) {
			int[] guest = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
			List<Integer> list = new ArrayList<>();
			for (int j = 1; j < guest.length; j++) {
				list.add(guest[j]);
				for (int k = j+1; k < guest.length; k++) {
					graph[guest[j]][guest[k]] = true;
					graph[guest[k]][guest[j]] = true;
				}
			}
			guestList.add(list);
		}
		
		br.close();
		
		List<Integer> knownList = new ArrayList<>(known);
		for (Integer integer : knownList) {
			BFS(integer, graph, known, visited);
		}
		
		for (List<Integer> list : guestList) {
			for (int i = 0; i < list.size(); i++) {
				if(!known.contains(list.get(i)))
					continue;
				
				party--;
				break;
			}
		}
		
		System.out.println(party);
	}

	private static void BFS(Integer next, boolean[][] graph, Set<Integer> known, boolean[] visited) {
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(next);
		visited[next] = true;
		while(!qu.isEmpty()) {
			int now = qu.poll();
			for (int i = 1; i < graph[now].length; i++) {
				if(graph[now][i] && !visited[i]) {
					qu.offer(i);
					visited[i] = true;
					known.add(i);
				}
			}
		}
	}

}
