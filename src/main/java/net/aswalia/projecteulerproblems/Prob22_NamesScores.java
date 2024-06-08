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
import java.util.stream.Stream;

/**
 *
 * @author asi
 */
public class Prob22_NamesScores {
    
    private static int valueOfName(String s) {
        char[] cs = s.toCharArray();
        int sum = 0;
        for (char c : cs) {
            sum += ((int)c - (int)'A' + 1);
        }
        return sum;
    }

    private static void readFile(String fileName) {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            List<Integer> ret = Arrays.stream(
                    lines.map(l -> l.replaceAll("\"",""))
                         .map(l -> l.split(","))
                         .toList().get(0)).sorted()
                                          .map(Prob22_NamesScores::valueOfName).toList();
            long sum = 0;
            int index = 1;
            for (Integer s : ret) {
                sum += index*s;
                index++;
            }
            System.out.println(sum);
        } catch (IOException ex) {
            Logger.getLogger(Prob22_NamesScores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Prob22_NamesScores.readFile("C:\\Users\\asi\\Documents\\home\\Development\\ProjectEulerProblems\\src\\main\\resources\\0022_names.txt");
    }
}
