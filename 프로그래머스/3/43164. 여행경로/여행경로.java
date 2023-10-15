import java.util.*;

class Solution {
    private static final String start = "ICN";
    private static String[] answer;
    private static Map<String, List<String>> graph;

    public static String[] solution(String[][] tickets) {
        initGraph(tickets);
        answer = new String[tickets.length+1];

        List<String> now = graph.get(start);
        Stack<String> route = new Stack<>();
        route.push(start);
        dfs(now, route);

        return answer;
    }

    private static void initGraph(String[][] tickets) {
        graph = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            List<String> set = graph.get(tickets[i][0]);
            if (set == null) {
                set = new ArrayList<>();
                graph.put(tickets[i][0], set);
            }
            set.add(tickets[i][1]);
        }

        for (List<String> value : graph.values()) {
            value.sort(Comparator.naturalOrder());
        }
    }

    private static boolean dfs(List<String> now, Stack<String> route) {
        if (route.size() == answer.length) {
            for (int i = 0; i < answer.length; i++) {
                answer[i] = route.get(i);
            }
            return true;
        }

        if (now == null) {
            return false;
        }

        String[] clone = now.stream().toArray(String[]::new);
        for (int i = 0; i < clone.length; i++) {
            String next = clone[i];
            now.remove(next);
            route.push(next);
            if(dfs(graph.get(next), route))
                return true;
            route.pop();
            now.add(next);
        }

        return false;
    }
}