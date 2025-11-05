import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static List<Room> roomList = new ArrayList<>();
    private static int maxPlayer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int playerCnt = Integer.parseInt(line[0]);
        maxPlayer = Integer.parseInt(line[1]);

        for (int i = 0; i < playerCnt; i++) {
            line = br.readLine().split(" ");
            Player player = new Player(Integer.parseInt(line[0]), line[1]);
            boolean isEnter = enterRoom(player);
            if(!isEnter)
                roomList.add(new Room(maxPlayer, player));
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (Room room : roomList) {
            sb.append(room);
        }
        System.out.println(sb);
    }

    private static boolean enterRoom(Player player) {
        for (Room room : roomList) {
            if(room.enter(player))
                return true;
        }
        return false;
    }

    static class Room {
        int maxPlayer;
        int startLevel, endLevel;
        List<Player> playerList = new LinkedList<>();

        public Room(int maxPlayer, Player player) {
            this.maxPlayer = maxPlayer;
            this.startLevel = player.level - 10;
            this.endLevel = player.level + 10;
            playerList.add(player);
        }

        public boolean enter(Player player) {
            boolean canEnter = canPlay(player) && playerList.size() < maxPlayer;
            if(canEnter)
                playerList.add(player);
            return canEnter;
        }

        public boolean canPlay(Player player) {
            return startLevel <= player.level
                    && player.level <= endLevel;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(maxPlayer == playerList.size() ? "Started!\n" : "Waiting!\n");

            playerList.stream().sorted(Comparator.comparing(p1 -> p1.name))
                    .forEach(p -> sb.append(p));
            return sb.toString();
        }
    }

    static class Player{
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(level).append(" ").append(name).append("\n");
            return sb.toString();
        }
    }
}
