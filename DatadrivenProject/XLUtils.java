package DatadrivenProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

    public static int getRowCount(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        wb.close();
        fis.close();
        return rowCount;
    }

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        String cellData = cell.toString();
        wb.close();
        fis.close();
        return cellData;
    }

    public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String data) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null)
            row = sheet.createRow(rowNum);
        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);
        FileOutputStream fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

    public static void fillGreenColor(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        applyCellStyle(filePath, sheetName, rowNum, colNum, IndexedColors.GREEN.getIndex());
    }

    public static void fillRedColor(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        applyCellStyle(filePath, sheetName, rowNum, colNum, IndexedColors.RED.getIndex());
    }

    private static void applyCellStyle(String filePath, String sheetName, int rowNum, int colNum, short colorIndex) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null)
            row = sheet.createRow(rowNum);
        Cell cell = row.getCell(colNum);
        if (cell == null)
            cell = row.createCell(colNum);
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        FileOutputStream fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }
}
