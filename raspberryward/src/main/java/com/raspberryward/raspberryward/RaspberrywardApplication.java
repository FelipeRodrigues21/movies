package com.raspberryward.raspberryward;

import com.raspberryward.raspberryward.service.ReadSpreadsheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
public class RaspberrywardApplication {

    static ReadSpreadsheet readSpreadsheet;

    public RaspberrywardApplication(ReadSpreadsheet readSpreadsheet) {
        this.readSpreadsheet = readSpreadsheet;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RaspberrywardApplication.class, args);
        readSpreadsheet.persistDatas();
    }

}
