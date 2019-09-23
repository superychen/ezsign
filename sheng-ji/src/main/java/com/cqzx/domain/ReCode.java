package com.cqzx.domain;

/**
 * describe:
 *
 * @author donting
 * @date 2019/07/26
 */

import java.util.HashMap;

public class ReCode {


    public static final int OK = 200;
    public static final String OK_MSG = "请求成功";

    public static final int EXCEPTION = -1;
    public static final String EXCEPTION_MSG = "未知错误EXCEPTION";

    public static final int EMAILE_CODE_FAIL = 401;
    public static final String EMAILE_CODE_FAIL_MSG = "邮箱验证码错误";


    //--------------------------------Tan
    public static final int NOT_LOGIN = 201;
    public static final String NOT_LOGIN_MSG = "未登录！";

    public static final int NOT_DATA = 202;
    public static final String NOT_DATA_MSG = "查询无结果！";

    public static final int ERROR_IMG_VERIFICATIONS_CODE = 203;
    public static final String ERROR_IMG_VERIFICATIONS_CODE_MSG = "图片验证码错误";

    public static final int ERROR_USERNAME_OR_PASSWORD = 204;
    public static final String ERROR_USERNAME_OR_PASSWORD_MSG = "用户名或密码错误";

    public static final int ERROR_FAIL_TO_EDIT = 205;
    public static final String ERROR_FAIL_TO_EDIT_MSG = "操作失败";

    public static final int ERROR_UPDATE = 206;
    public static final String ERROR_UPDATE_MSG = "修改失败";
    public static final int ERROR_DELETE = 207;
    public static final String ERROR_DELETE_MSG = "删除失败";

    public static final int ERROR_ORDER_CANCEL_FAILUER = 208;
    public static final String ERROR_ORDER_CANCEL_FAILUER_MSG = "取消订单失败";

    public static final int ERROR_PAY = 209;
    public static final String ERROR_PAY_MSG = "支付失败";

    public static final int EXCPTION_REQUEST = 210;
    public static final String EXCPTION_REQUEST_MSG = "异常请求！！！";

    public static final int ERROR_UPDATE_LOGIN_MESSAGE = 211;
    public static final String ERROR_UPDATE_LOGIN_MESSAGE_MSG = "修改的新密码与旧密码相同！！";

    public static final int ERROR_LOGIN_MESSAGE = 212;
    public static final String ERROR_LOGIN_MESSAGE_MSG = "原密码输入错误！！";

    public static final int ERROR_INSERT = 213;
    public static final String ERROR_INSERT_MSG = "添加失败";


    //---------------------------yuan


    public static final int ERROR_ADD_GOODS_FAIL = 304;
    public static final String ERROR_ADD_GOODS_FAIL_MSG = "商品添加失败";


    public static final int ERROR_DELETE_GOODS_FAIL = 306;
    public static final String ERROR_DELETE_GOODS_FAIL_MSG = "商品删除失败";

    public static final int SELECT_GOODS_FAIL = 307;
    public static final String ERROR_SELECT_GOODS_FAIL_MSG = "商品查询失败";

    public static final int ERROR_SELECT_WITHDRAWAL_FAIL = 308;
    public static final String ERROR_SELECT_WITHDRAWAL_FAIL_MSG = "审批订单为空";

    public static final int ERROR_UPDATE_WITHDRAWAL_FAIL = 309;
    public static final String ERROR_UPDATE_WITHDRAWAL_FAIL_MSG = "提现订单审批失败";

    public static final int ERROR_UPDATE_PRODUCT_FAIL = 310;
    public static final String ERROR_UPDATE_PRODUCT_FAIL_MSG = "商品订单审批失败";

    public static final int ERROR_PURCHASE_GOODS_FAIL = 311;
    public static final String ERROR_PURCHASE_GOODS_FAIL_MSG = "商品购买失败";
    public static final int ERROR_NOT_PRODUCT = 312;
    public static final String ERROR_NOT_PRODUCT_MSG = "没有商品";
    public static final int ERROR_NOT_PURCHASE_PRODUCT = 313;
    public static final String ERROR_NOT_PURCHASE_PRODUCT_MSG = " 你以购买了商品";
    public static final int ERROR_NOT_PAY_ORDER = 314;
    public static final String ERROR_NOT_PAY_ORDER_MSG = " 你没有未支付的订单";

    public static final int ERROR_NOT_UPGRADE_ORDER = 315;
    public static final String ERROR_NOT_UPGRADE_ORDER_MSG = " 升级失败";

