class AirthmeticOperator 
{
	public static void main(String[] args) 
	{
		int a=11;
		int b=2;

		System.out.println("a+b = "+(a+b));
		System.out.println("a-b = "+(a-b));
		System.out.println("a*b = "+(a*b));
		System.out.println("a/b = "+(a/b));
		System.out.println("a%b = "+(a%b));

		//++ -- 
		//precre postinc predec  postdec
		int c=10;
		System.out.println(--c); //9
		System.out.println(c--); //9 c=8
		
		System.out.println(++c); //9
		System.out.println(c++); //9 c=10

	}
}
