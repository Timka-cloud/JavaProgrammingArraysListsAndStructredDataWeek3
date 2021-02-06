package N1.ClassWork;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer {
    /**
     * this class iterate file and String line convert to Entry
     */
    private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<>();
    }

    public void readFile(String filename){
        FileResource f = new FileResource(filename);
        for(String line : f.lines()){
            LogEntry r = WebLogParser.parseEntry(line);
            records.add(r);
        }
    }

    public int countUniqueIPs(){  // this method counts unique IP address
        //uniqueIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<>();
        // for each element le in records
        for(LogEntry le : records){
            // ipAddr is le's ipAddress
            String ipAddr = le.getIpAddress();
            // if ipAddr is not in uniqueIps
            if(!uniqueIPs.contains(ipAddr)){
                // add ipAddr to uniqueIps
                uniqueIPs.add(ipAddr);
            }


        } // return the size of uniqueIPs
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        /**
         * этот метод распечатывает статус запроса которые выше num
         */

        for(LogEntry le : records){
            int status = le.getStatusCode();
            if(status >= num){
                System.out.println(le);
            }
        }
    }

    public int uniqueIPVisitsOnDay(String someday){
        /**
         * этот метод распечатывает данные которые равны параметру someday и с уникальными IP
         */
        ArrayList<String> uniqueIPs = new ArrayList<>(); // создаем список для хранение уникальных IP
        ArrayList<String> uniqueDate = new ArrayList<>(); // создаем список для хранение даты и время
        Date d = new Date();
        for(LogEntry le : records){ // перебираем файл
            String ipAddr = le.getIpAddress(); // получаем IP и записываем в строку
            d = le.getAccessTime(); // получаем дату и время
            String strDate = d.toString(); // записываем в строку дату и время
            if(!uniqueIPs.contains(ipAddr) && strDate.contains(someday)){ // проверяем на содержание в коллекциях
                uniqueIPs.add(ipAddr); // добавляем в список
                System.out.println(uniqueIPs); // for testing
                uniqueDate.add(strDate); // // добавляем в список
                System.out.println(uniqueDate); // for testing
            }


        }
        return uniqueDate.size(); // возвращаем размер
    }

    public int countUniqueIPsInRange(int low, int high){
        /**
         * этот метод подсчитывает сколько раз использовался статус запроса с уникальным IP
         * (статус запроса это предпоследние цифры (например все мы знаем 404 это сайт не работает))
         *
         */
        ArrayList<String> uniqueIPs = new ArrayList<>(); // создаем список для хранение уникальных IP
        ArrayList<Integer> status = new ArrayList<>();
        for(LogEntry le : records){
            String unique = le.getIpAddress();
            int statusCode = le.getStatusCode();
            if(!uniqueIPs.contains(unique) && statusCode >= low && statusCode <= high){
                uniqueIPs.add(unique);
                System.out.println(uniqueIPs);
                status.add(statusCode);
                System.out.println(status);

            }


        }
        return status.size();
    }

    public HashMap<String,Integer> countVisitsPerIP(){
        /**
         * этот метод считывает сколько раз уникальных ip заходила на сайт
         */
        //Make an empty HashMap<String,Integer> counts
        HashMap<String,Integer> counts = new HashMap<>();
        //For each le in records
        for(LogEntry le : records){
            //ip is le's ipAddress
            String ip = le.getIpAddress();
            //check if ip is in counts
            if(!counts.containsKey(ip)){
                //if not put ip in with a value of 1
                counts.put(ip,1);
//                System.out.println(counts); // for testing
            }
            // if so update the value to be more
            else {
                int a = counts.get(ip); // я получаю значение у ключа
                counts.put(ip,a + 1);
//                System.out.println(counts);  // for testing
            }
        }
        // count is the answer
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> myMap){
        /**
         * этот метод ищет наибольшее посещения сайта с одного ip
         */
        int temp = 0;
        for(Integer i : myMap.values()){ // цикл возвращает значение
            if(i > temp){
                temp = i;
            }
        }
        return temp;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> myMap){
        /**
         * этот метод возвращает список ip которые больше всех заходили на сайт
         */
        ArrayList<String> listOfIp = new ArrayList<>();
        int a = mostNumberVisitsByIP(myMap);
        for(String s : myMap.keySet()){
            int temp = myMap.get(s);
            if(temp == a){
                listOfIp.add(s);
            }
        }
        return listOfIp;
    }


    public HashMap<String,ArrayList<String>> iPsForDays(){
        /**
         * этот метод показывает по дате какие ip обращались
         */
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String str = d.toString();
            String day = str.substring(4,10); // что бы получить значение как Sep 21 и тому подобное
            String ip = le.getIpAddress();
            ArrayList<String> list = new ArrayList<String>();
            if (!map.containsKey(day)) {

                list.add(ip);
               // System.out.println(list); // for testing
                map.put(day, list);
//                System.out.println(map);  // for testing

            }
            else {

                list = map.get(day);
//                System.out.println(s);   // for testing
                if (!list.contains(ip) || list.contains(ip)){
                    list.add(ip);
//                    System.out.println(s);   // for testing
//                    System.out.println(map);   // for testing
                }

            }
        }
        return map;
    }


    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> myMap){
        /**
         * этот метод показывает день когда больше всего зашло пользователей (не важно на уникальность ip)
         */
        int indexMap = 0;
//        System.out.println(myMap);

        for (ArrayList s: myMap.values()){
            if(indexMap < s.size()){
                indexMap = s.size();
                System.out.println(s);
            }

        }
        for(String s: myMap.keySet()){
            ArrayList ips = myMap.get(s);
            System.out.println(ips);
            if(indexMap == ips.size()){
                System.out.println(ips);
                return s;
            }
        }
        return null;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> myMap,String date){
        /**
         * этот метод показывает по дате какие ip заходили на сайт
         */
        ArrayList<String> ips = new ArrayList<>();
        ArrayList<String> uniqueIP = new ArrayList<>();

        for(String s : myMap.keySet()){
            if(s.equals(date)){
                ips = myMap.get(s);
                for(String s1 : ips){
                    if(!uniqueIP.contains(s1)){
                        uniqueIP.add(s1);
                    }
                }
            }
        }
        return uniqueIP;
    }



    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }
}

