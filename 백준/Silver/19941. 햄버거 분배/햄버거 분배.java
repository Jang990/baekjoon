import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[] edible;
    private static boolean[] isPerson;
    private static int range;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        edible = new boolean[Integer.parseInt(line[0])];
        isPerson = new boolean[Integer.parseInt(line[0])];
        range = Integer.parseInt(line[1]);

        line = br.readLine().split("");
        for (int i = 0; i < line.length; i++) {
            if(line[i].equals("H"))
                edible[i] = true;
            else
                isPerson[i] = true;
        }
        br.close();

        int result = 0;
        for (int i = 0; i < line.length; i++) {
            if(!isPerson[i])
                continue;
            if (eat(i)) {
                result++;
            }
        }

        System.out.println(result);

    }

    private static boolean eat(int pIdx) {
        int leftStart = pIdx - range;
        for (int i = 0; i < range; i++) {
            int current = leftStart + i;
            if(current < 0)
                continue;
            if(!edible[current] || isPerson[current])
                continue;
            edible[current] = false;
            return true;
        }

        int rightStart = pIdx + 1;
        for (int i = 0; i < range; i++) {
            int current = rightStart + i;
            if(current >= edible.length)
                break;
            if(!edible[current] || isPerson[current])
                continue;
            edible[current] = false;
            return true;
        }


        return false;
    }
}
