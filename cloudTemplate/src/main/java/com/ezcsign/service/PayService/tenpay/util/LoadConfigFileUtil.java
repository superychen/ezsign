package com.ezcsign.service.PayService.tenpay.util;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadConfigFileUtil {

	private static Logger logger = LoggerFactory.getLogger(LoadConfigFileUtil.class);

	// 根据文件名读取配置文件，文件后缀名必须为.properties
	@SuppressWarnings("unused")
	public synchronized static Configuration loadFile(String filePath) {
		Configuration configs = null;
		logger.debug("开始加载配置文件：" + filePath);
		try {
			configs = new PropertiesConfiguration(filePath);
			if (configs == null) {
				throw new IllegalStateException("can`t find file by path:" + filePath);
			} else {
				Iterator<String> iterator = configs.getKeys();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = configs.getString(key);
					String encode = getEncoding(value);
					String envalue = new String(value.getBytes(encode),"UTF-8");
					configs.setProperty(key, envalue);
//					System.out.println("key:"+key+"_________value:"+value+"__________encode:"+encode+"__________envalue:"+envalue+"_____________configs:"+configs.getString(key));
					
				}

			}
		} catch (Exception e) {
			logger.debug("加载配置文件失败:" + filePath);
		}
		logger.debug("结束加载配置文件：" + filePath);
		return configs;
	}

	public static String getEncoding(String str) {
		String encode[] = new String[]{"ISO-8859-1","UTF-8","GB2312","GBK","GB18030","Big5","Unicode","ASCII"};
		for(int i=0;i<encode.length;i++){
			try {
				if(str.equals(new String(str.getBytes(encode[i]),encode[i]))){
					return encode[i];
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
		return null;
	}
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			Configuration configs = loadFile("aliyuninfo.properties");
			System.out.println("*****************************************************************************************************************");
			Configuration configs1 = loadFile("mailinfo.properties");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
