import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Student min = convert(br.readLine());
        Student max = min;
        for (int i = 1; i < n; i++) {
            Student student = convert(br.readLine());
            if(min.birthday.isBefore(student.birthday))
                min = student;
            if(max.birthday.isAfter(student.birthday))
                max = student;
        }
        br.close();

        System.out.println(min.name + "\n" + max.name);
    }

    private static Student convert(String line) {
        String[] args = line.split(" ");
        return new Student(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }

    static class Student {
        String name;
        LocalDate birthday;

        public Student(String name, int day, int month, int year) {
            this.name = name;
            this.birthday = LocalDate.of(year, month, day);
        }
    }
}
