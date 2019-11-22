package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fantasy.football.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Player")
@Table(name = "player")
public class Player extends AuditModel implements Serializable {
	
	private static final long serialVersionUID = -8075696392068480911L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String playerName = "default";
	protected String position = "default";
	protected boolean active = false;
	protected boolean flex = false;
	protected float fantasyPoints = 0;	
	
	@ManyToMany(mappedBy = "players")
	@JsonIgnore
    private List<Team> teams = new ArrayList<>();
	
	public Player() {}
	
	public Player(String playerName) {
		this.setPlayerName(playerName);
	}
	
	public Player(Player player) {
		this.setPlayerName(player.getPlayerName());
		this.setPosition(player.getPosition());
		this.setFantasyPoints(player.getFantasyPoints());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void toggleActive() {
		this.active = !this.active;
	}
	public float getFantasyPoints() {
		return fantasyPoints;
	}

	public void setFantasyPoints(float fantasyPoints) {
		this.fantasyPoints = fantasyPoints;
	}

	public boolean isFlex() {
		return flex;
	}
	public void setFlex(boolean flex) {
		this.flex = flex;
	}
	

    /**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team team) {
        teams.add(team);
        team.getPlayers().add(this);
    }
 
    public void removeTeam(Team team) {
        teams.remove(team);
        team.getPlayers().remove(this);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(fantasyPoints);
		result = prime * result + (flex ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (active != other.active)
			return false;
		if (Float.floatToIntBits(fantasyPoints) != Float.floatToIntBits(other.fantasyPoints))
			return false;
		if (flex != other.flex)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName)) {
			return false;
		}
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position)) {
			return false;
		}
		return true;
	}
	
	
}
