<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会ID的隐藏域 -->
    <input type="hidden" name="id" value="${(provider.id)!}">
    <#-- 设置指派人的隐藏域ID -->
    <#--<input type="hidden" id="assignManId" value="${(saleChance.assignMan)!}">-->

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供货商名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="name" id="name"  value="${(provider.name)!}" placeholder="请输入供应商名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供货商地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="address" id="address" value="${(provider.address)!}" placeholder="请输入供应商地址">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供货商联系人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="contact"  lay-verify="required"  value="${(provider.contact)!}" placeholder="请输入联系人">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供货商电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="number" name="number" value="${(provider.number)!}" id="phone" placeholder="请输入联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="remarks" value="${(provider.remarks)!}" id="phone" placeholder="请输入备注">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateProvider">确认 </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/provider/add.provider.js"></script>
</body>
</html>