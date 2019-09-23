package com.ezcsign.controller;

import com.alibaba.fastjson.JSONObject;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.entity.Invoice;
import com.ezcsign.service.InvoiceService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 开票控制层
 * @Author: cqyc
 * @Date: 2019-9-6
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/before/applyInvoice")
    public Map<String,Object> beforeApplyInvoice(@RequestParam("rechargeIds") String[] rechargeIds,
                                                 @RequestParam("notInvoiceTotal") String notInvoiceTotal,
                                                 HttpSession session){
        Map<String,Object> customerMap = (Map<String, Object>) session.getAttribute("customer");
        if(customerMap.size() <= 0){
            throw new ZxException(ExceptionEnums.SESSION_HAS_ERROR);
        }
        String jsonObject = new JSONObject(customerMap).toJSONString();
        Customer customer = JSONObject.parseObject(jsonObject, Customer.class);
        return invoiceService.beforeApplyInvoice(rechargeIds,notInvoiceTotal,customer);
    }

    @PostMapping("/after/invoice")
    public Boolean afterApplyInvoice(@RequestBody @Validated Invoice invoice, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ZxException(ExceptionEnums.INVOICE_FIELDS_NOT_NULL);
        }
        return invoiceService.afterApplyInvoice(invoice);
    }
}
