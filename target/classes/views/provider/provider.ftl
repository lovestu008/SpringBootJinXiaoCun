<!DOCTYPE html>
<html>
<head>
    <title>供货商计划管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<#--&lt;#&ndash;行工具栏&ndash;&gt;-->

<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="address"
                           class="layui-input
                           searchVal" placeholder="供货商地址"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="contact"
                           class="layui-input
                           searchVal" placeholder="联络⼈"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="name"
                           class="layui-input
                           searchVal" placeholder="供应商名称"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="number"
                           class="layui-input
                           searchVal"  placeholder="供应商电话号码"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <table id="provider" class="layui-table" lay-filter="provider"></table>
</form>

<#--头部工具栏-->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">

        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            添加
        </a>

        <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
            <i class="layui-icon">&#xe608;</i>
            删除
        </a>
    </div>
</script>
<!--操作-->
<script id="providerListBar" type="text/html">
    <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>
<script type="text/javascript" src="${ctx}/js/provider/provider.js"></script>

</body>
</html>









































