import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        int[] num = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = Integer.parseInt(br.readLine());
        br.close();

        System.out.printf("%.2f\n", calculate(line, num));
    }

    private static double calculate(String line, int[] num) {
        Stack<Double> stNum = new Stack<>();

        String[] args = line.split("");
        for (String arg : args) {
            char c = arg.charAt(0);
            if(isNum(c))
                stNum.push(Double.parseDouble(Integer.toString(num[c - 'A'])));
            else
                calculateStack(stNum, c);
        }

        return stNum.pop() * 100 / 100.0;
    }

    private static void calculateStack(Stack<Double> stNum, char op) {
        double n2 = stNum.pop(), n1 = stNum.pop();
        switch (op) {
            case '+':
                stNum.push(n1 + n2);
                break;
            case '-':
                stNum.push(n1 - n2);
                break;
            case '*':
                stNum.push(n1 * n2);
                break;
            case '/':
                stNum.push(n1 / n2);
                break;
        }
    }

    private static boolean isNum(char c) {
        return 'A' <= c && c <= 'Z';
    }
}
