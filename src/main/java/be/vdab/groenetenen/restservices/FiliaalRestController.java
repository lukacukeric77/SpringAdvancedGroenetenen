package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.exceptions.FiliaalNietGevonedenException;
import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@ExposesResourceFor(Filiaal.class)
@RequestMapping("/filialen")
class FiliaalRestController {

    private final FiliaalService service;
    private final EntityLinks entityLinks;

    public FiliaalRestController(FiliaalService service, EntityLinks entityLinks) {
        this.service = service;
        this.entityLinks = entityLinks;
    }

    @GetMapping("{filiaal}")
    Filiaal get(@PathVariable Optional<Filiaal> filiaal){
        if (filiaal.isPresent()){
            return filiaal.get();
        }
        throw new FiliaalNietGevonedenException();
    }

    @ExceptionHandler(FiliaalNietGevonedenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void FiliaalNietGevonden(){
    }

    @DeleteMapping("{filiaal}")
    void delete(@PathVariable Optional<Filiaal> filiaal){
        if (!filiaal.isPresent()){
            throw new FiliaalNietGevonedenException();
        } service.delete(filiaal.get());
    }

    @ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String filiaalHeeftNogWerknemers(){
        return "filiaal heeft nog werknemers";
    }

//    @PostMapping
//    void create (@RequestBody @Valid Filiaal filiaal){
//        service.create(filiaal);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders create (@RequestBody @Valid Filiaal filiaal){
        service.create(filiaal);
        HttpHeaders headers = new HttpHeaders();
        Link link = entityLinks.linkToItemResource(Filiaal.class, filiaal.getId());
        headers.setLocation(URI.create(link.getHref()));
        return headers;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex){
        StringBuilder fouten = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                fouten.append(fieldError.getField())
                        .append(':')
                        .append(fieldError.getDefaultMessage())
                        .append('\n'));
        fouten.deleteCharAt(fouten.length()-1);
        return fouten.toString();
    }

    @PutMapping("{id}")
    void update(@RequestBody @Valid Filiaal filiaal){
        service.update(filiaal);
    }

//    @GetMapping("{filiaal}")
//    FiliaalModel get(@PathVariable Optional<Filiaal> filiaal){
//        if (filiaal.isPresent()){
//            return new FiliaalModel(filiaal.get(), entityLinks);
//        }
//        throw new FiliaalNietGevonedenException();
//    }

}
