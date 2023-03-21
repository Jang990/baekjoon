class Solution {
    public static int solution(String[] board) {
        int answer = -1;

        String[][] boardArr = new String[3][3];

        int dotCnt = 0,
                OCnt = 0,
                XCnt = 0;

        for (int i = 0; i < board.length; i++) {
            boardArr[i] = board[i].split("");
            for (String s : boardArr[i]) {
                char c = s.charAt(0);
                switch (c) {
                    case '.':
                        dotCnt++;
                        break;
                    case 'O':
                        OCnt++;
                        break;
                    case 'X':
                        XCnt++;
                        break;
                }
            }
        }

        if(dotCnt == 9)
            return 1;
        if(dotCnt%2 == 0 && OCnt != XCnt+1)
            return 0;
        else if(dotCnt%2 == 1 && OCnt != XCnt)
            return 0;


        // .이 짝수면 O가 마지막
        // 즉 .이 짝수일 때 X가 만들어져있으면 0;
        String[] check = new String[2];
        check[0] = "X";
        check[1] = "O";


        if(isComplete(boardArr, check[dotCnt%2]))
            return 0;
        else
            return 1;

    }

    private static boolean isComplete(String[][] boardArr, String s) {
        int cnt;
        for (int i = 0; i < boardArr.length; i++) {
            cnt = 0;
            for (int j = 0; j < boardArr[i].length; j++) {
                if(boardArr[i][j].equals(s)) {
                    cnt++;
                }
            }
            if(cnt == 3)
                return true;
        }

        for (int i = 0; i < boardArr[0].length; i++) {
            cnt = 0;
            for (int j = 0; j < boardArr.length; j++) {
                if(boardArr[j][i].equals(s)) {
                    cnt++;
                }
            }
            if(cnt == 3)
                return true;
        }

        if(boardArr[0][0].equals(s) && boardArr[1][1].equals(s) && boardArr[2][2].equals(s))
            return true;
        if(boardArr[0][2].equals(s) && boardArr[1][1].equals(s) && boardArr[2][0].equals(s))
            return true;

        return false;
    }
}