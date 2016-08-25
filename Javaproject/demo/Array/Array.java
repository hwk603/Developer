import java.util.Scanner;
	public class Array{
		public static void main(String[] args){
			double[] array=new double[10];
			boolean isvalid=false;
			double a=0;
			int b=0;
			Scanner input=new Scanner(System.in);
			System.out.println("Please input 10 numbers: ");
			for(int i=0;i<array.length;i++){
				
				a=input.nextDouble();
				for(int j=0;j<b;j++){
					if(array[j]==a){
						isvalid=true;
						break;
					}
				}
				if(isvalid)
					continue;
				array[b++]=a;
			}
			for(int i=0;i<b;i++){
				System.out.println("\t"+array[i]);
			}
		}
	}