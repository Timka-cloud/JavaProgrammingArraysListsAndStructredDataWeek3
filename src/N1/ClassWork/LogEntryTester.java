package N1.ClassWork;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.duke.*;

public class LogEntryTester {
    public static void main(String[] args) {
        testIPsForDays();
        testIPsWithMostVisitsOnDay();
        


    }
    public static void testLogAnalyzer(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/test_log_files");
        analyzer.printAll();
    }

    public static void testUniqueIPs(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        int uniqueIP = analyzer.countUniqueIPs();
        System.out.println("There are " + uniqueIP + " unique IPs");
    }

    public static void testPrintAllHigherThanNum(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_weblog");
        analyzer.printAllHigherThanNum(400);
    }

    public static void testUniqueIPVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        int a = analyzer.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(a);
    }

    public static void testCountUniqueIPsInRange(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        int a = analyzer.countUniqueIPsInRange(400,499);
        System.out.println(a);
    }

    public static void testCountVisitsPerIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String,Integer> temp = analyzer.countVisitsPerIP();
        System.out.println(temp);
    }

    public static void testMostNumberVisitsByIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String,Integer> map = analyzer.countVisitsPerIP();
        int a = analyzer.mostNumberVisitsByIP(map);
        System.out.println("The most number visit by IP: " + a);
    }

    public static void testIPsMostVisits(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String,Integer> map = analyzer.countVisitsPerIP();
        System.out.println(map);
        ArrayList<String> list = analyzer.iPsMostVisits(map);
        System.out.println(list);
    }

    public static void testIPsForDays(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String,ArrayList<String>> list = analyzer.iPsForDays();
        System.out.println(list);
    }



    public static void testDayWithMostIPVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String, ArrayList<String>> list = analyzer.iPsForDays();
        String s = analyzer.dayWithMostIPVisits(list);
        System.out.println(s);
    }

    public static void testIPsWithMostVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("Files/exam_2");
        HashMap<String, ArrayList<String>> list = analyzer.iPsForDays();
        ArrayList<String> arraylist = analyzer.iPsWithMostVisitsOnDay(list,"Sep 29");
        System.out.println(arraylist);
    }





}
