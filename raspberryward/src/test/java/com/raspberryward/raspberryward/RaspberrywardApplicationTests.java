package com.raspberryward.raspberryward;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raspberryward.raspberryward.domain.Movie;
import com.raspberryward.raspberryward.service.ReadSpreadsheet;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RaspberrywardApplication.class)
@AutoConfigureMockMvc
class RaspberrywardApplicationTests {

    @Autowired
    ReadSpreadsheet readSpreadsheet;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup () throws IOException {
    }

    @Test
    public void createMovie() throws Exception {
        Movie movie = initMovie();
        movie.setId(null);

        mvc.perform(post("/api/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional(readOnly = true)
    public void listMoviesWinner() throws Exception {
        readSpreadsheet.persistDatas();

        mvc.perform(get("/api/movies/winner")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void deleteMovie() throws Exception {
        Movie movie = initMovie();

        mvc.perform(delete("/api/movies/{id}", movie.getId()))
                .andExpect(status().isOk());
    }

    public Movie initMovie() {
        Movie movie = new Movie();

        movie.setId("d8606e34-19db-4666-8ef5-98df8132d1e4");
        movie.setTitle("Transformers: The Last Knight");
        movie.setStudio("Paramount Pictures");
        movie.setProducers("Don Murphy, Tom DeSanto, Lorenzo di Bonaventura and Ian Bryce");
        movie.setYear("2017");
        movie.setWinner("");

        return movie;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
