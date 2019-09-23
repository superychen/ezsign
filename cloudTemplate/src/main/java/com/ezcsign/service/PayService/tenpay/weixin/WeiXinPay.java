package com.ezcsign.service.PayService.tenpay.weixin;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezcsign.service.PayService.tenpay.ClientRequestHandler;
import com.ezcsign.service.PayService.tenpay.PrepayIdRequestHandler;
import com.ezcsign.service.PayService.tenpay.util.ConstantUtil;
import com.ezcsign.service.PayService.tenpay.util.WXUtil;
import com.ezcsign.service.PayService.tenpay.util.XMLUtil;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class WeiXinPay {

    private Logger log = LoggerFactory.getLogger(WeiXinPay.class);
    /* ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:application.xml");
 */
    @RequestMapping("weixinRequest.do")
    public void doWeinXinRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String retInfo;
        String prepayid = "";
        String detail ="";
        String total_fee = "";
        //JdbcTemplate jdbc = (JdbcTemplate)ac.getBean("jdbcTemplate");


        ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);//返回客户端支付参数的请求类
        try {

            //解析并提取返回参数值，在这里主要取openid、product_id
            //微信支付系统收到客户端请求，发起对商户后台系统支付回调URL的调用。调用请求将带productid和用户的openid等参数
            Map<String, String> map = getPayInfo(request);

            String openid = map.get("openid");//用户在商户appid下的唯一标识
            String product_id = map.get("product_id");//商户定义的商品id 或者订单号
            //String is_subscribe = map.get("is_subscribe");//用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注

            //根据product_id生成商户系统的订单。
            try {
                Products products = new MyProducts();
                Map<String, Object> productInfo = products.getProductInfoByid(product_id);
                //detail = (String) productInfo.get("detail");
                total_fee = productInfo.get("total_fee")+"";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }



            //openid与product_id不能为空

            //获取预支付ID

            //组建商品详情参数

            //组建预支付请求参数
            PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//生成package的请求类
            prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
            prepayReqHandler.setParameter("attach", "支付测试");
            prepayReqHandler.setParameter("body", "NATIVE支付测试");
            prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
            prepayReqHandler.setParameter("nonce_str", WXUtil.getUUID());
            prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
            prepayReqHandler.setParameter("openid", openid);
            String out_trade_no = WXUtil.getUUID();
            prepayReqHandler.setParameter("out_trade_no",out_trade_no);
            prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
            prepayReqHandler.setParameter("total_fee", total_fee);
            if(detail!=""&&detail!=null)
                prepayReqHandler.setParameter("detail", detail);
            prepayReqHandler.setParameter("trade_type", "NATIVE");
            prepayReqHandler.setParameter("product_id", product_id);

            //设置请求URL
            prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
            //增加非参与签名的额外参数
            prepayReqHandler.setParameter("sign", prepayReqHandler.createSign());
            //获取prepayId
            prepayid = prepayReqHandler.sendPrepayToId();

            clientHandler.setParameter("nonce_str", WXUtil.getUUID());//随机字符串
            clientHandler.setParameter("appid", ConstantUtil.APP_ID);//公众账号ID
            clientHandler.setParameter("mch_id", ConstantUtil.PARTNER);//商户号
            clientHandler.setParameter("return_code", "SUCCESS");//返回状态码
            clientHandler.setParameter("return_msg", "");//返回信息
            clientHandler.setParameter("prepay_id", prepayid);//预支付ID
            if (prepayid == null || "".equals(prepayid)) {
                clientHandler.setParameter("result_code", "FAIL");//业务结果
                clientHandler.setParameter("err_code_des", "");//错误描述
            } else {
                clientHandler.setParameter("result_code", "SUCCESS");//业务结果
                clientHandler.setParameter("err_code_des", "");//错误描述


                /*jdbc.update("INSERT INTO tradelist (out_trade_no,trade_state,prepayid ) VALUES (?,?,?)",
                        out_trade_no,"NOTPAY",prepayid);*/
            }
        } catch (Exception e) {
            e.printStackTrace();

            clientHandler.setParameter("return_code", "FAIL");
            clientHandler.setParameter("return_msg", "签名失败");
            clientHandler.setParameter("appid", ConstantUtil.APP_ID);
            clientHandler.setParameter("mch_id", ConstantUtil.PARTNER);
            clientHandler.setParameter("nonce_str", WXUtil.getUUID());
            clientHandler.setParameter("prepay_id", prepayid);
            clientHandler.setParameter("result_code", "FAIL");//业务结果
            clientHandler.setParameter("err_code_des", "签名失败");//错误描述
        }
        //增加非参与签名的额外参数
        clientHandler.setParameter("sign", clientHandler.createSign());//签名
        retInfo = clientHandler.getXmlBody();
        System.out.println("再次发参数：" + retInfo);

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(retInfo.getBytes());
        out.flush();
        out.close();

    }

	private Map getPayInfo(HttpServletRequest request) throws IOException, JDOMException {
        BufferedReader reader = request.getReader();
        StringBuffer jb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            jb.append(line);
        }

        System.out.println(jb.toString());


        return XMLUtil.doXMLParse(jb.toString());
    }

