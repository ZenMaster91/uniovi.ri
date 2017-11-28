package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchConnection;
import file.FileToLines;

/**
 * Hello world!
 *
 */
public class Main {

	public static HashMap<String, Long> allWords;
	private static ElasticSearch ES = new ElasticSearchConnection(
			"elasticsearch", 9300);

	public static void main(String[] args) throws Exception {

		System.out.println("WELCOME TO RELATED TERMS SEARCH ENGINE!!!");
		ES.connect();
		System.out.print("Desea indexar la colección? (SI/NO): ");
		String ans = readWord();
		if (ans.equals("SI")) {

			System.out.println("indexing...");

			ES.indexDocument();
			
			System.out.println("done...");

		}
		System.out.println("> Indexado saltado");
		System.out.println(
				"Please, enter a word to look for related terms in the collection.");

		// allWords = new ContractImplementation().allWordsAndHits();

		System.out.print("Enter a word: ");
		String searchedWord = readWord();
		System.out.println("Word entered: " + searchedWord);
		System.out.println("Searching...");
		Set<Entry<String, Double>> relatedTerms = new ComputeWord(searchedWord)
				.compute().relatedTerms().entrySet();
		List<Entry<String, Double>> listOfRelatedTerms = new ArrayList<Entry<String, Double>>(
				relatedTerms);

		Collections.sort(listOfRelatedTerms, new TermsComparator());

		for (Entry<String, Double> relatedWord : listOfRelatedTerms) {
			// Not sure if it sorts ascending or descending...
			System.out.println(
					relatedWord.getKey() + " GND: " + relatedWord.getValue());
		}

		System.out.println("All finished...");
	}

	private static String readWord() {
		Scanner scanner = new Scanner(System.in);
		String word = scanner.next();
		scanner.close();
		return word;
	}

}