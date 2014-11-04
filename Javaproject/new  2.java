public class Fan {
	final static int SLOW = 1;
	final static int MEDIUM = 2;
	final static int FAST = 3;
	private int speed = SLOW;
	private boolean on = false;
	private double radius = 5;
	private String color = "blue";
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Fan()
	{
		
	}
	public String toString()
	{
		if(on==true)
			return "风扇速度："+this.getSpeed()+"\n"
			+"风扇半径："+this.getRadius()+"\n"
			+"风扇颜色："+this.getColor()+"\n";
		else
			return "fan is off"+"\n"+"风扇半径："+this.getRadius()+"\n"
			+"风扇颜色："+this.getColor()+"\n";
	}
	public static void main(String[] args)
	{
		Fan fan = new Fan();
		fan.setColor("yello");
		fan.setRadius(10.0);
		fan.setSpeed(FAST);
		fan.setOn(true);
		Fan fan1 = new Fan();
		fan1.setColor("bule");
		fan1.setOn(false);
		fan1.setRadius(5.0);
		System.out.println(fan.toString());
		System.out.println(fan1.toString());
	}
}