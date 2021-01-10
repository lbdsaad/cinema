package org.sid.cinema;

import org.sid.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.sid.cinema.service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class);
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPalces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
	}
}