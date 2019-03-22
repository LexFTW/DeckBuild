package deck_build.models;

public class Card {

	private int cardId, summonCost, attack, defense, value;
	private String name;
	
	public Card(int cardId, int summonCost, int attack, int defense, int value, String name) {
		super();
		this.cardId = cardId;
		this.summonCost = summonCost;
		this.attack = attack;
		this.defense = defense;
		this.value = value;
		this.name = name;
	}

	public Card() {
		super();
	}

	public int getCardId() {
		return cardId;
	}

	public int getSummonCost() {
		return summonCost;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public void setSummonCost(int summonCost) {
		this.summonCost = summonCost;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + " | At: " + this.attack + " | Df: " + this.defense + " | Summon Cost: " + this.summonCost;
	}
	
}
