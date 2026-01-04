import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);
        int days = line[0];
        int money = line[1];

//        x + y = days;
//        5x + 1y = money / 1000;
//        y = days - x;
//        x = (money / 1000 - days) / 4;
        int selectACnt = (money / 1000 - days) / 4;

        PriorityQueue<Menu> pq = new PriorityQueue<>(
                Comparator.comparingInt(Menu::getDiff)
                        .reversed()
        );
        for (int i = 0; i < days; i++) {
            line = readLine(br);
            pq.offer(new Menu(line[0], line[1]));
        }
        br.close();

        int result = 0;
        while (!pq.isEmpty()) {
            Menu menu = pq.poll();
            if (menu.diff < 0 || selectACnt <= 0) {
                result += menu.b;
            } else {
                result += menu.a;
                selectACnt--;
            }
        }

        System.out.println(result);
    }

    static class Menu {
        int a, b, diff;

        public Menu(int a, int b) {
            this.a = a;
            this.b = b;
            diff = a - b;
        }

        public int getDiff() {
            return diff;
        }
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
