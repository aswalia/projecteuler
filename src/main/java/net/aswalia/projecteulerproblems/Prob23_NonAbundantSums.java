/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.aswalia.projecteulerproblems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author asi
 */
public class Prob23_NonAbundantSums {
    private static final int MAX_NON_ABUNDANT = 100;
    
    private static IntStream factors(int v) {
        return IntStream.rangeClosed(1, v/2).filter(i -> v % i == 0);        
    }
    
    private static boolean isAbundant(int v) {
        return (factors(v).reduce(0, (a, b) -> (a + b))) > v;
    }

    private static IntStream rangeAbundant(int a, int b) {
        return IntStream.rangeClosed(a, b).filter(Prob23_NonAbundantSums::isAbundant);
    }
    
    public static void main(String[] args) {
        List<Integer> res = Prob23_NonAbundantSums.rangeAbundant(1, Prob23_NonAbundantSums.MAX_NON_ABUNDANT)
                                            .boxed().toList();
        int[][] array = new int[res.size()][res.size()];
        for (int i=0; i<res.size(); i++) {
            for (int j=i; j<res.size(); j++) { 
                array[i][j] = res.get(j);
            }
        }
        
        for (int[] a : array) {
            for (int v : a) {
                System.out.print(v + ",");
            }
            System.out.println();
        }
        
/*        
        int[][] a = Stream.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}).toArray(int[][]::new);
        for (int[] arr : a) {
            System.out.println(Arrays.toString(arr));
        }
        int n = 4;
        System.out.println(Arrays.deepToString(IntStream.rangeClosed(0, n - 1)
              .boxed().map(x -> IntStream.rangeClosed(x * n + 1, (x + 1) * n)
              .boxed().toArray()).toArray()));
*/    }
}
