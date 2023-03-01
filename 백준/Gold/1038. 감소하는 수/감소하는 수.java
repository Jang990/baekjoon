import java.io. BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		br.close();
		if(n == 0) {
			System.out.println(0);
			return;
		}
		if(n < 10) {
			System.out.println(n);
			return;
		}
		
		list.add((long) 0);
		for (int i = 1; i < 10; i++) {
			Rec(i, 1);
		}
		
		list = list.stream().sorted().collect(Collectors.toList());
		
		if(list.size() <= n)
			System.out.println(-1);
		else
			System.out.println(list.get(n));
	}

	private static void Rec(long num, int depth) {
		if(depth > 10)
			return;
		list.add(num);
		
		for (int i = 0; i < 10; i++) {
			if(num%10 <= i)
				continue;
			
			Rec(num*10 + i, depth+1);
		}
	}

}
