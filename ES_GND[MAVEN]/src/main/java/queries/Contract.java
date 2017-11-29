package queries;

import java.util.HashMap;

public interface Contract {
	
	/**
	 * Gets the hits of a word in the collection.
	 * 
	 * @param searchedWord is the searched word.
	 * @return
	 */
	public HashMap<String, Long> allWordsAndHits();
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Long> allWordsUnionSearchedWordAndHits(String searchedWord);

}
