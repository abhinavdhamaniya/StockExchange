package com.abhinav.dhamaniya.StockExchange.admin.rest;

import com.abhinav.dhamaniya.StockExchange.admin.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.admin.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.admin.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.admin.service.IpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/ipos")
public class IpoController {

    @Autowired
    IpoService ipoService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createIpo(@RequestBody IpoDto ipoDto) {

        int generatedIpoId;
        try{
            generatedIpoId = ipoService.createIpo(ipoDto);
        }
        catch (Exception exception)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Company Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedIpoId, "IPO Created."), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllIpos() {
        return new ResponseEntity(ipoService.getAllIpos(), HttpStatus.OK);
    }
}
