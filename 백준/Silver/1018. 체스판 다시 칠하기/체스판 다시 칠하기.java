import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        board = new String[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
        for (int i = 0; i < board.length; i++)
            board[i] = br.readLine().split("");
        br.close();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= board.length - 8; i++) {
            for (int j = 0; j <= board[0].length - 8; j++) {
                min = Math.min(min, blackAndWhite(j, i));
                min = Math.min(min, whiteAndBlack(j, i));
            }
        }


        System.out.println(min);
    }

    private static int whiteAndBlack(int startX, int startY) {
        int changedCnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String rightColor;
                if((i % 2 + j) % 2 == 0)
                    rightColor = "W";
                else
                    rightColor = "B";

                if(!board[startY + i][startX + j].equals(rightColor))
                    changedCnt++;
            }
        }
        return changedCnt;
    }

    private static int blackAndWhite(int startX, int startY) {
        int changedCnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String rightColor;
                if((i % 2 + j) % 2 == 0)
                    rightColor = "B";
                else
                    rightColor = "W";

                if(!board[startY + i][startX + j].equals(rightColor))
                    changedCnt++;
            }
        }
        return changedCnt;
    }
}
