package za.co.jaredfishy.susan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridLayoutIndexGenerator {
    public static List<List<Integer>> spreadNicely(int itemCount) {

        if (itemCount == 2)
            return buildSpread(Arrays.asList(1, 1));

        int fullRows = itemCount / 2;
        List<Integer> spread = new ArrayList<>();
        for (int i = 0; i < fullRows; i++) {
            spread.add(2);
        }

        if (itemCount % 2 == 1)
            spread.add(1);
        return buildSpread(spread);

    }

    public static List<List<Integer>> spreadNicelyHC(int itemCount) {

        switch (itemCount) {
            case 1:
                return buildSpread(Arrays.asList(1));
            case 2:
                return buildSpread(Arrays.asList(2));
            case 3:
                return buildSpread(Arrays.asList(2, 1));
            case 4:
                return buildSpread(Arrays.asList(1, 2, 1));
            case 5:
                return buildSpread(Arrays.asList(2, 2, 1));
            case 6:
                return buildSpread(Arrays.asList(1, 2, 2, 1));
            case 7:
                return buildSpread(Arrays.asList(2, 2, 2, 1));
            case 8:
                return buildSpread(Arrays.asList(1, 2, 2, 2, 1));
            case 9:
                return buildSpread(Arrays.asList(2, 2, 2, 2, 1));
        }

        return new ArrayList<>();
    }

    private static List<List<Integer>> buildSpread(List<Integer> spread) {

        List<List<Integer>> rows = new ArrayList<>();
        int index = -1;
        for (Integer count : spread) {
            List<Integer> singleRow = new ArrayList<>();
            for (int i = 0; i < count; i++)
                singleRow.add(++index);
            rows.add(singleRow);
        }
        return rows;
    }

}
