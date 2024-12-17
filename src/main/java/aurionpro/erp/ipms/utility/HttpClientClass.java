package aurionpro.erp.ipms.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;

public class HttpClientClass {
	
	public static String getData(String myUrl, String authToken, String myInput, String method) {
		String outputData = null;
		try {
			//URL url = new URL("http://localhost:8080/RESTfulExample/json/product/post");
			URL url = new URL(myUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			//conn.setRequestMethod("POST");
			conn.setRequestMethod(method.toUpperCase());
			conn.setRequestProperty("Content-Type", "application/json");
			if(authToken!=null)
				conn.setRequestProperty("authToken", authToken);
			
			String input = myInput;
			
			if(input!=null){
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			/*String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/
			outputData = br.readLine();

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputData;
	}
	
	public static String sendNotification(String myUrl, String authToken, String myInput, String method) {
		String outputData = null;
		try {
			URL url = new URL(myUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method.toUpperCase());
			conn.setRequestProperty("Content-Type", "application/json");
			if(authToken!=null)
				conn.setRequestProperty("Authorization", authToken);
			
			String input = myInput;
			
			if(input!=null){
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			outputData = br.readLine();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputData;
	}
	
	public static String sendPoToOb(String myUrl, HashMap<String, String> auth, String myInput, String method) {
		String outputData = null;
		try {
			URL url = new URL(myUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method.toUpperCase());
			conn.setRequestProperty("Content-Type", "application/json");
			if(auth!=null){
				String authorization = auth.get("username") + ":" + auth.get("password");
				byte[] authEncBytes = Base64.encodeBase64(authorization.getBytes());
				String authStringEnc = new String(authEncBytes);
				conn.setRequestProperty("Authorization", "Basic "+authStringEnc);
			}
			
			String input = myInput;
			
			if(input!=null){
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Failed : HTTP error code : "+ conn.getResponseCode() + " : " + conn.getResponseMessage());
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode() + " : " + conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			/*String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/
			outputData = br.readLine();

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputData;
	}

}
