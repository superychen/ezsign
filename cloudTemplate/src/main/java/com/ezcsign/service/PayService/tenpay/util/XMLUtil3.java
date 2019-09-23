package com.ezcsign.service.PayService.tenpay.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLUtil3 {
	
	public static List<WaybillRoute> getWaybillRoute(String fileName) throws Exception{  
        Document document = null;  
        List<WaybillRoute> ways = null;  
        
        StringReader sr = new StringReader(fileName);   
        InputSource is = new InputSource(sr);   
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
        DocumentBuilder builder;   
        builder = factory.newDocumentBuilder();   
        document = builder.parse(is);   
        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList  
        NodeList wayList = document.getElementsByTagName("WaybillRoute");  
        ways = new ArrayList<WaybillRoute>();  
        //遍历ways 
        for(int i=0;i<wayList.getLength();i++){  
        	WaybillRoute way = new WaybillRoute();  
            //获取第i个way结点  
            org.w3c.dom.Node node = wayList.item(i);  
            //获取第i个way的所有属性  
            NamedNodeMap NodeMap = node.getAttributes();  
            way.setId(NodeMap.getNamedItem("id").getTextContent());  
            way.setMailno(NodeMap.getNamedItem("mailno").getTextContent());  
            way.setOrderid(NodeMap.getNamedItem("orderid").getTextContent());  
            way.setAcceptTime(NodeMap.getNamedItem("acceptTime").getTextContent());
            way.setAcceptAddress(NodeMap.getNamedItem("acceptAddress").getTextContent());
            way.setRemark(NodeMap.getNamedItem("remark").getTextContent());
            way.setOpCode(NodeMap.getNamedItem("opCode").getTextContent());
            
            ways.add(way);  
        }  
          
        return ways;  
          
    } 
	
    
    public static void main(String args[]){  
        String fileName = "<?xml version='1.0' encoding='UTF-8'?><Request service='RoutePushService' lang='zh-CN'><Body><WaybillRoute id='5348054' mailno='444033995644' orderid='1006621016570007' acceptTime='2018-02-03 14:36:36' acceptAddress='深圳市' remark='派件成功' opCode='80'/><WaybillRoute id='5348055' mailno='444033995744' orderid='1006621016570007' acceptTime='2018-02-03 14:36:36' acceptAddress='深圳市' remark='派件成功' opCode='80'/><WaybillRoute id='5348056' mailno='444033995624' orderid='1006621016570007' acceptTime='2018-02-03 14:36:36' acceptAddress='深圳市' remark='派件成功' opCode='80'/><WaybillRoute id='5348057' mailno='444033995641' orderid='1006621016570007' acceptTime='2018-02-03 14:36:36' acceptAddress='深圳市' remark='派件成功' opCode='80'/><WaybillRoute id='5348058' mailno='444033995640' orderid='1006621016570007' acceptTime='2018-02-03 14:36:36' acceptAddress='深圳市' remark='上门收件' opCode='50'/></Body></Request>";  
        try {  
            List<WaybillRoute> list = XMLUtil3.getWaybillRoute(fileName);  
            for(WaybillRoute way :list){  
            	System.out.println("id="+way.getId()
        				+",mailno="+way.getMailno()
        				+",orderid="+way.getOrderid()
        				+",acceptTime="+way.getAcceptTime()
        				+",acceptAddress="+way.getAcceptAddress()
        				+",remark="+way.getRemark()
        				+",opCode="+way.getOpCode());
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}
