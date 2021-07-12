import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YahooScraper {
    public static void main(String[] args) throws Exception {
        ArrayList<StockEntry> entries = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Yahoo Stock Watchlist URL");
        final String url = scan.nextLine();
        scan.close();
        try {
            //Connects to URL
            final Document doc = Jsoup.connect(url).get();
            //Grabs the table rows from the Watchlist
            final Elements rows = doc.select("table.cwl-symbols tr");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();    
            final String title = dtf.format(now).toString() + " " + doc.select("title").text();
            //Grabs data from each row and saves that data to a StockEntry object, and stores to an arraylist
            for (Element row : rows) {
                Elements items = row.select("td");
                if(items.size() > 0){
                    StockEntry entry = new StockEntry(items.get(0).text(), items.get(1).text(), items.get(2).text(), items.get(3).text(), items.get(4).text(), items.get(5).text(), items.get(6).text(), items.get(7).text(), items.get(8).text());
                    entries.add(entry);
                }
            }
            //Creates and formats spreadsheet headings
            Workbook book = new XSSFWorkbook();
            Sheet sheet = book.createSheet("StockEntries");
            String[] headings = {"Symbol", "Company Name", "Last Price", "Change", "Change %", "Market Time", "Volume", "Average Volume(3 Month)", "Market Cap", "Date Pulled"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < headings.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headings[i]);
            }
            int rowNum = 1;
            //Adds each entry field to a workbook cell
            boolean dateFlag = false;
            for(StockEntry entry : entries){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getSymbol());
                row.createCell(1).setCellValue(entry.getCompanyName());
                row.createCell(2).setCellValue(entry.getLastPrice());
                row.createCell(3).setCellValue(entry.getChange());
                row.createCell(4).setCellValue(entry.getChangePercent());
                row.createCell(5).setCellValue(entry.getMarketTime());
                row.createCell(6).setCellValue(entry.getVolume());
                row.createCell(7).setCellValue(entry.getAvgVol());
                row.createCell(8).setCellValue(entry.getMarketCap());
                if(dateFlag == false){
                   row.createCell(9).setCellValue(dtf.format(now).toString()); 
                   dateFlag = true;
                }
            }
            //Adds dynamic workbook formatting
            sheet.createFreezePane(0, 1);
            for (int i = 0; i < headings.length; i++) {
                sheet.autoSizeColumn(i);
            }
            //Writes to spreadsheet file
            FileOutputStream fileOut = new FileOutputStream("/Users/joshuamasters724/Documents/Programming/Spreadsheets/Stocks/" + title + ".xlsx");
            book.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
