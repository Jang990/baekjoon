import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] switches = initSwitches(br, n);

        int studentCnt = Integer.parseInt(br.readLine());
        while (studentCnt-- > 0) {
            String[] arg = br.readLine().split(" ");
            if (arg[0].equals("1"))
                doBoy(switches, Integer.parseInt(arg[1]));
            else
                doGirl(switches, Integer.parseInt(arg[1]));
        }
        br.close();

        print(switches);
    }

    private static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
            if(i % 20 == 0)
                sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] initSwitches(BufferedReader br, int n) throws IOException {
        int[] switches = new int[n + 1];
        String[] switchesStr = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            switches[i + 1] = Integer.parseInt(switchesStr[i]);
        }
        return switches;
    }

    private static void doGirl(int[] switches, int targetIdx) {
        change(switches, targetIdx);

        int left = targetIdx - 1, right = targetIdx + 1;
        while (left > 0 && right < switches.length) {
            if(switches[left] != switches[right])
                break;

            change(switches, left);
            change(switches, right);
            left--;
            right++;
        }
    }

    private static void doBoy(int[] switches, int baseIdx) {
        int i = 1;
        while (baseIdx * i < switches.length) {
            change(switches, baseIdx * i);
            i++;
        }
    }

    private static void change(int[] switches, int idx) {
        switches[idx] = (switches[idx] == 1) ? 0 : 1;
    }
}
