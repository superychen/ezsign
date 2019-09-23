package com.ezcsign.service.PayService.tenpay.weixin;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class TradeInfo {

    String appid;
    String mch_id;
    String openid;
    String trade_type;
    int trade_state;
    String bank_type;
    String total_fee;
    String settlement_total_fee;
    String time_end;
    String trade_state_desc;
    String out_refund_no;
    String out_trade_no;
    
    String transaction_id;
    String cash_fee;



    /**
     *
     * @return 公众账号ID
     */
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    /**
     *
     * @return 商户号
     */
    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    /**
     *
     * @return 用户标示
     */
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    /**
     *
     * @return 交易类型
     */
    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    /**
     *
     * @return 交易状态
     */
    public int getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        if(trade_state.equals("CLOSED")) { //已关闭
            this.trade_state = 2;
        }else if(trade_state.equals("REFUND")) { // 退款
            this.trade_state = 3;
        }else if(trade_state.equals("SUCCESS")) {//支付成功
            this.trade_state = 1;
        }else if(trade_state.equals("NOTPAY")) {//未支付 
            this.trade_state = 0;
        }else if(trade_state.equals("FAIL")){ //订单不存在(第一次查询)
        	this.trade_state = 4;
        }
    }
    /**
     *
     * @return 付款银行
     */
    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }
    /**
     *
     * @return 标价金额
     */
    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
    /**
     *
     * @return 应结订单金额
     */
    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }
    /**
     *
     * @return 支付完成时间
     */
    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    /**
     *
     * @return 交易状态描述
     */
    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
    
    /**
     * 
     * @return 商户退款单号
     */
    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    /**
     * 订单号
     * @return
     */
    public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/**
     * 微信支付返回订单号
     * @return
     */
	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	/**
	 * 现金支付金额
	 * @return
	 */
	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

}
