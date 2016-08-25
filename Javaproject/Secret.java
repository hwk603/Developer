import java.util.*;
	public class Secret{
		public static void main(String[] args){
			Scanner input = new Scanner(System.in);
			System.out.println("Please input the secret : ");
			String sc = input.next();
			if(isValid(sc))
				System.out.println("The secret is valid.");
			else
				System.out.println("The secret is not valid.");
		}
		private static boolean isValid(String sc){
			if(isLength(sc)&&isChar(sc)&&isDigit(sc))
				return true;
			else
				return false;
		}
		private static boolean isLength(String sc){
			if(sc.length()>=8)
				return true;
			else 
				return false;
		}
		private static boolean isChar(String sc){
			boolean is = true;
			for(int i = 0;i < sc.length();i++){
				if(!Character.isLetterOrDigit(sc.charAt(i)))
					is = false;
					break;
			}
			return is;
		}
		private static boolean isDigit(String sc){
			int count = 0;
			for(int i = 0;i < sc.length();i++){
				if(Character.isDigit(sc.charAt(i)))
					count++;
			}
			if(count >= 2)
				return true;
			else 
				return false;
		}
	}