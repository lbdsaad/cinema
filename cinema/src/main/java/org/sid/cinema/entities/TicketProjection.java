package org.sid.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketsProj", types = Ticket.class)
public interface TicketProjection {
}
