class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx4G = 0;

        for (int i = 1; i <= n; i++) {
            if (idx4G < stations.length && stations[idx4G] - w <= i && i <= stations[idx4G] + w) {
                i = stations[idx4G] + w;
                idx4G++;
                continue;
            }

            answer++;
            i += (2 * w);
        }

        return answer;
    }
}