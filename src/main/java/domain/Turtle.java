package Domain;

public class Turtle {
	
	private int id;
	private String name;
	private int speed;
	private int stamina;
	private int luck;
	
	
	public Turtle(String name, int speed, int stamina, int luck) {
		this.name = name;
		setSpeed(speed);
		setStamina(stamina);
		setLuck(luck);
	}
	
	public Turtle(int id, String name, int speed, int stamina, int luck) {
		this(name, speed, stamina, luck);
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSpeed() {
		
		return speed;
	}


	public void setSpeed(int speed) {
		
		if (speed > 0 && speed <=10) {
			this.speed = speed;			
		} else {
			this.speed = 6;
		}
	}


	public int getStamina() {
		return stamina;
	}


	public void setStamina(int stamina) {
		
		if (stamina > 0 && stamina <= 10 ) {
			this.stamina = stamina;			
		} else {
			this.stamina = 6;
		}
	}


	public int getLuck() {
		return luck;
	}


	public void setLuck(int luck) {
		if (luck > 0 && luck <= 10 ) {
			this.luck = luck;
						
		} else {
			this.luck = 6;
		}
	}


	@Override
	public String toString() {
		return "Turtle [name=" + name + ", speed=" + speed + ", stamina=" + stamina + ", luck=" + luck + "]";
	}
	
	
	

		
	
	
	
	

}

