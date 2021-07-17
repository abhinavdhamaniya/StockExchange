package com.abhinav.dhamaniya.StockExchange.admin.rest;

import com.abhinav.dhamaniya.StockExchange.admin.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.admin.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.admin.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import com.abhinav.dhamaniya.StockExchange.admin.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.admin.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/stock-exchanges")
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @GetMapping(produces = "application/json")
    public List<StockExchange> getAllStockExchanges() {
        return stockExchangeService.getAllStockExchanges();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createStockExchange(@RequestBody StockExchangeDto stockExchangeDto) {

        int generatedStockExchangeId;
        try{
            generatedStockExchangeId = stockExchangeService.createStockExchange(stockExchangeDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Company Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedStockExchangeId, "Stock Exchange Created."), HttpStatus.OK);
    }

}
