<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">类别名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="name" id="name"   placeholder="请输入类别名">
        </div>
    </div>


    <#--添加根级菜单-->
    <#--设置隐藏域 id -->
    <input type="hidden" name="id" value="${id!}">
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addGoodsType">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeBtn" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/goodsType/add.js"></script>
</body>
</html>