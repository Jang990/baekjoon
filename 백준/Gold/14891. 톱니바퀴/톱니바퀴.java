import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Wheel> wheels = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            int wheel[] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();

            wheels.put(i, new Wheel(wheel));
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheelNumber = Integer.parseInt(st.nextToken());
            int rotationDirection = Integer.parseInt(st.nextToken());

            // 0 동작 안함, 1 시계, -1 반시계
            int[] orders = new int[4 + 1];

            if (rotationDirection == 1) {
                rotationClockwiseWheels(wheelNumber,orders);
            } else if (rotationDirection == -1) {
                rotationCounterclockwiseWheels(wheelNumber,orders);
            }

            rotation(orders);
        }
        br.close();

        int result = 0;
        for (int i = 1; i <= 4; i++) {
            if (wheels.get(i).getTopSawtooth() != 1) {
                continue;
            }

            result += Math.pow(2,i-1);
        }

        System.out.println(result);
    }

    private static void rotation(int[] orders) {
        for (int i = 1; i <= 4; i++) {
            if (orders[i] == 0) {
                continue;
            }

            if (orders[i] == 1) {
                wheels.get(i).rotationClockwise();
            } else if (orders[i] == -1) {
                wheels.get(i).rotationCounterclockwise();
            }
        }
    }

    private static void rotationClockwiseWheels(int wheelNumber, int[] orders) {
        orders[wheelNumber] = 1;

        Wheel leftWheel = getLeftWheel(wheelNumber, orders);
        Wheel rightWheel = getRightWheel(wheelNumber, orders);
        Wheel nowWheel = wheels.get(wheelNumber);

        if (leftWheel != null && nowWheel.getLeftSawtooth() != leftWheel.getRightSawtooth()) {
            rotationCounterclockwiseWheels(wheelNumber-1, orders);
        }
        if (rightWheel != null && nowWheel.getRightSawtooth() != rightWheel.getLeftSawtooth()) {
            rotationCounterclockwiseWheels(wheelNumber+1, orders);
        }
    }

    private static void rotationCounterclockwiseWheels(int wheelNumber, int[] orders) {
        orders[wheelNumber] = -1;


        Wheel leftWheel = getLeftWheel(wheelNumber, orders);
        Wheel rightWheel = getRightWheel(wheelNumber, orders);
        Wheel nowWheel = wheels.get(wheelNumber);

        if (leftWheel != null && nowWheel.getLeftSawtooth() != leftWheel.getRightSawtooth()) {
            rotationClockwiseWheels(wheelNumber-1, orders);
        }
        if (rightWheel != null && nowWheel.getRightSawtooth() != rightWheel.getLeftSawtooth()) {
            rotationClockwiseWheels(wheelNumber+1, orders);
        }

    }
    
    

    private static Wheel getRightWheel(int wheelNumber, int[] orders) {
        int rightWheelIdx = wheelNumber+1;

        if (rightWheelIdx <= 4 && orders[rightWheelIdx] == 0) {
            return wheels.get(rightWheelIdx);
        }
        return null;
    }

    private static Wheel getLeftWheel(int wheelNumber, int[] orders) {
        int leftWheelIdx = wheelNumber-1;
        if (leftWheelIdx >= 1 && orders[leftWheelIdx] == 0) {
            return wheels.get(leftWheelIdx);
        }

        return null;
    }

    static class Wheel {
        private List<Integer> sawtooth = new LinkedList<>();

        int getLeftSawtooth() {
            return sawtooth.get(6);
        }

        int getRightSawtooth() {
            return sawtooth.get(2);
        }
        int getTopSawtooth() {
            return sawtooth.get(0);
        }

        public Wheel(int[] arr) {
            for (int i : arr) {
                sawtooth.add(i);
            }
        }

        void rotationClockwise() {
            int pop = sawtooth.remove(sawtooth.size()-1);
            sawtooth.add(0, pop);
        }

        void rotationCounterclockwise() {
            int pop = sawtooth.remove(0);
            sawtooth.add(pop);
        }
    }
}
