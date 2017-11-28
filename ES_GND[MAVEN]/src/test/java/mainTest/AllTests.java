package mainTest;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchConnection;
import elasticsearch.ElasticSearchSearch;
import file.FileToLines;

public class AllTests {
	
	ElasticSearch es = new ElasticSearchConnection("elasticsearch", 9300);
	
	@Before
	public void setUp() throws Exception {
		es.connect();
	}
	
	@After
	public void drop() throws Exception {
		es.getClient().close();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Test @Ignore
	public void JSONtoMapTest() {
		FileToLines ftl = new FileToLines( "2008-Feb-02-04.json" );

		HashMap<String, Object> jsonAsMap = null;
		HashMap<String, Object> source = null;
		double i = 0;
		for (String str : ftl.lines()) {
			i++;
			if (i == 10001)
				break;
			try {
				jsonAsMap = new ObjectMapper().readValue( str,
						new TypeReference<HashMap<String, Object>>() {} );
			} catch (IOException e) {
				System.err.println( "Error while parsing the json document" );
				e.printStackTrace();
			}

			for (Entry<String, Object> entry : jsonAsMap.entrySet()) {
				System.out.println( entry.getKey() + ": " + entry.getValue() );
			}
			System.err.println( "ID_str " + ((HashMap<String, Object>)jsonAsMap.get( "_source" )).get( "id_str" ));

			System.out.println( "Proceso: " + new DecimalFormat( "#0.0000" )
					.format( ( (double) ( i / 10000.0 ) ) * 100 ) );
		}
	}
	
	@Test
	public void testElasticConection() throws Exception {
		System.out.println(es.getClient());
	}
	
	@Test
	public void indexTest() throws Exception {
		
		System.out.println(es.getClient());
	
		System.out.println("indexing...");
		
		es.indexDocument();
		System.out.println("done...");
		
	}
	
	@Test
	public void searchTest() throws Exception {
		long a = System.currentTimeMillis();
		System.out.println("Hits for match (2672): " + new ElasticSearchSearch(es.getClient()).getHitsOf("the").getHits().totalHits);
		long b = System.currentTimeMillis();
		System.out.println("Time elapsed : " + (b-a));
	}

}
