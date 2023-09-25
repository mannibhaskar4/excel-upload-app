package com.example.exceluploadapp.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class ExcelService {

    public ExcelSummary processExcelFile(MultipartFile file) throws IOException {
            // Initialize variables to store the sum of hours and total
            double totalHours = 0.0;
            double totalTotal = 0.0;

            // Read the uploaded Excel file
            try (InputStream inputStream = file.getInputStream();
                Workbook workbook = new XSSFWorkbook(inputStream)) {

                // Assuming the data is in the first sheet
                Sheet sheet = workbook.getSheetAt(0);

                // Iterate through rows
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next(); // Skip header row if present

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    // Assuming the columns are in the following order:
                    // Column 0: Emp_ID (String)
                    // Column 1: Project_ID (String)
                    // Column 2: Hours (Numeric/Decimal)
                    // Column 3: Total (Numeric/Decimal)

                    Cell hoursCell = row.getCell(2);
                    Cell totalCell = row.getCell(3);

                    // Extract numeric values from cells
                    double hours = hoursCell.getNumericCellValue();
                    double total = totalCell.getNumericCellValue();

                    // Accumulate the values
                    totalHours += hours;
                    totalTotal += total;
                }
            }

            // Create an object to hold the summary
            ExcelSummary summary = new ExcelSummary(totalHours, totalTotal);
            return summary;
        }
}