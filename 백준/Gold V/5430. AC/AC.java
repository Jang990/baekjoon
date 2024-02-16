import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int left, right;
    static int now;
    static String[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        while (testcase > 0) {
            String[] command = br.readLine().split("");
            initArray(br);

            for (int i = 0; i < command.length; i++) {
                if (command[i].equals("R")) {
                    now = (now == left) ? right : left;
                    continue;
                }

                if (now == left) {
                    left++;
                    now++;
                } else {
                    right--;
                    now--;
                }

                if(left > right)
                    break;
            }

            if (left <= right)
                sb.append(createArrayString()).append("\n");
            else {
                sb.append("error\n");
            }

            testcase--;
        }
        br.close();
        System.out.println(sb);
    }

    private static String createArrayString() {
        StringBuilder arraySb = new StringBuilder("[");
        if (left == now) {
            for (int i = now; i < right; i++) {
                arraySb.append(numbers[i]).append(",");
            }
        } else {
            for (int i = right-1; i >= left ; i--) {
                arraySb.append(numbers[i]).append(",");
            }
        }
        if(arraySb.length() > 1)
            arraySb.deleteCharAt(arraySb.length() - 1);
        arraySb.append("]");
        return arraySb.toString();
    }

    private static void initArray(BufferedReader br) throws IOException {
        now = 0;
        left = 0;
        right = Integer.parseInt(br.readLine());
        String line = br.readLine().substring(1);
        line = line.substring(0, line.length() - 1);
        numbers = line.split(",");
    }
}
