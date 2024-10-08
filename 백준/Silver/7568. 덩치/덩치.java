import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Student[] arr = input();
        int[] ranks = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ranks[i] = rank(i, arr);
        }

        print(ranks);
    }

    private static int rank(int idx, Student[] arr) {
        int result = 1;
        Student current = arr[idx];
        for (int i = 0; i < arr.length; i++) {
            if(idx == i) continue;
            if(current.isSmallerThan(arr[i]))
                result++;
        }
        return result;
    }

    private static void print(int[] ranks) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ranks).forEach(rank -> sb.append(rank).append(" "));
        System.out.println(sb);
    }

    private static Student[] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Student[] result = new Student[N];
        for (int i = 0; i < N; i++) {
            String[] args = br.readLine().split(" ");
            result[i] = new Student(i, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        br.close();
        return result;
    }

    static class Student {
        int id, weight, height;

        public Student(int id, int weight, int height) {
            this.id = id;
            this.weight = weight;
            this.height = height;
        }

        public boolean isSmallerThan(Student s) {
            if(height < s.height && weight < s.weight)
                return true;
            return false;
        }
    }
}
