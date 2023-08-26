import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Card> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String color = st.nextToken();
            int number = Integer.valueOf(st.nextToken());
            list.add(new Card(color, number));
        }
        br.close();

        System.out.println(getScore(list));
    }

    private static int getScore(List<Card> list) {
        int top = list.stream().map(Card::getNumber).max(Comparator.naturalOrder()).get();
        boolean isFlush = isFlush(list);
        boolean isStraight = isStraight(list);

        if (isStraight && isFlush) {
            // 스트레이트 플러시 - 가장 높은 수 + 900
            return top + 900;
        }


        List<Integer> numberList = list.stream().map(Card::getNumber).collect(Collectors.toList());
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : numberList) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map<Integer, List<Integer>> valueMap = new HashMap<>();
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            if (valueMap.containsKey(value)) {
                valueMap.get(value).add(key);
            }
            else {
                List<Integer> keyList = new ArrayList<>();
                keyList.add(key);
                valueMap.put(value, keyList);
            }
        }
        
        if (isFourCard(map)) {
            // ---포카드 - 포카드 숫자 + 800
            return valueMap.get(4).get(0) + 800;
        }

        if (isFullHouse(map)) {
            // ---풀하우스 - 세장수*10 + 두장수 + 700
            int tripleNumber = valueMap.get(3).get(0);
            int pairNumber = valueMap.get(2).get(0);
            return tripleNumber * 10 + pairNumber + 700;
        }

        if (isFlush) {
            // 플러시 - 가장 높은 수 + 600
            return top + 600;
        }

        if (isStraight) {
            // 스트레이트 - 가장 높은 수 + 500
            return top + 500;
        }


        if (isTriple(valueMap)) {
            // ---트리플 - 세장수 + 400
            return valueMap.get(3).get(0) + 400;
        }

        if (isPair(valueMap)) {
            List<Integer> pairNumber = valueMap.get(2);
            if (pairNumber.size() == 2) {
                // ---투페어 - 높은두장수 * 10 + 낮은두장수 + 300
                int n1 = pairNumber.get(0) * 10 + pairNumber.get(1);
                int n2 = pairNumber.get(1) * 10 + pairNumber.get(0);
                return Math.max(n1, n2) + 300;
            }

            // ---원페어 - 두장수 + 200
            return pairNumber.get(0) + 200;
        }



        // ---탑 - 가장 높은 수 + 100
        return top + 100;
    }

    private static boolean isPair(Map<Integer, List<Integer>> valueMap) {
        return valueMap.containsKey(2);
    }

    private static boolean isTriple(Map<Integer, List<Integer>> valueMap) {
        return valueMap.containsKey(3);
    }

    private static boolean isFullHouse(Map<Integer, Integer> map) {
        return map.keySet().size() == 2 && map.values().contains(3) && map.values().contains(2);
    }

    private static boolean isFourCard(Map<Integer, Integer> map) {
        return map.keySet().size() == 2 && map.values().contains(4);
    }

    private static boolean isStraight(List<Card> list) {
        List<Integer> sortedList = list.stream().map(Card::getNumber).sorted().collect(Collectors.toList());
        int before = sortedList.get(0);
        for (int i = 1; i < sortedList.size(); i++) {
            int now = sortedList.get(i);
            if (before + 1 != now) {
                return false;
            }
            before = now;
        }
        return true;
    }

    private static boolean isFlush(List<Card> list) {
        return list.stream().map(Card::getColor).distinct().collect(Collectors.toList()).size() < 2;
    }

    static class Card {
        String color;
        int number;

        public Card(String color, int number) {
            this.color = color;
            this.number = number;
        }

        public String getColor() {
            return color;
        }

        public int getNumber() {
            return number;
        }
    }
}
