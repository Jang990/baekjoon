import java.util.*;

class Solution {
    private static Map<String, List<String>> graph;
    private static HashMap<String, Integer> visited;
    
    public static int solution(String begin, String target, String[] words) {
        if (isNotContains(words, target)) {
            return 0;
        }

        graph = initGraph(begin, words);
        visited = new HashMap<>();
        for (String word : words) {
            visited.put(word, 0);
        }

        bfs(begin);

        return visited.get(target) - 1;
    }

    private static void bfs(String begin) {
        Queue<String> qu = new LinkedList<>();
        qu.offer(begin);
        visited.put(begin, 1);

        while (!qu.isEmpty()) {
            String now = qu.poll();
            List<String> nextList = graph.get(now);
            for (String next : nextList) {
                int nowVisited = visited.get(now);
                int nextVisited = visited.get(next);

                if (nextVisited != 0 && nowVisited >= nextVisited) {
                    continue;
                }

                visited.replace(next, nowVisited + 1);
                qu.offer(next);
            }
        }
    }

    private static Map<String, List<String>> initGraph(String begin, String[] words) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put(begin, new ArrayList<>());
        for (String word : words) {
            graph.put(word, new ArrayList<>());
        }

        for (String key : graph.keySet()) {
            for (String word : words) {
                if (isSimilarWord(key, word)) {
                    graph.get(key).add(word);
                }
            }
        }

        return graph;
    }

    private static boolean isSimilarWord(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }

        int cnt = 0;
        for (int i = 0; i < s1.length() && cnt < 2; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }

            cnt++;
        }

        return cnt < 2;
    }

    private static boolean isNotContains(String[] words, String target) {
        for (String word : words) {
            if (target.equals(word)) {
                return false;
            }
        }

        return true;
    }
}