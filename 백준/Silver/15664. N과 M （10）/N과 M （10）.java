import java.util.*;

public class Main {

	static int[] inArr, outArr;
	static boolean[] visited;
	static Set<String> set = new LinkedHashSet<String>();
	static int N, M;
	static StringBuffer sb = new StringBuffer();
	
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
		
		inArr = Arrays.stream(inArr).sorted().toArray();
		
		Rec(0);
		
		set.stream().forEach((s) -> sb.append(s + "\n"));
		System.out.println(sb);
		
		sc.close();
	}

	private static void Rec(int idx) {
		if(idx == M) {
			String s = new String("");
			for (int i = 0; i < outArr.length; i++) {
				s += outArr[i] + " ";
			}
			set.add(s);
			return;
		}
		
		for (int i = 0; i < inArr.length; i++) {
			if(!visited[i] && check(idx, inArr[i])) {
				visited[i] = true;
				outArr[idx] = inArr[i];
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

	private static boolean check(int idx, int num) {
		for (int j = 0; j < idx; j++) {
			if(outArr[j] > num) return false;
		}
		
		return true;
	}

}