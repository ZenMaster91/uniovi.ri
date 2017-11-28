package elasticSearch;

import java.io.IOException;
import java.util.HashMap;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchConnection extends ElasticSearch {

	private Client client;
	private IndexResponse response;

	public ElasticSearchConnection( String clusterName, int port ) {
		super( clusterName, port );
	}

	public Client getConnection() {
		return this.client;
	}

	public void connect() {

		Node node = NodeBuilder.nodeBuilder().clusterName( super.clusterName() )
				.client( true ).node();

		this.client = node.client();

	}

	public void executeQuery( String query ) {
	}

	@SuppressWarnings("unchecked")
	public void indexDocument( String JSONDocument ) {
		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue( JSONDocument, HashMap.class );
		} catch (IOException e) {
			System.err.println( "Error while parsing the json document" );
			e.printStackTrace();
		}

		this.response = client.prepareIndex( "2008-Feb-02-04-EN", "tweet", ((HashMap<String, Object>)jsonAsMap.get( "_source" )).get( "id_str" ).toString() )
				.setSource( ((HashMap<String, Object>)jsonAsMap.get( "_source" )) ).get();
	}
}
