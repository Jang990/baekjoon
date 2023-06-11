import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Point> house, chicken;
    static int min = Integer.MAX_VALUE;
    static int N, M;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        int[][] map =  new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        house = new ArrayList<>();
        chicken = new ArrayList<>();

        // 치킨집 좌표 뽑기 - 일반집 좌표 뽑기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    house.add(new Point(j, i));
                }
                else if (map[i][j] == 2) {
                    chicken.add(new Point(j, i));
                }
            }
        }

        check = new boolean[chicken.size()];

        // 경우의 수 뽑기
        back(0, 0);
        System.out.println(min);
    }

    private static void back(int depth, int beforeIdx) {
        if (depth == M) {
            calcCityChickenDistance();
            return;
        }

        for (int i = beforeIdx; i < chicken.size(); i++) {
            if(check[i])
                continue;

            check[i] = true;
            back(depth+1, i);
            check[i] = false;
        }
    }

    private static void calcCityChickenDistance() {
        // 경우의 수에 대한 최단 거리 뽑기
        List<Point> selectedChicken = getSelectedChicken();
        int sum = 0;
        for (Point houseLoc : house) {
            int distanceMin = Integer.MAX_VALUE;
            for (Point chickenLoc : selectedChicken) {
                int distance = Math.abs(chickenLoc.x - houseLoc.x) + Math.abs(chickenLoc.y - houseLoc.y);
                distanceMin = Math.min(distanceMin, distance);
            }
            sum += distanceMin;
        }

        min = Math.min(sum, min);
    }

    private static List<Point> getSelectedChicken() {
        List<Point> selected = new ArrayList<>();
        for (int i = 0; i < check.length; i++) {
            if(!check[i])
                continue;
            selected.add(chicken.get(i));
        }
        return selected;
    }
}
