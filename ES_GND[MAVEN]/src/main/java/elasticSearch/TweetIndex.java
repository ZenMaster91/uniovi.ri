package elasticSearch;

import java.io.IOException;
import java.util.HashMap;

import org.elasticsearch.client.Client;
import org.elasticsearch.action.index.IndexResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TweetIndex {

	private Client client;
	private IndexResponse response;

	public TweetIndex( ElasticSearchConnection connection ) {
		this.client = connection.getConnection();
	}

	@SuppressWarnings("unchecked")
	public void createIndex( String JSONDocument ) {

		HashMap<String, Object> jsonAsMap = null;

		try {
			jsonAsMap = new ObjectMapper().readValue( JSONDocument, HashMap.class );
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.response = client
				.prepareIndex( "2008-Feb-02-04-EN",
								"tweet",
								( (HashMap<String, Object>) jsonAsMap.get( "_source" ) )
								.get( "id_str" )
								.toString() )
				.setSource( ( (HashMap<String, Object>) jsonAsMap.get( "_source" ) ) )
				.get();
	}

}
