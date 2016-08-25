import java.util.*;
public class Age{
	public static void main(String[] args){
		Scanner a = new Scanner(System.in);
		System.out.println("请输入年份：");
		int age = a.nextInt();
		System.out.println("请输入月份：");
		int month = a.nextInt();
		int day=0;
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
			day = 31;
		else if(month==4||month==6||month==9||month==11)
			day = 30;
		else if(month==2){
			if((age%4==0&&age%100!=0)||(age%400==0))
			day = 29;
			else
			day = 28;
			}
		System.out.println(age+"年"+month+"月"+"有"+day+"天");
		}
	}
			
		
		