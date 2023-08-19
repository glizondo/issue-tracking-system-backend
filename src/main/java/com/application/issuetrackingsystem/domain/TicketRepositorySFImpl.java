package com.application.issuetrackingsystem.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.application.issuetrackingsystem.service.SFRestNetConnection;

public class TicketRepositorySFImpl implements TicketRepository {

	@Override
	public JSONArray ticketsByAgentId(String agentId) {
		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("Select Id, Tier__c, Description__c, LastModifiedById, OwnerId, "
				+ "Resolution__c, Status__c, Name, Ticket_Creation_Date__c, Ticket_Title__c, Agent__c  From "
				+ "Ticket__c where Agent__c = '" + agentId + "' ORDER BY Name");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONArray result = sfConnection.query(sfRestAPI);
		System.out.println("query result: " + result);

		return result;
	}

	@Override
	public JSONArray getAllTickets() {
		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("Select Id, Tier__c, Description__c, LastModifiedById, OwnerId, "
				+ "Resolution__c, Status__c, Name, Ticket_Creation_Date__c, Ticket_Title__c, Agent__c From "
				+ "Ticket__c ORDER BY Name");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONArray result = sfConnection.query(sfRestAPI);
		System.out.println("query result: " + result);

		return result;
	}

	@Override
	public JSONArray getTiers() {
		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("Select Id, Name, Tier_Code__c From Tier__c ORDER BY Name");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONArray result = sfConnection.query(sfRestAPI);

		return result;
	}

	@Override
	public JSONObject getTierName(String tierId) {
		System.out.println(tierId);

		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("Select Name From " + "Tier__c where Id = '" + tierId + "'");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONObject result = sfConnection.query(sfRestAPI).getJSONObject(0);

		return result;
	}

	@Override
	public JSONArray getStatuses() {
		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("SELECT EntityParticle.QualifiedApiName, Label, Value FROM PicklistValueInfo "
				+ "WHERE EntityParticle.EntityDefinition.QualifiedApiName='Ticket__c' ORDER BY Label");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONArray records = sfConnection.query(sfRestAPI);

		System.out.println("records: " + records);

		JSONArray requests = new JSONArray();
		JSONObject listObjects = null;
		for (int i = 0; i < records.length(); i++) {
			try {
				listObjects = new JSONObject();

				String listName = records.getJSONObject(i).getJSONObject("EntityParticle").getString("QualifiedApiName")
						.replace("__c", "");
				listObjects.put(listName, listName + " * " + records.getJSONObject(i).getString("Label"));
				requests.put(listObjects);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		System.out.println("requests: " + requests);
		return requests;
	}

	@Override
	public JSONObject ticketBySFId(String sfId) {
		System.out.println(sfId);

		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfSOQLQuery("Select Id, Tier__c, Description__c, LastModifiedById, OwnerId, "
				+ "Resolution__c, Status__c, Name, Ticket_Creation_Date__c, Ticket_Title__c, Agent__c From "
				+ "Ticket__c where Id = '" + sfId + "'");

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		JSONObject result = sfConnection.query(sfRestAPI).getJSONObject(0);

		return result;
	}

	@Override
	public JSONObject createTicket(Ticket ticket) {

		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfObjectType("Ticket__c");

		JSONObject result = new JSONObject();
		boolean sfResults = false;

		JSONObject sfObject = new JSONObject();
		sfObject.put("Agent__c", ticket.getAgent());
		sfObject.put("Description__c", ticket.getDescription());
		sfObject.put("Resolution__c", ticket.getResolution());
		sfObject.put("Status__c", ticket.getStatus());
		sfObject.put("Ticket_Creation_Date__c", ticket.getTicketCreationDate());
		sfObject.put("Ticket_Title__c", ticket.getTicketTitle());
		sfObject.put("Tier__c", ticket.getTier());

		sfRestAPI.setSfObjectJSON(sfObject);

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		sfResults = sfConnection.createObject(sfRestAPI);

		if (!sfResults) {
			result.put("message", "Salesforce Create failed: " + sfObject);
		} else {
			result.put("message", "Salesforce Create successful");
		}

		return result;
	}

	@Override
	public JSONObject updateTicket(Ticket ticket) {

		System.out.println("Id: " + ticket.getSfId());

		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfObjectType("Ticket__c");
		sfRestAPI.setSfObjectId(ticket.getSfId());

		JSONObject result = new JSONObject();
		boolean sfResults = false;
		JSONObject sfObject = new JSONObject();

		sfObject.put("Ticket_Title__c", ticket.getTicketTitle());
		sfObject.put("Status__c", ticket.getStatus());
		sfObject.put("Resolution__c", ticket.getResolution());
		sfObject.put("Tier__c", ticket.getTier());
		sfObject.put("Agent__c", ticket.getAgent());
		sfObject.put("Description__c", ticket.getDescription());

		sfRestAPI.setSfObjectJSON(sfObject);

		SFRestNetConnection sfConnection = new SFRestNetConnection();

		sfResults = sfConnection.updateObject(sfRestAPI);

		if (!sfResults) {
			result.put("message", "Salesforce Update failed: " + sfObject);
		} else {
			result.put("message", "Salesforce Update successful");
		}

		return result;
	}

	@Override
	public JSONObject deleteTicket(String sfId) {
		SF_Rest_API sfRestAPI = new SF_Rest_API();
		sfRestAPI.setSfObjectType("Ticket__c");
		sfRestAPI.setSfObjectId(sfId);

		JSONObject result = new JSONObject();
		boolean sfResults = false;

		SFRestNetConnection sfConnection = new SFRestNetConnection();
		sfResults = sfConnection.deleteObject(sfRestAPI);

		if (!sfResults) {
			result.put("message", "Salesforce Delete failed: " + sfId);
		} else {
			result.put("message", "Salesforce Delete successful");
		}

		return result;
	}

}
