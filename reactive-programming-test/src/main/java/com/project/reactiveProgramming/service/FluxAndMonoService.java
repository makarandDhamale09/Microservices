package com.project.reactiveProgramming.service;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService {

  public static void main(String[] args) {
    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    fluxAndMonoService.fruitsFlux().subscribe(System.out::println);

    fluxAndMonoService.fruitsMono().subscribe(s -> System.out.println("Mono :" + s));
  }

  public Flux<String> fruitsFlux() {
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
  }

  public Flux<String> fruitsFluxMap() {
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).map(String::toUpperCase).log();
  }

  public Flux<String> fruitsFluxFilterMap(int number) {
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .filter(s -> s.length() > number)
        .map(String::toUpperCase)
        .log();
  }

  public Flux<String> fruitsFluxFlatMap() {
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .flatMap(s -> Flux.just(s.split("")))
        .log();
  }

  public Flux<String> fruitsFluxFlatMapAsync() {
    return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
        .flatMap(s -> Flux.just(s.split("")))
        .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
        .log();
  }

  public Mono<String> fruitsMono() {
    return Mono.just("Mango");
  }

  public Mono<List<String>> fruitsMonoFlatMap() {
    return Mono.just("Mango").flatMap(s -> Mono.just(List.of(s.split("")))).log();
  }

  public Flux<String> fruitsMonoFlatMapMany() {
    return Mono.just("Mango").flatMapMany(s -> Flux.just(s.split(""))).log();
  }

  public Flux<String> fruitsFluxTransform(int number) {

    Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

    return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).transform(filterData)
            .defaultIfEmpty("Default")
            .log();
  }

  public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {

    Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

    return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).transform(filterData)
            .switchIfEmpty(Flux.just("PineApple","Jack Fruit")
                    .transform(filterData))
            .log();
  }
}
