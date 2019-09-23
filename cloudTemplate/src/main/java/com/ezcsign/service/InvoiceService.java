package com.ezcsign.service;

import com.ezcsign.entity.Customer;
import com.ezcsign.entity.Invoice;

import java.util.Map;

public interface InvoiceService {
    Map<String,Object> beforeApplyInvoice(String[] rechargeIds,String notInvoiceTotal, Customer customer);

    Boolean afterApplyInvoice(Invoice invoice);
}
