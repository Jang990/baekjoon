import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] number;
    static String[] operator;
    static int[] order;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        String line = br.readLine();
        br.close();

        number = new int[n / 2 + 1];
        operator = new String[n / 2];
        order = new int[n / 2];
        init(line);

        rec(0, number[0]);
        System.out.println(max);
    }

    private static void rec(int depth, int result) {
        if (depth >= order.length) {
            max = Math.max(max, result);
            return;
        }

        rec(depth + 1, calc(result, number[depth+1], operator[depth]));
        if (depth + 1 < order.length) {
            int rightIdx = depth+1;
            int rightCalcResult = calc(number[rightIdx], number[rightIdx+1], operator[rightIdx]);
            rec(depth + 2, calc(result, rightCalcResult, operator[depth]));
        }
    }

    private static int calc(int base, int target, String operatorIdx) {
        if (operatorIdx.equals("+"))
            return base + target;
        else if(operatorIdx.equals("-"))
            return base - target;
        else if(operatorIdx.equals("*"))
            return base * target;
        else
            return base / target;
    }

    private static void init(String line) {
        number = Arrays.stream(line.replaceAll("[*-/+]", " ").trim().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        operator = line.replaceAll("[0-9]", " ").trim().split(" ");
    }
}
