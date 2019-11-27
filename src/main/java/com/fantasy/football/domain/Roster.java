package com.fantasy.football.domain;

import java.util.List;
import java.util.Objects;
import com.fantasy.football.domain.entity.Player;

public class Roster {
	private int qb = 0;
	private int rb = 0;
	private int wr = 0;
	private int te = 0;
	private int flex = 0;
	private int def = 0;
	private int k = 0;


	public Roster(final List<Player> localPlrs) {
		for (Player tmpPlyr: localPlrs) {
			switch (tmpPlyr.getPos()) {
			case "QB":
				this.qb++;
				break;
			case "RB":
				this.rb++;
				break;
			case "WR":
				this.wr++;
				break;
			case "TE":
				this.te++;
				break;
			case "FLEX":
				this.flex++;
				break;
			case "DEF":
				this.def++;
				break;
			case "K":
				this.k++;
				break;
			default:
				break;
			}
		}
	}

	public boolean checkPosition(final String localPos) {
		switch (localPos) {
		case "QB":
			return this.checkQb();			
		case "RB":
			return this.checkRb();
		case "WR":
			return this.checkWr();
		case "TE":
			return this.checkTe();
		case "FLEX":
			return this.checkFlex();
		case "DEF":
			return this.checkDef();
		case "K":
			return this.checkKicker();
		default:
			return false;
		}
	}
	
	public boolean checkQb() {
		if (this.qb < 1) {
			return true;
		} else {
		return false;
		}
	}

	public boolean checkRb() {
		if (this.rb < 2) {
			return true;
		} else {
			return false;
			}
	}

	public boolean checkWr() {
		if (this.wr < 3) {
			return true;
		} else {
			return false;
			}
	}

	public boolean checkTe() {
		if (this.te < 1) {
			return true;
		} else {
			return false;
			}
	}

	public boolean checkFlex() {
		if (this.flex < 1) {
			return true;
		} else {
			return false;
			}
	}
	
	public boolean checkDef() {
		if (this.def < 1) {
			return true;
		} else {
			return false;
			}
	}

	public boolean checkKicker() {
		if (this.k < 1)	{
			return true;
		} else {
			return false;
			}
	}

	public int getQb() {
		return qb;
	}

	public void setQb(final int qb) {
		this.qb = qb;
	}

	public int getRb() {
		return rb;
	}

	public void setRb(final int rb) {
		this.rb = rb;
	}

	public int getWr() {
		return wr;
	}

	public void setWr(final int wr) {
		this.wr = wr;
	}

	public int getTe() {
		return te;
	}

	public void setTe(final int te) {
		this.te = te;
	}

	public int getDef() {
		return def;
	}

	public void setDef(final int def) {
		this.def = def;
	}
final 
	public int getK() {
		return k;
	}

	public void setK(final int k) {
		this.k = k;
	}

	@Override
	public int hashCode() {
		return Objects.hash(def, k, qb, rb, te, wr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Roster)) {
			return false;
		}
		Roster other = (Roster) obj;
		return def == other.def && k == other.k && qb == other.qb && rb == other.rb && te == other.te && wr == other.wr;
	}

}
