package deckbuild.models;

public class Card {

	private int id, summonCost, attack, defense, value;
	private String name;
	
	public Card(int id, int summonCost, int attack, int defense, int value, String name) {
		super();
		this.id = id;
		this.summonCost = summonCost;
		this.attack = attack;
		this.defense = defense;
		this.value = value;
		this.name = name;
	}

	public Card() {
		super();
	}

	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
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
		return "Card [id=" + id + ", summonCost=" + summonCost + ", attack=" + attack + ", defense=" + defense
				+ ", value=" + value + ", name=" + name + "]";
	}
}
