package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fantasy.football.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "League")
@Table(name = "league")
public class League extends AuditModel implements Serializable {
		
	private static final long serialVersionUID = -4408681528897336928L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int count = 0;
	private int draftOrder = 1;
	private String leagueName = "Default";
	private int startWeek = 1;	
	private int currentWeek = 1;	
	private String status = "Created";
	
	public League() {		
	}
	
	public League(String leagueName) {
		this.setLeagueName(leagueName);
	} 
	
	//Relationship
	@ManyToMany(mappedBy = "leagues")
	@JsonIgnore
	private List<Account> accounts = new ArrayList<>();	
	
	@OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Team> teams = new ArrayList<Team>();
	
	// Relationship getters and setters
	public void addAccount(Account account) {
        accounts.add(account);
        account.getLeagues().add(this);
    }
	public void removeAccount(Account account) {
        accounts.remove(account);
        account.getLeagues().remove(this);
    }	
	public Account getAccount(String accountName) {
		for (Account account: this.accounts) {
			if (account.getAccountName().equals(accountName)) {
				return account;
			}
		}
		return null;
	}
	public void removeAllAccounts() {
		this.accounts.clear();
	}
	public Team getTeam(String teamName) {
		for (Team team : teams) {
			if (team.getTeamName().equals(teamName)) {
				return team;
			}
		}
		return null;
	}
	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}
	public void addTeam(Team localTeam) {
		 teams.add(localTeam);
	     localTeam.setLeague(this);
     }
	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	// Basic Getter and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public int getStartWeek() {
		return startWeek;
	}
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}

	public int getDraftOrder() {
		return draftOrder;
	}

	public void setDraftOrder(int draft_order) {
		this.draftOrder = draft_order;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(accounts, count, currentWeek, draftOrder, id, leagueName, startWeek, status, teams);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof League))
			return false;
		League other = (League) obj;
		return Objects.equals(accounts, other.accounts) && count == other.count && currentWeek == other.currentWeek
				&& draftOrder == other.draftOrder && Objects.equals(id, other.id)
				&& Objects.equals(leagueName, other.leagueName) && startWeek == other.startWeek
				&& Objects.equals(status, other.status) && Objects.equals(teams, other.teams);
	}
	
	
	
}
