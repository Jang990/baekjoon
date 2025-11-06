import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        // 우선순위큐로 서버 증설한다.
        // 증설횟수 기록한다.
        // peek 비교해서 시간이 지났으면 없앤다.
        int answer = 0;
        Servers servers = new Servers(m, k);
        for(int time = 0; time < players.length; time++) {
            servers.play(time, players[time]);
        }
        
        return servers.createdServerCnt;
    }
    
    static class Servers {
        final int maxPlayerInServer;
        final int timeLimit;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int createdServerCnt = 0;
        
        public Servers(int maxPlayerInServer, int timeLimit) {
            this.maxPlayerInServer = maxPlayerInServer;
            this.timeLimit = timeLimit;
        }
        
        public void play(int time, int playerCnt) {
            pollServers(time);
            if(playerCnt < maxPlayerInServer)
                return;
            
            int requiredServerCnt = playerCnt / maxPlayerInServer;
            if(requiredServerCnt <= pq.size())
                return;
            createNewServer(time, requiredServerCnt - pq.size());
        }
        
        void createNewServer(int time, int cnt) {
            for(int i = 0; i < cnt; i++)
                pq.offer(time);
            createdServerCnt += cnt;
        }
        
        void pollServers(int time) {
            while(!pq.isEmpty() && time - pq.peek() >= timeLimit)
                pq.poll();
        }
    }
}