//    @RequestMapping("reNotify.do")
//    public void reNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String resXml;
//        Map<String, String> map = getPayInfo(request);
//
//
//        String result_code = map.get("result_code");//用户在商户appid下的唯一标识
//
//        if ("SUCCESS".equals(result_code)) {
//            // 这里是支付成功
//            //System.out.println("支付成功通道!-----------");
//
//
//
//            //String is_subscribe = (String) map.get("is_subscribe");
//            String out_trade_no = (String) map.get("out_trade_no");
//            String total_fee = (String) map.get("total_fee");
//            String subject = (String)map.get("attach");
//            BigDecimal t_pr = new BigDecimal(total_fee);
//            BigDecimal b2 = new BigDecimal(100);
//            total_fee= t_pr.divide(b2).toString();
////            WeiXinPay.doSometh(out_trade_no,total_fee,subject);
//
//            System.out.println("交易号："+out_trade_no);
//
//            log.info("支付成功");
//            // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//
//        } else {
//            log.info("支付失败,错误信息：" + map.get("err_code"));
//            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//        }
//
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        out.write(resXml.getBytes());
//        out.flush();
//        out.close();
//    }
//
//    
//
//    //退款
//    @RequestMapping("reFund.do")
//    public void ReFundTradeAction(HttpServletRequest request, HttpServletResponse response)
//    {
//        Session session1 = null;
//        Connection con = null;
//        Statement st = null;
//        String sql="";
//        String out_trade_no = request.getParameter("out_trade_no");
//        String refund_fee = request.getParameter("refund_fee");
//        String total_fee = request.getParameter("total_fee");
//        System.out.println("退款信息："+out_trade_no+refund_fee);
//        response.setContentType("UTF-8");
//
//
//        try {
//            session1 = HibernateSessionFactory.getSession();
//            con = session1.connection();
//            st = con.createStatement();
//            session1.getTransaction().begin();
//            String rs = WeiXin.ReFound(out_trade_no, Integer.valueOf(total_fee), Integer.valueOf(refund_fee));
//            if(rs.substring(0,4).equals("退款成功")) {
//
//                sql = "update ez_cert_update_olinereq b1 set b1.ispay='1' ,b1.auditstatus='1' WHERE b1.ORDERNUMBER ='" + out_trade_no + "'";
//                System.out.println(sql);
//                st.executeUpdate(sql);
//                session1.getTransaction().commit();
//            }
//            reNewTrade(out_trade_no);
//            PrintWriter out = response.getWriter();
//            out.write(rs);
//            out.close();
//        }catch (Exception e)
//        {
//            session1.getTransaction().rollback();
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                if (st != null) {
//                    st.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//
//    //更新订单|查询订单信息
//    @RequestMapping("viewTradeInfo.do")
//    public void viewTradeInfo(HttpServletRequest request)
//    {
//        String out_trade_no = request.getParameter("out_trade_no");
//        reNewTrade(out_trade_no);
//    }
//   /* @RequestMapping("getRedirect.do")
//    public String test(HttpServletResponse request,HttpServletResponse response) throws Exception
//    {
//
//
//        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?" +
//                "appid="+ConstantUtil.APP_ID +
//                "redirect_uri=http://183.230.175.249:8007/easyca/certserver/getfp.jsp" +
//                "?response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
//
//    }*/
//
//    //关闭订单
//    @RequestMapping("closeOrder.do")
//    public void closeOrder(HttpServletRequest request)
//    {
//        String out_trade_no = request.getParameter("out_trade_no");
//
//        try {
//            WeiXin.CloseOrder(out_trade_no);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//        reNewTrade(out_trade_no);
//
//    }
//
////查看订单列表
//    @RequestMapping("ViewWXTradeList.do")
//    public void ViewTradeList(HttpServletRequest request,HttpServletResponse response)
//    {
///*
////test
//        System.out.println("++++++++++++++++++++++");
//        String URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("OPENID","oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
//        String jsonStr = CommonUtil.sendGet(URL);
//        request.setAttribute("jsonStr",jsonStr);
//        System.out.println(jsonStr);
//*/
//
//        try
//        {
//            Session session = HibernateSessionFactory.getSession();
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            int count = 0;
//            int offset = 0;
//            int totalPage = 0;
//            int page = 1;
//            int state = -1;
//            if (request.getParameter("state")!=null&&request.getParameter("state")!="")
//               state = Integer.valueOf(request.getParameter("state"));
//
////取得交易总数，分页数
//
//            String sql=" SELECT COUNT(*) AS \"COUNT\"  FROM  EZ_WEIXINPAY_PAYLIST WHERE 1=1";
//            if(state!=-1)
//                sql = sql +" AND TRADE_STATE = '"+state+"'";
//            SQLQuery query = session.createSQLQuery(sql);
//            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//            List<Map<String, Object>> list = query.list();
//            if(list != null && list.size()>0) {
//                count = ((BigDecimal)(list.get(0).get("COUNT"))).intValue();
//                if(count%20==0)
//                {
//                    totalPage = count/20;
//                }
//                else
//                {
//                    totalPage = count/20 + 1;
//                }
//            }
//
//
//
//            String action=request.getParameter("action");
//            if(action!=null&&!action.equals("")) {
//                page = Integer.valueOf(request.getParameter("page"));
//
//                if(action.equals("next"))
//                {
//                    if(!((page+1)>totalPage))
//                       page = page+1;
//                }
//                if(action.equals("prev"))
//                {
//
//                    if(!((page-1)<1))
//                        page = page-1;
//                }
//                if(action.equals("first"))
//                {
//                    page = 1;
//                }
//                if(action.equals("last"))
//                {
//                    page = totalPage;
//                }
//
//            }
//
//
//
//            offset = page*20;
//
//        sql = "select b.id,b.ordernumber,b.certsn,b.flag,b.trade_no,b.productname,b.price,b.trade_state,b.createtime " +
//                "from (select rownum \"row_num\",f.* from ez_weixinpay_paylist f ) b where \"row_num\" " +
//                "between "+(offset-20)+" and "+offset;
//            if(state!=-1)
//                sql = "select b.id,b.ordernumber,b.certsn,b.flag,b.trade_no,b.productname,b.price,b.trade_state,b.createtime " +
//                        "from (select rownum \"row_num\",f.* from ez_weixinpay_paylist f WHERE TRADE_STATE = '"+state+"') b where \"row_num\" " +
//                        "between "+(offset-20)+" and "+offset;
//        query = session.createSQLQuery(sql);
//            List list1 = query.list();
//
//            request.setAttribute("list",list1);
//            request.setAttribute("page",page);
//            request.setAttribute("count",count);
//            request.setAttribute("state",state);
//            request.setAttribute("totalPage", totalPage);
//            request.getRequestDispatcher("weixin_trade_list.jsp").forward(request,response);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//    @RequestMapping("loadTradeRecord.do")
//    public void loadTradeRecord(HttpServletRequest request,HttpServletResponse response)
//    {
//        Session session = HibernateSessionFactory.getSession();
//        response.setContentType("text/html");
//
//        String state = request.getParameter("state");
//        String startTime = request.getParameter("startTime");
//        String endTime = request.getParameter("endTime");
//        String sql = "SELECT t.ordernumber \"订单号\",t.certsn \"证书序列号\",'已支付' as \"订单状态\",t.price \"金额\" FROM ez_weixinpay_paylist t where 1=1";
//        System.out.println("success");
//       /* SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
//        Date start;
//        Date end;
//        try {
//            start = fmt.parse(startTime);
//            end = fmt.parse(endTime);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        if(state!="-1")
//        {
//            sql = sql +" and t.TRADE_STATE = '1' ";
//        }*/
//
//        sql = sql+ " and to_char(CREATETIME,'yyyy/mm/dd') between '"+startTime+"' and '"+endTime+"'";
//
//        sql = sql +" and t.TRADE_STATE = '1' ";
//        System.out.println(sql);
//        SQLQuery query = session.createSQLQuery(sql);
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//        List<Map<String, Object>> list = query.list();
//        try {
//
//			ExcelUitl util = new ExcelUitl();
//            response.setHeader("Content-Disposition", "attachment;filename="+"test.xls" );
//            response.setContentType("application/binary;charset=UTF-8");
//            util.outExcel(list,response.getOutputStream());
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//    //zx获取订单交易状态
//    @RequestMapping("selectPayState.do")
//    public void selectPayState(HttpServletRequest request, HttpServletResponse response) throws IOException
//    {
//        Session session = HibernateSessionFactory.getSession();
//        response.setContentType("text/html");
//        
//        PrintWriter out = response.getWriter();
//        String str="传入参数错误!";
//        String state="";
//        try {
//
//            String ordernumber = request.getParameter("ordernumber");//获取参数
//            String sql="select * from  EZ_WEIXINPAY_PAYLIST where ORDERNUMBER='"+ordernumber+"'";
////				session.beginTransaction();//开启事务
//            SQLQuery query = session.createSQLQuery(sql);
//            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//            List<Map<String, Object>> list = query.list();
//            if(list != null && list.size()>0){
//                for(int i = 0 ;i<list.size();i++){
//                    Map mp = (HashMap)list.get(0);
//                    state = mp.get("TRADE_STATE").toString();
//                    break;
//                }
//            }
//            if(state.trim().length()>0 && state.equals("1")){
//                str = "success";
//            }
////				session.getTransaction().commit();//提交
////				str="修改成功!";
////				session.getTransaction().rollback();
//           // System.out.println(str);
//            out.print(str);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                out.print(str);
//            } catch (Exception ex) {}
//        }finally {
//            session.flush();
//            session.clear();
//            session.close();
//        }
//        out.flush();
//        out.close();
//    }
//
//    public  void reNewTrade(String out_trade_no)
//    {
//        Session session1 = null;
//        Connection con = null;
//        Statement st = null;
//        String sql="";
//        try {
//
//            TradeInfo tradeInfo = WeiXin.getTradeInfo(out_trade_no);
//
//
//
//                session1 = HibernateSessionFactory.getSession();
//                con = session1.connection();
//                st = con.createStatement();
//                session1.getTransaction().begin();
//                sql="update ez_weixinpay_paylist b1 set b1.trade_state='"+tradeInfo.trade_state
//                        +"' WHERE ORDERNUMBER ='"+out_trade_no+"'";
//                st.executeUpdate(sql);
//                session1.getTransaction().commit();
//
//
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            if(session1!=null)
//            session1.getTransaction().rollback();
//
//        }
//        finally {
//            try {
//                if (st != null) {
//                    st.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//                    session1.flush();
//                    session1.clear();
//                    session1.close();
//                }
//            catch (Exception ex)
//                {
//                     ex.printStackTrace();
//                }
//        }
//    }
//
//
//    @RequestMapping("uploadRecords.do")
//    public void uploadRecords(HttpServletRequest request,HttpServletResponse response) throws Exception
//    {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        String path =request.getClass().getClassLoader().getResource("").getPath();
//        File file = new File(path+"\\temp.csv");
//        if (file.exists())
//            file.delete();
//        file.createNewFile();
//
//        MultipartFile mfile=((MultipartHttpServletRequest)request).getFile("file");
//        mfile.transferTo(file);
//        read(file,response);
//    }
//
//    private static int total = 0;
//    private static int sames = 0;
//    public static void read(File file,HttpServletResponse response) {
//        try {
//            Writer out =response.getWriter();
//            Session session1 = HibernateSessionFactory.getSession();
//            FileInputStream in = new FileInputStream(file);
//            String code = codeString(new BufferedInputStream(in));
//            BufferedReader bur =  new BufferedReader(new InputStreamReader(new FileInputStream(file),code));
//            //System.out.println(this.codeString(new BufferedInputStream(file.getInputStream())));
//            System.out.println(code);
//            List<String> list = new ArrayList();
//            String lineTxt = null;
//            while ((lineTxt=bur.readLine())!=null) {
//               /* System.out.println("未转化前："+lineTxt);
//                lineTxt = new String(lineTxt.getBytes("iso-8859-1"),"UTF-8");*/
//               // System.out.println(lineTxt);
//                list.add(lineTxt);
//            }
//
//            list.remove(0);
//            in.close();
//            bur.close();
//            list.remove(list.size()-1);
//            list.remove(list.size()-1);
//            session1.beginTransaction();
//            for(String t : list)
//            {
//                String[] strings=t.split("`");
//                if(strings.length!=25)
//                {
//                    String result = "请传人正确的格式";
//                    String outbody="<html>\n" +
//                            "<head>\n" +
//                            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                            "    <title>导入微信交易清单</title>\n" +
//                            "</head>"+
//                            "<body><div style=\"width: 400px;margin: 0 auto;padding:30px\">"+result+
//                            "</div></body></html>";
//                    out.write(outbody);
//                    return;
//                }
//                String[] son = strings[22].split("-");
//                if(son.length<4)
//                {
//                    String[] nson = new String[4];
//                    nson[0] = son[0];
//                    nson[1] = "未知";
//                    nson[2] = son[1];
//                    nson[3] = son[2];
//                    son = nson;
//
//                }
//                else if(son.length==5)
//            {
//                String[] nson = new String[4];
//                nson[0] = son[0]+"-"+son[1];
//                nson[1] = son[2];
//                nson[2] = son[3];
//                nson[3] = son[4];
//                son = nson;
//
//            }
//                String refound_status = strings[20].replace(",","");
//                if (refound_status.equals("")||refound_status==null){
//                    refound_status = "-";
//                }
//               /* String sql ="insert into weixin_bills_check VALUES ('','"+strings[0].replace(",","")+"'," +
//                        "'','','','','','','','','','','','','','','','','','','','','','')";*/
//                String sql="select * from weixin_bills_check t where t.pay_time = ? and t.wx_trade_no = ? and t.ra_order_no= ? and t.wx_openid = ? and " +
//                        "  t.trade_type= ? and t.order_state= ? and t.order_money= ? and t.wx_trade_refund_no = ? and " +
//                        " t.ra_order_refund_no= ? and t.order_refund_money = ? and t.order_refund_status= ? and t.order_subject=? and t.ra_project=? and " +
//                        " t.unit_name= ? and t.cert_name= ? and t.use_type= ? and t.fee = ? and t.flag_state= ? ";
//                PreparedStatement pr =session1.connection().prepareStatement(sql);
//                pr.setString(1,strings[1].replace(",",""));
//                pr.setString(2,strings[6].replace(",",""));
//                pr.setString(3,strings[7].replace(",",""));
//                pr.setString(4,strings[8].replace(",",""));
//                pr.setString(5,strings[9].replace(",",""));
//                pr.setString(6,strings[10].replace(",",""));
//                pr.setString(7,strings[13].replace(",",""));
//                pr.setString(8,strings[15].replace(",",""));
//                pr.setString(9,strings[16].replace(",",""));
//                pr.setString(10,strings[17].replace(",",""));
//                pr.setString(11,refound_status);
//                pr.setString(12,strings[21].replace(",",""));
//                pr.setString(13,son[0].replace(",",""));
//                pr.setString(14,son[1].replace(",",""));
//                pr.setString(15,son[2].replace(",",""));
//                pr.setString(16,son[3].replace(",",""));
//                pr.setString(17,strings[23].replace(",",""));
//                pr.setString(18,"0");
//                ResultSet rs = pr.executeQuery();
//
//                if (rs.next()){
//                    sames++;
//                    pr.close();
//                    continue;
//                }
//                pr.close();
//               /* sql="insert into weixin_bills_check VALUES ('"+ UUID.randomUUID().toString().substring(0,30)+"','"+strings[1].replace(",","")+"'," +
//                        "'"+strings[6].replace(",","")+"','"+strings[7].replace(",","")+"','"+strings[8].replace(",","")+"',"
//                        +"'"+strings[9].replace(",","")+"','"+strings[10].replace(",","")+"','"+strings[13].replace(",","")+"','"+strings[15].replace(",","")+"'"
//                        +",'"+strings[16].replace(",","")+"','"+strings[17].replace(",","")+"','"+strings[20].replace(",","")+"','"+strings[21].replace(",","")+"',"
//                        +"'"+son[0].replace(",","")+"','"+son[1].replace(",","")+"','"+son[2].replace(",","")+"','"+son[2].replace(",","")+"','"+strings[23].replace(",","")+"','0')";*/
//                sql = "insert into weixin_bills_check VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
//                pr = session1.connection().prepareStatement(sql);
//                pr.setString(1,UUID.randomUUID().toString().substring(0, 30));
//                pr.setString(2,strings[1].replace(",",""));
//                pr.setString(3,strings[6].replace(",",""));
//                pr.setString(4,strings[7].replace(",",""));
//                pr.setString(5,strings[8].replace(",",""));
//                pr.setString(6,strings[9].replace(",",""));
//                pr.setString(7,strings[10].replace(",",""));
//                pr.setString(8,strings[13].replace(",",""));
//                pr.setString(9,strings[15].replace(",",""));
//                pr.setString(10,strings[16].replace(",",""));
//                pr.setString(11,strings[17].replace(",",""));
//                pr.setString(12,refound_status);
//                pr.setString(13,strings[21].replace(",",""));
//                pr.setString(14,son[0].replace(",",""));
//                pr.setString(15,son[1].replace(",",""));
//                pr.setString(16,son[2].replace(",",""));
//                pr.setString(17,son[3].replace(",",""));
//                pr.setString(18,strings[23].replace(",",""));
//                pr.setString(19,"0");
//                int ed = pr.executeUpdate();pr.close();
//                total = total + ed;
//                if(ed==0)
//                    System.out.println("插入"+ed+"条记录异常");
//                // System.out.println(sql);
//
//            }
//
//
//            session1.getTransaction().commit();
//            System.out.println("本次成功导入" + total + "记录 \r\n" + "有" + sames + "条重复记录未导入");
//            String result = "本次成功导入" + total + "记录 \r\n" + "有" + sames + "条重复记录未导入";
//            String outbody="<html>\n" +
//                    "<head>\n" +
//                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                    "    <title>导入微信交易清单</title>\n" +
//                    "</head>"+
//                    "<body><div style=\"width: 400px;margin: 0 auto;padding:30px\">"+result+
//                    "</div></body></html>";
//            out.write(outbody);
//            total =0;
//            sames=0;
//            file.delete();
//            session1.close();
//            out.close();
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    private static String codeString(BufferedInputStream bin) throws Exception{
//        int p = (bin.read() << 8) + bin.read();
//        String code = null;
//
//        switch (p) {
//            case 0xefbb:
//                code = "UTF-8";
//                break;
//            case 0xfffe:
//                code = "Unicode";
//                break;
//            case 0xfeff:
//                code = "UTF-16BE";
//                break;
//            default:
//                code = "GBK";
//        }
//
//        return code;
//    }
//
//   /* public static void main(String[] args) {
//       *//* SelfUpdate sf = new SelfUpdate();
//        try {
//            int rv = sf.AutoUpdateAudit("20170410181410276","24a29aa6d02dc7fa570cef6b02054897");
//            System.out.println(rv);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }*//*
//        File file = new File("D:\\test.csv");
//        System.out.println(file.length());
//        new WeiXinPay().read(file);
//        readCsv.read(file);
//    }*/
}
