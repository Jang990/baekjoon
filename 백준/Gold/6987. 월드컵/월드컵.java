import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static Country[] countriesResult = new Country[6];
    static boolean[] result = new boolean[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int caseIdx = 0; caseIdx < 4; caseIdx++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int countryIdx = 0; countryIdx < 6; countryIdx++) {
                int winIdx = countryIdx * 3;
                int drawIdx = countryIdx * 3 + 1;
                int loseIdx = countryIdx * 3 + 2;
                countriesResult[countryIdx] =
                        new Country(line[winIdx], line[drawIdx], line[loseIdx]);
            }
            boolean invalidResult = false;
            for (Country country : countriesResult) {
                if(country.win + country.draw + country.lose == 5)
                    continue;
                invalidResult = true;
            }
            if (invalidResult) {
                continue;
            }

            Country[] testCountry = new Country[6];
            for (int i = 0; i < 6; i++) {
                testCountry[i] = new Country(0, 0, 0);
            }
            play(0, testCountry, caseIdx);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (boolean b : result) {
            if(b)
                sb.append("1 ");
            else
                sb.append("0 ");
        }
        System.out.println(sb);
    }

    private static void play(int depth, Country[] countries, int caseIdx) {
        if (depth == 15) {
            result[caseIdx] = true;
            return;
        }

        int countryIdx1 = getCountryIdx1(depth);
        int countryIdx2 = getCountryIdx2(depth);

        // 승
        countries[countryIdx1].win++;
        countries[countryIdx2].lose++;
        if (countries[countryIdx1].win <= countriesResult[countryIdx1].win
                && countries[countryIdx2].lose <= countriesResult[countryIdx2].lose)
            play(depth + 1, countries, caseIdx);
        countries[countryIdx1].win--;
        countries[countryIdx2].lose--;

        // 무
        countries[countryIdx1].draw++;
        countries[countryIdx2].draw++;
        if(countries[countryIdx1].draw <= countriesResult[countryIdx1].draw
                && countries[countryIdx2].draw <= countriesResult[countryIdx2].draw)
            play(depth + 1, countries, caseIdx);
        countries[countryIdx1].draw--;
        countries[countryIdx2].draw--;

        // 패
        countries[countryIdx1].lose++;
        countries[countryIdx2].win++;
        if(countries[countryIdx1].lose <= countriesResult[countryIdx1].lose
                && countries[countryIdx2].win <= countriesResult[countryIdx2].win)
            play(depth + 1, countries, caseIdx);
        countries[countryIdx1].lose--;
        countries[countryIdx2].win--;
    }

    private static int getCountryIdx2(int depth) {
        if(depth < 5)
            return depth + 1;
        if(depth < 9)
            return depth - 3;
        if(depth < 12)
            return depth - 6;
        if(depth < 14)
            return depth - 8;
        return 5;
    }

    private static int getCountryIdx1(int depth) {
        if(depth < 5)
            return 0;
        if(depth < 9)
            return 1;
        if(depth < 12)
            return 2;
        if(depth < 14)
            return 3;
        return 4;
    }

    static class Country {
        int win, draw, lose;

        public Country(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }
}
