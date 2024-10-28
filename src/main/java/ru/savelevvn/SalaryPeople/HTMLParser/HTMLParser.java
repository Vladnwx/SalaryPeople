package ru.savelevvn.SalaryPeople.HTMLParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import ru.savelevvn.SalaryPeople.entity.People;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HTMLParser {

    static String patchName = "test.html";
    static String writePatchName = "out.txt";
    static String htmlText = "";
    static int k=0; // Количество попыток подключения

    static File file=null;
    static Document doc = null;
    static Elements elments = null;

    static Calendar currentCalendar = new GregorianCalendar(); // Текущий календарь (Год-месяц)
    static SimpleDateFormat currentCalendarFormater = new SimpleDateFormat(); // Форматтер, как он закодирован в html

    static Calendar reportCalendar = new GregorianCalendar(); // календарь отчета (Год-месяц)
    static SimpleDateFormat reportCalendarFormater = new SimpleDateFormat(); // Форматтер, как он закодирован в html

    static {
        currentCalendar.setTime(new Date());
        reportCalendarFormater.applyPattern("yyyy-MM");
       //System.out.println(currentCalendarFormater.format("Текущий календарь : " + currentCalendar.getTime()));
    }

    public static void main(String[] args) throws IOException {
        List<People> peopleList = new ArrayList<>();
        HTMLParser htmlParser = new HTMLParser();
        peopleList = htmlParser.getPeopleList("test.html");
        System.out.println(peopleList);
    }

    public static Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public static Calendar getReportCalendar() {
        return reportCalendar;
    }


    void initDoc (String filename){
        try {
            //Получаем HTML код страницы
            file = new File(filename);
            doc = Jsoup.parse(file, "UTF-8", "test.itis.pro");
            //System.out.println(doc);
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному файлу" + filename);
        }
    }

    public List<String> getTHEAD (String filename) {
        initDoc(filename);
        List<String> list = new ArrayList<>();
        try {
            //Извлекаем список заголовков
            elments = doc.select("#report > thead > tr > th");
            for (int i=0; i < elments.size(); i++){
                String temp = elments.get(i).text();
                list.add(temp);
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        return list != null ? list : null;
    }

    private HashMap<Integer, String> getPersonHashMap (String filename) throws IOException {
        initDoc(filename);
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            //Извлекаем список людей
            elments = doc.select("#report > tbody > tr > td:nth-child(1)");
            List<Node> nodes = null;
            for (int i=1; i < elments.size()-1; i++){
                Integer idTemp = Integer.valueOf(elments.get(i).attribute("data-user_id").getValue());
                String nameTemp = elments.get(i).text();
                hashMap.put(idTemp, nameTemp);
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        return hashMap != null ? hashMap : null;
    }

    public  List<People> getPeopleList (String filename) throws IOException {
        initDoc(filename);
        List<People> list = new ArrayList<>();
        try {
            //Извлекаем список людей
            elments = doc.select("#report > tbody > tr > td:nth-child(1)");
            List<Node> nodes = null;
            for (int i=1; i < elments.size()-1; i++){
                Long idTemp = Long.valueOf(elments.get(i).attribute("data-user_id").getValue());
                String nameTemp = elments.get(i).text();
                String[] name = nameTemp.split(" ");
                list.add(new People(idTemp, name[0], name[1]));
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        return list;
    }

    public void getPersonHashMaps (String filename) {
        initDoc(filename);
        HashMap<Integer, String> hashMap = new HashMap<>();
        try {
            //Извлекаем список людей
            elments = doc.select("#report > tbody > tr > td:nth-child(1)");
            List<Node> nodes = null;
            for (int i=1; i < elments.size()-1; i++){
                Integer idTemp = Integer.valueOf(elments.get(i).attribute("data-user_id").getValue());
                String nameTemp = elments.get(i).text();
                hashMap.put(idTemp, nameTemp);
            }
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        finally {
            System.out.println(hashMap);
        }

    }

    public  Calendar getReportDate (String filename){
        initDoc(filename);
        try {
            //Получаем HTML код страницы
            file = new File(filename);
            doc = Jsoup.parse(file, "UTF-8", "test.itis.pro");
            elments = doc.select("#year_month ");
            reportCalendar.setTime(reportCalendarFormater.parse(elments.get(0).attribute("value").getValue()));
            System.out.println("Календарь отчета: " + reportCalendarFormater.format(reportCalendar));
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        return reportCalendar;
    }



    public  List<String> getReport (String filename) throws IOException {
        initDoc(filename);
        Elements peopleListElements = null;
        try {
            //Получаем HTML код страницы
            file = new File(filename);
            doc = Jsoup.parse(file, "UTF-8", "test.itis.pro");
            //Извлекаем список людей
            peopleListElements = doc.select("#report > tbody > tr");

            List<Node> nodes = null;

            for (int i=1; i < peopleListElements.size(); i++){
                nodes = peopleListElements.get(i).childNodes();
                System.out.println(nodes);
            }

        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");
        }
        return peopleListElements != null ? peopleListElements.eachText() : null;
    }


    public  void printConsoleFromFile (String filename){
        initDoc(filename);
        try{
            //Извлекаем список людей
              Elements tableHeadElements = doc.select("#report > thead");
              Elements peopleListElements = doc.select("#report > tbody > tr > td:nth-child(1)");
              Elements peopleAndTimeListElements = doc.select("#report > tbody > tr");
              List<String> peopleList = peopleListElements.eachText();
              List<String> masterList = peopleList.stream().filter(s -> s.contains("ИТР")).collect(Collectors.toList());
            System.out.println(masterList);


//            Elements userParent = doc.select("body > table > tbody > tr > td > div > table > " +
//                    "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
//                    "tr > td:nth-child(1)");
            List<Node> nodes = tableHeadElements.get(0).childNodes();

           // System.out.println(nodes);

            for (int i=0; i<tableHeadElements.size(); i++){
          //      System.out.println(tableHead.get(i).text());
            }
     //       System.out.println(tableHead.size());
            htmlText = tableHeadElements.text();


        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Ошибка соединения, нет доступа к указанному url");

            while (k<4){
                System.out.println("Попытка подключения: №" + (k+1));
                k++;
               // printConsole();
            }
            return;
        }
    }


    public  void writeFile(String str, String patchName) throws IOException {
        File file = new File(patchName);

        if (!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = null;
        try{
            out = new PrintWriter(file, "cp1251");
            out.write(htmlText);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            assert out != null;
            out.flush();
            out.close();
        }
    }


}
