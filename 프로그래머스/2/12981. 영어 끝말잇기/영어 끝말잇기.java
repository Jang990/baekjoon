import java.util.HashSet;
class Solution {
    public static int[] solution(int n, String[] words) {
        HashSet<String> set = new HashSet<>();
        set.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            int round = round(n, i);
            int turn = turn(n, i);

            String prev = words[i - 1];
            if(prev.charAt(prev.length() - 1) != words[i].charAt(0) || set.contains(words[i])) {
                return new int[]{turn, round};
            }

            set.add(words[i]);
        }

        return new int[]{0, 0};
    }

    public static int round(int peopleCnt, int idx) {
        return idx / peopleCnt + 1;
    }

    public static int turn(int peopleCnt, int idx) {
        return idx % peopleCnt + 1;
    }
}