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

    @PatchMapping(value = "/{id}", produces = "application/json")
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

    @PutMapping (produces = "application/json")
    public ResponseEntity updateCompany(@RequestBody CompanyDto companyDto) {

        int generatedCompanyId;
        try{
            generatedCompanyId = companyService.updateCompany(companyDto);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            return new ResponseEntity(new EntityNotFoundResponse("Entity Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new EntityCreatedResponse(generatedCompanyId, "Company Updated."), HttpStatus.OK);
    }
}
