class Solution {
    static int answer;
	public static int solution(int[] number) {
        answer = 0;
        Rec(number, 0, 0, 0);
        return answer;
    }

	private static void Rec(int[] number, int idx, int cnt, int sum) {
		if(cnt == 3) {
			if(sum == 0) {
				answer++;
			}
			return;
		}
		
		for (int i = idx; i < number.length; i++) {
			Rec(number, i+1, cnt+1, sum+number[i]);
		}
			
	}
}