package edu.stevens.ssw555;

public class Individual {

	
	private String id;
	private String name;
	private String sex;
	private String birth;
	private String death;
	private String spouseOf;
	private String childOf;
	
	
	public String getId() {
		return id;
	}
	public String getSpouseOf() {
		return spouseOf;
	}
	public void setSpouseOf(String spouseOf) {
		this.spouseOf = spouseOf;
	}
	public String getChildOf() {
		return childOf;
	}
	public void setChildOf(String childOf) {
		this.childOf = childOf;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	
	public Individual(String id) {
		this.id = id;
		this.name = null;
		this.sex = null;
		this.birth = null;
		this.death = null;
	}
	public Individual() {
		
	}
	
	public String toString() {
		return "individual [id=" + id + ", name=" + name + ", sex=" + sex + ", birth=" + birth + ", death=" + death
				+ "]";
	}
	
	
}