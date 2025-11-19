import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int goal;
    private static List<Road> roads = new ArrayList<>();
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readLine(br);
        int roadCnt = arr[0];
        goal = arr[1];

        for (int i = 0; i < roadCnt; i++) {
            arr = readLine(br);
            if(arr[1] > goal)
                continue;
            roads.add(new Road(arr[0], arr[1], arr[2]));
        }
        br.close();

        roads.sort(Comparator.comparingInt(Road::getStart));

        rec(-1, 0, 0);

        System.out.println(result);
    }

    private static void rec(int selectedRoad, int currentLoc, int movedLen) {
        if (currentLoc == goal) {
            result = Math.min(result, movedLen);
            return;
        }

        // 직선이동
        rec(-1, goal, movedLen + goal - currentLoc);

        // 지름길 탐색
        for (int roadIdx = selectedRoad + 1; roadIdx < roads.size(); roadIdx++) {
            Road road = roads.get(roadIdx);
            if(road.start < currentLoc)
                continue;
            int nextMovedLen = road.start - currentLoc + road.len + movedLen;
            rec(roadIdx, road.end, nextMovedLen);
        }
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static class Road {
        int start, end, len;

        public int getStart() {
            return start;
        }

        public Road(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }
    }
}
