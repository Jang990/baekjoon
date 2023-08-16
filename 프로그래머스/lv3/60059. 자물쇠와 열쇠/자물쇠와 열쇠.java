class Solution {
    static int M;
    public static boolean solution(int[][] key, int[][] lock) {
        int[][][] ketSet = new int[4][key.length][key[0].length];
        M = key.length;
        ketSet[0] = key;
        int[][] searchMap = new int[lock.length + M*2 - 2][lock[0].length + M*2 - 2];

        for (int i = 1; i < 4; i++) {
            ketSet[i] = rotation(ketSet[i-1]);
        }

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[0].length; j++) {
                searchMap[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        for (int i = 0; i < searchMap.length - M + 1; i++) {
            for (int j = 0; j < searchMap[0].length - M + 1; j++) {
                for (int k = 0; k < 4; k++) {
                    if (isOk(searchMap, i, j, ketSet[k])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isOk(int[][] base, int startKeyY, int startKeyX, int[][] key) {
        int endLock = base.length - M + 1;
        int startLock = M - 1;

        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base[0].length; j++) {
                if (isRangeOfKey(startKeyY, startKeyX, i, j) && isRangeOfLock(startLock, endLock, i, j)) {
                    int nowKeyX = j - startKeyX;
                    int nowKeyY = i - startKeyY;
                    if (key[nowKeyY][nowKeyX] == base[i][j]) {
                        return false;
                    }
                }
                else if (isRangeOfLock(startLock, endLock, i, j) && base[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRangeOfLock(int startLock, int endLock, int nowY, int nowX) {
        return startLock <= nowX && nowX < endLock && startLock <= nowY && nowY < endLock;
    }

    private static boolean isRangeOfKey(int startKeyY, int startKeyX, int nowY, int nowX) {
        return startKeyX <= nowX && nowX < startKeyX + M && startKeyY <= nowY  && nowY < startKeyY + M;
    }

    private static int[][] rotation(int[][] prevKey) {
        int[][] keySet = new int[prevKey.length][prevKey[0].length];
        for (int i = 0; i < prevKey[0].length; i++) {
            for (int j = prevKey.length-1; j >= 0; j--) {
                keySet[i][prevKey.length-1-j] = prevKey[j][i];
            }
        }

        return keySet;
    }
}