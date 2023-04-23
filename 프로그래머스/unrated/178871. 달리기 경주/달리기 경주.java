import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerAndRank = new HashMap<>();
        Map<Integer, String> rankAndPlayer = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            playerAndRank.put(players[i], i+1);
            rankAndPlayer.put(i+1, players[i]);
        }

        for (String player : callings) {
            int nowRank = playerAndRank.get(player);
            if(nowRank <= 1)
                continue;
            int nextRank = nowRank-1;
            String nextPlayer = rankAndPlayer.get(nextRank);
            playerAndRank.put(player, nextRank);
            playerAndRank.put(nextPlayer, nowRank);
            rankAndPlayer.put(nextRank, player);
            rankAndPlayer.put(nowRank, nextPlayer);
        }

        return rankAndPlayer.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getValue()).toArray(String[]::new);
    }
}