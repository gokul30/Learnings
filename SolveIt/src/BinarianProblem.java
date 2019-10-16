import java.util.ArrayList;
import java.util.List;

public class BinarianProblem {

	/**
	 * Rakuten
	 * I have an array say
		int[] x = {1,0,0,2,0,2}
		and an equation say
		sumValue = sumValue + Math.pow(2,x[i]).
		for x value of sumValue is 13.
		I want to find possible shortest array which has same sumValue(13) as given array x has for given equation.
	 */
	public static void binarian() {

		double givenBinarian = 0;
		int[] a = new int[] { 1,0,0,2,0,2 };

		for (int i : a) {
			givenBinarian = givenBinarian + Math.pow(2, i);
		}
		System.out.println(givenBinarian);

		int calculatedBinarian = (int) givenBinarian;
		int binaryArray[] = new int[30];
		int i = 0;
		int count = 0 ;
		while (calculatedBinarian > 0) {
			if(calculatedBinarian % 2 > 0) {
				count ++;
			}
			binaryArray[i++] = calculatedBinarian % 2;
			calculatedBinarian = calculatedBinarian / 2;
		}
		System.out.println(count);

		System.out.println("i "+i); 
		//int sum = 0; 
		List<Integer> list = new ArrayList<Integer>();


		do { for (int j = i - 1; j >= 0; j--) { 
			i--; 
			if (binaryArray[j] > 0) {
				list.add(i); 
			} 
			//sum = sum + (binaryArray[j] * (int) Math.pow(2, i));


		} } while (i > 0); 
		System.out.println(list); 
		System.out.println(list.size());
	}
	
	public static void main(String[] args) {
		binarian();
	}
}
