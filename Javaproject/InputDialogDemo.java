import javax.swing.JOptionPane;

public class InputDialogDemo{
	public static void main(String args[]){
		String yearString = JOptionPane.showInputDialog(null,"Enter a year","Example 2.1 Input (int)",JOptionPane.QUESTION_MESSAGE);
		int year = Integer.parseInt(yearString);
		boolean isLeapYear = ((year%4==0)&&(year%100!=0))||(year%400==0);
		JOptionPane.showMessageDialog(null,year+"is a leap year?"+isLeapYear,"Example 2.1 Output (int)",JOptionPane.INFORMATION_MESSAGE);
		String doubleString = JOptionPane.showInputDialog(null,"Enter a double value","Example 2.1 Input (double)",JOptionPane.QUESTION_MESSAGE);
		double doubleValue = Double.parseDouble(doubleString);
	    JOptionPane.showMessageDialog(null,doubleValue+"is positive?"+(doubleValue>0),"Example 2.1 Output (double)",JOptionPane.INFORMATION_MESSAGE);
	}
}