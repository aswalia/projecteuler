/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.aswalia.projecteulerproblems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 * @author asi
 */
public class Prob23_NonAbundantSums {
    private static final int MAX_NON_ABUNDANT = 28123;
    private static final Set<Integer> nas = new HashSet<>();
    
    private static IntStream factors(int v) {
        return IntStream.rangeClosed(1, v/2).filter(i -> v % i == 0);        
    }
    
    private static boolean isAbundant(int v) {
        return (factors(v).reduce(0, (a, b) -> (a + b))) > v;
    }

    private static IntStream rangeAbundant(int a, int b) {
        return IntStream.rangeClosed(a, b).filter(Prob23_NonAbundantSums::isAbundant);
    }
    
    private static boolean isNotMember(int i) {
        return !nas.contains(i);
    }
    
    public static void main(String[] args) {
        List<Integer> res = Prob23_NonAbundantSums.rangeAbundant(1, Prob23_NonAbundantSums.MAX_NON_ABUNDANT)
                                            .boxed().toList();
        for (int i=0; i < res.size(); i++) {
            for (int j=i; j < res.size(); j++) {
                nas.add(res.get(i) + res.get(j));
            }
        }
        
        int sum = IntStream.rangeClosed(1, MAX_NON_ABUNDANT)
                .filter(Prob23_NonAbundantSums::isNotMember).reduce(0, (subtotal, element) -> subtotal + element);
        
        System.out.println("sum: " + sum);
        
    }
}