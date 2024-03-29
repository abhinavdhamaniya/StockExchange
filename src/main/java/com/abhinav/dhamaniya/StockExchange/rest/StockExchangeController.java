package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.dto.StockExchangeDto;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.entities.StockExchange;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://stock-exchange-web-app-abhinav.herokuapp.com/")
@RestController
@RequestMapping("admin/stock-exchanges")
public class StockExchangeController {

    @Autowired
    StockExchangeService stockExchangeService;

    @GetMapping(produces = "application/json")
    public List<StockExchangeDto> getAllStockExchanges() {
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

    @GetMapping(value = "/list/{idList}", produces = "application/json")
    public ResponseEntity getStockExchangeListByStockExchangeIdList(@PathVariable List<Integer> idList) {
        return new ResponseEntity(stockExchangeService.getStockExchangeListByStockExchangeIdList(idList), HttpStatus.OK);
    }
}
