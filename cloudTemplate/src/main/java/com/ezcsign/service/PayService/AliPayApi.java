package com.ezcsign.service.PayService;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.ezcsign.service.PayService.alipay.trade.Main;
import com.ezcsign.service.PayService.alipay.trade.config.Configs;
import com.ezcsign.service.PayService.alipay.trade.model.ExtendParams;
import com.ezcsign.service.PayService.alipay.trade.model.GoodsDetail;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayHeartbeatSynRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.hb.*;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FPrecreateResult;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FQueryResult;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FRefundResult;
import com.ezcsign.service.PayService.alipay.trade.service.AlipayMonitorService;
import com.ezcsign.service.PayService.alipay.trade.service.AlipayTradeService;
import com.ezcsign.service.PayService.alipay.trade.service.impl.AlipayMonitorServiceImpl;
import com.ezcsign.service.PayService.alipay.trade.service.impl.AlipayTradeServiceImpl;
import com.ezcsign.service.PayService.alipay.trade.utils.Utils;
import com.ezcsign.service.PayService.alipay.trade.utils.ZxingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2019-09-17.
 */
@Component
public class AliPayApi {
    private static Log                  log = LogFactory.getLog(Main.class);

    @Autowired
    private HttpSession session;

    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

//    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
//    private static AlipayTradeService   tradeWithHBService;
//
    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

//        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
//        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();
//
        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();
    }


    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }


    // 系统商的调用样例，填写了所有系统商商需要填写的字段
    public void test_monitor_sys() {
        // 系统商使用的交易信息格式，json字符串类型
        List<SysTradeInfo> sysTradeInfoList = new ArrayList<SysTradeInfo>();
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000001", 5.2, HbStatus.S));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000002", 4.4, HbStatus.F));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000003", 11.3, HbStatus.P));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000004", 3.2, HbStatus.X));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000005", 4.1, HbStatus.X));

        // 填写异常信息，如果有的话
        List<ExceptionInfo> exceptionInfoList = new ArrayList<ExceptionInfo>();
        exceptionInfoList.add(ExceptionInfo.HE_SCANER);
        //        exceptionInfoList.add(ExceptionInfo.HE_PRINTER);
        //        exceptionInfoList.add(ExceptionInfo.HE_OTHER);

        // 填写扩展参数，如果有的话
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        //        extendInfo.put("SHOP_ID", "BJ_ZZ_001");
        //        extendInfo.put("TERMINAL_ID", "1234");

        String appAuthToken = "应用授权令牌";//根据真实值填写

        AlipayHeartbeatSynRequestBuilder builder = new AlipayHeartbeatSynRequestBuilder()
                .setAppAuthToken(appAuthToken).setProduct(Product.FP).setType(Type.CR)
                .setEquipmentId("cr1000001").setEquipmentStatus(EquipStatus.NORMAL)
                .setTime(Utils.toDate(new Date())).setStoreId("ezca_store_id").setMac("0a:00:27:00:00:00")
                .setNetworkType("LAN").setProviderId("2088100200300400500") // 设置系统商pid
                .setSysTradeInfoList(sysTradeInfoList) // 系统商同步trade_info信息
                //                .setExceptionInfoList(exceptionInfoList)  // 填写异常信息，如果有的话
                .setExtendInfo(extendInfo) // 填写扩展信息，如果有的话
                ;

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);
        dumpResponse(response);
    }

    //创建支付二维码;
    public PayResult CreatePayCode(String amount)
    {
        PayResult pRes=new PayResult();
        pRes.imgBase64=null;
        pRes.errorMsg="未提交支付请求";
        pRes.amount=amount;
        pRes.errorcode=-1;

        String outTradeNo = "esign" + System.currentTimeMillis()+ (long) (Math.random() * 10000000L);
        pRes.tradeNo=outTradeNo;

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "易通签商务云平台账户充值";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = amount;

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "易通签账户购买服务需充值"+amount+"元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "esign_alipay";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "ezca_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        //GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        //goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        //GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        //goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                //.setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);
        log.info("在这里需要向session存入outTradeNo-->"+outTradeNo);
        //在这里向session里面存入outTradeNo
        session.setAttribute("outTradeNo",outTradeNo);
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                pRes.errorcode=0;
                pRes.errorMsg="支付宝预下单成功";
                log.error(pRes.errorMsg);
                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);
                // 需要修改为运行机器上的路径
                byte[] imgBytes=ZxingUtils.getQRCodeImgeIO(response.getQrCode(), 256);
                pRes.imgBase64= Base64.toBase64String(imgBytes);
                break;

            case FAILED:
                pRes.errorcode=-2;
                pRes.errorMsg=("支付宝预下单失败!!!");
                log.error(pRes.errorMsg);
                break;

            case UNKNOWN:
                pRes.errorcode=-3;
                pRes.errorMsg=("系统异常，预下单状态未知!!!");
                log.error(pRes.errorMsg);
                break;

            default:
                pRes.errorcode=-4;
                pRes.errorMsg=("不支持的交易状态，交易返回异常!!!");
                log.error(pRes.errorMsg);
                break;
        }
        return pRes;
    }


    public ReFundResult ReFundPay(String tradeNo, String amount)
    {
        ReFundResult rRes=new ReFundResult();
        rRes.tradeNo=tradeNo;
        rRes.errorcode=-1;
        rRes.errorMsg="退款失败";
        rRes.amount=amount;
        String outTradeNo =tradeNo;

        // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
        String refundAmount = amount;

        // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
        // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
        String outRequestNo = "";

        // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
        String refundReason = "本次充值出现问题，暂停交易，并退还付款";

        // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
        String storeId = "ezca_store_id";

        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
                .setOutRequestNo(outRequestNo).setStoreId(storeId);

        AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                rRes.errorcode=0;
                rRes.errorMsg="支付宝退款成功";
                log.info(rRes.errorMsg);
                break;

            case FAILED:
                rRes.errorcode=-2;
                rRes.errorMsg="支付宝退款失败!";
                if(result.getResponse().getCode().compareTo("40004")==0)
                {
                    rRes.errorcode=-6;
                    rRes.errorMsg="交易未支付!";
                }
                log.info(rRes.errorMsg);
                break;

            case UNKNOWN:
                rRes.errorcode=-3;
                rRes.errorMsg="系统异常，订单退款状态未知!";
                log.info(rRes.errorMsg);
                break;

            default:
                rRes.errorcode=-4;
                rRes.errorMsg="不支持的交易状态，交易返回异常!";
                log.info(rRes.errorMsg);
                break;
        }
        return  rRes;
    }


    /**
     * @param tradeNo
     * @return
     */
    //查询付款状态
    public TradeStatus GetPayStatus(String tradeNo)
    {
        TradeStatus tStatus=new TradeStatus();
        tStatus.errorcode=-1;
        tStatus.errorMsg="查询支付状态失败";


        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder().setOutTradeNo(tradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                tStatus.errorcode=0;
                tStatus.errorMsg="查询返回该订单支付成功";
                log.info(tStatus.errorMsg);

                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);
                if(response.getCode().compareTo("10000")==0)
                {
                    tStatus.errorcode=0;
                    tStatus.errorMsg="支付成功";
                }
                else
                {
                    tStatus.errorMsg=response.getMsg();
                    tStatus.errorcode=-5;
                }
                tStatus.payerCode=response.getBuyerUserId();
                tStatus.payerId=response.getBuyerLogonId();
                tStatus.amount=response.getTotalAmount();
                tStatus.payTime=String.valueOf( response.getSendPayDate().getTime());

//                log.info(response.getTradeStatus());
//                if (Utils.isListNotEmpty(response.getFundBillList()))
//                {
//                    for (TradeFundBill bill : response.getFundBillList())
//                    {
//                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
//                    }
//                }
                break;

            case FAILED:
                tStatus.errorcode=-2;
                tStatus.errorMsg="查询返回该订单支付失败或被关闭!";
                if(result.getResponse().getCode().compareTo("40004")==0)
                {
                    tStatus.errorcode=-6;
                    tStatus.errorMsg="交易未支付!";
                }
                log.info(tStatus.errorMsg);
                break;

            case UNKNOWN:
                tStatus.errorcode=-3;
                tStatus.errorMsg="系统异常，订单支付状态未知!";
                log.info(tStatus.errorMsg);
                break;

            default:
                tStatus.errorcode=-4;
                tStatus.errorMsg="不支持的交易状态，交易返回异常!";
                log.info(tStatus.errorMsg);
                break;
        }
        return  tStatus;
    }


 public static void main(String[] args) {
    AliPayApi api=new AliPayApi();

    //生成支付二维码;
     PayResult res= api.CreatePayCode("0.01");

     byte[] imgBytes =Base64.decode(res.imgBase64);
     // 2、将字节数组转为二进制
     BASE64Encoder encoder = new BASE64Encoder();
     String binary = encoder.encodeBuffer(imgBytes).trim();
     System.out.println(binary);
//    PdfControl.saveFile(imgBytes,"C:\\Users\\Administrator\\Desktop\\payimg.png");

    //查询支付状态;
     String tradeCode="esigntrade15687777411511106344";
     TradeStatus ss=api.GetPayStatus(tradeCode);

    //当支付出现问题时，退回本次支付货款，并终止交易
     ReFundResult rr=api.ReFundPay(tradeCode,"0.01");

     ss=api.GetPayStatus(tradeCode);
    }

}
