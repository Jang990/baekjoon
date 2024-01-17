import java.util.*;
class Solution {
    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, List<Music>> listMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i],map.getOrDefault(genres[i], 0) + plays[i]);
            List<Music> list = listMap.getOrDefault(genres[i], new LinkedList<>());
            list.add(new Music(i, plays[i]));
            listMap.put(genres[i], list);
        }

        List<Integer> result = new LinkedList<>();
        int size = map.size();
        for (int i = 0; i < size; i++) {
            String key = getMaxGenre(map);
            map.remove(key);
            List<Music> musics = listMap.get(key);
            musics.sort(getMusicComparator());
            result.add(musics.get(0).idx);
            if(musics.size() >= 2)
                result.add(musics.get(1).idx);
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private static Comparator<Music> getMusicComparator() {
        return (m1, m2) -> {
            if (m1.play == m2.play) {
                if (m1.idx > m2.idx) {
                    return 1;
                }
                return -1;
            }

            if (m1.play > m2.play) {
                return -1;
            } else {
                return 1;
            }
        };
    }

    private static String getMaxGenre(Map<String, Integer> map) {
        String result = "";
        int max = Integer.MIN_VALUE;
        for (String s : map.keySet()) {
            int val = map.get(s);
            if (val <= max)
                continue;

            result = s;
            max = val;
        }

        return result;
    }

    static class Music {
        int idx;
        int play;

        public Music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }
}