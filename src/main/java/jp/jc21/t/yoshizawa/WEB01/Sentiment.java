package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Setiment message = getSentiment("I bought a size S and it fit perfectly. I found the zipper a little bit difficult to get up & down due to the side rushing. The color and material are beautiful in person. Amazingly comfortable!","en");
		if (message != null) {
			System.out.println(message.documents[0].confidenceScores.positive);
			System.out.println(message.documents[0].confidenceScores.neutral);
			System.out.println(message.documents[0].confidenceScores.negative);
		}
	}

	static Setiment getSentiment(String s,String l) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3a08-text.cognitiveservices.azure.com/text/analytics/v3.1/sentiment?stringIndexType=TextElement_v8";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "efc0d6ca5e2f4dcea7f55826109d39d1");

		Setiment_Docs doc = new Setiment_Docs();
		doc.id = "1";
		doc.language = l;
		doc.text = s;

		Setiment_Source src = new Setiment_Source();
		src.documents = new Setiment_Docs[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		Setiment message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Setiment.class);
			reader.close();
		}
		return message;
	}


}
class Setiment {
	Setiment_Dcuments[] documents;
	String[] errors;
	String modelVersion;
}

class Setiment_Dcuments {
	confidenceScores confidenceScores;
	String id;
	String setiment;
	Object sentences;
	String[] warinings;
}

class confidenceScores {
	String positive;
	String neutral;
	String negative;
}

class Setiment_Source {
	Setiment_Docs[] documents;
}

class Setiment_Docs {
	String id;
	String text;
	String language;
}
