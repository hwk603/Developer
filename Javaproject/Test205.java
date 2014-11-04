import java.util.Scanner;
 
class StudentInfo {
    private String stuno;
    private int score;
 
    public String getStuno() {
        return stuno;
    }
 
    public void setStuno(String stuno) {
        this.stuno = stuno;
    }
 
    public int getScore() {
        return score;
    }
 
    public void setScore(int score) {
        this.score = score;
    }
 
}
 
public class Test205 {
 
    public static void main(String[] args) {
 
        StudentInfo[] stu = new StudentInfo[20];
        Scanner scan = new Scanner(System.in);
        int nCount = 0;
        System.out.println("请输入输入的学生个数：");
        nCount = scan.nextInt();
        System.out.println("请输入输入的学生学号和分数(以空格隔开)：");
 
        int nCurrent = 0;
        while (nCurrent < nCount) {
            Scanner sca = new Scanner(System.in);
            String strLine = sca.nextLine();
 
            String[] strLineArr = strLine.split(" ");
            StudentInfo st = new StudentInfo();
            st.setStuno(strLineArr[0]);
            st.setScore(Integer.parseInt(strLineArr[1]));
            stu[nCurrent] = st;
            nCurrent++;
        }
 
        // 排序
        for (int i = 0; i < nCount; i++) {
            for (int j = 0; j < nCount - i - 1; j++) {
                if (stu[j].getScore() > stu[j + 1].getScore()) {
                    StudentInfo tmp = new StudentInfo();
                    tmp.setStuno(stu[j].getStuno());
                    tmp.setScore(stu[j].getScore());
 
                    stu[j].setStuno(stu[j + 1].getStuno());
                    stu[j].setScore(stu[j + 1].getScore());
 
                    stu[j + 1].setStuno(tmp.getStuno());
                    stu[j + 1].setScore(tmp.getScore());
                }
            }
        }
        // 输出学生信息
        System.out.println("学号    分数");
 
        for (int i = 0; i < nCount; i++) {
            System.out.println(stu[i].getStuno() + "   " + stu[i].getScore());
        }
 
    }
 
}