package br.com.dextra.MakeMagic.controller;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;
import br.com.dextra.MakeMagic.domain.requests.PersonagePutRequestBody;
import br.com.dextra.MakeMagic.service.PersonageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Personage", value = "Personages")
@RequiredArgsConstructor
@RequestMapping(value = "/api/personages", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonageController {

    private final PersonageService personageService;

    @GetMapping
    @ApiOperation(value = "List of Personages")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "List of Personages", response = PersonageDto.class),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "List of Personages Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error"),

    })
    public ResponseEntity<List<PersonageDto>> listAll() {
        List<PersonageDto> personageDtoList = personageService.listAll();
        if (personageDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(personageDtoList);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Personage by Id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Personage by Id", response = PersonageDto.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Personage Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error"),

    })
    public ResponseEntity<PersonageDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personageService.findById(id));
    }

    @GetMapping(path = "/find")
    @ApiOperation(value = "List of Personages by House")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "List of Personages by House", response = PersonageDto.class),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "List of Personages Not Found"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error"),

    })
    public ResponseEntity<List<PersonageDto>> findByHouse(@RequestParam String house) {
        List<PersonageDto> personageDtoList = personageService.findByHouse(house);
        if (personageDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(personageDtoList);
    }

    @PostMapping
    @ApiOperation(value = "Create Personage")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Created Personage", response = Personage.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error"),

    })
    public ResponseEntity<Personage> save(@RequestBody @Valid PersonagePostRequestBody personagePostRequestBody) {
        return new ResponseEntity<>(personageService.save(personagePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Replace Personage")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Replaced Personage"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error"),

    })
    public ResponseEntity<Void> replace(@RequestBody PersonagePutRequestBody personagePutRequestBody) {
        personageService.replace(personagePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Personage")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Deleted Personage"),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
