package com.ezcsign.service.PayService.tenpay.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ezcsign.service.PayService.tenpay.ClientRequestHandler;
import com.ezcsign.service.PayService.tenpay.PrepayIdRequestHandler;
import com.ezcsign.service.PayService.tenpay.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2017/3/3 0003.
 */
@SuppressWarnings({ "unused", "unchecked" })
public class InterFaceWeiXinUtil {

	private static Logger logger = LoggerFactory.getLogger(InterFaceWeiXinUtil.class);
	//private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	public static String getShortUrl(String longUrl) throws Exception {
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		cl.setParameter("long_url", longUrl);
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest(ConstantUtil.SHORTURL, "POST", XMLUtil.getRequestXml(cl.getAllParameters()));
		Map<String, String> map = XMLUtil.doXMLParse(result);
		if (!map.get("return_code").equals("SUCCESS")) {
			System.out.println("请求失败!");
			throw new Exception("请求失败!");
		} else {
			if (!map.get("result_code").equals("SUCCESS")) {
				String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "业务结果:失败" + "\n";
				System.out.println(rs);
				throw new Exception("业务结果:失败！");
			} else {

				System.out.println(map.get("short_url"));
				if (map.get("err_code") != null && map.get("err_code") != "")
					System.out.println(map.get("err_code"));

			}
		}

