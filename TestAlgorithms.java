public class TestAlgorithms
{
	public static void main(String[] args)
	{
		// Test the SoundEx algorithm
		String word = "elephant";
		// Instanciating the SoundEx class passing the test word as an argument
		SoundEx sx = new SoundEx(word);
		System.out.println("SoundEx");
		// Printing out the test word using the SoundEx encoding algorithm
		System.out.println(word+" = "+sx.encode());
		System.out.println("--------------------");
		System.out.println();


		// Test the Levenshtein algorithm
		// Instanciating the Levenshtein class
		Levenshtein ld = new Levenshtein("Peninsular", "Caravan");
		// Calling the getDistance method in the levenshtein class
		ld.getDistance();

	}
}
