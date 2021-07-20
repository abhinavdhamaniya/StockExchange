package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.dto.IpoDto;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.service.IpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateIpo(@PathVariable int id, @RequestBody IpoDto ipoDto) {

        int generatedIpoId;
        try{
            ipoDto.setId(id);
            generatedIpoId = ipoService.updateIpo(ipoDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("IPO Not Found"), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Company Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedIpoId, "IPO Updated."), HttpStatus.OK);
    }
}
