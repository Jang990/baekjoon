import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] crane;
		List<Integer> cargo;
		
		br.readLine();
		crane = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).sorted().toArray();
		br.readLine();
		cargo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).sorted().boxed().collect(Collectors.toList());
		br.close();
		
		if(cargo.get(cargo.size()-1) > crane[crane.length-1]) {
			System.out.println(-1);
			return;
		}
		
		int count = 0;
		while(!cargo.isEmpty()) {
			
			for (int i = 0; i < crane.length; i++) {
				for (int j = cargo.size()-1; 0 <= j; j--) {
					if(crane[i] < cargo.get(j))
						continue;
					
					cargo.remove(j);
					break;
				}
			}
			count++;
		}
		
		System.out.println(count);
	}

}
