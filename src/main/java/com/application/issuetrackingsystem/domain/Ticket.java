package com.application.issuetrackingsystem.domain;

public class Ticket {

	private String sfId;
	private String description;
	private String lastModifiedBy;
	private String owner;
	private String resolution;
	private String status;
	private String ticketNumber;
	private String ticketCreationDate;
	private String ticketTitle;
	private String tier;
	private String agent;

	public Ticket() {

	}

	public String getSfId() {
		return sfId;
	}

	public void setSfId(String sfId) {
		this.sfId = sfId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTicketCreationDate() {
		return ticketCreationDate;
	}

	public void setTicketCreationDate(String ticketCreationDate) {
		this.ticketCreationDate = ticketCreationDate;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

}
