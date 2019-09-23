package com.ezcsign.service.impl;

import com.ezcsign.comm.MD5Hash;
import com.ezcsign.comm.exception.ExceptionEnums;
import com.ezcsign.comm.exception.ZxException;
import com.ezcsign.entity.Customer;
import com.ezcsign.entity.Invoice;
import com.ezcsign.feign.CommService;
import com.ezcsign.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 开票业务层
 * @Author: cqyc
 * @Date: 2019-9-6
 */
@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private HttpSession session;

    @Autowired
    private CommService commService;

    @Override
    public Map<String, Object> beforeApplyInvoice(String[] rechargeIds,String notInvoiceTotal, Customer customer) {
        Invoice invoice = new Invoice();
        String userId = (String) session.getAttribute("userId");
        BigDecimal bd = new BigDecimal(notInvoiceTotal);
        //发票申请Id
        invoice.setInvoiceId(MD5Hash.UUIDCreate());
        invoice.setInvoiceMembertype(customer.getUserType());
        invoice.setInvoiceMemberid(userId);
        invoice.setInvoiceAmount(bd);
        invoice.setInvoiceOperattime(new Date());//发票操作时间
        invoice.setInvoiceStatus(3); //提交发票申请未开票
        invoice.setInvoiceType(1);//在线普票
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(invoice.getInvoiceOperattime());

        String sql = "insert into invoice (invoice_id,invoice_membertype,invoice_memberid,invoice_amount,invoice_operattime," +
                "invoice_status,invoice_type) values ('"+invoice.getInvoiceId()+"','"+invoice.getInvoiceMembertype()+"'," +
                "'"+invoice.getInvoiceMemberid()+"','"+invoice.getInvoiceAmount()+"','"+formatDate+"'," +
                "'"+invoice.getInvoiceStatus()+"','"+invoice.getInvoiceType()+"')";
        Map<String, Object> map = commService.executeSql(sql);
        log.info("执行后的sql语句----->{}",map);
        String upData = String.valueOf(map.get("data"));
        //比较当前比较的结果
        if (!StringUtils.equals(upData,"1")) {
            throw new ZxException(ExceptionEnums.INVOICE_CREATE_ERROR);
        }
        //循环修改
        String updateSql = "";
        for (int i = 0; i < rechargeIds.length; i++) {
            updateSql = "update recharge set recharge_invoiceid = '"+invoice.getInvoiceId()+"', recharge_invoicestatus= '2' " +
                    " where recharge_id = '"+rechargeIds[i]+"' ";
            Map<String, Object> updateMap = commService.executeSql(updateSql);
            String upRes = String.valueOf(updateMap.get("data"));
            //比较当前比较的结果
            if (!StringUtils.equals(upRes,"1")) {
                throw new ZxException(ExceptionEnums.INVOICE_CREATE_ERROR);
            }
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("status",200);
        resMap.put("msg","修改成功");
        resMap.put("invoiceId",invoice.getInvoiceId());
        return resMap;
    }

    /**
     * 修改发票申请中的数据
     * @param invoice 发票实体类
     */
    @Override
    public Boolean afterApplyInvoice(Invoice invoice) {
        String sql = "update invoice set  invoice_amount='"+invoice.getInvoiceAmount()+"', " +
                "invoice_address = '"+invoice.getInvoiceAddress()+"', invoice_name = '"+invoice.getInvoiceName()+"', " +
                "invoice_creditcode='"+invoice.getInvoiceCreditcode()+"', invoice_telephone='"+invoice.getInvoiceTelephone()+"', " +
                "invoice_bankname='"+invoice.getInvoiceBankname()+"', invoice_bankaccount='"+invoice.getInvoiceBankaccount()+"', " +
                "invoice_mailaddress='"+invoice.getInvoiceMailaddress()+"', invoice_mailcontactor='"+invoice.getInvoiceMailcontactor()+"', " +
                "invoice_mailtelephone='"+invoice.getInvoiceMailtelephone()+"', invoice_email='"+invoice.getInvoiceEmail()+"'  " +
                " where invoice_id='"+invoice.getInvoiceId()+"'";
        Map<String, Object> map = commService.executeSql(sql);
        String res = String.valueOf(map.get("data"));
        //比较当前比较的结果
        if (!StringUtils.equals(res,"1")) {
            throw new ZxException(ExceptionEnums.INVOICE_CREATE_ERROR);
        }
        //修改用户充值表中发票的状态
        String upSql = "update recharge set recharge_invoicestatus = '3' where " +
                " recharge_invoiceid = '"+invoice.getInvoiceId()+"'";
        Map<String, Object> upMap = commService.executeSql(upSql);
        String upRes = String.valueOf(upMap.get("code"));
        //比较当前比较的结果
        if (!StringUtils.equals(upRes,"200")) {
            throw new ZxException(ExceptionEnums.INVOICE_CREATE_ERROR);
        }
        return true;
    }
}
