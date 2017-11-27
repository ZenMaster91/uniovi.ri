package core;

import java.util.HashMap;

import google.NormalizedGoogleDistance;

public class ComputeWord {
	
	private HashMap<String, Double> finalWords;
	private String searchedWord;
	
	public ComputeWord(String searchedWord) {
		this.searchedWord = searchedWord;
	}
	
	public HashMap<String, Double> relatedTerms() {
		return this.finalWords;
	}
	
	public ComputeWord compute() {
		HashMap<String, Long> words = Main.allWords;
		
		words.forEach( 
				(k, v) -> finalWords.put( k, 
						new NormalizedGoogleDistance( 
								words.get( searchedWord ),
								v
								/*, new Contract().allWordsUnionSearchedWordAndHits( searchedword ).get( k )*/
								)
						.execute()
						.gnd()) );
		return this;
	}

}
