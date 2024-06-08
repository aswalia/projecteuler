/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.aswalia.projecteulerproblems;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author asi
 */
public class Prob19_CountingSundays {
    enum Days {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    };
    
    record DayJan1st(Days day, boolean leapyear) {
    }
    
// 1900-01-01 is a Monday
// Jan, Mar, May, Jul, Aug, Oct, Dec have 31 days
// Apr, Jun, Sep, Nov have 30 days
// Feb has 28 days on non-leap year and 29 on leap year
// A year is leap year if divisble by 4 and not divisible by 100 or 
// divisible by 400
    
// so 1900 is NOT leap year, while 2000 is leap year!
    
// 1st move to 1901-01-01
// 2nd generate all the 1st day in the year
// 3rd filter all Sundays 
// 4th count the set
// 5th repeat 2-4
    private static final Days JAN1ST1900 = Days.MONDAY;
    
    private static int noOfSundaysPerYear(DayJan1st rec) {
        int ret = 0;
        if (!rec.leapyear()) {
            switch (rec.day()) {
                case WEDNESDAY, FRIDAY, SATURDAY -> ret = 1;
                case MONDAY, TUESDAY, SUNDAY -> ret = 2;
                case THURSDAY -> ret = 3;                
            }
        } else {
            switch (rec.day()) {
                case TUESDAY, FRIDAY, SATURDAY -> ret = 1;
                case MONDAY, WEDNESDAY, THURSDAY -> ret = 2;
                case SUNDAY -> ret = 3;                
            }            
        }
        return ret;
    }
    
    private static boolean isLeapyear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
    
    private static int noOfLeapyearsSince1900(int year) {
        if (year < 1900) {
            return 0;
        } else {
            return (int)IntStream.rangeClosed(1901, year)
                                 .filter(Prob19_CountingSundays::isLeapyear)
                                 .count();
        }
    }
    
    private static DayJan1st generateRecord(int year) {
        if (year < 1900) {
            return null;
        } else {
            int y = year - 1900;
            return new DayJan1st(Days.values()[(JAN1ST1900.ordinal() + y + noOfLeapyearsSince1900(year-1)) % 7], isLeapyear(year));
        }
    } 
    
    private Stream<Integer> getYears(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed();
    }
     
    public static void main(String[] args) {
        Prob19_CountingSundays test = new Prob19_CountingSundays();
        
        int from = Integer.parseInt(args[0]);
        int to = Integer.parseInt(args[1]);
             
        System.out.println("No of \"1st of a month is a Sunday\" in the period " + from + " to " + to + " is: " + 
                           test.getYears(from, to)
                               .map(Prob19_CountingSundays::generateRecord)
                               .mapToInt(Prob19_CountingSundays::noOfSundaysPerYear)
                               .sum());
    }
}
