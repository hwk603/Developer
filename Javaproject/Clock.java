import java.util.*;
import java.io.IOException;

public class Clock{
	public static void main(String[] args) throws InterruptedException,IOException{
		Scanner a = new Scanner(System.in);
		System.out.print("Enter the number of second: ");
		int time = a.nextInt();
		for(int i=time-1;i>0;i--){
			//i--;
			System.out.println(i+" seconds remaining");
			Thread.sleep(1000);
			}
		}
	}
			