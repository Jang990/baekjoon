import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pictureCnt = Integer.parseInt(br.readLine());
        int studentCnt = Integer.parseInt(br.readLine());
        PictureFrame pictureFrame = new PictureFrame(pictureCnt);
        Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).forEach((student) -> pictureFrame.push(new Student(student)));
        br.close();

        StringBuilder sb = new StringBuilder();
        pictureFrame.getNowStatus().sorted().forEach(i -> sb.append(i + " "));
        System.out.println(sb);
    }

    static class Student implements Comparable<Student>{
        int studentNumber;
        int postIdx;
        int recommended = 0;

        private static int PostIdxId;

        public Student(int studentNumber) {
            this.studentNumber = studentNumber;
            this.postIdx = PostIdxId++;
        }

        public void recommend() {
            recommended++;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Student)) {
                return false;
            }

            Student target = (Student) obj;
            if (target.studentNumber != this.studentNumber) {
                return false;
            }

            return true;
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        @Override
        public int compareTo(Student o) {
            if (recommended > o.recommended) {
                return -1;
            }
            else if(recommended < o.recommended) {
                return 1;
            }

            if (postIdx < o.postIdx) {
                return 1;
            }
            else if(postIdx > o.postIdx) {
                return -1;
            }

            return 0;
        }
    }

    static class PictureFrame {
        private List<Student> pictureFrame;
        private int frameMaxCount;

        public PictureFrame(int pictureCnt) {
            pictureFrame = new ArrayList<>();
            this.frameMaxCount = pictureCnt;
        }

        public void push(Student student) {
            int index = pictureFrame.indexOf(student);
            if (index != -1) {
                pictureFrame.get(index).recommend();
                return;
            }

            if (pictureFrame.size() >= frameMaxCount) {
                remove();
            }

            pictureFrame.add(student);
        }

        private void remove() {
            Collections.sort(pictureFrame);
            pictureFrame.remove(pictureFrame.size() - 1);
        }

        public Stream<Integer> getNowStatus() {
            return pictureFrame.stream()
                    .map(Student::getStudentNumber).mapToInt(Integer::valueOf).boxed();
        }
    }

}
