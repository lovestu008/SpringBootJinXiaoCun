<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="customerName" id="customerName" value="${(saleChance.customerName)!}" placeholder="请输⼊客户名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会来源</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="chanceSource"
                   id="chanceSource" value="${(saleChance.chanceSource)!}" placeholder="请输⼊机会来源">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系⼈</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="linkMan"
                   lay-verify="required" value="${(saleChance.linkMan)!}" placeholder="请输⼊联系⼈">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="phone"
                   name="linkPhone" value="${(saleChance.linkPhone)!}" id="phone" placeholder="请输⼊联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">概要</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="overview" value="${(saleChance.overview)!}" id="phone" placeholder="请输⼊概要">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成功⼏率(%)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="cgjl" value="${(saleChance.cgjl)!}"
                   placeholder="请输⼊成功⼏率">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
             <textarea placeholder="请输⼊机会描述信息" name="description" class="layui-textarea">
                 ${(saleChance.description)!}
             </textarea>
        </div>

    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">指派给</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="">请选择</option>
            </select>
        </div>
    </div>

    <#-- 设置营销机会的ID -->
    <input type="hidden" name="id" value="${(saleChance.id)!}">

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSaleChance">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/saleChance/add.update.js"></script>
</body>
</html>