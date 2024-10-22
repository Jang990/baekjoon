import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            int result = 0;
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Score> scores = new PriorityQueue<>(Comparator.comparing(Score::getTest1));
            for (int j = 0; j < N; j++) {
                String[] line = br.readLine().split(" ");
                scores.add(new Score(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
            }

            int minTest2 = scores.poll().test2;
            result++;
            while (!scores.isEmpty()) {
                Score current = scores.poll();
                if(current.test2 > minTest2)
                    continue;
                minTest2 = current.test2;
                result++;
            }

            sb.append(result).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    static class Score {
        int test1, test2;

        public Score(int test1, int test2) {
            this.test1 = test1;
            this.test2 = test2;
        }

        public int getTest1() {
            return test1;
        }
    }
}
