import java.util.HashSet;
class Solution {
    boolean[] visited;
	String[] number;
	HashSet<Integer> set;
	public int solution(String numbers) {
        set = new HashSet<>();
        
        number = numbers.split("");
        visited = new boolean[number.length];
        
        Rec(0, "");
        
        return set.size();
    }
	private void Rec(int idx, String prime) {
		if(idx > number.length || prime.length() > number.length) {
			return;
		}
		
		if(prime.length() > 0) {
			int n = Integer.valueOf(prime);
			if(isPrime(n)) {
				set.add(n);
			}
		}
		
		for (int i = 0; i < number.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			Rec(idx+1, prime+number[i]);
			visited[i] = false;
		}
	}
	
	private boolean isPrime(int prime) {
		if(prime <= 1) return false;
		
		for (int i = 2; i <= (int)Math.sqrt(prime); i++) {
			if(prime%i == 0)
				return false;
		}
		
		return true;
	}
}