package com.ezcsign.service.PayService.alipay.trade;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ezcsign.service.PayService.alipay.trade.config.Configs;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradePayRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FPayResult;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FPrecreateResult;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FQueryResult;
import com.ezcsign.service.PayService.alipay.trade.model.result.AlipayF2FRefundResult;
import com.ezcsign.service.PayService.alipay.trade.service.AlipayTradeService;
import com.ezcsign.service.PayService.alipay.trade.service.impl.AlipayTradeServiceImpl;

@SuppressWarnings({ "unused"})
public class InterFaceAlipayUtil {
	private static AlipayTradeService tradeService;

	static {
		/**
		 * 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
		 * Configs会读取classpath下的zfbinfo
		 * .properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
		 */
		Configs.init("zfbinfo.properties");

		/**
		 * 使用Configs提供的默认参数 AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
		 */
		tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
	}

	/**
	 * 当面付2.0_二维码支付
	 * 
	 * @param qrTradeNo
	 *            商品订单号
	 * @param qrSubject
	 *            商品说明   (描述用户的支付目的)
	 * @param qrMoney
	 *            总金额   (【订单总金额】=【打折金额】+【不可打折金额】)
	 * @param qrDiscount
	 *            折扣金额
	 * @param qrStoreId
	 *            商户门店编号
	 * @return
	 */
	public static AlipayTradePrecreateResponse QRCodePayOrder(String qrTradeNo,
			String qrSubject, String qrMoney, String qrDiscount,
			String qrStoreId) throws Exception {

		// 支付超时，定义为120分钟
		String timeoutExpress = "120m";

		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
				.setOutTradeNo(qrTradeNo).setSubject(qrSubject)
				.setTotalAmount(qrMoney).setDiscountableAmount(qrDiscount)
				.setStoreId(qrStoreId).setTimeoutExpress(timeoutExpress);
		AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
		return result.getResponse();
	}

	/**
	 * 当面付2.0_扫码支付
	 * 
	 * @param scanTradeNo
	 *            商品订单号   (商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线)
	 * @param scanSubjeact
	 *            商品说明   (描述用户的支付目的)
	 * @param scanTotalAmount
	 *            支付总金额   (【订单总金额】=【打折金额】+【不可打折金额】)
	 * @param scanDiscountableAmount
	 *            订单可打折金额
	 * @param scanAuthCode
	 *            付款条码
	 * @param scanStoreId
	 *            商户门店编号
	 * @return
	 */
	public static AlipayTradePayResponse scanCodePayOrder(String scanTradeNo,
			String scanSubjeact, String scanAuthCode, String scanTotalAmount,
			String scanDiscountableAmount, String scanStoreId) throws Exception {

		// 支付超时，线下扫码交易定义为5分钟
		String timeoutExpress = "5m";

		// 创建条码支付请求builder，设置请求参数
		AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
				.setOutTradeNo(scanTradeNo).setSubject(scanSubjeact)
				.setAuthCode(scanAuthCode).setTotalAmount(scanTotalAmount)
				.setDiscountableAmount(scanDiscountableAmount)
				.setStoreId(scanStoreId).setTimeoutExpress(timeoutExpress);

		// 调用tradePay方法获取当面付应答
		AlipayF2FPayResult result = tradeService.tradePay(builder);
		return result.getResponse();
	}

	/**
	 * 当面付2.0_查询记录
	 * 
	 * @param queryOutTradeNo
	 *            商户订单号
	 * @return
	 */
	public static AlipayF2FQueryResult tradeQuery(String queryOutTradeNo)
			throws Exception {
		// 创建查询请求builder，设置请求参数
		AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
				.setOutTradeNo(queryOutTradeNo);

		AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
		return result;
	}

	/**
	 * 当面付2.0_退款
	 * 
	 * @param refundOutTradeNo
	 *            退款订单号
	 * @param refundMoney
	 *            退款金额 (退款金额必须小于等于订单的支付金额，单位为元)
	 * @param refundOutRequestNo
	 *            部分退款金额标识  (部分退款必填：HZ01RF002)
	 * @param refundCause
	 *            退款原因
	 * @param refundStoreId
	 *            商户门店编号
	 * @return
	 */
	public static AlipayTradeRefundResponse tradeRefund(
			String refundOutTradeNo, String refundMoney,
			String refundOutRequestNo, String refundCause, String refundStoreId)
			throws Exception {
		// 创建退款请求builder，设置请求参数
		AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
				.setOutTradeNo(refundOutTradeNo).setRefundAmount(refundMoney)
				.setRefundReason(refundCause)
				.setOutRequestNo(refundOutRequestNo).setStoreId(refundStoreId);

		AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
		return result.getResponse();
	}

	public static void main(String[] args) {
		try {
			//  AlipayTradePayResponse res = InterFaceAlipayUtil.scanCodePayOrder("tradepay15095227470411137578","seven-eleven(弹子石店)", "10", "6", "287834363708783421", "se_123456"); //扫码支付
			 AlipayTradePrecreateResponse res = InterFaceAlipayUtil.QRCodePayOrder("test2018022415251111","测试支付超时", "0.01", "0","测试");  //二维码支付
//			 AlipayF2FQueryResult res = InterFaceAlipayUtil.tradeQuery("T20180202114118");  //查询订单
//			 AlipayTradeRefundResponse res = InterFaceAlipayUtil.tradeRefund("T2017121617575087297705838","1.00","HZ01RF002", "正常退款", "test_store_id"); // 退款
			 System.err.println("生成二维码信息1______"+res.getQrCode());
			 System.err.println("生成二维码信息2______"+res.toString());
//			 System.err.println("______"+res.getResponse().getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
