import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    static int[][] graph;
    static int maxChicken;
    static int[][] chickenLen;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readLine(br);
        int N = input[0];
        maxChicken = input[1];
        graph = new int[N][N];
        for (int i = 0; i < N; i++)
            graph[i] = readLine(br);

        br.close();

        List<Point> customer = find(1);
        List<Point> chicken = find(2);
        chickenLen = calculateChickenLen(customer, chicken);

        selectRec(0, -1, chicken.size(), new Stack<>());
        System.out.println(min);
    }

    private static void selectRec(int depth, int beforeSelected, int chickenCnt, Stack<Integer> selected) {
        if (depth == maxChicken) {
            min = Math.min(calcMin(selected), min);
            return;
        }

        for (int i = beforeSelected + 1; i < chickenCnt; i++) {
            selected.push(i);
            selectRec(depth + 1,i, chickenCnt, selected);
            selected.pop();
        }
    }

    private static int calcMin(List<Integer> selected) {
        int result = 0;
        for (int i = 0; i < chickenLen[0].length; i++) {
            int currentMin = Integer.MAX_VALUE;
            for (Integer chicken : selected) {
                currentMin = Math.min(chickenLen[chicken][i], currentMin);
            }
            result += currentMin;
        }
        return result;
    }

    private static int[][] calculateChickenLen(List<Point> customer, List<Point> chicken) {
        int[][] chickenLen = new int[chicken.size()][customer.size()];
        for (int i = 0; i < chicken.size(); i++) {
            for (int j = 0; j < customer.size(); j++) {
                Point chickenLoc = chicken.get(i);
                Point customerLoc = customer.get(j);

                chickenLen[i][j] = Math.abs(chickenLoc.x - customerLoc.x)
                        + Math.abs(chickenLoc.y - customerLoc.y);
            }
        }

        return chickenLen;
    }

    private static List<Point> find(int target) {
        List<Point> result = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(graph[i][j] != target) continue;
                result.add(new Point(j, i));
            }
        }
        return result;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