		return map.get("short_url");
	}

	/**
	 * 
	 * @param date
	 *            账单日期,下载对账单的日期，格式：20140603
	 * @param billType
	 *            账单类型 ALL，返回当日所有订单信息，默认值
	 * 
	 *            SUCCESS，返回当日成功支付的订单
	 * 
	 *            REFUND，返回当日退款订单
	 * 
	 *            RECHARGE_REFUND，返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）
	 * 
	 * @return 账单
	 */
	public static String downloadBills(String date, String billType) {
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		cl.setParameter("bill_date", date);
		cl.setParameter("bill_type", billType);
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest(ConstantUtil.DOWNLOADBILLURL, "POST", XMLUtil.getRequestXml(cl.getAllParameters()));
		try {
			Map<String, String> map = XMLUtil.doXMLParse(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
    
    /**
     * H5支付
     * @param outTradeNo 订单号
     * @param attach 客户名称
     * @param body 商品明细
     * @param payStore 支付门店
     * @param totalFee 总金额
     * @return
     * @throws Exception
     */
    public static String  weChatPayByH5(String outTradeNo,String attach,String body,String payStore,BigInteger totalFee,HttpServletRequest request) throws Exception{
    	String url = "";  //唤起手机端微信URL
    	String payMapStr = "";
    	//预支付交易会话标识（该参数有效期为2小时）
    	String prepayId = "";
    	PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler();//生成package的请求类
	    prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
	    prepayReqHandler.setParameter("attach", attach);
	    prepayReqHandler.setParameter("body", body);
	    prepayReqHandler.setParameter("device_info", payStore);
	    prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
	    prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
	    prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
	    prepayReqHandler.setParameter("out_trade_no",outTradeNo);
	    prepayReqHandler.setParameter("spbill_create_ip", getIpAddr(request));
	    prepayReqHandler.setParameter("total_fee", totalFee.toString());
	    prepayReqHandler.setParameter("trade_type", "MWEB"); //交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MWEB--H5支付）
	    //增加非参与签名的额外参数
	    prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
	    //设置请求URL
	    prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
	    //获取prepayId
	    String result = prepayReqHandler.sendPrepayToId();
	    String[] resultStr = result.split(";");
	    
	    if(result != null && "RETURN_FAIL".equals(result)){
	        System.out.print("预支付通信标识失败,签名或者参数格式校验错误(H5支付)");
	        throw new Exception("预支付通信标识失败,联系系统管理员！（外）");
	    }else if(result != null && "RESULT_FAIL".equals(result)){
	    	throw new Exception("当前订单已下单，不能重复提交，请【取消订单】，重新提交新订单支付或者使用【支付宝】支付");
	    }
	    
	    Long timeStamp =  new Date().getTime() / 1000;
	    
	    //将下单返回的sign重新用MD5生成
	    String sign = MD5Util.MD5Encode(resultStr[2].toString(), "UTF-8").toUpperCase();
	    
	    //支付成功后跳转页面URL
	    String redirect_url = ConstantUtil.REDIRECT_URL +"&orderId="+outTradeNo;
	    String redirectURL =  URLEncoder.encode(redirect_url,"utf-8");
	    payMapStr = resultStr[1];
	    String data =  "&appid="  + ConstantUtil.APP_ID 
    			+ "&mch_id=" + ConstantUtil.PARTNER
    			+ "&noncestr=" + WXUtil.getUUID()
    			+ "&sign=" + sign
    			+ "&timeStamp=" + timeStamp
    			+ "&redirect_url=" + redirectURL;
	    url = payMapStr + data;
	    
    	return url;
    }
    
    
    /**
     * 微信内H5支付
     * @param outTradeNo 订单号
     * @param attach
     * @param body
     * @param payStore
     * @param openid 用户标识
     * @param totalFee 支付金额
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, Object>  weChatPayByJSAPI(String outTradeNo,String attach,String body,String payStore,String openid,BigInteger totalFee,HttpServletRequest request) throws Exception{
    	//预支付交易会话标识（该参数有效期为2小时）
    	String prepayId = "";
    	PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler();//生成package的请求类
	    prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
	    prepayReqHandler.setParameter("attach", attach);
	    prepayReqHandler.setParameter("body", body);
	    prepayReqHandler.setParameter("device_info", payStore);
	    prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
	    prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
	    prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
	    prepayReqHandler.setParameter("out_trade_no",outTradeNo);
	    prepayReqHandler.setParameter("spbill_create_ip", getIpAddr(request));
	    prepayReqHandler.setParameter("total_fee", totalFee.toString());
	    prepayReqHandler.setParameter("trade_type", "JSAPI"); //交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MWEB--H5支付）
	    prepayReqHandler.setParameter("openid", openid);
	    //增加非参与签名的额外参数
	    prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
	    //设置请求URL
	    prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
	    //获取prepayId
	    String result = prepayReqHandler.sendPrepayToId();
	    String[] resultStr = result.split(";");
	    
	    if(result != null && "RETURN_FAIL".equals(result)){
	        System.out.print("预支付通信标识失败,签名或者参数格式校验错误(H5支付)");
	        throw new Exception("预支付通信标识失败,联系系统管理员！（内）");
	    }else if(result != null && "RESULT_FAIL".equals(result)){
	    	throw new Exception("当前订单已下单，不能重复提交，请【取消订单】，重新提交新订单支付");
	    }
	    
	    Long timeStamp =  new Date().getTime() / 1000;
	    String wxuuid = WXUtil.getUUID();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("appId",ConstantUtil.APP_ID);
	    map.put("timeStamp",Long.toString(timeStamp));
	    map.put("nonceStr", wxuuid);
	    map.put("package","prepay_id="+resultStr[0]);
	    map.put("signType","MD5");
	    
	  //将下单返回的sign重新用MD5生成
	    String tmpStr = "appId=" + ConstantUtil.APP_ID + "&nonceStr=" + wxuuid + "&package=prepay_id=" + resultStr[0] + "&signType=MD5&timeStamp=" + Long.toString(timeStamp) + "&key="+ConstantUtil.APP_KEY;
	    String sign = MD5Util.MD5Encode(tmpStr, "UTF-8").toUpperCase();
	    map.put("paySign",sign);
    	return map;
    }


	public static String weChatPay(String outTradeNo, String attach, String body, String payStore, BigInteger totalFee, HttpServletRequest request) throws Exception {
		String codeURL = "";
		PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler();// 生成package的请求类
		prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
		prepayReqHandler.setParameter("attach", attach);
		prepayReqHandler.setParameter("body", body);
		prepayReqHandler.setParameter("device_info", payStore);
		prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
		prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
		prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
		prepayReqHandler.setParameter("out_trade_no", outTradeNo);
		prepayReqHandler.setParameter("spbill_create_ip", ConstantUtil.SPBILL_CREATE_IP);
		prepayReqHandler.setParameter("total_fee", totalFee.toString());
		prepayReqHandler.setParameter("trade_type", "NATIVE"); // 交易类型（JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付）

		// 增加非参与签名的额外参数
		prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
		// 设置请求URL
		prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
		// 获取codeURL
		String result = prepayReqHandler.sendPrepayToCodeUrl();
		if (result == "FAIL") {
			System.out.print("预支付下单失败");
			throw new Exception("预支付下单失败，通信标识出错");
		} else if("".equals(result)){
			throw new Exception("当前订单已下单，不能重复提交，请【取消订单】，重新提交新订单支付或者使用【支付宝】支付");
		} else {
			codeURL = result;
		}
		return codeURL;
	}

	/**
	 * 关闭订单
	 * 
	 * @param out_trade_no
	 *            交易号
	 * @throws Exception
	 */
	public static void closeOrder(String out_trade_no) throws Exception {
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("out_trade_no", out_trade_no);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest(ConstantUtil.CLOSEORDERURL, "POST", XMLUtil.getRequestXml(cl.getAllParameters()));
		Map<String, String> map = XMLUtil.doXMLParse(result);
		if (!map.get("return_code").equals("SUCCESS")) {
			System.out.println("订单关闭失败！签名失败!");
			throw new Exception("订单关闭失败！签名失败!");
		} else {
			if (!map.get("result_code").equals("SUCCESS")) {
				String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "业务结果:失败" + "\n";
				System.out.println(rs);
				// throw new Exception("业务结果:失败！");
			} else {
				// result_msg
				System.out.println(map.get("return_msg"));
				if (map.get("err_code") != null && map.get("err_code") != "")
					System.out.println(map.get("err_code"));
				if (map.get("err_code_des") != null && map.get("err_code_des") != "")
					System.out.println(map.get("err_code_des"));
			}
		}

	}

	/**
	 * 查询订单
	 * 
	 * @param out_trade_no
	 *            订单号
	 * @return 订单详情
	 * @throws Exception
	 */
	public static TradeInfo getTradeInfo(String out_trade_no) throws Exception {
		TradeInfo tradeInfo = new TradeInfo();
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("out_trade_no", out_trade_no);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest(ConstantUtil.QUERYURL, "POST", XMLUtil.getRequestXml(cl.getAllParameters()));
		Map<String, String> map = XMLUtil.doXMLParse(result);
		if (!map.get("return_code").equals("SUCCESS")) { //通信标识
			System.out.println("查询失败！");
			throw new Exception("微信查询订单通信标识失败，签名或者参数格式校验错误");

		} else {
			//业务结果判断：SUCCESS/FAIL 
			if (!map.get("result_code").equals("SUCCESS")) {  
				String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n"  + "商户订单号:" + out_trade_no + "\n" + "业务结果:" + map.get("err_code_des") + "\n";
				tradeInfo.setTrade_state("FAIL");
				logger.info(rs);
				// throw new Exception("业务结果:失败！");
			} else {

				String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "商户订单号:" + map.get("out_trade_no") + "\n" + "用户标示:" + map.get("openid") + "\n" 
							+ "交易类型:" + map.get("trade_type") + "\n" + "交易状态:" + map.get("trade_state") + "\n" + "付款银行:" + map.get("bank_type") + "\n" 
							+ "标价金额:" + map.get("total_fee") + "\n" + "应结订单金额:" + map.get("settlement_total_fee") + "\n"
							+ "支付完成时间:" + map.get("time_end") + "\n" + "交易状态描述:" + map.get("trade_state_desc") + "\n";
				tradeInfo.setAppid(map.get("appid"));
				tradeInfo.setMch_id(map.get("mch_id"));
				tradeInfo.setOut_trade_no(map.get("out_trade_no"));
				tradeInfo.setOpenid(map.get("openid"));
				tradeInfo.setTrade_type(map.get("trade_type"));
				tradeInfo.setTrade_state(map.get("trade_state"));
				tradeInfo.setBank_type(map.get("bank_type"));
				tradeInfo.setTotal_fee(map.get("total_fee"));
				tradeInfo.setSettlement_total_fee(map.get("settlement_total_fee"));
				tradeInfo.setTime_end(map.get("time_end"));
				tradeInfo.setTrade_state_desc(map.get("trade_state_desc"));
				tradeInfo.setTransaction_id(map.get("transaction_id"));
				logger.info(rs);
			}
		}
		return tradeInfo;
	}

	/**
	 * 退款
	 * 
	 * @param out_trade_no
	 *            商户交易号
	 * @param total_fee
	 *            交易总金额
	 * @param refund_fee
	 *            退款金额
	 * @return
	 */
	public static String reFound(String out_trade_no, int total_fee, int refund_fee) {
		// TradeInfo tradeInfo =new TradeInfo();
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("out_trade_no", out_trade_no);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		String out_refund_no = WXUtil.getUUID();
		cl.setParameter("out_refund_no", out_refund_no);
		cl.setParameter("total_fee", total_fee + "");
		cl.setParameter("refund_fee", refund_fee + "");
		cl.setParameter("op_user_id", ConstantUtil.PARTNER + "");

		// cl.setParameter("key", ConstantUtil.APP_KEY);
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest2(ConstantUtil.REFOUND, XMLUtil.getRequestXml(cl.getAllParameters()));
		System.out.println(result);
		try {
			Map<String, String> map = XMLUtil.doXMLParse(result);
			if (!map.get("return_code").equals("SUCCESS")) {
				System.out.println("退款失败！");
				return "退款失败！";

			} else {

				if (!map.get("result_code").equals("SUCCESS")) {
					String rs = "公众账号ID:" + map.get("appid") + "\n" + "商户号:" + map.get("mch_id") + "\n" + "业务结果:失败" + map.get("err_code_des") + "\n";
					System.out.println(rs);
					// throw new Exception("业务结果:失败！");
					return rs;
				} else {

					System.out.println("退款成功,退款单号为：" + out_refund_no);
					return "退款成功,退款单号为：" + out_refund_no;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// return out_refund_no;

	}

	/**
	 * 退款查询
	 * 
	 * @param out_refund_no
	 *            退款单号
	 */
	public static void reFundQuery(String out_refund_no) {
		ClientRequestHandler cl = new ClientRequestHandler(null, null);
		cl.setParameter("appid", ConstantUtil.APP_ID);
		cl.setParameter("mch_id", ConstantUtil.PARTNER);
		cl.setParameter("nonce_str", WXUtil.getUUID());
		cl.setParameter("out_refund_no", out_refund_no);
		cl.setParameter("sign", cl.createSign());
		String result = CommonUtil.httpsRequest2(ConstantUtil.REFOUNDQUERY, XMLUtil.getRequestXml(cl.getAllParameters()));
		System.out.println(result);
	}

	/**
	 * 获取客户端真实ip
	 * 
	 * @param request
	 * @return
	 */
	private static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		System.out.println("x-forwarded-for ip: " + ip);
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr ip: " + ip);
		}
		System.out.println("获取客户端ip: " + ip);
		return ip;
	}

	public String access_token() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	String appid=ConstantUtil.APP_ID;
        	String appsecret=ConstantUtil.APP_SECRET;
            URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        JSONObject json=  JSONObject.fromObject(result);
        String access_token= (String) json.get("access_token");
	
		return access_token;
    }
	
//	/**
//	 * first.DATA 对应first
//	 * keyword1.DATA 对应keyword1
//	 * keyword2.DATA对应keyword2
//	 * remark.DATA对应remark
//	 *
//	 * 模版ID5nXZuuX-MpXpOzslAyJtxMvKRE_uS8UcOUMcGawDC1U
//开发者调用模版消息接口时需提供模版ID
//标题认证进度通知
//行业商业服务 - 检测|认证
//详细内容
//{{first.DATA}}
//审核状态：{{keyword1.DATA}}
//补充说明：{{keyword2.DATA}}
//{{remark.DATA}}
//在发送时，需要将内容中的参数（{{.DATA}}内为参数）赋值替换为需要的信息
//内容示例
//认证进度通知
//审核状态：认证失败
//补充说明：请完善个人居住地址，精确到门牌号，谢谢。
//点击即可进入修改界面，提交后稍等片刻，认证结果等待进一步通知
//	 *
//	 * @param map
//	 */
//	public void sendwechat(Map<String,Object> map) {
//		String urladdress=" https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token();
//
//
//		try {
//			JSONObject obj = new JSONObject();
//			JSONObject data = new JSONObject();
//
//			JSONObject first =  new JSONObject();
//			first.put("value", StringUtil.formatObj(map.get("first")));
//			first.put("color","#173177");
//
//			JSONObject keyword1 =  new JSONObject();
//			keyword1.put("value", StringUtil.formatObj(map.get("keyword1")));
//			keyword1.put("color","#173177");
//
//			JSONObject keyword2 =  new JSONObject();
//			keyword2.put("value",StringUtil.formatObj(map.get("keyword2")));
//			keyword2.put("color","#173177");
//
//			JSONObject remark =  new JSONObject();
//			remark.put("value",StringUtil.formatObj(map.get("remark")));
//			remark.put("color","#173177");
//
//			data.put("first", first);
//			data.put("keyword1", keyword1.toString());
//			data.put("keyword2", keyword2.toString());
//			data.put("remark", remark);
//
//			obj.put("touser", map.get("openId")); // openid
//			obj.put("template_id","5nXZuuX-MpXpOzslAyJtxMvKRE_uS8UcOUMcGawDC1U");
//			obj.put("data", data);
////			obj.put("url", StringUtil.formatObj(map.get("url")));
//			String json = obj.toString();
//
//
//
//
//
//			URL url = new URL(urladdress);
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//            http.setDoOutput(true);
//            http.setDoInput(true);
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
//            http.connect();
//            OutputStream os= http.getOutputStream();
//            os.write(json.getBytes("UTF-8"));//传入参数
//            os.flush();
//            os.close();
//            InputStream is =http.getInputStream();
//            int size =is.available();
//            byte[] jsonBytes =new byte[size];
//            is.read(jsonBytes);
//            String message=new String(jsonBytes,"UTF-8");
//            System.out.println(message);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}

	public static void main(String[] args) throws Exception {
//		try {
////			Map<String, Object> item = new HashMap<String, Object>();
////			item.put("first", "订单审核通知");
////			String orderId = "test1234567890";
////			item.put("openId", "o5P_Gw9aAhWSKNGSyHUYSwyD2LGM");
////			// String order_bustypename =
////			// String order_project_name =
////			item.put("keyword1", "资料审核成功");
////			item.put("keyword2", "请登录系统进行支付!订单号：" + orderId);
////			item.put("remark", "新办业务请等待邮寄，更新请登录系统插入KEY进行下载，请使用IE8-IE11浏览器。");
////			InterFaceWeiXinUtil util = new InterFaceWeiXinUtil();
////			util.sendwechat(item);
//			Map<String, Object> item = new HashMap<String, Object>();
//			item.put("abc", "abc");
////			String abc = "zhongwen测试括号问题(小括号)baohan测试（大括号）问题";
////			System.out.println(abc.replace("（", "("));
//			System.out.println("_________"+item.get("abc"));
//			item.put("abc", "def");
//			System.out.println("_________"+item.get("abc"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
