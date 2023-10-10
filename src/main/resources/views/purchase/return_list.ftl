<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--<input type="hidden" id="operatePerson" value="${(purchase.operatePerson)!}">&lt;#&ndash;操作人&ndash;&gt;
    <input type="hidden" name="id" value="${(purchase.id)!}">-->
    <input type="hidden" name="purchaseId" id="purchaseId" value="${(inpRetGoodsList.purchaseId)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label" >商品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" readonly lay-verify="required" name="goodsName" id="goodsName"  value="${(inpRetGoodsList.goodsName)!}" ><#--商品名称-->
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供应商</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" readonly lay-verify="required" name="provider" id="provider" value="${(inpRetGoodsList.provider)!}" ><#--进货供应商-->
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">库存数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" readonly lay-verify="required" name="retNum" value="${(inpRetGoodsList.goodsNum)!}" id="retNum" ><#--库存数量-->
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">退货数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="retNum" value="${(inpRetGoodsList.retNum)!}" id="retNum" placeholder="请输入退货数量"><#--退货数量-->
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remark" value="${(inpRetGoodsList.remark)!}" id="remark" placeholder="请输入备注"><#--退货备注-->
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg layui-bg-red" lay-submit=""  lay-filter="returnPurchase">确认 </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal layui-bg-orange" id="closeBtn">取消</button><#--记得一定要写id属性值-->
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/purchase/return.list.js"></script>
</body>
</html>