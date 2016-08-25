import java.util.*; 
	public class Test{ 
		public static void main(String[] args){ 
			Scanner input=new Scanner(System.in); 
			int[] array=new int[10]; 
			int pos=0;
			System.out.println("please input ten numbers: ");
			while(pos<array.length){     
				int temp=input.nextInt(); 
				if(!isExist(temp,array)){ 
					array[pos]=temp; 
					pos++ ; 
				}
				
			
			} 
			display(array); 
		} 
		public static boolean isExist(int temp,int[] array){ 
			for(int i=0;i<array.length;i++) {    
				if(array[i]==temp) 
				return true; 
			} 
			return false; 
		} 
		public static void display(double[] array){ 
			for(int i=0;i<array.length;i++){    
				System.out.println(array[i]); 
			} 
		}
	}