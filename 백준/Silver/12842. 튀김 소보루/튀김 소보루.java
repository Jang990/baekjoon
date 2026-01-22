import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int breadCnt = Integer.parseInt(line[0]) - Integer.parseInt(line[1]);

        PriorityQueue<Person> pq = new PriorityQueue<>(
                Comparator.comparingInt(Person::getNextTime)
                        .thenComparing(Person::getId)
        );
        int personCnt = Integer.parseInt(br.readLine());
        for (int id = 1; id <= personCnt; id++) {
            int range = Integer.parseInt(br.readLine());
            pq.offer(new Person(id, range));
        }

        br.close();

        while (breadCnt > 1) {
            Person p = pq.poll();
            pq.offer(new Person(p));
            breadCnt--;
        }

        System.out.println(pq.poll().id);
    }

    static class Person {
        int id;
        int nextTime;
        int range;

        public Person(int id, int range) {
            this.id = id;
            this.nextTime = 1;
            this.range = range;
        }

        public Person(Person p) {
            this.id = p.id;
            this.nextTime = p.nextTime + p.range;
            this.range = p.range;
        }

        public int getId() {
            return id;
        }

        public int getNextTime() {
            return nextTime;
        }
    }
}
