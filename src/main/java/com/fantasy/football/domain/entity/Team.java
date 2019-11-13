package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fantasy.football.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Team")
@Table(name = "team")
public class Team extends AuditModel implements Serializable {

	private static final long serialVersionUID = 7540779346243793203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String teamName = "Default";	
	protected int draftPosition = 0;
	protected String helmet = "";
	protected float totalPoints = 0;
	
	// Relationships
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "league_id")
	@JsonIgnore
	private League league = new League();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account = new Account();
	
    @ManyToMany(cascade = {
    	    CascadeType.PERSIST,
    	    CascadeType.MERGE
    	})
    	@JoinTable(name = "team_player",
    	    joinColumns = @JoinColumn(name = "team_id"),
    	    inverseJoinColumns = @JoinColumn(name = "player_id")
    	)
    	private List<Player> players = new ArrayList<Player>();
	
	// Constructors
	public Team() {
	}

	public Team(String teamName) {
		this.teamName = teamName;
	}

	public Team(List<Player> players) {
		for (Player player : players) {
			Player tmpPlayer = new Player(player);
			this.players.add(tmpPlayer);
		}
	}

	public Team(Team team) {
		this.setTeamName(team.getTeamName());		
		this.setDraftPosition(team.getDraftPosition());
		this.setHelmet(this.getHelmet());
		this.setTotalPoints(team.getTotalPoints());
		for (Player tmpPlayer : team.getPlayers()) {
			this.players.add(tmpPlayer);
		}
	}	

	// Relationship getters and setters
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getPlayers() {
		if (this.players == null) {
			this.players = new ArrayList<>();
		}
		return this.players;
	}
	public Player getPlayer(String playerName) {
		for (Player player: this.players) {
			if (player.getPlayerName().equals(playerName)) {
				return player;
			}
		}
		return null;
	}
	
	public void addPlayer(Player player) {
        players.add(player);
        player.getTeams().add(this);
    }
 
    public void removePlayer(Player player) {
        players.remove(player);
        player.getTeams().remove(this);
    }
 
	// Basic getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getDraftPosition() {
		return draftPosition;
	}

	public void setDraftPosition(int draftPosition) {
		this.draftPosition = draftPosition;
	}

	public String getHelmet() {
		return helmet;
	}

	public void setHelmet(String helmet) {
		this.helmet = helmet;
	}
	
	
	
    public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(float totalPoints) {
		this.totalPoints = totalPoints;
	}

	/**
	 * @return the league
	 */
	public Account getAccount() {
		return this.account;
	}

	/**
	 * @param league the league to set
	 */
	public void setAccount(Account account) {
		this.account= account;
	}

    /**
	 * @return the league
	 */
	public League getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(League league) {
		this.league = league;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        return id != null && id.equals(((Team) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }

}
