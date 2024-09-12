import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int nodeCnt = Integer.parseInt(br.readLine());
            int[] parent = initParent(br, nodeCnt);

            String[] line = br.readLine().split(" ");
            List<Integer> path1 = findPath(parent, parse(line[0]));
            List<Integer> path2 = findPath(parent, parse(line[1]));

            sb.append(findNearestParent(path1, path2)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int findNearestParent(List<Integer> path1, List<Integer> path2) {
        int min = Math.min(path1.size(), path2.size());
        int i = 1;
        while(min > i && path1.get(i).equals(path2.get(i)))
            i++;

        return path1.get(i - 1);
    }

    private static List<Integer> findPath(int[] parent, int current) {
        List<Integer> result = new LinkedList<>();
        result.add(current);

        while (parent[current] != 0) {
            current = parent[current];
            result.add(current);
        }

        Collections.reverse(result);
        return result;
    }

    private static int[] initParent(BufferedReader br, int nodeCnt) throws IOException {
        int[] result = new int[nodeCnt + 1];
        for (int i = 1; i < nodeCnt; i++) {
            String[] line = br.readLine().split(" ");
            result[parse(line[1])] = parse(line[0]);
        }
        return result;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
