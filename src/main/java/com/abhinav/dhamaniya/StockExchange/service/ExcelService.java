package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import com.abhinav.dhamaniya.StockExchange.repository.IpoRepository;
import com.abhinav.dhamaniya.StockExchange.service.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ExcelService {

    @Autowired
    IpoRepository ipoRepository;

    public List<Ipo> saveExcel(MultipartFile file) {
        try {
            List<Ipo> ipoList = ExcelHelper.excelToIpo(file.getInputStream());
            ipoRepository.saveAll(ipoList);
            return ipoList;
        } catch (IOException ioException) {
            throw new RuntimeException("Failed to store excel data: " + ioException.getMessage());
        }
    }
}