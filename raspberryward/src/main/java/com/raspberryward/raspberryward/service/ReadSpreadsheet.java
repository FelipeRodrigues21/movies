package com.raspberryward.raspberryward.service;


import com.raspberryward.raspberryward.domain.Movie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReadSpreadsheet {

    List<Movie> readDatas() throws IOException;

    void persistDatas() throws IOException;
}