    public static final int ERROR_NOT_NOTICE = 316;
    public static final String ERROR_NOT_NOTICE_MSG = "当前没有公告";
    public static final int ERROR_NOT_DELETE_NOTICE = 317;
    public static final String ERROR_NOT_DELETE_NOTICE_MSG = "公告删除错误";
    public static final int ERROR_NOT_UPDATE_NOTICE = 318;
    public static final String ERROR_NOT_UPDATE_NOTICE_MSG = "公告修改错误";
    public static final int ERROR_NOT_INSERT_NOTICE = 319;
    public static final String ERROR_NOT_INSERT_NOTICE_MSG = "公告新增错误";

    public static final int ERROR_PASSWORD = 320;
    public static final String ERROR_PASSWORD_MSG = "你没有密码";

    public static final int ERROR_VERIFICATION = 321;
    public static final String ERROR_VERIFICATION_MSG = "密码验证失败";


    public static HashMap<Integer, String> map;

    static {
        map = getR();

    }

    public static HashMap<Integer, String> getR() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(OK, OK_MSG);
        map.put(EMAILE_CODE_FAIL, EMAILE_CODE_FAIL_MSG);

        //--------------------------------Tan

        map.put(NOT_LOGIN, NOT_LOGIN_MSG);
        map.put(NOT_DATA, NOT_DATA_MSG);
        map.put(ERROR_IMG_VERIFICATIONS_CODE, ERROR_IMG_VERIFICATIONS_CODE_MSG);
        map.put(ERROR_FAIL_TO_EDIT, ERROR_FAIL_TO_EDIT_MSG);
        map.put(ERROR_USERNAME_OR_PASSWORD, ERROR_USERNAME_OR_PASSWORD_MSG);
        map.put(EXCEPTION, EXCEPTION_MSG);
        map.put(ERROR_UPDATE, ERROR_UPDATE_MSG);
        map.put(ERROR_DELETE, ERROR_DELETE_MSG);
        map.put(ERROR_ORDER_CANCEL_FAILUER, ERROR_ORDER_CANCEL_FAILUER_MSG);
        map.put(ERROR_PAY, ERROR_PAY_MSG);


        //---------------------------yuan
        map.put(ERROR_ADD_GOODS_FAIL, ERROR_ADD_GOODS_FAIL_MSG);
        map.put(ERROR_DELETE_GOODS_FAIL, ERROR_DELETE_GOODS_FAIL_MSG);
        map.put(ERROR_SELECT_WITHDRAWAL_FAIL, ERROR_SELECT_WITHDRAWAL_FAIL_MSG);
        map.put(ERROR_UPDATE_WITHDRAWAL_FAIL, ERROR_UPDATE_WITHDRAWAL_FAIL_MSG);
        map.put(ERROR_UPDATE_PRODUCT_FAIL, ERROR_UPDATE_PRODUCT_FAIL_MSG);
        map.put(ERROR_PURCHASE_GOODS_FAIL, ERROR_PURCHASE_GOODS_FAIL_MSG);
        map.put(ERROR_NOT_PRODUCT, ERROR_NOT_PRODUCT_MSG);
        map.put(ERROR_NOT_PURCHASE_PRODUCT, ERROR_NOT_PURCHASE_PRODUCT_MSG);
        map.put(ERROR_NOT_PAY_ORDER, ERROR_NOT_PAY_ORDER_MSG);
        map.put(ERROR_NOT_UPGRADE_ORDER, ERROR_NOT_UPGRADE_ORDER_MSG);
        map.put(ERROR_NOT_NOTICE, ERROR_NOT_NOTICE_MSG);
        map.put(ERROR_NOT_DELETE_NOTICE, ERROR_NOT_DELETE_NOTICE_MSG);
        map.put(ERROR_NOT_UPDATE_NOTICE, ERROR_NOT_UPDATE_NOTICE_MSG);
        map.put(ERROR_NOT_INSERT_NOTICE, ERROR_NOT_INSERT_NOTICE_MSG);
        map.put(ERROR_PASSWORD, ERROR_PASSWORD_MSG);
        map.put(ERROR_VERIFICATION, ERROR_VERIFICATION_MSG);
        map.put(EXCPTION_REQUEST, EXCPTION_REQUEST_MSG);
        map.put(ERROR_UPDATE_LOGIN_MESSAGE, ERROR_UPDATE_LOGIN_MESSAGE_MSG);
        map.put(ERROR_LOGIN_MESSAGE, ERROR_LOGIN_MESSAGE_MSG);
        map.put(ERROR_INSERT, ERROR_INSERT_MSG);
        return map;
    }



}
