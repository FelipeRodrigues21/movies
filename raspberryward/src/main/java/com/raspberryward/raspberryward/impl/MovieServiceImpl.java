package com.raspberryward.raspberryward.impl;

import com.raspberryward.raspberryward.Dto.AwardDto;
import com.raspberryward.raspberryward.Dto.AwardMaxMinDto;
import com.raspberryward.raspberryward.domain.Movie;
import com.raspberryward.raspberryward.repository.MovieRepository;
import com.raspberryward.raspberryward.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Movie movie = repository.findById(id).orElse(null);
        repository.delete(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public AwardMaxMinDto moviesWinner() {
        List<AwardDto> movies = new ArrayList<>(separedItensIepeated(repository.findAll()));
        return castingAward(movies);
    }

    private AwardMaxMinDto castingAward(List<AwardDto> awardDtos) {
        AwardMaxMinDto awardMaxMinDto = new AwardMaxMinDto();
        List<AwardDto> revertList = awardDtos.stream().sorted(Comparator.comparingInt(AwardDto::getInterval)).collect(Collectors.toList());

        awardMaxMinDto.setMax(new ArrayList<>());
        awardMaxMinDto.setMin(new ArrayList<>());

        awardMaxMinDto.getMax().add(awardDtos.get(0));
        awardMaxMinDto.getMax().add(awardDtos.get(1));

        awardMaxMinDto.getMin().add(revertList.get(0));
        awardMaxMinDto.getMin().add(revertList.get(1));

        return awardMaxMinDto;
    }

    private Set<AwardDto> separedItensIepeated(List<Movie> movies) {
        HashSet<Movie> setMovie = new HashSet<>(movies);
        List<Movie> producersRepeat = new ArrayList<>();
        List<AwardDto> awardDtos = new ArrayList<>();

        for (Movie value : setMovie) {
            producersRepeat.add(value);
        }

        for (Movie list1 : producersRepeat) {
            AwardDto awardDto = new AwardDto();
            Integer yearList1 = Integer.parseInt(list1.getYear());
            for (Movie list2 : producersRepeat) {
                Integer yearList2 = Integer.parseInt(list2.getYear());
                Integer result = yearList1 - yearList2;
                if (list1.getProducers().contains(list2.getProducers())) {
                    if (result > 0) {
                        awardDto.setInterval(result);
                        awardDto.setProducer(list2.getProducers());
                        awardDto.setFollowingWin(yearList2);
                        awardDto.setPreviousWin(yearList1);

                        awardDtos.add(awardDto);
                    }
                }
            }
        }


        return awardDtos.stream().sorted(
                Comparator.comparingInt(AwardDto::getInterval).reversed()
        ).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
