package com.example.ReactiveClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rt")
public class EmpController {


    WebClient webClient= WebClient.builder().baseUrl("http://localhost:9090/emp").build();


    @PostMapping("")
    public Mono<String> BookNow(@RequestBody Emp emp) {
        return webClient.post().uri("/").syncBody(emp).retrieve().bodyToMono(String.class);
    }

    @GetMapping("")
    public Flux<Emp> trackAllBooking() {
        return webClient.get().uri("/").retrieve().bodyToFlux(Emp.class);
    }

    @GetMapping("/{id}")
    public Mono<Emp> getBookingById(@PathVariable int id) {
        return webClient.get().uri("/" + id).retrieve().bodyToMono(Emp.class);
    }

    @DeleteMapping("/{id}")
    public Mono<String> cancelBooking(@PathVariable int id) {
        return webClient.delete().uri("/" + id).retrieve().bodyToMono(String.class);
    }

    @PutMapping("/{id}")
    public Mono<Emp> updateBooking(@PathVariable int bookingId, @RequestBody Emp emp) {
        return webClient.put().uri("/" + bookingId).syncBody(emp).retrieve()
                .bodyToMono(Emp.class);
    }
}
