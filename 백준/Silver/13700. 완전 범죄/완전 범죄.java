import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> policeBuildingsSet = new HashSet<>();

        // 건물수 시작 끝 전진수 후진수 경살서 수
        int[] line = readLine(br);
        int buildingCnt = line[0];
        int start = line[1];
        int goal = line[2];
        int forwardLen = line[3];
        int backwardLen = line[4];
        int policeBuildingCnt = line[5];

        int[] policeBuildings = {0};
        if(policeBuildingCnt != 0)
            policeBuildings = readLine(br);
        br.close();

        for (int policeBuilding : policeBuildings)
            policeBuildingsSet.add(policeBuilding);

        int[] visited = new int[buildingCnt + 1];
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = 1;
        while (!qu.isEmpty()) {
            int current = qu.poll();
            int moveForwardLoc = current + forwardLen;
            if (moveForwardLoc <= buildingCnt
                    && !policeBuildingsSet.contains(moveForwardLoc)
                    && (visited[moveForwardLoc] == 0
                    || visited[moveForwardLoc] > visited[current] + 1)
            ) {
                qu.offer(moveForwardLoc);
                visited[moveForwardLoc] = visited[current] + 1;
            }

            int moveBackwardLoc = current - backwardLen;
            if (moveBackwardLoc >= 1
                    && !policeBuildingsSet.contains(moveBackwardLoc)
                    && (visited[moveBackwardLoc] == 0
                    || visited[moveBackwardLoc] > visited[current] + 1)
            ) {
                qu.offer(moveBackwardLoc);
                visited[moveBackwardLoc] = visited[current] + 1;
            }
        }

        if(visited[goal] == 0)
            System.out.println("BUG FOUND");
        else
            System.out.println(visited[goal] - 1);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
