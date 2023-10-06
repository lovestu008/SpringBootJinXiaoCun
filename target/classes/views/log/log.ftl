<!DOCTYPE html>
<html>
<head>
    <title>日志管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form">
    <#--<#if permissions?seq_contains("101001")>-->
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <select name="type" id="type">
                        <option value="">请选择操作类型</option>
                        <option value="登录操作">登录操作</option>
                        <option value="查询操作">查询操作</option>
                        <option value="添加操作">添加操作</option>
                        <option value="删除操作">删除操作</option>
                        <option value="修改操作">修改操作</option>
                        <option value="注销操作">注销操作</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="uname" class="layui-input
                    searchVal" placeholder="登录人姓名"/>
                </div>
                开始时间：
                <div class="layui-input-inline">
                    <input type="date" name="startTime"
                           class="layui-input searchVal" placeholder="开始时间"/>
                </div>
                结束时间：
                <div class="layui-input-inline">
                    <input type="date" name="endTime"
                           class="layui-input searchVal" placeholder="结束时间"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <#--</#if>-->

    <!-- 数据表格 -->
    <table id="logList" class="layui-table" lay-filter="log">
    </table>

    <#--头部工具栏-->

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
<#--            <#if permissions?seq_contains("101003")>-->
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                批量删除
            </a>
            <#--</#if>-->
        </div>
    </script>


</form>

<script type="text/javascript" src="${ctx}/js/log/log.js"></script>
</body>
</html>