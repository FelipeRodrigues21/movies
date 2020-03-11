package com.raspberryward.raspberryward.impl;

import com.raspberryward.raspberryward.domain.Movie;
import com.raspberryward.raspberryward.repository.MovieRepository;
import com.raspberryward.raspberryward.service.ReadSpreadsheet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadSpreadsheetImpl implements ReadSpreadsheet {

    private static final String FILE = "C:\\Users\\felipe\\Documents\\raspberryward\\src\\main\\resources\\file\\movielist.csv";

    MovieRepository movieRepository;

    public ReadSpreadsheetImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> readDatas() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(FILE));

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("year", "title", "studios", "producers", "winner")
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .withTrim());

        List<Movie> movies = new ArrayList<>();
        for (CSVRecord csvRecord : csvParser) {
            Movie movie = new Movie();

            movie.setYear(csvRecord.get("year"));
            movie.setTitle(csvRecord.get("title"));
            movie.setStudio(csvRecord.get("studios"));
            movie.setProducers(csvRecord.get("producers"));
            movie.setWinner(csvRecord.get("winner"));

            movies.add(movie);

        }

        return movies;

    }

    @Transactional
    public void persistDatas() throws IOException {
        movieRepository.saveAll(readDatas());
    }
}
