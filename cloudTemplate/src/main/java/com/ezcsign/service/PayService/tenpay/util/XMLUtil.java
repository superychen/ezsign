package com.ezcsign.service.PayService.tenpay.util;

import org.dom4j.DocumentException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.ByteArrayInputStream;
import java.util.Set;
import java.util.SortedMap;


/**
 * xml工具类
 * @author miklchen
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}

			m.put(k, v);
		}

		//关闭流
		in.close();

		return m;
	}

	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}

	public static String getRequestXml(SortedMap<Object,Object> parameters){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Entry entry = (Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/** 
	   * map转成xml 
	   * 
	   * @param arr 
	   * @return 
	   */
	public static String mapToXml(Map<String, String> map) { 
		String xml = "<xml>"; 
		Iterator<Entry<String, String>> iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
			Entry<String, String> entry = iter.next(); 
			String key = entry.getKey(); 
			String val = entry.getValue(); 
			xml += "<" + key + ">" + val + "</" + key + ">"; 
		}
		xml += "</xml>"; 
		return xml;
	}
	
	
	/**
	 * xml转map
	 * @param doc
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, Object> Dom2Map(org.dom4j.Document doc) throws DocumentException {
		Map<String, Object> map = new HashMap<String, Object>();
		if(doc == null)
			return map;
		org.dom4j.Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			org.dom4j.Element e = (org.dom4j.Element) iterator.next();
			List list = e.elements();
			if(list.size() > 0){
				map.put(e.getName(), Dom2MapByElement(e));
			}else
				map.put(e.getName(), e.getText());
		}
		return map;
	}
	

	public static Map Dom2MapByElement(org.dom4j.Element e) throws DocumentException{
		Map	map = new HashMap();
		List list = ((org.dom4j.Element) e).elements();
		if(list.size() > 0){
			for (int i = 0;i < list.size(); i++) {
				org.dom4j.Element iter = (org.dom4j.Element) list.get(i);
				List mapList = new ArrayList();
				
				if(((org.dom4j.Element) iter).elements().size() > 0){
					Map m = Dom2MapByElement(iter);
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), m);
				}
				else{
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), iter.getText());
				}
			}
		}else
			map.put(e.getName(), e.getText());
		return map;
	}
}
