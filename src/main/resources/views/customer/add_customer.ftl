<!DOCTYPE html>
<html>
<head>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
	<#-- 设置超市客户ID的隐藏域 -->
	<input type="hidden" name="id" value="${(customer.id)!}">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">超市名称</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" lay-verify="required" name="name" id="name"  value="${(customer.name)!}" placeholder="请输入超市名称">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">超市地址</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input"  name="address" id="address" value="${(customer.address)!}" placeholder="请输入超市地址">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">联系人</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" name="contact"  lay-verify="required"  value="${(customer.contact)!}" placeholder="请输入联系人">
		</div>
	</div>

	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">超市电话</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" lay-verify="number" name="number" value="${(customer.number)!}" id="number" placeholder="请输入联系电话">
		</div>
	</div>
	<#--<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">状态</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" lay-verify="isDel" name="isDel" value="${(customer.isDel)!}" id="phone" placeholder="状态">
		</div>
	</div>-->
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">备注</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input"
				   name="remarks" value="${(customer.remarks)!}" id="remarks" placeholder="请输入备注">
		</div>
	</div>
	<br/>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateCustomer">确认 </button>
			<button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctx}/js/customer/add.customer.js"></script>
</body>
</html>