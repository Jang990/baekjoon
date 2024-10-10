import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String board = br.readLine();
        br.close();

        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) != 'X') {
                sb.append(".");
                continue;
            }

            int length = getXLength(board, i);
            if (length % 2 != 0) {
                System.out.println(-1);
                return;
            }

            addAAAA(length / 4);
            if(length % 4 != 0)
                addBB();
            i += (length - 1);
        }

        System.out.println(sb);
    }

    private static void addBB() {
        sb.append("BB");
    }

    private static int getXLength(String board, int start) {
        int result = 0;
        for (int i = start; i < board.length(); i++) {
            if(board.charAt(i) != 'X')
                break;
            result++;
        }
        return result;
    }

    private static void addAAAA(int repeat) {
        while (repeat-- > 0) {
            sb.append("AAAA");
        }
    }
}
