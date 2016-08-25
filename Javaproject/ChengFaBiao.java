public class ChengFaBiao{
	public static void main(String[] agrs){
		System.out.print("* |");
		int i;
		int j;
		for(i=1;i<=9;i++){
			if(i==1)
				System.out.print(" "+i);
			else
				System.out.print("  "+i);
			}
		System.out.println();
		System.out.println("__|____________________________");
		for(i=1;i<=9;i++){
			System.out.print(i+" |");
			for(j=1;j<=i;j++){
				if(i*j<10)
					System.out.print(" "+i*j+" ");
				else
					System.out.print(i*j+" ");
				}
			System.out.println();
			}
		}
	}
				
		
			
		