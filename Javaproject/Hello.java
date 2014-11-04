import java.awt.*;
import java.applet.*;
public class Hello extends Applet{
	public void paint(Graphics g){
	g.drawString("You like coffee,",5,34);
	g.drawString("I like java,",15,55);
	g.drawString("You and me enjoy coffee and java.",25,75);
	g.drawString("But doctor says child should not drink coffee.",5,95);
	}
}