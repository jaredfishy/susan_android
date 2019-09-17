package za.co.jaredfishy.susan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridLayoutIndexGenerator {


    public static List<List<Integer>> spreadNicely(int itemCount) {

        switch (itemCount) {
            case 1:
                return buildSpread(Arrays.asList(1));
            case 2:
                return buildSpread(Arrays.asList(2));
            case 3:
                return buildSpread(Arrays.asList(2, 1));
            case 4:
                return buildSpread(Arrays.asList(2, 2));
            case 5:
                return buildSpread(Arrays.asList(3, 2));
            case 6:
                return buildSpread(Arrays.asList(3, 3));
            case 7:
                return buildSpread(Arrays.asList(2, 3, 2));
        }

        return new ArrayList<>();
    }

    private static int getBoxSize(int value) {
        return (int) Math.floor(Math.sqrt(value));
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
