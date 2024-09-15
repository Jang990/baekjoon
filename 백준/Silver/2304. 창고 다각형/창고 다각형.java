import java.io.*;
import java.util.*;

public class Main {
    static Pillar[] pillars;
    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(pillars, Comparator.comparing(Pillar::getLocation));
        setPillarsIndex();

        Pillar highestPillar = findHighestPillar(new IndexRange(0, pillars.length - 1), true);
        int sum = highestPillar.height;
        sum += leftArea(new IndexRange(0, highestPillar.idx - 1));
        sum += rightArea(new IndexRange(highestPillar.idx + 1, pillars.length - 1));

        System.out.println(sum);
    }

    private static int rightArea(IndexRange range) {
        if(range.start > range.end)
            return 0;
        Pillar basePillar = findHighestPillar(range, false);
        int currentArea = calculateArea(new IndexRange(range.start - 1, basePillar.idx), basePillar.height);
        if(range.start == range.end)
            return currentArea;
        else
            return currentArea + rightArea(createRightSubRange(range, basePillar));
    }

    private static int leftArea(IndexRange range) {
        if(range.start > range.end)
            return 0;
        Pillar basePillar = findHighestPillar(range, true);
        int currentArea = calculateArea(new IndexRange(basePillar.idx, range.end + 1), basePillar.height);
        if(range.start == range.end)
            return currentArea;
        else
            return currentArea + leftArea(createLeftSubRange(range, basePillar));
    }

    private static void setPillarsIndex() {
        for (int i = 0; i < pillars.length; i++) {
            pillars[i].setIndex(i);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pillarCnt = parse(br.readLine());
        pillars = new Pillar[pillarCnt];
        for (int i = 0; i < pillarCnt; i++) {
            String[] line = br.readLine().split(" ");
            pillars[i] = new Pillar(parse(line[0]), parse(line[1]));
        }
        br.close();
    }

    private static IndexRange createLeftSubRange(IndexRange range, Pillar basePillar) {
        return new IndexRange(range.start, basePillar.idx - 1);
    }

    private static IndexRange createRightSubRange(IndexRange range, Pillar basePillar) {
        return new IndexRange(basePillar.idx + 1, range.end);
    }

    private static Pillar findHighestPillar(IndexRange range, boolean leftResult) {
        Pillar result = pillars[range.start];
        for (int i = range.start; i <= range.end; i++) {
            if(pillars[i].height < result.height)
                continue;
            if(leftResult && pillars[i].height == result.height)
                continue;
            result = pillars[i];
        }
        return result;
    }

    private static int parse(String numStr) {
        return Integer.parseInt(numStr);
    }

    private static int calculateArea(IndexRange range, int height) {
        return (pillars[range.end].location - pillars[range.start].location) * height;
    }

    static class Pillar {
        int location, height, idx;

        public Pillar(int location, int height) {
            this.location = location;
            this.height = height;
        }

        public int getLocation() {
            return location;
        }

        public int idx() {
            return idx;
        }

        public void setIndex(int idx) {
            this.idx = idx;
        }
    }

    /** start <= range <= end */
    static class IndexRange {
        int start, end;

        public IndexRange(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
