import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        double result = 0;
        for (int i = 0; i < 20; i++) {
            String s = br.readLine();
            String[] split = s.substring(s.lastIndexOf(".") - 1).split(" ");

            if(split[1].equals("P"))
                continue;

            int gpa = Integer.valueOf(split[0].substring(0,1));
            sum += Integer.valueOf(split[0].substring(0,1));
            int subjectGPA = 0;
            switch (split[1]) {
                case "A+":
                    subjectGPA = 45;
                    break;
                case "A0":
                    subjectGPA = 40;
                    break;
                case "B+":
                    subjectGPA = 35;
                    break;
                case "B0":
                    subjectGPA = 30;
                    break;
                case "C+":
                    subjectGPA = 25;
                    break;
                case "C0":
                    subjectGPA = 20;
                    break;
                case "D+":
                    subjectGPA = 15;
                    break;
                case "D0":
                    subjectGPA = 10;
                    break;
                case "F":
                    subjectGPA = 0;
                    break;
            }

            result += ((double)gpa) * subjectGPA / 10;
        }

        System.out.println(result/sum);
        br.close();
    }
}
