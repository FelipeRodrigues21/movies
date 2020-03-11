package com.raspberryward.raspberryward.service;

import com.raspberryward.raspberryward.Dto.AwardDto;
import com.raspberryward.raspberryward.Dto.AwardMaxMinDto;
import com.raspberryward.raspberryward.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    AwardMaxMinDto moviesWinner();

    Movie save(Movie movie);

    void delete(String id);

}
