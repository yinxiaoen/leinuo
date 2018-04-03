package org.spring.springboot.config.error;



/**
 * Created by geran on 16/9/20.
 */

/*
http://wiki.hualala.com/pages/viewpage.action?pageId=2787580
库存线的errorcode，8位，
5678后四位代表具体错误，
34两位代表具体的子模块，
  00代表共通错误
  01代表basic
  02代表bill
  03代表stock
  04代表bill采购单
  05代表platform supplier
12两位空缺保留
 */
public enum SupplyErrorCode {
    ERROR_PARAMETER_FORMAT("00000000", ""),
    RESPONSE_ERROR("00010001", "响应数据有误"),
    ILLEGAL_ARGUMENTS_ERROR("00010002", "非法的请求参数"),
    ISOPERATORING("00010011", "该数据正在处理，请等待处理结果"),
    SUCCESS("000","服务执行成功"),
    WARING("00020002","不在会计期内不能录入单据"),
    /**02Bill模块错误编码**/
    CANTPROCESE("00020002","加工单需加工量不能小于等于0，请检查"),
    UNROUTER("00020003","等原材料没有设置配送规则"),
    UNFORMULA("00020004","等加工品没有设置加工配方"),
    UNHOUSE("00020005","请先设置默认仓库"),
    UNMATERIAL("00020006","没有找到相应的加工原料"),
    UNORDERRECORDS("00020007","已领料/订货加工品项不能生成订货单或采购单"),
    CANTORDER("00020008","不满足订货条件"),
    GOODSNUMCANTORDER("00021000","以下品项订货数量经过加减单的调整后订货数量不满足大于0的条件"),
    GOODSADDORSUBNUMMISSING("00021001","请填写加减单数量"),
    GOODSADDNUM("00021002","加单数量必须大于等于0"),
    GOODSSUBNUM("00021003","减单数量必须小于等于0"),
    CANNOTADDBILL("00021004","已提交状态的订货单才能做加减单"),
    BILLISSUBMIT("00021005","该订单已经提交过，请勿重复提交"),
    TEMPLATESISNULL("00021006","模板名称为空，请检查模板名称"),
    ISBEYONDDEADLINE("00021007","模板已经超过班表设置的加减单截止时间"),
    CANNOTFINFRULE("00021008","该订单中的模板未查找到班表截止时间"),
    SUBTRACTEMPTY("000210010","生产入库单单据明细为空"),
    SUBTRACTHOUSENULL("00021011","加工间下半成品为空"),
    SUBTRACTFORMULENULL("00021012","没有设置配方管理，请设置后再操作自动扣库"),
    SUBTRACTBALANCENULL("00021013","原料库存数量不足，不足以实行扣库操作"),
    SUBTRACTISNO("00021014","该集团设置不允许扣库"),
    SUBTRACTISOUTPRICE("00021015","先进先出不允许自动扣库"),
    SUBTRACTMOREMATERIALS("00021016","多个半成品在同一个配方下，不支持自动扣库功能"),
    SUBTRACTNOMATERIAL("00021017","在配方下没有原材料与其对应"),
    ERROR_GOODS_UNROUTER("00020001","请联系配送中心管理员设置该品项的配送规则后添加订货单"),
    ERROR_GOODS_UNPRICE("00020010","以下品项没有设置价格"),
    UNENOUGHSTOCK("00021018","以下仓库的品项库存不够,不能出库"),
    BILLNEEDPAY("00021019","请先付款在进行审核操作"),
    /**01Basic模块错误编码**/
    MISSINGPARAM("00010001","缺少请求参数，请检查"),
    MISSINGDISTRIBUTION("00010002","等组织没有所属配送中心，请先前往商户中心设置"),
    PARAMERROR("00010003","配送方式和仓库或供应商信息不匹配，请检查"),
    AGENTRULESERROR("00010004","以下品项的路由规则不一致，请检查"),
    FIXED_ACCOUNT_ERROR("00010004","当前会计期业务正在处理中请稍后操作"),
    /**03Stock模块错误编码**/
    NONTHINGBALANCE("00030001","等组织没有确认期初，请先确认期初再操作"),
    BILLNONTHINGBALANCE("00030006","组织没有确认期初，请先确认期初再操作"),
    IMPORTNOTGOODSNUM("00030004","的数量不能为空"),
    NOTISOPENINGBALANCE("00030005","组织没有确认期初，请先确认期初再操作"),
    NOTANTIVOUCHER("00030007","没有启用反审核功能"),
    BAOSUNWARING("00030008","日期不在会计期内不能审核"),
    CHECKINWARING("00030009","不在当前会计期内，不能验货入库"),
    ERRORGOODINSTOCK("00030010","领用仓位没有使用该品项的权限，请联系配送中心管理人员设置"),
    ORGIDPARAMS("00030011","组织ID不能为空"),
    SUPPLIERIDPARAMS("00030012","供应商ID不能为空"),
    SUPPLIERNAMEPARAMS("00030013","供应商名称不能为空"),

