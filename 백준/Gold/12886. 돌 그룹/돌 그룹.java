import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        if(bfs())
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static boolean bfs() {
        if (equalEachElements(arr))
            return true;

        Queue<int[]> qu = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(arr));
        qu.offer(arr);


        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            for (int i = 0; i < 3; i++) {
                if (now[i % 3] == now[(i + 1) % 3])
                    continue;

                int[] next = process(now[i % 3], now[(i + 1) % 3], now[(i + 2) % 3]);
                String str = Arrays.toString(next);
                if(visited.contains(str))
                    continue;

                if (equalEachElements(next))
                    return true;

                visited.add(str);
                qu.offer(next);
            }


        }
        return false;
    }

    private static int[] process(int n1, int n2, int n3) {
        int[] result = new int[3];
        result[2] = n3;
        if (n1 > n2) {
            result[0] = n1 - n2;
            result[1] = n2 * 2;
        } else {
            result[0] = n2 - n1;
            result[1] = n1 * 2;
        }

        return result;
    }

    private static boolean equalEachElements(int[] arr) {
        return arr[0] == arr[1] && arr[1] == arr[2];
    }
}
