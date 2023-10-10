<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--<input type="hidden" id="operatePerson" value="${(purchase.operatePerson)!}">&lt;#&ndash;操作人&ndash;&gt;
    <input type="hidden" name="id" value="${(purchase.id)!}">-->
    <input type="hidden" name="goodsId" id="goodsId" value="${(purchase.goodsName)!}">
    <input type="hidden" id="id" name="id" value="${(purchase.id)!}">
    <div class="layui-input-inline  layui-col-md4">
        <label class="layui-form-label">商品</label>
        <div class="layui-input-block">
            <select name="goodsName" id="goodsName">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <br>
    <br>
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
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remark" value="${(purchase.remark)!}" id="remark" placeholder="请输入备注">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg layui-bg-red" lay-submit=""  lay-filter="updatePurchase">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal layui-bg-orange" id="closeBtn">取消</button><#--记得一定要写id属性值-->
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/purchase/update.js"></script>
</body>
</html>