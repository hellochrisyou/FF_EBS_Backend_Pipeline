package com.fantasy.football.domain.model;

import java.util.Objects;

import com.fantasy.football.domain.entity.Player;

public class Dto {
	String myLeagueName = "leagueName";
	String myAccountName = "accountName";
	String myTeamName = "teamName";
	String myTeamHelmet = "red";
	String otherTeamName = "otherTeamName";
	Player player1 = new Player("player1Name");
	Player player2 = new Player("player2Name");
	String password = "Password";
	String token = null;

	
	public Dto() {}
	
	public Dto(Dto dto) {
		super();
		this.myLeagueName = dto.myLeagueName;
		this.myAccountName = dto.myAccountName;
		this.myTeamName = dto.myTeamName;
		this.myTeamHelmet = dto.myTeamHelmet;
		this.otherTeamName = dto.otherTeamName;
		this.player1 =dto. player1;
		this.player2 = dto.player2;
		this.password = dto.password;
		this.token = dto.token;
	}

	public String getMyLeagueName() {
		return myLeagueName;
	}

	public void setMyLeagueName(String myLeagueName) {
		this.myLeagueName = myLeagueName;
	}

	public String getMyAccountName() {
		return myAccountName;
	}

	public void setMyAccountName(String myAccountName) {
		this.myAccountName = myAccountName;
	}

	public String getMyTeamName() {
		return myTeamName;
	}

	public void setMyTeamName(String myTeamName) {
		this.myTeamName = myTeamName;
	}

	public String getOtherTeamName() {
		return otherTeamName;
	}

	public void setOtherTeamName(String otherTeamName) {
		this.otherTeamName = otherTeamName;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public String getMyTeamHelmet() {
		return myTeamHelmet;
	}

	public void setMyTeamHelmet(String myTeamHelmet) {
		this.myTeamHelmet = myTeamHelmet;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(myAccountName, myLeagueName, myTeamHelmet, myTeamName, otherTeamName, password, player1,
				player2, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Dto))
			return false;
		Dto other = (Dto) obj;
		return Objects.equals(myAccountName, other.myAccountName) && Objects.equals(myLeagueName, other.myLeagueName)
				&& Objects.equals(myTeamHelmet, other.myTeamHelmet) && Objects.equals(myTeamName, other.myTeamName)
				&& Objects.equals(otherTeamName, other.otherTeamName) && Objects.equals(password, other.password)
				&& Objects.equals(player1, other.player1) && Objects.equals(player2, other.player2)
				&& Objects.equals(token, other.token);
	}
}
