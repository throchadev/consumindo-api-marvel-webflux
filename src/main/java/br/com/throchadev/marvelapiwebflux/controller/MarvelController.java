package br.com.throchadev.marvelapiwebflux.controller;

import br.com.throchadev.marvelapiwebflux.client.interfaces.MarvelClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/marvel", produces="application/json")
public class MarvelController {

  private MarvelClient marvelClient;

  public MarvelController(MarvelClient marvelClient) {
    this.marvelClient = marvelClient;
  }

  @GetMapping("/characters/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<String> getCharacterById(@PathVariable String id){
    return marvelClient.findACharacterById(id);
  }

  @GetMapping("/characters/name/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<String> getCharacterByName(@PathVariable String name){
    return marvelClient.findACharacterByName(name);
  }
}
