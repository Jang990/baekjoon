import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int[] A = parseArray(br.readLine());
            int[] B = parseArray(br.readLine());
            sb.append(compare(A, B)).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    private static String compare(int[] a, int[] b) {
        for (int i = 4; i > 0; i--) {
            if(a[i] > b[i])
                return "A";
            else if(a[i] < b[i])
                return "B";
        }
        return "D";
    }

    private static int[] parseArray(String line) {
        int[] result = new int[5];
        String[] args = line.split(" ");
        for (int i = 1; i < args.length; i++) {
            result[Integer.parseInt(args[i])]++;
        }
        return result;
    }
}
