package com.fantasy.football.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
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
	@Column(unique = true, nullable = false)
	protected Long id;
	@Column(unique = true, nullable = false)
	protected String plyrName = "";
	@Column(unique = false, nullable = false)
	protected String pos = "";
	@Column(unique = false, nullable = false)
	protected boolean isActive = false;
	@Column(unique = false, nullable = false)
	protected boolean isFlex = false;
	@Column(unique = false, nullable = false)
	protected float points = 0;

	@ManyToMany(mappedBy = "players")
	@JsonIgnore
	private List<Team> teams = new ArrayList<>();

	public Player() {
	}

	public Player(final String localPlyrName) {
		this.setPlyrName(localPlyrName);
	}

	public Player(final Player localPlyr) {
		this.setPlyrName(localPlyr.getPlyrName());
		this.setPos(localPlyr.getPos());
		this.setPoints(localPlyr.getPoints());
	}

	// Basic Getter and Setters
	public Long getId() {
		return id;
	}

	public void setId(final Long localId) {
		this.id = localId;
	}
	public String getPlyrName() {
		return plyrName;
	}

	public void setPlyrName(final String localPlyrName) {
		this.plyrName = localPlyrName;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(final String localPos) {
		this.pos = localPos;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(final boolean localIsActive) {
		this.isActive = localIsActive;
	}

	public boolean isFlex() {
		return isFlex;
	}

	public void setFlex(final boolean localIsFlex) {
		this.isFlex = localIsFlex;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(final float localPoints) {
		this.points = localPoints;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(final List<Team> localTms) {
		this.teams = localTms;
	}

	public void removeTeam(Team localTm) {
		teams.remove(localTm);
		localTm.getPlayers().remove(this);
	}

//	Relationship getters and setters
	public void addTeam(Team localTm) {
		teams.add(localTm);
		localTm.getPlayers().add(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, isActive, isFlex, plyrName, points, pos, teams);
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
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(id, other.id) && isActive == other.isActive && isFlex == other.isFlex
				&& Objects.equals(plyrName, other.plyrName)
				&& Float.floatToIntBits(points) == Float.floatToIntBits(other.points) && Objects.equals(pos, other.pos)
				&& Objects.equals(teams, other.teams);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", plyrName=" + plyrName + ", pos=" + pos + ", isActive=" + isActive + ", isFlex="
				+ isFlex + ", points=" + points + ", teams=" + teams + "]";
	}

}
