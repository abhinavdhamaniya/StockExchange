package com.abhinav.dhamaniya.StockExchange.service;

import com.abhinav.dhamaniya.StockExchange.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.entities.Ipo;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.mapper.IpoMapper;
import com.abhinav.dhamaniya.StockExchange.repository.IpoRepository;
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

    public Boolean checkIpoExists(int id) {
        if(ipoRepository.findById(id).isPresent()) return true;
        return false;
    }

    public int updateIpo(IpoDto ipoDto) throws EntityNotFoundException, Exception{
        Ipo ipo = ipoRepository.findById(ipoDto.getId()).orElse(null);
        if(ipo == null) throw new EntityNotFoundException("Ipo Not Found");
        return ipoRepository.save(ipoMapper.convertToEntity(ipoDto)).getId();
    }
}
