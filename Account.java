import java.util.Date;
	public class Account{
		private int id;
		private double balance;
		private double annualInterestRate;
		private Date dateCreated = new Date();
		public Account(){
			this.id = 0;
			this.balance = 0;
			this.annualInterestRate = 0;
		}
		public Account(int id,double balance){
			this.id = id;
			this.balance = balance;
		}
		public void setId(int id){
			this.id = id;
		}
		public void setBalance(double balance){
			this.balance = balance;
		}
		public void setAnnualInterestRate(double annualInterestRate){
			this.annualInterestRate = annualInterestRate;
		}
		public Date getDateCreated(){
			return this.dateCreated;
		}
		public int getId(){
			return this.id;
		}
		public double getBalance(){
			return balance;
		}
		public double getAnnualInterestRate(){
			return this.annualInterestRate;
		}
		public double getMonthlyInterestRate(){
			return this.annualInterestRate/12;
		}
		public double withDraw(double money){
			balance = balance - money;
			return this.balance;
		}
		public double deposit(double money){
			balance = balance + money;
			return this.balance;
		}
		public static void main(String[] args){
			Account account  = new Account(1122,20000);
			account.setAnnualInterestRate(0.045);
			account.withDraw(2500);
			account.deposit(3000);
			System.out.println("\t"+"balance: "+account.getBalance()+"\n"+"\t"+"MonthlyInterest: "+account.getMonthlyInterestRate()*account.getBalance()+"\n"+"\t"+"Date: "+account.getDateCreated());
		}
	}