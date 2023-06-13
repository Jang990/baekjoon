import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static String map[][];
    static int min = Integer.MAX_VALUE;
    static List<CCTV> cctvList;
    public static void main(String[] args) throws IOException {
        /*
        map = new String[5][5];
        map[0] = new String[]{"0", "0", "0", "0", "0"};
        map[1] = new String[]{"0", "0", "0", "0", "0"};
        map[2] = new String[]{"0", "0", "4", "0", "0"};
        map[3] = new String[]{"0", "0", "0", "0", "0"};
        map[4] = new String[]{"0", "0", "0", "0", "0"};

        CCTV cctv = new CCTV(2, 2, "5");
        cctv.setDirection(4);
        cctv.view(map);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        */

        // CCTV 설정한다.
        // 백트레킹으로 CCTV 의 방향을 5개중 하나로 설정한다.
        // 본다
        // 0의 최소값을 구한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split(" ");
        }

        br.close();

        cctvList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                String type = map[i][j];
                if (type.equals("0") || type.equals("6")) {
                    continue;
                }
                cctvList.add(new CCTV(j, i, type));
            }
        }


        back(0);
        System.out.println(min);
    }

    private static void back(int depth) {
        if (depth == cctvList.size()) {
            String[][] cloneMap = new String[map.length][map[0].length];
            for (int i = 0; i < cloneMap.length; i++) {
                for (int j = 0; j < cloneMap[0].length; j++) {
                    cloneMap[i][j] = map[i][j];
                }
            }

            for (CCTV cctv : cctvList) {
                cctv.view(cloneMap);
            }

            int blindSpotCnt = calcBlindSpot(cloneMap);
            min = Math.min(min, blindSpotCnt);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            cctvList.get(depth).setDirection(i);
            back(depth + 1);
        }
    }

    private static int calcBlindSpot(String[][] cloneMap) {
        int cnt = 0;
        for (int i = 0; i < cloneMap.length; i++) {
            for (int j = 0; j < cloneMap[0].length; j++) {
                if (cloneMap[i][j].equals("0")) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    static class CCTV {
        private int cctvX, cctvY;
        private int direction;
        private CctvViewLogic viewLogic;

        public CCTV(int x, int y, String type) {
            cctvX = x;
            cctvY = y;
            direction = 1;

            if (type.equals("1")) {
                viewLogic = new OneCctvViewLogic();
            } else if (type.equals("2")) {
                viewLogic = new TwoCctvViewLogic();
            } else if (type.equals("3")) {
                viewLogic = new ThreeCctvViewLogic();
            } else if (type.equals("4")) {
                viewLogic = new FourCctvViewLogic();
            } else if (type.equals("5")) {
                viewLogic = new FiveCctvViewLogic();
            }
        }

        public void view(String[][] map) {
            int[] moveX = viewLogic.getNextX(direction);
            int[] moveY = viewLogic.getNextY(direction);

            int[] nowX = new int[moveX.length];
            int[] nowY = new int[moveY.length];
            Arrays.fill(nowX, cctvX);
            Arrays.fill(nowY, cctvY);

            for (int i = 0; i < nowX.length; i++) {
                while (true) {
                    nowX[i] += moveX[i];
                    nowY[i] += moveY[i];

                    if (outOfBoundary(nowX[i], nowY[i]) || map[nowY[i]][nowX[i]].equals("6")) {
                        break;
                    }

                    if (map[nowY[i]][nowX[i]].equals("0")) {
                        map[nowY[i]][nowX[i]] = "#";
                    }
                }
            }
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }
    }

    interface CctvViewLogic {
        int[] getNextX(int direction);
        int[] getNextY(int direction);
    }

    static class OneCctvViewLogic implements CctvViewLogic {

        @Override
        public int[] getNextX(int direction) {
            int[] movement = new int[1];
            if (direction == 1) {
                movement[0] = 1;
                return movement;
            } else if (direction == 2) {
                movement[0] = -1;
                return movement;
            }

            return movement;
        }

        @Override
        public int[] getNextY(int direction) {
            int[] movement = new int[1];
            if (direction == 3) {
                movement[0] = 1;
                return movement;
            } else if (direction == 4) {
                movement[0] = -1;
                return movement;
            }

            return movement;
        }
    }

    static class TwoCctvViewLogic implements CctvViewLogic {

        @Override
        public int[] getNextX(int direction) {
            int[] movement = new int[2];
            if (direction == 1) {
                movement[0] = 1;
                movement[1] = -1;
                return movement;
            } else if (direction == 2) {
                movement[0] = -1;
                movement[1] = 1;
                return movement;
            }

            return movement;
        }

        @Override
        public int[] getNextY(int direction) {
            int[] movement = new int[2];
            if (direction == 3) {
                movement[0] = 1;
                movement[1] = -1;
                return movement;
            } else if (direction == 4) {
                movement[0] = -1;
                movement[1] = 1;
                return movement;
            }

            return movement;
        }
    }

    static class ThreeCctvViewLogic implements CctvViewLogic {

        @Override
        public int[] getNextX(int direction) {
            int[] movement = new int[2];
            if (direction == 1) {
                movement[0] = 1;
                movement[1] = 0;
                return movement;
            } else if (direction == 2) {
                movement[0] = -1;
                movement[1] = 0;
                return movement;
            } else if (direction == 3) {
                movement[0] = 1;
                movement[1] = 0;
                return movement;
            } else if (direction == 4) {
                movement[0] = -1;
                movement[1] = 0;
                return movement;
            }

            return movement;
        }

        @Override
        public int[] getNextY(int direction) {
            int[] movement = new int[2];
            if (direction == 1) {
                movement[0] = 0;
                movement[1] = -1;
                return movement;
            } else if (direction == 2) {
                movement[0] = 0;
                movement[1] = 1;
                return movement;
            } else if (direction == 3) {
                movement[0] = 0;
                movement[1] = 1;
                return movement;
            } else if (direction == 4) {
                movement[0] = 0;
                movement[1] = -1;
                return movement;
            }

            return movement;
        }
    }

    static class FourCctvViewLogic implements CctvViewLogic {

        @Override
        public int[] getNextX(int direction) {
            int[] movement = new int[3];
            if (direction == 1) {
                movement[0] = 1;
                movement[1] = 0;
                movement[2] = -1;
                return movement;
            } else if (direction == 2) {
                movement[0] = -1;
                movement[1] = 0;
                movement[2] = 1;
                return movement;
            } else if (direction == 3) {
                movement[0] = 0;
                movement[1] = 1;
                movement[2] = 0;
                return movement;
            } else if (direction == 4) {
                movement[0] = 0;
                movement[1] = -1;
                movement[2] = 0;
                return movement;
            }

            return movement;
        }

        @Override
        public int[] getNextY(int direction) {
            int[] movement = new int[3];
            if (direction == 1) {
                movement[0] = 0;
                movement[1] = -1;
                movement[2] = 0;
                return movement;
            } else if (direction == 2) {
                movement[0] = 0;
                movement[1] = 1;
                movement[2] = 0;
                return movement;
            } else if (direction == 3) {
                movement[0] = 1;
                movement[1] = 0;
                movement[2] = -1;
                return movement;
            } else if (direction == 4) {
                movement[0] = -1;
                movement[1] = 0;
                movement[2] = 1;
                return movement;
            }

            return movement;
        }
    }

    static class FiveCctvViewLogic implements CctvViewLogic {

        @Override
        public int[] getNextX(int direction) {
            int[] movement = {0,0,1,-1};
            return movement;
        }

        @Override
        public int[] getNextY(int direction) {
            int[] movement = {1,-1,0,0};
            return movement;
        }
    }

    public static boolean outOfBoundary(int x, int y) {
        return 0 > x || x >= map[0].length || 0 > y || y >= map.length;
    }
}
