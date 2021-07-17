package com.abhinav.dhamaniya.StockExchange.admin.rest;

import com.abhinav.dhamaniya.StockExchange.admin.entities.StockExchange;
import com.abhinav.dhamaniya.StockExchange.admin.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public StockExchange createStockExchange(@RequestBody StockExchange stockExchange) {
        // System.out.println(stockExchange);
        return stockExchangeService.createStockExchange(stockExchange);
    }

}
