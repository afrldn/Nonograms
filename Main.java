import java.util.Scanner;
public class Main
{
	public static void main(String[] args)

	{
		int n=0; //original number
		int m=100000;//limit
		int i, ans;

		Scanner scan =new Scanner(System.in);

		n=scan.nextInt();
		i=n;
		while(i<=m)
		{
			if (isPrime(i)==true)
			{
				if(isPalen(i).equals(Integer.toString(i)))
				{

					System.out.println(i);
					break;

				}

			}
			i++;
		}
	}

 public static  String isPalen(int name)
 {
	 String test=Integer.toString(name);
	 int ll=test.length()-1;
	 String s2="";

	  for(int mm=0;mm<=ll;mm++)
		{
		s2+=test.charAt(ll-mm);
		}

		return(s2);

}

 public static boolean isPrime(int number)
 {
        for(int i=2; i<number; i++)

        {
           if(number%i == 0)
           {
               return false;
           }
        }
        return true;
    }
}
