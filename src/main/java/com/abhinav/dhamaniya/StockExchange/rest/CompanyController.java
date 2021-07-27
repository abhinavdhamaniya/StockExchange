package com.abhinav.dhamaniya.StockExchange.rest;

import com.abhinav.dhamaniya.StockExchange.dto.CompanyDto;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityCreatedResponse;
import com.abhinav.dhamaniya.StockExchange.dto.response.EntityNotFoundResponse;
import com.abhinav.dhamaniya.StockExchange.exception.EntityNotFoundException;
import com.abhinav.dhamaniya.StockExchange.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://stock-exchange-web-app-abhinav.herokuapp.com/")
@RestController
@RequestMapping("admin/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createCompany(@RequestBody CompanyDto companyDto) {

        int generatedCompanyId;
        try{
            generatedCompanyId = companyService.createCompany(companyDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("StockExchange Not Found"), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Sector Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedCompanyId, "Company Created."), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllCompanies() {
        return new ResponseEntity(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getCompanyById(@PathVariable int id) {
        try {
            return new ResponseEntity(companyService.getCompanyById(id), HttpStatus.OK);
        }
        catch(EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Company with id: "+id+" not found."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/list/{idList}", produces = "application/json")
    public ResponseEntity getCompanyListByCompanyIdList(@PathVariable List<Integer> idList) {
        return new ResponseEntity(companyService.getCompanyListByCompanyIdList(idList), HttpStatus.OK);
    }

    @PatchMapping(value = "deactivate/{id}", produces = "application/json")
    public ResponseEntity deactivateCompany(@PathVariable int id) {
        int returnedId;
        try{
            returnedId = companyService.deactivateCompany(id);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Company Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(returnedId, "Company Deactivated."), HttpStatus.OK);
    }

    @PutMapping (value = "/{id}", produces = "application/json")
    public ResponseEntity updateCompany(@PathVariable int id, @RequestBody CompanyDto companyDto) {

        int generatedCompanyId;
        try{
            companyDto.setId(id);
            generatedCompanyId = companyService.updateCompany(companyDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Entity Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedCompanyId, "Company Updated."), HttpStatus.OK);
    }

    @GetMapping(value = "checkExists/{id}")
    public ResponseEntity checkCompanyExists(@PathVariable int id) {
        boolean exists = companyService.checkCompanyExists(id);
        if(exists) return new ResponseEntity(true, HttpStatus.OK);
        else return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }
}
