package support;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;


    public static String readCellValue(String filePath, String sheetName, int rowNum, int colNum) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }
            //(first row, first column) = (0,0)
            Cell cell = sheet.getRow(rowNum).getCell(colNum);

            //Use DataFormatter to get the formatted string as it appears in Excel
            DataFormatter formatter = new DataFormatter();
            String value = formatter.formatCellValue(cell);
            workbook.close();
            if (cell == null) {
                return "";
            } else {
                return value;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
        }



    }

    public static void writeCellValue(String filePath, String sheetName, int rowNum, int colNum, String value) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
             workbook = new XSSFWorkbook(fis);
             sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) { //check if row already exists
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);
            if(cell==null){
                cell = row.createCell(colNum);
            }

            cell.setCellValue(value);

            // Save changes to the file
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to write to Excel file: " + filePath, e);
        }
    }

    public static void createExcelFileAndSheet(String filePath, String sheetName, int rowNum, int colNum, String value) {
        try {
             workbook = new XSSFWorkbook(); // Create a new workbook (.xlsx)
             sheet = workbook.createSheet(sheetName); // Create a new sheet

            // Create a row and add some cells
            Row row = sheet.createRow(rowNum); // First row
            row.createCell(colNum).setCellValue(value);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos); // Write workbook to file
            workbook.close();
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to Excel file: " + filePath, e);
        }
    }


    public List<List<String>> getAllData() {
        List<List<String>> data = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(cell.toString());
            }
            data.add(rowData);
        }
        return data;
    }

    public static void writeData(String filePath, String sheetName, List<List<String>> data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i);
            List<String> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                row.createCell(j).setCellValue(rowData.get(j));
            }
        }

        try  {
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write Excel file: " + filePath, e);
        }
    }



}
