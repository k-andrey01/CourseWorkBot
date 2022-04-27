package DifferentMethods;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExcelParser {

    public static String parse(String fileName, String group, String dayWeek) {
        int start = 0; int end=0;
        if (dayWeek.equals("Понедельник")){
            start = 16; end = 29;
        }
        if (dayWeek.equals("Вторник")){
            start = 31; end = 44;
        }
        if (dayWeek.equals("Среда")){
            start = 46; end = 59;
        }
        if (dayWeek.equals("Четверг")){
            start = 61; end = 75;
        }
        if (dayWeek.equals("Пятница")){
            start = 77; end = 90;
        }
        if (dayWeek.equals("Суббота")){
            start = 92; end = 106;
        }

        //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);

        Iterator<Row> it1 = sheet.iterator();
        int index = 0;
        //проходим по всему листу
        while (it1.hasNext()) {
            Row row1 = it1.next();
            Iterator<Cell> cells1 = row1.iterator();
            while (cells1.hasNext()) {
                Cell cell = cells1.next();
                CellType cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case STRING:
                        if (cell.getStringCellValue().equals(group))
                            index = cell.getColumnIndex();
                        break;
                    default:
                        break;
                }
            }
        }

        int colIndex = 0;
        Iterator<Row> it = sheet.iterator();
        //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            if (row.getRowNum()>=start && row.getRowNum()<=end){
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    CellType cellType = cell.getCellType();
                    if (cell.getColumnIndex()==index || cell.getColumnIndex()==1)
                        for (int i=0; i<sheet.getNumMergedRegions(); i++) {
                            CellRangeAddress region = sheet.getMergedRegion(i); //Region of merged cells
                            if (region.containsColumn(index) && region.containsRow(row.getRowNum())) {
                                colIndex = region.getFirstColumn();
                                i=sheet.getNumMergedRegions();
                            }
                        }
                    if (cell.getColumnIndex()==index || cell.getColumnIndex()==1 || cell.getColumnIndex()==colIndex)
                        //перебираем возможные типы ячеек
                        switch (cellType) {
                            case STRING:
                                result += cell.getStringCellValue()+"\n\n";
                                break;
                            default:
                                break;
                        }
                }
            }
        }

        return result;
    }

}