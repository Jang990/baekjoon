import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int[] num;
    static int[] cond;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        /*
        cond 조합하기. - 백트레킹
        조합한 cond 계산하기 - 최솟값 최댓값에 넣기
        출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCnt = Integer.parseInt(br.readLine());
        num = readLine(br);
        cond = readLine(br);
        br.close();

        rec(0, new Stack<>());

        System.out.println(max);
        System.out.println(min);
    }

    private static void rec(int depth, Stack<Integer> selected) {
        if (depth >= num.length - 1) {
            int result = calc(num, selected);
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(cond[i] == 0)
                continue;
            cond[i]--;
            selected.push(i);
            rec(depth + 1, selected);
            cond[i]++;
            selected.pop();
        }
    }

    private static int calc(int[] num, Stack<Integer> selected) {
        int result = num[0];
        for (int i = 0; i < selected.size(); i++) {
            switch (selected.get(i)) {
                case 0:
                    result += num[i + 1];
                    break;
                case 1:
                    result -= num[i + 1];
                    break;
                case 2:
                    result *= num[i + 1];
                    break;
                case 3:
                    result /= num[i + 1];
                    break;
            }
        }

        return result;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
