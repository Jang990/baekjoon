import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Main {

    private static int[][] arr;
    private static int max = Integer.MIN_VALUE;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }
        br.close();

        rec(0, new int[5]);

        System.out.println(max);
    }

    private static void rec(int depth, int[] dir) {
        if (depth == 5) {
            int[][] result = moveAll(dir);
            max = Math.max(max, getMaxNumber(result));
            return;
        }

        for (int i = 0; i < 4; i++) {
            dir[depth] = i;
            rec(depth + 1, dir);
        }
    }

    private static int getMaxNumber(int[][] result) {
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                max = Math.max(max, result[i][j]);
            }
        }
        return max;
    }

    private static int[][] moveAll(int[] dir) {
        int[][] moved = cloneArr();
        for (int i = 0; i < dir.length; i++) {
            Function<int[][], int[][]> move = null;
            Function<int[][],Boolean> merge = null;
            switch (dir[i]) {
                case 0:
                    move = (target) -> down(target);
                    merge = (target) -> mergeDown(target);
                    break;
                case 1:
                    move = (target) -> up(target);
                    merge = (target) -> mergeUp(target);
                    break;
                case 2:
                    move = (target) -> left(target);
                    merge = (target) -> mergeLeft(target);
                    break;
                default:
                    move = (target) -> right(target);
                    merge = (target) -> mergeRight(target);
                    break;
            }

            moved = move.apply(moved);
            merge.apply(moved);
            moved = move.apply(moved);
        }
        return moved;
    }

    private static int[][] cloneArr() {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = arr[i][j];
            }
        }
        return clone;
    }

    private static int[][] right(int[][] base) {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cloneIdx = 0;
            for (int j = n-1; j >= 0; j--) {
                if (base[i][j] == 0)
                    continue;

                clone[i][n-1-cloneIdx] = base[i][j];
                cloneIdx++;
            }
        }
        return clone;
    }

    private static int[][] left(int[][] base) {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cloneIdx = 0;
            for (int j = 0; j < n; j++) {
                if (base[i][j] == 0)
                    continue;

                clone[i][cloneIdx] = base[i][j];
                cloneIdx++;
            }
        }
        return clone;
    }

    private static int[][] up(int[][] base) {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cloneIdx = 0;
            for (int j = 0; j < n; j++) {
                if (base[j][i] == 0)
                    continue;

                clone[cloneIdx][i] = base[j][i];
                cloneIdx++;
            }
        }
        return clone;
    }

    private static int[][] down(int[][] base) {
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            int cloneIdx = 0;
            for (int j = n-1; j >= 0; j--) {
                if (base[j][i] == 0)
                    continue;

                clone[n-1-cloneIdx][i] = base[j][i];
                cloneIdx++;
            }
        }
        return clone;
    }

    private static boolean mergeDown(int[][] base) {
        boolean isMerged = false;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > 0; j--) {
                if (base[j][i] == 0 || base[j][i] != base[j-1][i])
                    continue;

                base[j][i] += base[j-1][i];
                base[j-1][i] = 0;
                isMerged = true;
            }
        }
        return isMerged;
    }

    private static boolean mergeUp(int[][] base) {
        boolean isMerged = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                if (base[j][i] == 0 || base[j][i] != base[j+1][i])
                    continue;

                base[j][i] += base[j+1][i];
                base[j+1][i] = 0;
                isMerged = true;
            }
        }
        return isMerged;
    }

    private static boolean mergeLeft(int[][] base) {
        boolean isMerged = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                if (base[i][j] == 0 || base[i][j] != base[i][j+1])
                    continue;

                base[i][j] += base[i][j+1];
                base[i][j+1] = 0;
                isMerged = true;
            }
        }
        return isMerged;
    }

    private static boolean mergeRight(int[][] base) {
        boolean isMerged = false;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > 0; j--) {
                if (base[i][j] == 0 || base[i][j] != base[i][j-1])
                    continue;

                base[i][j] += base[i][j-1];
                base[i][j-1] = 0;
                isMerged = true;
            }
        }
        return isMerged;
    }
}
