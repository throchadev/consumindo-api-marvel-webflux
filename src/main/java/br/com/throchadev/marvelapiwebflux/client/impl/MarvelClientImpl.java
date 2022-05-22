package br.com.throchadev.marvelapiwebflux.client.impl;

import br.com.throchadev.marvelapiwebflux.client.interfaces.MarvelClient;
import br.com.throchadev.marvelapiwebflux.configuration.MarvelConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static br.com.throchadev.marvelapiwebflux.utils.ConstantsUtil.*;

@Service
@Slf4j
public class MarvelClientImpl implements MarvelClient {

  private MarvelConfiguration marvelConfiguration;
  private WebClient webClient;

  public MarvelClientImpl(MarvelConfiguration marvelConfiguration, WebClient.Builder builder) {
    this.marvelConfiguration = marvelConfiguration;
    this.webClient = builder.baseUrl(marvelConfiguration.getUrl()).build();
  }

  public Mono<String> findACharacterById(String id) {
    log.info("Searching for character with id [{}]", id);
    return webClient
        .get()
        .uri("/v1/public/characters/" + id + PATH_SEPARATOR_INTERROGATION + marvelConfiguration.getPathCredentials())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError,
            error -> Mono.error(new RuntimeException("Check the information")))
        .bodyToMono(String.class);
  }

  public Mono<String> findACharacterByName(String name) {
    log.info("Searching for character with name [{}]", name);
    return webClient
        .get()
        .uri("/v1/public/characters" + PATH_SEPARATOR_INTERROGATION + "name" + PATH_SEPARATOR_EQUAL + name + PATH_SEPARATOR_AND_COMMERCIAL + marvelConfiguration.getPathCredentials())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError,
            error -> Mono.error(new RuntimeException("Check the information")))
        .bodyToMono(String.class);
  }
}
