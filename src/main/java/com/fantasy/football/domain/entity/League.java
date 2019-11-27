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
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(unique = true, nullable = false)
	private String leagueName = "";
	@Column(unique = false, nullable = false)
	private int count = 0;
	@Column(unique = false, nullable = false)
	private int draftNum = 1;	
	@Column(unique = false, nullable = false)
	private int startWk = 1;	
	@Column(unique = false, nullable = false)
	private int curWeek = 1;	
	@Column(unique = false, nullable = false)
	private String status = "Created";
	
	public League() {		
	}
	
	public League(String localLeagueName) {
		this.setLeagueName(localLeagueName);
	} 
	
	//Relationship
	@ManyToMany(mappedBy = "leagues")
	@JsonIgnore
	private List<Account> accounts = new ArrayList<>();	
	
	@OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Team> teams = new ArrayList<>();
	
	// Relationship getters and setters
	public void addAccount(Account account) {
        accounts.add(account);
        account.getLeagues().add(this);
    }
	public void removeAccount(Account localAcct) {
        accounts.remove(localAcct);
        localAcct.getLeagues().remove(this);
    }	
	public Account getAccount(String localAccountName) {
		for (Account account: this.accounts) {
			if (account.getAcctName().equals(localAccountName)) {
				return account;
			}
		}
		return null;
	}
	public void removeAllAccounts() {
		this.accounts.clear();
	}
	public Team getTeam(final String localTmName) {
		for (Team team : teams) {
			if (team.getTmName().equals(localTmName)) {
				return team;
			}
		}
		return null;
	}

	public List<Team> getTeams() {
		return teams;
	}
	public void addTeam(Team localTm) {
		 teams.add(localTm);
		 localTm.setLeague(this);
     }

	public void setTeams(final List<Team> localTeams) {
		this.teams = localTeams;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(final List<Account> localAccts) {
		this.accounts = localAccts;
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

	public void setLeagueName(final String localLeagueName) {
		this.leagueName = localLeagueName;
	}

	public int getStartWk() {
		return startWk;
	}
	public void setStartWk(final int localStartWk) {
		this.startWk = localStartWk;
	}
	public int getCount() {
		return count;
	}
	public void setCount(final int count) {
		this.count = count;
	}

	public int getCurWeek() {
		return curWeek;
	}

	public void setCurWeek(final int localCurWeek) {
		this.curWeek = localCurWeek;
	}

	public int getDraftNum() {
		return draftNum;
	}

	public void setDraftNum(final int localDraftNum) {
		this.draftNum = localDraftNum;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(accounts, count, curWeek, draftNum, id, leagueName, startWk, status, teams);
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
		if (!(obj instanceof League)) {
			return false;
		}
		League other = (League) obj;
		return Objects.equals(accounts, other.accounts) && count == other.count && curWeek == other.curWeek
				&& draftNum == other.draftNum && Objects.equals(id, other.id)
				&& Objects.equals(leagueName, other.leagueName) && startWk == other.startWk
				&& Objects.equals(status, other.status) && Objects.equals(teams, other.teams);
	}

	@Override
	public String toString() {
		return "League [id=" + id + ", leagueName=" + leagueName + ", count=" + count + ", draftNum=" + draftNum
				+ ", startWk=" + startWk + ", curWeek=" + curWeek + ", status=" + status + ", accounts=" + accounts
				+ ", teams=" + teams + "]";
	}

	
	
	
	
}
