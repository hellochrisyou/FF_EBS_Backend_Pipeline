package com.fantasy.football.domain.model;

import java.util.Objects;

public class Dto {
	private String acctName = "";
	private String acctPass = "";
	private String leagueName = "";
	private String myTmName = "";
	private String myTmHelmet = "";
	private String secTmName = "";
	private String plyr1Name = "";
	private String plyr1Pos = "";
	private String plyr2Name = "";
	private String plyr2Pos = "";
	private String token = "";

	public Dto() {
	}

	public Dto(final String acctName, final String acctPass, final String leagueName, final String myTmName, final String myTmHelemt, final String secTmName,
			final String plyr1Name, final String plyr1Pos, final String plyr2Name, final String plyr2Pos, final String token) {
		this.acctName = acctName;
		this.acctPass = acctPass;
		this.leagueName = leagueName;
		this.myTmName = myTmName;
		this.myTmHelmet = myTmHelemt;
		this.secTmName = secTmName;
		this.plyr1Name = plyr1Name;
		this.plyr1Pos = plyr1Pos;
		this.plyr2Name = plyr2Name;
		this.plyr2Pos = plyr2Pos;
		this.token = token;
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

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(final String localLeagueName) {
		this.leagueName = localLeagueName;
	}

	public String getMyTmName() {
		return myTmName;
	}

	public void setMyTmName(final String localMyTmName) {
		this.myTmName = localMyTmName;
	}

	public String getMyTmHelmet() {
		return myTmHelmet;
	}

	public void setMyTmHelmet(final String localMyTmHelmet) {
		this.myTmHelmet = localMyTmHelmet;
	}

	public String getSecTmName() {
		return secTmName;
	}

	public void setSecTmName(final String localSecTmName) {
		this.secTmName = localSecTmName;
	}

	public String getPlyr1Name() {
		return plyr1Name;
	}

	public void setPlyr1Name(final String localPlyr1Name) {
		this.plyr1Name = localPlyr1Name;
	}

	public String getPlyr1Pos() {
		return plyr1Pos;
	}

	public void setPlyr1Pos(final String localPlyr1Pos) {
		this.plyr1Pos = localPlyr1Pos;
	}

	public String getPlyr2Name() {
		return plyr2Name;
	}

	public void setPlyr2Name(final String localPlyr2Name) {
		this.plyr2Name = localPlyr2Name;
	}

	public String getPlyr2Pos() {
		return plyr2Pos;
	}

	public void setPlyr2Pos(final String localPlyr2Pos) {
		this.plyr2Pos = localPlyr2Pos;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String localToken) {
		this.token = localToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acctName, acctPass, leagueName, myTmHelmet, myTmName, plyr1Name, plyr1Pos, plyr2Name,
				plyr2Pos, secTmName, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Dto)) {
			return false;
		}
		Dto other = (Dto) obj;
		return Objects.equals(acctName, other.acctName) && Objects.equals(acctPass, other.acctPass)
				&& Objects.equals(leagueName, other.leagueName) && Objects.equals(myTmHelmet, other.myTmHelmet)
				&& Objects.equals(myTmName, other.myTmName) && Objects.equals(plyr1Name, other.plyr1Name)
				&& Objects.equals(plyr1Pos, other.plyr1Pos) && Objects.equals(plyr2Name, other.plyr2Name)
				&& Objects.equals(plyr2Pos, other.plyr2Pos) && Objects.equals(secTmName, other.secTmName)
				&& Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "Dto [acctName=" + acctName + ", acctPass=" + acctPass + ", leagueName=" + leagueName + ", myTmName="
				+ myTmName + ", myTmHelemt=" + myTmHelmet + ", secTmName=" + secTmName + ", plyr1Name=" + plyr1Name
				+ ", plyr1Pos=" + plyr1Pos + ", plyr2Name=" + plyr2Name + ", plyr2Pos=" + plyr2Pos + ", token=" + token
				+ "]";
	}


}
