package com.application.issuetrackingsystem.domain;

import org.json.JSONArray;
import org.json.JSONObject;

public interface TicketRepository {

	public JSONArray ticketsByAgentId(String agentId);

	public JSONArray getTiers();

	public JSONArray getStatuses();

	public JSONArray getAllTickets();

	public JSONObject getTierName(String tierId);

	public JSONObject ticketBySFId(String sfId);

	public JSONObject createTicket(Ticket ticket);

	public JSONObject updateTicket(Ticket ticket);

	public JSONObject deleteTicket(String sfId);

}
