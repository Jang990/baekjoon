import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Student> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            list.add(new Student(
                    input[0],
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]),
                    Integer.parseInt(input[3]))
            );
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        list.stream().sorted().forEach(s -> sb.append(s).append("\n"));
        System.out.println(sb);
    }

    static class Student implements Comparable {
        private String name;
        private int kor;
        private int eng;
        private int math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof Student))
                throw new IllegalArgumentException();

            Student s = (Student) o;

            if(s.kor != kor)
                return Integer.compare(s.kor, kor);
            if(s.eng != eng)
                return Integer.compare(eng, s.eng);
            if(s.math != math)
                return Integer.compare(s.math, math);
            return name.compareTo(s.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
