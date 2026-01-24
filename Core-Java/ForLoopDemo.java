class ForLoopDemo 
{
	public static void main(String[] args) 
	{
		for(int i=1;i<=5;i++)
		{
			if(i==3)
				break;
			
			System.out.println("Main aa gya hu... "+i);
		}

		int t=2;
		for(int i=1;i<=10;i++)
		{
			System.out.println(t+"X"+i+" = "+(t*i));
		}
	}
}
