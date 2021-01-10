package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.sid.cinema.dao.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import lombok.Data;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name = "id") Long id) throws Exception {
		Film f = filmRepository.findById(id).get();
		String photoName = f.getPhoto();
		File file = new File(System.getProperty("user.home") + "/projets/cinema/images/" + photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketFrom) {
		List<Ticket> listTickets = new ArrayList<>();
		ticketFrom.getTickets().forEach(idTicket -> {
//System.out.println(idTicket);
			Ticket ticket = ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketFrom.getNomClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketFrom.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}
}

@Data
class TicketForm {
	private String nomClient;
	private int codePayement;
	private List<Long> tickets = new ArrayList<>();
}
