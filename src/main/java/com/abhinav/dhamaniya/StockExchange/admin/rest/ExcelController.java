package com.abhinav.dhamaniya.StockExchange.admin.rest;

import com.abhinav.dhamaniya.StockExchange.admin.service.ExcelService;
import com.abhinav.dhamaniya.StockExchange.admin.service.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadExcel(@RequestParam("file") MultipartFile file) {

        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveExcel(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return new ResponseEntity(message, HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload excel file (Improper Format): " + file.getOriginalFilename() + "!";
                return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        message = "Please upload an excel file!";
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
}
