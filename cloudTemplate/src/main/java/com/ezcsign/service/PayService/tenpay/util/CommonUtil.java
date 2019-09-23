package com.ezcsign.service.PayService.tenpay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.ezcsign.service.PayService.tenpay.weixin.MyX509TrustManager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;


;


/**
 * 通用工具类
 * @author  
 * @date 2014-11-21下午9:10:30
 */
@SuppressWarnings("deprecation")
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return null;
	}


	public static String httpsRequest2(String url,String data)
	{
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			URL url2 = CommonUtil.class.getClassLoader().getResource("apiclient_cert.p12");
			URI uri = url2.toURI();
			System.out.println(url2);
			FileInputStream instream = new FileInputStream(new File(uri));//P12文件目录
			try {
				keyStore.load(instream, ConstantUtil.PARTNER.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, ConstantUtil.PARTNER.toCharArray())//这里也是写密码的
					.build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[]{"TLSv1"},
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			try {
				HttpPost httpost = new HttpPost(url); // 设置响应头信息
				httpost.addHeader("Connection", "keep-alive");
				httpost.addHeader("Accept", "*/*");
				httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				httpost.addHeader("Host", "api.mch.weixin.qq.com");
				httpost.addHeader("X-Requested-With", "XMLHttpRequest");
				httpost.addHeader("Cache-Control", "max-age=0");
				httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
				httpost.setEntity(new StringEntity(data, "UTF-8"));
				CloseableHttpResponse response = httpclient.execute(httpost);
				try {
					HttpEntity entity = (HttpEntity) response.getEntity();

					String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
					EntityUtils.consume((org.apache.http.HttpEntity) entity);
					return jsonStr;
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * 发送HttpGet请求
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		//1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//2.生成一个get请求
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			//3.执行get请求并返回结果
			response = httpclient.execute(httpget);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String result = null;
		try {
			//4.处理结果，这里将结果返回为字符串
			HttpEntity entity = (HttpEntity) response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString((org.apache.http.HttpEntity) entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}