    /**04Bill采购单模块错误编码**/
    NOTAUDITPRIVILEGE("00040011","当前登录用户没有权限审核该采购单"),
    MOMENTNOTAUDITPRIVILEGE("00040002","采购单当前的采购审核权限，当前登录用户暂时没有"),
    HADAUDITED("00040003","当前登录用户可审核的采购单明细已审核完毕"),
    INAUDITING("00040004","采购单据已处于审核中状态，不能修改"),
    NOTALLAUDITLEVEL("00040005","当前登录用户没有一键审核采购单的权限"),
    ERROR_INSPECTIONNUM("00030002","等验货数量不符合验货比，请先修改后再操作"),
    CANT_EMPTY_PRODUCTIONDATE("00030003","等生产日期为必填项，请先填写后再操作"),
    PURCHASEINSERTVALIDATE("00050008","采购单没有配送规则或者配送规则为直配或入库"),

    /**05platform supplier平台供货商模块错误编码**/
    PLATFORM_NOT_LOGIN("00050001", "登录过期，请重新登录"),
    PLATFORM_NOT_PAY_USER("00050002", "此供货商没有绑定结算主体"),
    PLATFORM_SMS_TIMES_QUICK("00050003", "验证码已发送，请1分钟后重新发送"),
    PLATFORM_NOT_SMS_CODE("00050004", "验证码已失效或不正确"),
    PLATFORM_NOT_EMAIL("00050006", "导出失败，请检查是否绑定邮箱"),
    PLATFORM_FUNC_CLOSE("00050007", "订购宝暂时关闭注册接口，请联系您供货的集团"),

    /**支付返回未绑定结算主体错误错误**/
    DISTRIBUTION_NOT_FOUND_SCM_SETTLE("00114303", "{0}没有绑定结算主体，请联系集团管理员（admin）登录组织架构进行绑定"),
    /**门店支付返回未绑定结算主体错误错误**/
    DISTRIBUTION_SHOP_NOT_FOUND_SCM_SETTLE("00114304", "门店没有绑定结算主体，请联系集团管理员（admin）登录组织架构进行绑定"),
    /**06 shop 商户中心、门店、支付错误编码**/
    DISTRIBUTION_SUPPLIER_NOT_FOUND_SCM_SETTLE("00114305", "您的供应商未绑定结算主体，请联系哗啦啦运营人员绑定"),
    /**06 shop 商户中心、门店、支付错误编码**/
    DISTRIBUTION_SUPPLIER_ERROR_PAY_AMOUNT("00114306", "您的实际付款金额与需要付款金额不相符，请确认后再付款"),

    SUPPLIER_NOT_FOUND_SCM_SETTLE("00114307", "{0}供应商/配送中心没有设置结算主题,请设置后审核提交订单。\n" + "如订单包含多个供应商，点击\"知道了\" 此订单将会被拆单。"),
    ONLINE_WEB_QUICKBANK_LEAST("00114308", "银行卡最低起充金额2元"),
    /**06 shop 商户中心、门店、支付错误编码**/
    SHOP_NOT_FOUND_INFO("00060001", "没有获取到门店信息"),
    SHOP_NOT_FOUND_ROLE("00060002", "没有获取到角色信息"),
    SHOP_NOT_FOUND_SETTLE("00060003", "此门店绑定的结算主体为空"),
    SHOP_PAY_TIMEOUT("0006006", "支付页面已超时，请刷新后重试"),
    SHOP_PAY_NOT_CARD("0006007", "没有查询到此银行卡信息，请使用其他卡片"),
    SHOP_PAY_NOT_PAY_WAY("0006008", "没有获取到支付方式，请联系集团管理员（admin）"),
    SHOP_PAY_NOTSUPPORT_PAY_WAY("0006009", "不支持此支付方式，请联系集团管理员（admin）"),
    /**07 shop IREPORT 打印导出错误编码**/
    IREPORT_NO_DATA_EXCEL_REEOR("00070091", "没有要导出的数据");

    private String code;
    private String message;

    private SupplyErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return "" + code;
    }

    public String getMessage() {
        return this.message;
    }




}
