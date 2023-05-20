package com.project.reactiveProgramming.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxAndMonoServiceTest {

  private FluxAndMonoService fluxAndMonoService;

  @BeforeEach
  public void onSetUp() {
    fluxAndMonoService = new FluxAndMonoService();
  }

  @Test
  void fruitsFlux() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFlux();

    StepVerifier.create(stringFlux).expectNext("Mango", "Orange", "Banana").verifyComplete();
  }

  @Test
  void fruitsMono() {
    Mono<String> stringMono = fluxAndMonoService.fruitsMono();
    StepVerifier.create(stringMono).expectNext("Mango").verifyComplete();
  }

  @Test
  void fruitsFluxMap() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxMap();
    StepVerifier.create(stringFlux).expectNext("MANGO", "ORANGE", "BANANA").verifyComplete();
  }

  @Test
  void fruitsFluxFilterMap() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxFilterMap(5);
    StepVerifier.create(stringFlux).expectNext("ORANGE", "BANANA").verifyComplete();
  }

  @Test
  void fruitsFluxFlatMap() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxFlatMap();
    StepVerifier.create(stringFlux).expectNextCount(17).verifyComplete();
  }

  @Test
  void fruitsFluxFlatMapAsync() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxFlatMapAsync();
    StepVerifier.create(stringFlux).expectNextCount(17).verifyComplete();
  }

  @Test
  void fruitsMonoFlatMap() {
    Mono<List<String>> listMono = fluxAndMonoService.fruitsMonoFlatMap();
    StepVerifier.create(listMono).expectNextCount(1).verifyComplete();
  }

  @Test
  void fruitsMonoFlatMapMany() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsMonoFlatMapMany();
    StepVerifier.create(stringFlux).expectNextCount(5).verifyComplete();
  }

  @Test
  void fruitsFluxTransform() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxTransform(5);
    StepVerifier.create(stringFlux).expectNext("Orange", "Banana").verifyComplete();
  }

  @Test
  void fruitsFluxTransformDefaultIfEmpty() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxTransform(10);
    StepVerifier.create(stringFlux).expectNext("Default").verifyComplete();
  }

  @Test
  void fruitsFluxTransformSwitchIfEmpty() {
    Flux<String> stringFlux = fluxAndMonoService.fruitsFluxTransformSwitchIfEmpty(8);
    StepVerifier.create(stringFlux).expectNext("PineApple", "Jack Fruit").verifyComplete();
  }
}
