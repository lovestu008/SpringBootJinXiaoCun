<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--<input type="hidden" id="operatePerson" value="${(purchase.operatePerson)!}">&lt;#&ndash;操作人&ndash;&gt;
    <input type="hidden" name="id" value="${(purchase.id)!}">-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label" >商品名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="goodsName" id="goodsName"  value="${(purchase.goodsName)!}" placeholder="请输入商品名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供应商</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="provider" id="provider" value="${(purchase.provider)!}" placeholder="请输入供应商">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">进货价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="inpPrice"  lay-verify="required"  value="${(purchase.inpPrice)!}" placeholder="请输入进货价格">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">进货数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="inpNum" value="${(purchase.inpNum)!}" id="inpNum" placeholder="请输入进货数量">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">进货总价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="allInpPrice" value="${(purchase.allInpPrice)!}" id="allInpPrice" placeholder="请输入进货总价格">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remark" value="${(purchase.remark)!}" id="remark" placeholder="请输入备注">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdatePurchase">确认 </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button><#--记得一定要写id属性值-->
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/purchase/add.update.js"></script>
</body>
</html>