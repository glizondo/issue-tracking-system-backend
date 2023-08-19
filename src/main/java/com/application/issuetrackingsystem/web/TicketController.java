package com.application.issuetrackingsystem.web;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.issuetrackingsystem.domain.Ticket;
import com.application.issuetrackingsystem.domain.TicketRepository;
import com.application.issuetrackingsystem.domain.TicketRepositorySFImpl;

@RestController
@CrossOrigin(origins = "http://localhost:300")
@RequestMapping("/tickets")
public class TicketController {

	private TicketRepository repo = new TicketRepositorySFImpl();

	@GetMapping(path = "/agentId/{agentId}")
	public String getTicketsByAgent(@PathVariable String agentId) {
		JSONArray result = repo.ticketsByAgentId(agentId);
		return result.toString();
	}

	@GetMapping(path = "/all")
	public String getAllTickets() {
		JSONArray result = repo.getAllTickets();
		return result.toString();
	}

	@GetMapping(path = "/status")
	public String getStatuses() {
		JSONArray result = repo.getStatuses();
		return result.toString();
	}
	
	@GetMapping(path = "/{sfId}")
	public String getTicket(@PathVariable String sfId) {
		JSONObject result = repo.ticketBySFId(sfId);
		return result.toString();
	}

	@GetMapping(path = "/tiers")
	public String getTiers() {
		JSONArray result = repo.getTiers();
		return result.toString();
	}
	
	@GetMapping(path = "/tierId/{tierId}")
	public String getTierName(@PathVariable String tierId) {
		JSONObject result = repo.getTierName(tierId);
		return result.toString();
	}

	@PostMapping(consumes = { "application/json" })
	public String createTicket(@RequestBody Ticket ticket) {
		JSONObject result = repo.createTicket(ticket);
		return result.toString();
	}

	@DeleteMapping(path = "/{sfId}")
	public String deleteTicket(@PathVariable String sfId) {
		JSONObject result = repo.deleteTicket(sfId);
		return result.toString();
	}

	@PutMapping(consumes = { "application/json" })
	public String updateTicket(@RequestBody Ticket ticket) {
		JSONObject result = repo.updateTicket(ticket);
		return result.toString();
	}
}
