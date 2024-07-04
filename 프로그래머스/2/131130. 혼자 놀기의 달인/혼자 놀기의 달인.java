import java.util.*;
class Solution {
    static int[] parent;
    public static int solution(int[] cards) {
        parent = new int[cards.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < cards.length; i++) {
            union(i, cards[i] - 1);
        }
        int[] groupSize = new int[cards.length];
        for (int i = 0; i < groupSize.length; i++) {
            groupSize[find(i)]++;
        }
        Arrays.sort(groupSize);
        return groupSize[groupSize.length - 1] * groupSize[groupSize.length - 2];
    }

    static void union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);

        if(n1Parent > n2Parent)
            parent[n2Parent] = n1Parent;
        else
            parent[n1Parent] = n2Parent;
    }

    static int find(int n) {
        if(parent[n] == n)
            return n;
        return find(parent[n]);
    }
}