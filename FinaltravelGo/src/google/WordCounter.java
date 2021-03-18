package google;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordCounter 
{
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr)
    {
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws Exception
    {
    	
    	String retVal = "";
		URL url = new URL(this.urlStr);


		trustAllHttpsCertificates();
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
		URLConnection conn = url.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();
		
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
    }
    
    public int countKeyword(String keyword) throws Exception
    {
    	try 
    	{
    		if (content == null)
    		{
			
    			content = fetchContent();
    		}
    		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
    		content = content.toUpperCase();
    		keyword = keyword.toUpperCase();
		
		
		
    		Document doc = Jsoup.parse(content);
    		content = doc.body().text();
		
    		int retVal = 0;
    		int fromIdx = 0;
    		int found = -1;
	
    		while ((found = content.indexOf(keyword, fromIdx)) != -1)
    		{
    			System.out.println(keyword);
    			retVal++;
    			fromIdx = found + keyword.length();
    		}
	
    		return retVal;
    	}
    	catch (Exception e)
    	{
    		return 0;
    	}
    }
    
    
    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}