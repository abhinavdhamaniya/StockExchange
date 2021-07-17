package com.abhinav.dhamaniya.StockExchange.admin.service;

import com.abhinav.dhamaniya.StockExchange.admin.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.admin.mapper.IpoMapper;
import com.abhinav.dhamaniya.StockExchange.admin.repository.IpoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IpoService {

    @Autowired
    IpoRepository ipoRepository;
    @Autowired
    IpoMapper ipoMapper;

    public int createIpo(IpoDto ipoDto) throws Exception {
        return ipoRepository.save(ipoMapper.convertToEntity(ipoDto)).getId();
    }

    public List<IpoDto> getAllIpos() {
        return ipoRepository.findAll().stream().map(ipo -> ipoMapper.convertToDto(ipo)).collect(Collectors.toList());
    }
}
