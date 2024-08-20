package com.maids.maidstask.patrons.controllers;

import com.maids.maidstask.CustomResponseMessage;
import com.maids.maidstask.exceptions.BadRequestException;
import com.maids.maidstask.exceptions.NotFoundException;
import com.maids.maidstask.patrons.Dtos.*;
import com.maids.maidstask.patrons.models.Patron;
import com.maids.maidstask.patrons.services.PatronsService;
import jakarta.validation.Valid;
import jakarta.websocket.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronsController {
    private final PatronsService patronService;
    @Autowired
    public PatronsController(PatronsService patronService) {
        this.patronService = patronService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PatronResponseDto> getAllPatrons () throws NotFoundException {
        return this.patronService.getAllPatrons();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PatronResponseDto addPatron(@Valid @RequestBody CreatePatronRequestDTO patronData) throws BadRequestException {
        return this.patronService.addPatron(patronData);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatronResponseDto getPatronById (@PathVariable Integer id) throws NotFoundException {
        return this.patronService.getPatronById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatePatronResponseDto updatePatronById (@PathVariable Integer id, @Valid @RequestBody UpdatePatronRequestDto patronData) throws NotFoundException {
        return this.patronService.updatePatronById(id, patronData);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponseMessage deletePatronById (@PathVariable Integer id) throws NotFoundException {
        return this.patronService.deletePatronById(id);
    }
}
