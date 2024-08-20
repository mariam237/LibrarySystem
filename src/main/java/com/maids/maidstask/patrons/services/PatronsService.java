package com.maids.maidstask.patrons.services;

import com.maids.maidstask.CustomResponseMessage;
import com.maids.maidstask.Utils;
import com.maids.maidstask.exceptions.NotFoundException;
import com.maids.maidstask.patrons.Dtos.CreatePatronRequestDTO;
import com.maids.maidstask.patrons.Dtos.PatronResponseDto;
import com.maids.maidstask.patrons.Dtos.UpdatePatronRequestDto;
import com.maids.maidstask.patrons.Dtos.UpdatePatronResponseDto;
import com.maids.maidstask.patrons.models.Patron;
import com.maids.maidstask.patrons.repositories.PatronRepository;
import com.maids.maidstask.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"patrons"})
public class PatronsService {
    private final PatronRepository patronRepo;
    private final PatronMapper patronMapper;
    private final Utils utils;
    @Autowired
    public PatronsService(PatronRepository patronRepo, PatronMapper patronMapper, Utils utils) {
        this.patronRepo = patronRepo;
        this.patronMapper = patronMapper;
        this.utils = utils;
    }


    @Cacheable
    public List<PatronResponseDto> getAllPatrons() throws NotFoundException {
        List<Patron> patrons = this.patronRepo.findAll();
        if(patrons.isEmpty()){
            throw new NotFoundException("No patrons found");
        }

        return patrons.stream().map(patronMapper::toPatronResponseDto).toList();
    }

    @Transactional
    @Cacheable
    public PatronResponseDto addPatron(CreatePatronRequestDTO patronData) throws BadRequestException {
        // Check if patron already exists
        if(this.patronRepo.existsByUsername(patronData.username())){
            throw new BadRequestException("Patron already exists");
        }

        // Create new patron
        Patron patron = this.patronMapper.toPatron(patronData);
        this.patronRepo.save(patron);
        return this.patronMapper.toPatronResponseDto(patron);

    }

    @Cacheable
    public PatronResponseDto getPatronById (@RequestParam Integer id) throws NotFoundException {
        Patron patron = this.patronRepo.findById(id).orElseThrow(() -> new NotFoundException("Patron not found"));
        return this.patronMapper.toPatronResponseDto(patron);
    }


    @Transactional
    @Cacheable
    public UpdatePatronResponseDto updatePatronById (Integer id, UpdatePatronRequestDto newData) throws NotFoundException {
        Patron patron = this.patronRepo.findById(id).orElseThrow(() -> new NotFoundException("Patron not found"));

        //Assign new data to patron
        this.utils.assignObjects(patron, newData);
        this.patronRepo.save(patron);

        return this.patronMapper.toUpdatedPatronResponseDto(patron);

    }

    @Transactional
    @Cacheable
    public CustomResponseMessage deletePatronById (Integer id) throws NotFoundException {
        Patron patron = this.patronRepo.findById(id).orElseThrow(() -> new NotFoundException("Patron not found"));
        this.patronRepo.delete(patron);
        return new CustomResponseMessage(HttpStatus.OK,"Patron deleted successfully");
    }

    @Cacheable
    public Patron getFullPatronById (@RequestParam Integer id) throws NotFoundException {
        return this.patronRepo.findById(id).orElseThrow(() -> new NotFoundException("Patron not found"));
    }
}
