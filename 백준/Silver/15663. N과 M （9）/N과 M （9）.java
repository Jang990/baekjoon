import java.util.*;

public class Main {

	static int N, M;
	static StringBuffer sb = new StringBuffer();
	static Set<String> set = new LinkedHashSet<>();
	static int[] inArr, outArr;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		inArr = new int[N];
		visited = new boolean[N];
		outArr = new int[M];
		
		for (int i = 0; i < N; i++) {
			inArr[i] = sc.nextInt();
		}
		sc.close();
		
		inArr = Arrays.stream(inArr).sorted().toArray();
		Rec(0);
		set.stream().forEach((s) -> sb.append(s + "\n"));
		
		System.out.println(sb);
		
	}

	private static void Rec(int idx) {
		if(idx == M) {
			String str = new String("");
			for (int i = 0; i < outArr.length; i++) {
				str += outArr[i] + " ";
			}
			set.add(str);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				outArr[idx] = inArr[i];
				Rec(idx + 1);
				visited[i] = false;
			}
		}
	}

}