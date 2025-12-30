import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(":");
        int sec = Integer.parseInt(line[0]) * 60 + Integer.parseInt(line[1]);
        br.close();

        int result = 0;
        boolean isStarted = false;
        while (sec != 0) {
            if (sec >= 600)
                sec -= 600;
            else if(sec >= 60)
                sec -= 60;
            else if (sec >= 30) {
                sec -= 30;
                isStarted = true;
            }
            else
                sec -= 10;
            result++;
        }
        
        if(!isStarted)
            result++;

        System.out.println(result);
    }
}
