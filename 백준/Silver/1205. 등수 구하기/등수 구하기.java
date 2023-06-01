import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int myScore = Integer.parseInt(st.nextToken());
        int maxLength = Integer.parseInt(st.nextToken());

        if (N == 0) {
            System.out.println(1);
            br.close();
            return;
        }

        List<Integer> scores = scores = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());;
        br.close();


        if (scores.get(scores.size() - 1) >= myScore && N == maxLength) {
            System.out.println(-1);
            return;
        }

        insertMyScore(scores, myScore, maxLength);
        int result = checkMyRank(scores, myScore);
        System.out.println(result);
    }

    private static void insertMyScore(List<Integer> scores, int myScore, int maxLength) {
        int startSize = scores.size();
        for (int i = 0; i < scores.size() - 1; i++) {
            int nowScore = scores.get(i);
            int prevScore = scores.get(i+1);

            if(nowScore == myScore || (nowScore > myScore && myScore > prevScore)) {
                scores.add(i + 1, myScore);
                break;
            }
        }

        int endSize = scores.size();
        if(startSize == endSize) {
            scores.add(myScore);
        }

        if(scores.size() > maxLength)
            scores.remove(scores.size() - 1);
    }

    private static int checkMyRank(List<Integer> scores, int myScore) {
        if(scores.get(0) <= myScore)
            return 1;

        int rank = 2;

        for (int i = 1; i < scores.size(); i++) {
            int score = scores.get(i);
            if(score == myScore)
                return rank;
            else
                rank++;
        }
        return -1;
    }
}
