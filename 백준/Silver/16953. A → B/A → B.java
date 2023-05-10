import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] AB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        if(AB[0] == AB[1]) {
            System.out.println(-1);
            return;
        }

        int count = 1;
        while(AB[0] < AB[1]) {
            if(AB[1] % 10 == 1) {
                AB[1] /= 10;
            }
            else if(AB[1] % 2 == 0) {
                AB[1] /= 2;
            }
            else {
                break;
            }


            count++;
        }

        if(AB[0] == AB[1]) {
            System.out.println(count);
        }
        else {
            System.out.println(-1);
        }

    }
}
