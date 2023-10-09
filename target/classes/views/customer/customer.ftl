<!DOCTYPE html>
<html>
<head>
    <title>客户开发计划管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="name" class="layui-input searchVal"
                           placeholder="姓名"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="contact" class="layui-input searchVal"
                           placeholder="联络⼈"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="address" class="layui-input searchVal"
                           placeholder="地址"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="number" class="layui-input searchVal"
                           placeholder="电话号码"/>
                </div>
                <#--<div class="layui-input-inline">
                    <select name="devResult" id="devResult">
                        <option value="">请选择</option>
                        <option value="0">未开发</option>
                        <option value="1">开发中</option>
                        <option value="2">开发成功</option>
                        <option value="3">开发失败</option>
                    </select>
                </div>-->
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <table id="customer" class="layui-table" lay-filter="saleChances">
    </table>
    <script type="text/html" id="toolbarDemo"></script>
    <!--
    ⾏⼯具栏
    详情:机会数据已开发结束,点击详情展示计划项相关数据
    开发:机会数据处于开发中,点击开发添加计划项数据
    此时链接内容显示由开发结果值控制
    -->
    <#--<script id="op" type="text/html">
        {{# if (d.devResult== 0 || d.devResult==1) { }}
        <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-xs" lay-event="dev">修改</a>
        {{# } else { }}
        <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="info">详情</a>
        {{# } }}
    </script>-->

    <#-- 头部工具栏 -->
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

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加角色
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="grant">
                <i class="layui-icon">&#xe672;</i>
                授权
            </a>
        </div>
    </script>

    <!-- 行工具栏 -->
    <script id="customer" type="text/html">
            <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/customer/customer.js"></script>
</body>
</html>