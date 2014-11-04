/**设计一个名为Fan的类来表示一个风扇。这个类包括：
三个名为SLOW、MEDIUM和FAST而值是1、2和3的常量表示风扇的速度。
一个名为speed的int类型私有数据域表示风扇的速度（默认值SLOW）。
一个人名为on的boolean类型私有数据域表示风扇是否打开（默认值为false）。
一个名为radius的double类型私有数据域表示风扇的半径（默认值5）。
一个名为color的string类型数据域表示风扇的颜色（默认值为blue）。
这四个数据域的访问器和修改器。
一个创建默认风扇的无参构造方法。
一个名为toString()方法返回描述风扇的字符串。
如果风扇是打开的，那么该方法在一个组合的字符串中返回风扇的速度、颜色和半径。
如果风扇没有打开，该方法就会返回一个由“fan is off”和风扇颜色及半径组合成的字符串。
画出该类的UML图。实现这个类。
编写一个测试程序，创建两个Fan对象。
将第一个对象设置为最大速度、半径10、颜色为yellow、状态为打开。
将第二个对象设置为中等速度、半径为5、颜色为blue、状态为关闭。
通过调用它们的toSrting方法显示这些对象。
*/
	public class Fan{
		public static void main(String[] args){
			static int SLOW = 1;
			static int MEDIUM = 2;
			static int FAST = 3;
			private String color = 'bule';
			boolean on = false;
			String color = 'blue';}}