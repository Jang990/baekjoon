import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    static int[] towers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        towers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        Stack<Tower> st = new Stack<>();
        int[] result = new int[N];
        for (int i = towers.length - 1; i >= 0; i--) {
            while (!st.isEmpty()) {
                if(st.peek().h >= towers[i])
                    break;

                Tower tower = st.pop();
                result[tower.idx] = i + 1;
            }

            st.push(new Tower(towers[i], i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static class Tower {
        int h, idx;

        public Tower(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
    }
}
