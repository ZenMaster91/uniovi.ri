package core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		// Mensaje de bienvenida.
		System.out.println("WELCOME TO RELATED TERMS SEARCH ENGINE!!!");

		// Realiza la conexión ÚNICA a elasticSearch.
		ES.connect();

		// Pregunto si deseo indexar por si ya está indexado.
		System.out.print("Desea indexar la colección? (SI/NO): ");
		String ans = "NO";
		if (ans.equals("SI")) {

			System.out.println("indexing...");

			ES.indexDocument();

			System.out.println("done...");

		}
		System.out.println("> Indexado saltado");

		// Se le pide al usuario la palabra de la cual quiere buscar términos
		// relacionados.
		System.out.println(
				"Please, enter a word to look for related terms in the collection.");

		// allWords = new ContractImplementation().allWordsAndHits();

		System.out.print("Enter a word: ");
		String searchedWord = readWord();
		System.out.println("Word entered: " + searchedWord);
		System.out.println("Searching for related terms...");
		URL url = new URL("http://localhost:9200/tweets/_search?pretty");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("{\n" + "  \"query\": {\n" + "    \"match\": {\n"
				+ "      \"text\": \""+searchedWord+"\"\n" + "    }\n" + "  },\n"
				+ "  \"aggregations\": {\n" + "    \"my_sample\": {\n"
				+ "      \"sampler\": {\n" + "        \"shard_size\": 10000\n"
				+ "      },\n" + "      \"aggregations\": {\n"
				+ "        \"keywords\": {\n"
				+ "          \"significant_text\": {\n"
				+ "            \"field\": \"text\",\n"
				+ "            \"filter_duplicate_text\": false\n"
				+ "          }\n" + "        }\n" + "      }\n" + "    }\n"
				+ "  }\n" + "}");
		wr.flush();
		wr.close();

		BufferedReader iny = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = iny.readLine()) != null) {
			response.append(output);
		}
		iny.close();

		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue(response.toString(),
					new TypeReference<HashMap<String, Object>>() {});
		} catch (IOException e) {
			System.err.println("Error while parsing the json document");
			e.printStackTrace();
		}

		for (int i = 0; i < ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap
				.get("aggregations")).get("my_sample")).get("keywords"))
						.get("buckets")).size(); i++) {
			System.out.println("related word : "
					+ ((LinkedHashMap) ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap
							.get("aggregations")).get("my_sample"))
									.get("keywords")).get("buckets")).get(i))
											.get("key"));
		}

		System.out.println("All finished...");
		ES.disconnect();
	}

	private static String readWord() {
		Scanner scanner = new Scanner(System.in);
		String word = scanner.next();
		scanner.close();
		return word;
	}

}