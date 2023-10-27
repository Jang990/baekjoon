import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nationCnt = Integer.valueOf(st.nextToken());
        int nationNumber = Integer.valueOf(st.nextToken());

        Nation[] nations = new Nation[nationCnt];
        for (int i = 0; i < nationCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.valueOf(st.nextToken());
            int gold = Integer.valueOf(st.nextToken());
            int silver = Integer.valueOf(st.nextToken());
            int bronze = Integer.valueOf(st.nextToken());
            nations[i] = new Nation(number, gold, silver, bronze);
        }
        br.close();

        Arrays.sort(nations, (n1, n2) -> {
            if(n1.gold > n2.gold)
                return -1;
            else if(n1.gold < n2.gold)
                return 1;

            if(n1.silver > n2.silver)
                return -1;
            else if(n1.silver < n2.silver)
                return 1;

            if(n1.bronze > n2.bronze)
                return -1;
            else if(n1.bronze < n2.bronze)
                return 1;

            return 0;
        });

        Nation prev = nations[0];
        int rank = 1;
        int buffer = 0;
        for (int i = 1; i < nations.length; i++) {
            if (prev.number == nationNumber) {
                break;
            }

            buffer++;
            if (!prev.isSameMedal(nations[i])) {
                rank += buffer;
                buffer = 0;
            }

            prev = nations[i];
        }

        System.out.println(rank);
    }

    static class Nation {
        int number,gold,silver,bronze;

        public Nation(int number, int gold, int silver, int bronze) {
            this.number = number;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        public boolean isSameMedal(Nation target) {
            if (this.gold == target.gold && this.silver == target.silver && this.bronze == target.bronze)
                return true;

            return false;
        }
    }
}
