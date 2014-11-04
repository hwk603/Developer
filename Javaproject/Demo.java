import java.util.Scanner;	
	public class Demo{
		public static void main(String[] args){
			int Sum;
			int getinput;
			Sum = input();
			getinput  = getresult(Sum);
			showresult(Sum,getinput);
		}
		private static int input(){
			int sum = 0;
			Scanner sc  = new Scanner(System.in);
			//sum  = sc.nextInt();
			boolean validsum = false;
			while(!validsum){
				System.out.println("Please input a integer(0-1000)");
				sum = sc.nextInt();
				if(sum >= 0&&sum <= 1000)
					validsum = true;
				else
					System.out.println("Input error!!!");
			}
			return sum;
		}
		private static int getresult(int Sum){
			int temp;
			int result = 0;
			temp = Sum;
		    while(temp > 0){
				result += temp % 10;
				temp = temp/10;
			}
			return result;
		}
		private static void showresult(int sum,int result){
			System.out.println("sum"+"="+sum);
			System.out.println("result"+"="+result);
		}
	}