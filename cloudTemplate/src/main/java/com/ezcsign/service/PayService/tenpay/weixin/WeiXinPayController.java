package com.ezcsign.service.PayService.tenpay.weixin;

import com.alibaba.fastjson.JSON;
import com.ezcsign.service.PayService.tenpay.AccessTokenRequestHandler;
import com.ezcsign.service.PayService.tenpay.ClientRequestHandler;
import com.ezcsign.service.PayService.tenpay.PackageRequestHandler;
import com.ezcsign.service.PayService.tenpay.PrepayIdRequestHandler;
import com.ezcsign.service.PayService.tenpay.util.*;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付服务端简单示例
 *
 * @author seven_cm
 * @dateTime 2014-11-29
 */
@Controller
@RequestMapping("/weixin/")
public class WeiXinPayController extends ResponsePage {

    private Logger log = LoggerFactory.getLogger(WeiXinPayController.class);

    @SuppressWarnings("unchecked")
	private String[] getPayInfo(HttpServletRequest request) throws IOException, JDOMException {
        BufferedReader reader = request.getReader();
        StringBuffer jb = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            jb.append(line);
        }

        Map<String, String> map = XMLUtil.doXMLParse(jb.toString());

        System.out.println(map.get("openid"));
        System.out.println(map.get("product_id"));
        return new String[]{map.get("openid").toString(), map.get("product_id")};
    }

    @RequestMapping("doTest.do")
    public String doTest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        getPayInfo(request);

        return "";

    }

    public String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
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
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return null;
    }

    @RequestMapping("weixin.do")
    public String doWeinXinRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<Object, Object> resInfo = new HashMap<Object, Object>();
        //接收财付通通知的URL
        String notify_url = "http://127.0.0.1:8180/tenpay_api_b2c/payNotifyUrl11111.jsp";

        //---------------生成订单号 开始------------------------
        //当前时间 yyyyMMddHHmmss
        String currTime = TenpayUtil.getCurrTime();
        //8位日期
        String strTime = currTime.substring(8, currTime.length());
        //四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        //10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        //订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
        String out_trade_no = strReq;
        //---------------生成订单号 结束------------------------

        PackageRequestHandler packageReqHandler = new PackageRequestHandler(request, response);//生成package的请求类
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类
        ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);//返回客户端支付参数的请求类
        packageReqHandler.setKey(ConstantUtil.PARTNER_KEY);

        int retcode;
        String retmsg;
        String xml_body;

        //获取token值
        String token = AccessTokenRequestHandler.getAccessToken();

        log.debug("获取token------值 " + token);

        if (!"".equals(token)) {
            //设置package订单参数
            packageReqHandler.setParameter("bank_type", "WX");//银行渠道
            packageReqHandler.setParameter("body", "TEST"); //商品描述
            packageReqHandler.setParameter("notify_url", notify_url); //接收财付通通知的URL
            packageReqHandler.setParameter("partner", ConstantUtil.PARTNER); //商户号
            packageReqHandler.setParameter("out_trade_no", out_trade_no); //商家订单号
            packageReqHandler.setParameter("total_fee", "1"); //商品金额,以分为单位
            packageReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr()); //订单生成的机器IP，指用户浏览器端IP
            packageReqHandler.setParameter("fee_type", "1"); //币种，1人民币   66
            packageReqHandler.setParameter("input_charset", "GBK"); //字符编码

            //获取package包
            String packageValue = packageReqHandler.getRequestURL();
            resInfo.put("package", packageValue);

            log.debug("获取package------值 " + packageValue);

            String noncestr = WXUtil.getNonceStr();
            String timestamp = WXUtil.getTimeStamp();
            String traceid = "tanxf001";
            ////设置获取prepayid支付参数
            prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
            prepayReqHandler.setParameter("appkey", ConstantUtil.APP_KEY);
            prepayReqHandler.setParameter("noncestr", noncestr);
            prepayReqHandler.setParameter("package", packageValue);
            prepayReqHandler.setParameter("timestamp", timestamp);
            prepayReqHandler.setParameter("traceid", traceid);

            //生成获取预支付签名
            String sign = "";//prepayReqHandler.createSHA1Sign();
            //增加非参与签名的额外参数
            prepayReqHandler.setParameter("app_signature", sign);
            prepayReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
            String gateUrl = ConstantUtil.GATEURL + token;
            prepayReqHandler.setGateUrl(gateUrl);

            //获取prepayId
            String prepayid = prepayReqHandler.sendPrepayToId();
            System.out.println(prepayReqHandler.getDebugInfo());

            log.debug("获取prepayid------值 " + prepayid);

            //吐回给客户端的参数
            if (null != prepayid && !"".equals(prepayid)) {
                //输出参数列表
                clientHandler.setParameter("appid", ConstantUtil.APP_ID);
                clientHandler.setParameter("appkey", ConstantUtil.APP_KEY);
                clientHandler.setParameter("noncestr", noncestr);
                clientHandler.setParameter("package", "Sign=" + packageValue);
//                clientHandler.setParameter("package", "Sign=WXPay");
                clientHandler.setParameter("partnerid", ConstantUtil.PARTNER);
                clientHandler.setParameter("prepayid", prepayid);
                clientHandler.setParameter("timestamp", timestamp);
                //生成签名
                sign = "";//clientHandler.createSHA1Sign();
                clientHandler.setParameter("sign", sign);

                xml_body = clientHandler.getXmlBody();
                resInfo.put("entity", xml_body);
                retcode = 0;
                retmsg = "OK";
            } else {
                retcode = -2;
                retmsg = "错误：获取prepayId失败";
                log.debug(retmsg);
            }
        } else {
            retcode = -1;
            retmsg = "错误：获取不到Token";
            log.debug(retmsg);
        }

        resInfo.put("retcode", retcode);
        resInfo.put("retmsg", retmsg);
        String strJson = JSON.toJSONString(resInfo);
        log.debug(strJson);
        return responseAjax(request, strJson);
    }

    public static void main(String[] args) throws Exception {
        //生成支付二维码的字符串
        /*String hedr = "weixin://wxpay/bizpayurl?";

        StringBuffer sb = new StringBuffer();
        sb.append("appid=wx5dbc3815acaf0ab9");
        sb.append("&mch_id=1440832402");
        sb.append("&nonce_str=" + UUID.randomUUID().toString().replace("-", ""));
        sb.append("&product_id=7777777");
        sb.append("&time_stamp=" + new Date().getTime() / 1000);

        String signr = sb.toString() + "&key=e71b02f6a86c410b9b5472d0b9d0f4e2";

        String sing = Sha1Util.getSha1(signr).toUpperCase();
        System.out.println(sing);
        sing = MD5Util.MD5Encode(signr, "gbk").toUpperCase();
        System.out.println(sing);

        String ret = hedr + sb.toString() + "&sign=" + sing;

        ret = WeiXin.getShortUrl(ret);*/
       // WeiXin.DownloadBills("20170306","ALL");
        //WeiXin.getTradeInfo("b60d66b6107241ceaf4897a38b4a87fc");
        //WeiXin.ReFound("6f48fbb95ea34e03a7b6ac657152c5db",1,1);
        /*System.out.println(ret);*/
        InterFaceWeiXinUtil.reFundQuery("9c473e83700e4ad2b128c241781b1c08");

    }
}