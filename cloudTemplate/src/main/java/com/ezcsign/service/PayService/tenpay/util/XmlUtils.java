package com.ezcsign.service.PayService.tenpay.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dom4j读写xml
 *
 * @author whwang
 */
public class XmlUtils {
    private Document doc;
    private Element xmlRoot;


	private static String getStrObjCvert(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}




	public static Map<String, Object> xmlTomap(String str){
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> m = new HashMap<String, Object>();
    	 try {
    		 Document doc = DocumentHelper.parseText(str);
    		 Map<String, Object> docmap = XMLUtil.Dom2Map(doc);
    		 String head = getStrObjCvert(docmap.get("Head"));
    		 if("OK".equals(head)){
    			 XmlUtils dom4jUtil = new XmlUtils(str);
    				List<Element> listElement = dom4jUtil.xmlRoot.elements();
    				//递归遍历当前节点所有的子节点
    		        for (Element element : listElement) {//遍历所有一级子节点
    		        	map = dom4jUtil.getNodesToMap(element, "Body" ,m);//递归
    		        }
    		 }else{
    			 map = docmap;
    		 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    public XmlUtils(String xmlData) throws Exception {
        try {
            this.doc = DocumentHelper.parseText(xmlData);
            this.xmlRoot = this.doc.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception ("XML字符串格式错误！");
        }
    }


    @SuppressWarnings("unchecked")
	public Map<String, Object> getNodesToMap(Element node, String nodeName, Map<String, Object> m) throws Exception  {
        if (node == null) {
            node = this.xmlRoot;
        }
        //HashMap<String, String> m = new HashMap<>();
        List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list
        List<Element> listElement = node.elements();
        if (node.getParent() != null && node.getParent().getName().equals(nodeName)) {
            for (Attribute attr : listAttr) {//遍历当前节点的所有属性
                String name = attr.getName();//属性名称
                String value = attr.getValue();//属性的值
/*                System.out.println("结点" + node.getName() + " 属性 " + name + ":" + value);*/
                m.put(name, value);
            }
        }

        //递归遍历当前节点所有的子节点
        for (Element element : listElement) {//遍历所有一级子节点
            getNodesToMap(element, nodeName , m);//递归
        }

        return m;
    }
    
    @SuppressWarnings({ "unchecked" })
	public static List<Map<String, Object>> getSFwl(Element node,List<Map<String, Object>> list){
	    //当前节点的名称、文本内容和属性  
		String name;
		String value;
//		System.out.println("----------------------");
//	    System.out.println("当前节点名称："+node.getName());//当前节点名称  
//	    System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称  
		List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list  
	    for(Attribute attr:listAttr){//遍历当前节点的所有属性  
	        Map<String, Object> mm = new HashMap<String, Object>();
	    	name=attr.getName();//属性名称
	         value=attr.getValue();//属性的值  
//	        System.out.println("属性名称："+name+"属性值："+value);  
	        mm.put(name, value);
	        list.add(mm);
	    }  
	        
	    //递归遍历当前节点所有的子节点  
		List<Element> listElement=node.elements();//所有一级子节点的list  
	    for(Element e:listElement){//遍历所有一级子节点  
	    	getSFwl(e,list);//递归  
	    }
	    
	    return list;
	}
    

	
	public static void main(String[] args) {

	}

}

