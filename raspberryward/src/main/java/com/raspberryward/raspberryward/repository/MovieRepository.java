package com.raspberryward.raspberryward.repository;

import com.raspberryward.raspberryward.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findByWinner(String winner);
}
