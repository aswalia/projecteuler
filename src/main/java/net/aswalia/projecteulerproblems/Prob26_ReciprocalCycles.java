/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.aswalia.projecteulerproblems;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author asi
 */
public class Prob26_ReciprocalCycles {
    private static final int MAX_DENORMINATOR = 1000;
    
    private static boolean isPrime(long n) {
        for (long i=2; i<=(n/2); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        final BigDecimal ONE = new BigDecimal(1.0);
        int count = 0;
        for (int i=2; i<MAX_DENORMINATOR; i++) {
            if (isPrime(i)) {
                if ((MAX_DENORMINATOR % i != 0) && (MAX_DENORMINATOR % i != 1)) {
                // has a tail
                    count++;
                    BigDecimal factor = ONE.divide(new BigDecimal(i), 150, RoundingMode.HALF_UP);               
    //            BigDecimal factor = ONE.divide(new BigDecimal(i), MathContext.DECIMAL128);
                    System.out.println(i + " " + factor);
                }
            }
        }
        System.out.println(count);
    }
}
