package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import com.abhinav.dhamaniya.StockExchange.service.ExcelService;
import com.abhinav.dhamaniya.StockExchange.service.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "https://stock-exchange-web-app-abhinav.herokuapp.com/")
@RestController
@RequestMapping("admin/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadExcel(@RequestParam("excelFile") MultipartFile file) {

        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<Ipo> ipoList = excelService.saveExcel(file);
                message = "Upload Successful. " + ipoList.size() + " records inserted. Stock Exchange Name: "+ ipoList.get(0).getStockExchangeName();
                return new ResponseEntity(message, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload excel file (Improper Format, or Invalid Data): " + file.getOriginalFilename() + "!";
                return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else return new ResponseEntity("Wrong Format!", HttpStatus.BAD_REQUEST);
    }
}
