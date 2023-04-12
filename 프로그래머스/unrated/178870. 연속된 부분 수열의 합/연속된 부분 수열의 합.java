class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int resultStartIdx = 0, resultEndIdx = sequence.length-1;
        int endIdx = sequence.length-1, startIdx = sequence.length-1;
        int sum = 0;
        for (int i = sequence.length-1; i >= 0; i--) {
            startIdx = i;
            sum += sequence[i];
            if(sum >= k) {
                if(sum == k && resultEndIdx - resultStartIdx >= endIdx - startIdx) {
                    resultStartIdx = startIdx;
                    resultEndIdx = endIdx;
                }
                sum -= sequence[endIdx];
                endIdx--;
            }
        }

        return new int[] {resultStartIdx, resultEndIdx};
    }
}