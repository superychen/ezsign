package com.ezcsign.service.PayService.alipay.trade.service;

import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.ezcsign.service.PayService.alipay.trade.model.builder.AlipayHeartbeatSynRequestBuilder;

/**
 * Created by liuyangkly on 15/10/22.
 */
public interface AlipayMonitorService {

    // 可以提供给系统商/pos厂商使用
    public MonitorHeartbeatSynResponse heartbeatSyn(AlipayHeartbeatSynRequestBuilder builder);
}
