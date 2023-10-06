<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-form-item layui-row layui-col-xs12">
    <label class="layui-form-label">客户ID</label>
    <select name="customerId" id="customerId">
        <option value="">请选择</option>
    </select>
</div>
<div class="layui-form-item layui-row layui-col-xs12">
    <label class="layui-form-label">商品名</label>
    <div class="layui-col-md6">
        <select lay-search="" name="goodsName" id="goodsName" lay-filter="goodsByName">
            <option value="">请搜索</option>

            <option value="cccc1">cccc1</option>
            <option value="cccc2">cccc2</option>

        </select>
    </div>
</div>
<fieldset class="layui-elem-field site-demo-button">
    <legend>商品信息</legend>
    <br/>
    <input name="id" type="hidden" value="${(goods.id)!}"/>
    <input name="typeId" type="hidden" value="${(goods.typeId)!}"/>
    <input name="flag" type="hidden" value="${flag!}"/>

    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                       name="code" id="code" readonly="readonly" value="${(goods.code)!""}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品类别</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input minNum" readonly="readonly"
                       name="typeName" id="typeName" value="${(goods.typeName)!}">
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input model" readonly="readonly"
                       name="model" id="model" value="${(goods.model)!""}"
                       placeholder="商品型号">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品单位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input unitName" readonly="readonly"
                       name="unitName" id="unitName" value="${(goods.unitName)!}">
            </div>
        </div>
    </div>
    <br/>

    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">上次进价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="lastPurchasingPrice" id="lastPurchasingPrice"
                       value="${(goods.lastPurchasingPrice)!""}"
                       placeholder="商品销售价">
            </div>
        </div>
    </div>
</fieldset>
<hr/>
<div class="layui-row">
    <div class="layui-col-xs6">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input sellingPrice"
                   name="price" id="price"
                   value="${(goods.lastPurchasingPrice)!""}">
        </div>
    </div>
    <div class="layui-col-xs6">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input inventoryQuantity"
                   name="num" id="num" value="${(goods.num)!}">
        </div>
    </div>
</div>
<br/>
<div class="layui-form-item layui-row layui-col-xs12">
    <div class="layui-input-block">
        <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSale">确认</button>
        <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/sale/add.update.js"></script>
</body>
</html>