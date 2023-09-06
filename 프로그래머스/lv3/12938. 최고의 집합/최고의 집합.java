class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int div = s / n;
        if (div == 0) {
            return new int[] {-1};
        }

        int mod = s - div * n;
        for (int i = 1; i <= mod; i++) {
            answer[answer.length - i]++;
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] += div;
        }

        return answer;
    }
}