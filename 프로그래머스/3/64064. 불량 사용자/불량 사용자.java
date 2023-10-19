import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static Map<String, Integer> arrayIdx;
    private static Map<String, List<String>> similarId;
    private static String[] bannedId;
    private static boolean[] isBannedUserId;
    private static Set<Set<String>> logs = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        bannedId = banned_id;
        isBannedUserId = new boolean[user_id.length];

        arrayIdx = new HashMap<>();
        similarId = new HashMap<>();
        for (int i = 0; i < user_id.length; i++) {
            arrayIdx.put(user_id[i], i);
        }

        for (int i = 0; i < banned_id.length; i++) {
            ArrayList<String> value = new ArrayList<>();
            similarId.put(banned_id[i], value);
            for (int j = 0; j < user_id.length; j++) {
                if (isSimilarId(banned_id[i], user_id[j])) {
                    value.add(user_id[j]);
                }
            }
        }

        dfs(0, new Stack<>());

        return logs.size();
    }

    private static void dfs(int depth, Stack<String> history) {
        if (depth == bannedId.length) {
            logs.add(history.stream().collect(Collectors.toSet()));
            return;
        }

        List<String> similarUserIds = similarId.get(bannedId[depth]);
        for (String id : similarUserIds) {
            int idx = arrayIdx.get(id);
            if (isBannedUserId[idx])
                continue;

            isBannedUserId[idx] = true;
            history.push(id);
            dfs(depth + 1, history);
            history.pop();
            isBannedUserId[idx] = false;
        }
    }

    private static boolean isSimilarId(String banned, String user) {
        if (banned.length() != user.length()) {
            return false;
        }

        for (int i = 0; i < banned.length(); i++) {
            char c1 = banned.charAt(i);
            char c2 = user.charAt(i);
            if (c1 == '*')
                continue;

            if (c1 != c2)
                return false;
        }

        return true;
    }
}