package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(unique = true, nullable = false)
	protected String tmName = "";
	@Column(unique = false, nullable = false)
	protected int draftNum= 0;
	@Column(unique = false, nullable = false)
	protected String helmet = "";
	@Column(unique = false, nullable = false)
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
    	private List<Player> players = new ArrayList<>();
	
	// Constructors
	public Team() {
	}

	public Team(String localTmName) {
		this.tmName = localTmName;
	}

	public Team(final List<Player> localPlayers) {
		for (Player player : localPlayers) {
			Player tmpPlayer = new Player(player);
			this.players.add(tmpPlayer);
		}
	}

	public Team(final Team localTm) {
		this.setTmName(localTm.getTmName());		
		this.setDraftNum(localTm.getDraftNum());
		this.setHelmet(this.getHelmet());
		this.setTotalPoints(localTm.getTotalPoints());
		for (Player tmpPlayer : localTm.getPlayers()) {
			this.players.add(tmpPlayer);
		}
	}	

	// Relationship getters and setters
	public void setPlayers(final List<Player> localPlyrs) {
		this.players = localPlyrs;
	}

	public List<Player> getPlayers() {
		if (this.players == null) {
			this.players = new ArrayList<>();
		}
		return this.players;
	}
	public Player getPlayer(final String localPlyrName) {
		for (Player player: this.players) {
			if (player.getPlyrName().equals(localPlyrName)) {
				return player;
			}
		}
		return null;
	}
	
	public void addPlayer(Player localPlyr) {
        players.add(localPlyr);
        localPlyr.getTeams().add(this);
    }
 
    public void removePlayer(Player localPlyr) {
        players.remove(localPlyr);
        localPlyr.getTeams().remove(this);
    }
 
	// Basic getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long localId) {
		this.id = localId;
	}

	

	public String getTmName() {
		return tmName;
	}

	public void setTmName(final String tmName) {
		this.tmName = tmName;
	}

	public int getDraftNum() {
		return draftNum;
	}

	public void setDraftNum(final int localDraftNum) {
		this.draftNum = localDraftNum;
	}

	public String getHelmet() {
		return helmet;
	}

	public void setHelmet(final String helmet) {
		this.helmet = helmet;
	}
		
    public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(final float localTotalPoints) {
		this.totalPoints = localTotalPoints;
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
	public void setAccount(final Account localAcct) {
		this.account= localAcct;
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
	public void setLeague(final League localLeague) {
		this.league = localLeague;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(account, draftNum, helmet, id, league, players, tmName, totalPoints);
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
		if (!(obj instanceof Team)) {
			return false;
		}
		Team other = (Team) obj;
		return Objects.equals(account, other.account) && draftNum == other.draftNum
				&& Objects.equals(helmet, other.helmet) && Objects.equals(id, other.id)
				&& Objects.equals(league, other.league) && Objects.equals(players, other.players)
				&& Objects.equals(tmName, other.tmName)
				&& Float.floatToIntBits(totalPoints) == Float.floatToIntBits(other.totalPoints);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", tmName=" + tmName + ", draftNum=" + draftNum + ", helmet=" + helmet
				+ ", totalPoints=" + totalPoints + ", league=" + league + ", account=" + account + ", players="
				+ players + "]";
	}



}
