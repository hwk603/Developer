Git is a version control system.
Git is free software.
Hwk is handsome.
import javax.swing.JFrame;
public class JFrameDemo extends JFrame{
	public JFrameDemo(){
		super("JFrame");
		setBounds(50,50,300,120);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	    }
	public static void main(String[] args){
		JFrameDemo f = new JFrameDemo();
		}
	}
