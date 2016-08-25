	public class Space{
		public static void main(String[] args){
			int i = 0,j = 0,m = 0,n = 1;
			double[][]points = {{-1,0,3},{-1,-1,-1},{4,1,1},{2,0.5,9},{3.5,2,-1},{3,1.5,3},{-1,4,2},{5,4,-0.5}};
			for(i = 0;i <= 7;i++)
				for(j = i + 1;j <= 7;j++){
					if(distance(i,j) < distance(m,n)){
						m = i;
						n = j;
					}
				}
			for(i = 0;i <= 7;i++)
				for(j = i + 1;j <= 7;j++)
					if(distance(m,n) == distance(i,j)&&(m != i||n != j))
				System.out.println("The shortest disatance point is"+"{"+points[i][0]+","+points[i][1]+","+points[i][2]+" "+"to"+" "+"{"+points[j][0]+","+points[j][1]+","+points[j][2]+"}"+"or"+"{"+points[m][0]+","+points[m][1]+","+points[m][2]+"}"+" "+"to"+" "+"{"+points[n][0]+","+points[n][1]+","+points[n][2]+"}");
						
		}
		public static double distance(int p,int q){
			double[][]points = {{-1,0,3},{-1,-1,-1},{4,1,1},{2,0.5,9},{3.5,2,-1},{3,1.5,3},{-1,4,2},{5,4,-0.5}};
			return Math.sqrt(Math.pow((points[p][0]- points[q][0]),2)+Math.pow((points[p][1] - points[q][1]),2)+Math.pow((points[p][2] - points[q][2]),2));
		}
			
			
	}				