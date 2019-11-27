package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fantasy.football.domain.model.AuditModel;

@Entity(name = "Account")
@Table(name = "account")
public class Account extends AuditModel implements Serializable {

	private static final long serialVersionUID = 8187716038664503416L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(unique = true, nullable = false)
	private String acctName = "";
	@Column(unique = true, nullable = false)
	private String acctPass = "";

//	@ElementCollection(fetch = FetchType.EAGER)
//	private List<Role> roles = new ArrayList<>();

	// Relationship
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "account_league", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "league_id"))
	private List<League> leagues = new ArrayList<>();

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Team> teams = new ArrayList<>();

	// Constructor
	public Account() {
	}

	public Account(final String localAcctName) {
		this.setAcctName(localAcctName);
	}
	
	public Account(final String localAcctName, String localAcctPass) {
		this.setAcctName(localAcctName);
		this.setAcctPass(localAcctPass);
	}
	
	public Account(final Account localAcct) {
		this.setAcctName(localAcct.getAcctName());
		this.setAcctPass(localAcct.getAcctPass());
	}

	// Relationship Getters and Setters
	public void addLeague(League localLeague) {
		leagues.add(localLeague);
		localLeague.getAccounts().add(this);
	}

	public void removeLeague(League localLeague) {
		leagues.remove(localLeague);
		localLeague.getAccounts().remove(this);
	}

	// Basic Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(final Long localId) {
		this.id = localId;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(final String localAcctName) {
		this.acctName = localAcctName;
	}

	public String getAcctPass() {
		return acctPass;
	}

	public void setAcctPass(final String localAcctPass) {
		this.acctPass = localAcctPass;
	}

	public Team getTeam(String localTmName) {
		for (Team tmpTeam : this.teams) {
			if (tmpTeam.getTmName().equals(localTmName)) {
				return tmpTeam;
			}
		}
		return null;
	}


	public List<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team localTm) {
		teams.add(localTm);
		localTm.setAccount(this);
	}

	public League getLeague(final String localLeagueName) {
		for (League league : leagues) {
			if (league.getLeagueName().equals(localLeagueName)) {
				return league;
			}
		}
		return null;
	}

	public void setTeams(final List<Team> localTeams) {
		this.teams = localTeams;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(final List<League> localLeagues) {
		this.leagues = localLeagues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(acctName, acctPass, id, leagues, teams);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		return Objects.equals(acctName, other.acctName) && Objects.equals(acctPass, other.acctPass)
				&& Objects.equals(id, other.id) && Objects.equals(leagues, other.leagues)
				&& Objects.equals(teams, other.teams);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", acctName=" + acctName + ", acctPass=" + acctPass + ", leagues=" + leagues
				+ ", teams=" + teams + "]";
	}



}
