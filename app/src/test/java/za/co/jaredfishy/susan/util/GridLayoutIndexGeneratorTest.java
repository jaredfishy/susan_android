package za.co.jaredfishy.susan.util;

import org.junit.Test;

import java.util.List;

public class GridLayoutIndexGeneratorTest {


    @Test
    public void test() {
        for(int i=1;i<10;i++){
            testSet(i);
        }
    }

    private void testSet(int count){

        System.out.println("Testing: " + count);
        List<List<Integer>> rows = GridLayoutIndexGenerator.spreadNicely(count);

        for(List<Integer> singleRow: rows){
            StringBuilder row = new StringBuilder();
            for(Integer index: singleRow)
                row.append("[" + index + "]");
            System.out.println(row);
        }

        System.out.println("Done");
    }

}