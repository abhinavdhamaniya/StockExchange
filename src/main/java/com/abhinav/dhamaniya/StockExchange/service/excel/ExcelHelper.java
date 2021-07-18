package com.abhinav.dhamaniya.StockExchange.service.excel;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.abhinav.dhamaniya.StockExchange.entities.Company;
import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) return false;
        return true;
    }

    public static List<Ipo> excelToIpo(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Ipo> ipoList = new ArrayList<>();
            int rowIndex = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Ipo ipo = new Ipo();
                int cellIndex = 0;

                while (cellsInRow.hasNext()) {

                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            ipo.setCompany(new Company((int) currentCell.getNumericCellValue()));
                            break;
                        case 1:
                            ipo.setStockExchangeName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            ipo.setPricePerShare(currentCell.getNumericCellValue());
                            break;
                        case 3:
                            ipo.setTotalShares((long) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            ipo.setOpenDateTime(currentCell.getLocalDateTimeCellValue());
                            break;
                        case 6:
                            ipo.setRemarks(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }

                ipoList.add(ipo);
            }
            workbook.close();
            return ipoList;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}