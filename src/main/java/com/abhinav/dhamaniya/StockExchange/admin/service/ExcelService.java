package com.abhinav.dhamaniya.StockExchange.admin.service;

import com.abhinav.dhamaniya.StockExchange.admin.entities.Ipo;
import com.abhinav.dhamaniya.StockExchange.admin.repository.IpoRepository;
import com.abhinav.dhamaniya.StockExchange.admin.service.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ExcelService {

    @Autowired
    IpoRepository ipoRepository;

    public void saveExcel(MultipartFile file) {
        try {
            List<Ipo> tutorials = ExcelHelper.excelToIpo(file.getInputStream());
            ipoRepository.saveAll(tutorials);
        } catch (IOException ioException) {
            throw new RuntimeException("Failed to store excel data: " + ioException.getMessage());
        }
    }
}