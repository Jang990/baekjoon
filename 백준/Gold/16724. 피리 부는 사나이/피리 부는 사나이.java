import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    static final int[] dirs = {UP, DOWN, LEFT, RIGHT};
    static final String[] dirStrs = {"U", "D", "L", "R"};
    static final int[] dirX = {0, 0, -1, 1};
    static final int[] dirY = {-1, 1, 0, 0};

    static int[][] dirGraph;
    static int[][] visitedIds;
    static int id = 0;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        input();
        visitedIds = new int[dirGraph.length][dirGraph[0].length];
        for (int i = 0; i < dirGraph.length; i++) {
            for (int j = 0; j < dirGraph[0].length; j++) {
                if(visitedIds[i][j] != 0)
                    continue;

                if(isNewCycle(new Point(j, i)))
                    result++;
            }
        }

        System.out.println(result);
    }

    private static boolean isNewCycle(Point current) {
        id++;
        visitedIds[current.y][current.x] = id;

        while (true) {
            Point next = new Point(
                    current.x + dirX[dir(current)],
                    current.y + dirY[dir(current)]
            );

            if(isVisited(next) && isNotSameId(next, id))
                return false;
            if(isVisited(next))
                break;

            visitedIds[next.y][next.x] = id;
            current = next;
        }

        return true;
    }

    private static boolean isNotSameId(Point next, int id) {
        return visitedIds[next.y][next.x] != id;
    }

    private static boolean isVisited(Point next) {
        return visitedIds[next.y][next.x] != 0;
    }

    private static int dir(Point current) {
        return dirGraph[current.y][current.x];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        dirGraph = new int[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
        for (int i = 0; i < dirGraph.length; i++) {
            dirGraph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Main::convertDirStr)
                    .toArray();
        }
        br.close();
    }

    private static int convertDirStr(String dir) {
        for (int i = 0; i < dirStrs.length; i++) {
            if(dirStrs[i].equals(dir))
                return dirs[i];
        }
        throw new IllegalArgumentException();
    }
}
