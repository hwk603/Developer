import java.util.Scanner;
	public class Change{
		public static String binaryToHex(String binaryValue){
			return Long.toHexString(Long.parseLong(binaryValue,2));
		}
		public static void main(String[] args){
			System.out.println("input: ");
			Scanner sc = new Scanner(System.in);
			String str = sc.next();
			System.out.println("output: \n"+binaryToHex(str));
		} 
	}
