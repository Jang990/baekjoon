import java.util.*;
import java.awt.*;

class Solution {
    static int[][] graph;
    static int[][] id;
    static HashMap<Integer, Integer> idAndSize = new HashMap<>();

    int currentId = 1;
    public int solution(int[][] land) {
        // 땅마다 ID + 사이즈 붙혀주기
        // 세로땅마다 방문ID, 누적합 기록하기
        // 최고 누적합 출력
        graph = land;
        id = new int[land.length][land[0].length];
        
        checkIdAndSize();
        
        int answer = 0;
        for(int x = 0; x < graph[0].length; x++) {
            answer = Math.max(checkOil(x), answer);
        }
        return answer;
    }
    
    int checkOil(int x) {
        Set<Integer> visitedIds = new HashSet<>();
        int oil = 0;
        for(int y = 0; y < graph.length; y++) {
            int currentId = id[y][x];
            if(currentId == 0)
                continue;
            if(visitedIds.contains(currentId))
                continue;
            oil += idAndSize.get(currentId);
            visitedIds.add(currentId);
        }
        return oil;
    }
    
    void checkIdAndSize() {
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 0 || (graph[i][j] == 1 && id[i][j] != 0))
                    continue;
                int size = checkId(new Point(j, i), currentId);
                idAndSize.put(currentId, size);
                currentId++;
            }
        }
    }
    
    int[] dirX = {0,0,1,-1};
    int[] dirY = {1,-1,0,0};
    int checkId(Point start, int currentId) {
        int size = 1;
        Queue<Point> qu = new LinkedList<>();
        qu.offer(start);
        id[start.y][start.x] = currentId;
        
        while(!qu.isEmpty()) {
            Point next = qu.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = next.x + dirX[i];
                int nextY = next.y + dirY[i];
                
                if(isOutOfBound(nextX, nextY) 
                   || graph[nextY][nextX] != 1 
                   || id[nextY][nextX] != 0)
                    continue;
                qu.offer(new Point(nextX, nextY));
                id[nextY][nextX] = currentId;
                size++;
            }
        }
        
        return size;
    }
    
    boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y; 
    }
}