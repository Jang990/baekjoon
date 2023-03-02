import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] numberArray = new String[n];
		Map<Character, Integer> point = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String wordNumber = br.readLine();
			for (int j = 0; j < wordNumber.length(); j++) {
				char c = wordNumber.charAt(j);
				int wordPoint = (int)Math.pow(10, wordNumber.length()-1-j);
				point.put(c, point.getOrDefault(c, 0)+wordPoint);
			}
			
			numberArray[i] = wordNumber;
		}
		
		br.close();
		
		Map<Character, Integer> wordNumber = new HashMap<>();
		List<Character> sortedWords = point.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.map(e -> e.getKey()).collect(Collectors.toList());
		for (int i = 0; i < sortedWords.size(); i++) {
			wordNumber.put(sortedWords.get(i), 10-sortedWords.size()+i);
		}
		
		int sum = 0;
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length(); j++) {
				char c = numberArray[i].charAt(j);
				sum += wordNumber.get(c) * (int)Math.pow(10, numberArray[i].length()-1-j);
			}
		}
		
		System.out.println(sum);
		
	}

}
