package br.com.throchadev.marvelapiwebflux.client.interfaces;

import reactor.core.publisher.Mono;

public interface MarvelClient {

  Mono<String> findACharacterById(String id);
  Mono<String> findACharacterByName(String name);
}
