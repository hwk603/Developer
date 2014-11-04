import java.util.*;
class Stuset{
	private String stuname;
	private int score;
	public String getname(){
		return stuname;
	}
	public int getscore(){
		return score;
	}
	public void setname(String stuname){
		this.stuname = stuname;
	}
	public void setscore(int score){
		this.score = score;
	}
}
public class Stu{
	public static void main(String[] args){
		Scanner input  = new Scanner(System.in);
		Stuset[] stu = new Stuset[20];
		System.out.println("Please input the student's numbers :");
		int  number = input.nextInt();
		int count = 0;
		System.out.println("Please input the student's names and scores :");
		while(count < number){
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			String[] strline = str.split(" ");
			Stuset st = new Stuset();
			st.setname(strline[0]);
			st.setscore(Integer.parseInt(strline[1]));
			stu[count] = st;
			count++;
		}
	    //paixu       
		for(int i = 0;i < number;i++){
			for(int j = 0;j < number - i - 1;j++){
				if(stu[j].getscore() < stu[j + 1].getscore()){
					Stuset tem = new Stuset();
					tem.setname(stu[j].getname());
					tem.setscore(stu[j].getscore());
				
					stu[j].setname(stu[j + 1].getname());
					stu[j].setscore(stu[j + 1].getscore());	
				
					stu[j + 1].setname(tem.getname());
					stu[j + 1].setscore(tem.getscore());
				}
			}
		}
		System.out.println("name	score");
		for(int i = 0;i < number;i++){
				System.out.println(stu[i].getname()+"	"+stu[i].getscore());
		}
	
	}
}