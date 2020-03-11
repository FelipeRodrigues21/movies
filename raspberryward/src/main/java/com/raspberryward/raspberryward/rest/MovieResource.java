package com.raspberryward.raspberryward.rest;


import com.raspberryward.raspberryward.Dto.AwardMaxMinDto;
import com.raspberryward.raspberryward.domain.Movie;
import com.raspberryward.raspberryward.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieResource {

    private MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> created(@RequestBody Movie movie) throws IOException, URISyntaxException {
        System.out.println("Quantidade de registros" + movieService.getAll().size());
        return ResponseEntity.created(new URI("/movies")).body(movieService.save(movie));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAll() throws IOException {
        System.out.println("Quantidade de registros" + movieService.getAll().size());
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/movies/winner")
    public ResponseEntity<AwardMaxMinDto> moviesWinner() {
         return ResponseEntity.ok(movieService.moviesWinner());
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
