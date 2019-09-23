package com.ezcsign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice implements Serializable {
    /**
     * 开票申请ID,
     */
    @NotBlank(message = "开票申请ID不能为空")
    private String invoiceId;

    /**
     * 客户类型=0、1时为关联结点ID,否则为个人信息ID
     */
    private String invoiceMemberid;

    /**
     * 客户类型:0=企业;1=API;2=个人
     */
    private Integer invoiceMembertype;

    /**
     * 开票金额,
     */
    @NotNull(message = "开票金额不能为空")
    private BigDecimal invoiceAmount;

    /**
     * 开票操作时间,
     */
    private Date invoiceOperattime;

    /**
     * 发票邮寄地址,
     */
    @NotBlank(message = "发票邮寄地址不能为空")
    private String invoiceMailaddress;

    /**
     * 邮寄联系人,
     */
    @NotBlank(message = "邮寄联系人并不能为空")
    private String invoiceMailcontactor;

    /**
     * 邮寄电话,
     */
    @NotBlank(message = "邮寄电话不能为空")
    private String invoiceMailtelephone;

    /**
     * 发票状态,0 未开票;1 已开票;2 未成功;3 提交发票申请未开票;4 开票中
     */
    private Integer invoiceStatus;

    @NotBlank(message = "发票接收邮箱不能为空")
    private String invoiceEmail;

    /**
     * 发票类型,1 在线普票;2 在线专票;3 线下普票;4 线下专票
     */
    @NotNull(message = "发票类型不能为空")
    private Integer invoiceType;

    /**
     * 发票文件id,
     */

    private String invoiceFileid;

    /**
     * 发票号,
     */
    private String invoiceCode;

    /**
     * 开票抬头,
     */
    @NotBlank(message = "开票抬头不能为空")
    private String invoiceName;

    /**
     * 纳税人识别号,
     */
    @NotBlank(message = "纳税人识别号不能为空")
    private String invoiceCreditcode;

    /**
     * 地址,
     */
    @NotBlank(message = "地址不能为空")
    private String invoiceAddress;

    /**
     * 电话,
     */
    @NotBlank(message = "电话不能为空")
    private String invoiceTelephone;

    /**
     * 规格型号,
     */
    private String invoiceModel;

    /**
     * 票面时间,
     */
    private Date invoiceInvoicetime;

    /**
     * 服务名称,
     */
    private String invoiceServicename;

    /**
     * 开户行,
     */
    @NotBlank(message = "开户行不能为空")
    private String invoiceBankname;

    /**
     * 开户账户,
     */
    @NotBlank(message = "开户账户不能为空")
    private String invoiceBankaccount;

    /**
     * 单位（套/个）,
     */
    private String invoiceUnit;

    /**
     * 数量,
     */
    private Integer invoiceCount;

    /**
     * 单价,
     */
    private BigDecimal invoiceUnitprice;

    /**
     * 税率,
     */
    private Long invoiceTaxrate;

    /**
     * 票面金额,
     */
    private BigDecimal invoiceInvoiceamount;

    /**
     * 税额,
     */
    private BigDecimal invoiceTaxamount;

    /**
     * 销售方名称,
     */
    private String invoiceSellername;

    /**
     * 销售方纳税人识别号,
     */
    private String invoiceSellercreditcode;

    /**
     * 销售方地址,
     */
    private String invoiceSelleraddress;

    /**
     * 销售方电话,
     */
    private String invoiceSellertel;

    /**
     * 销售方开户行,
     */
    private String invoiceSellerbkname;

    /**
     * 销售方开户账号,
     */
    private String invoiceSellerbkaccount;

    /**
     * 备注,
     */
    private String invoiceNote;

    /**
     * 收款人,
     */
    private String invoicePayee;

    /**
     * 审核人,
     */
    private String invoiceAuditor;

    /**
     * 开票人,
     */
    private String invoiceDrawer;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者id
     */
    private String createId;

    /**
     * 创建者姓名
     */
    private String createName;

    /**
     * 修改者id
     */
    private String updateId;

    /**
     * 修改者姓名
     */
    private String updateName;

    /**
     * 修改者时间
     */
    private Date updateDatetime;

    /**
     * 数据状态:0=正常数据;1=逻辑删除
     */
    private Integer status;
}