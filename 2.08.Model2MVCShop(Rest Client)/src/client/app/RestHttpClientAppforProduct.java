package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Search;






public class RestHttpClientAppforProduct {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
		//파일 업로드 빼고는 뭐....
//		RestHttpClientAppforProduct.addProductTest();
		
//		System.out.println("\n====================================\n");
//		//CodeHaus lib 사용으로 getProduct() 
//		RestHttpClientAppforProduct.getProductTest();
		//완료
		
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
		RestHttpClientAppforProduct.getProductListTest();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용
		//RestHttpClientApp.LoginTest_Codehaus();		
	
		
	}
	
	public static void getProductTest() throws Exception{
				 
		HttpClient httpClient = new DefaultHttpClient();
				
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10083";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("Response data 확인용 : " +httpEntity);

		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
	
	
//================================================================//
	public static void addProductTest() throws Exception{
 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Product product = new Product();
		product.setFileName("testback.jpg");
		product.setManuDate("20140213");
		product.setPrice(24000);
		product.setProdCount(20);
		product.setProdDetail("restTest");
		product.setProdName("testerProduct");
		Search search = new Search();
		search.setCurrentPage(1);
		search.setOrdering("DESC");
		search.setPageSize(5);
		search.setSearchCondition("");
		search.setSearchKeyword("");
		
		
		ObjectMapper objectMapper = new ObjectMapper(); 
		
		String jsonString = objectMapper.writeValueAsString(product);
		HttpEntity httpEntity = new StringEntity(jsonString, "utf-8");
		System.out.println(jsonString);
		
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity returnHttpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = returnHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper sendObjectMapper = new ObjectMapper();
		Product addProduct = sendObjectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(addProduct);
	}	
	
	public static void getProductListTest() throws Exception{
						
		HttpClient httpClient = new DefaultHttpClient();
		
		
		JSONObject condition = new JSONObject();
		condition.put("cuurentPage", 3);
		
		HttpEntity reqHttpEnt = new StringEntity(condition.toString(), "UTF-8");
				
		String listUrl = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpPost httpPost = new HttpPost(listUrl);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setEntity(reqHttpEnt);
				
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity returnHttpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = returnHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper sendObjectMapper = new ObjectMapper();
		Map map = sendObjectMapper.readValue(jsonobj.toString(), new TypeReference<Map<String, Product>>() {});
		System.out.println(map.get("productMapList"));
	}	
	
	
}