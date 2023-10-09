<!DOCTYPE html>
<html>
<head>
    <title>商品进货</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <input type="hidden" id="providerId" value="${(purchase.provider)!}">
            <input type="hidden" id="goodsId" value="${(purchase.goodsName)!}">
            <div class="layui-inline">
                <div class="layui-input-inline layui-col-md3">
                    <label class="layui-form-label">供应商</label>
                    <div class="layui-input-block">
                        <select name="provider" id="provider">
                            <option value="">请选择供应商</option>
                        </select>
                    </div>
                </div>
                <div class="layui-input-inline layui-col-md3 ">
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-block">
                        <select name="goodsName" id="provider">
                            <option value="">请选择商品</option>
                        </select>
                    </div>
                </div>
                <#--<div class="layui-input-inline layui-col-md2 ">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-block">
                    <input type="text" name="beginTime" class="layui-input
					searchVal" placeholder="开始时间" />
                    </div>
                </div>
                <div class="layui-input-inline layui-col-md2">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-block">
                    <input type="text" name="endTime" class="layui-input
					searchVal" placeholder="结束时间" />
                    </div>
                </div>-->
                <#--<div class="layui-input-inline layui-col-md2 ">
                    <div class="layui-btn-container">
                        <button type="button" class="layui-btn layui-btn-md  layui-btn-danger " ><i class="layui-icon layui-btn-md layui-bg-red">&#xe615;</i>查询</button>
                        <button type="button" class="layui-btn layui-btn-md  layui-btn-danger " lay-even="rollback"><i class="layui-icon layui-btn-md layui-bg-red">&#xe615;</i>重置</button>
                    </div>
                </div>-->
                <a class="layui-btn layui-btn-danger search_btn" data-type="reload"><i
                            class="layui-icon layui-btn-md layui-bg-red">&#xe615;</i> 搜索</a>
                <a class="layui-btn search_btn" data-type="rolleback"><i
                            class="layui-icon layui-btn-warm layui-bg-orange">&#xe615;</i> 重置</a>
            </div>
        </form>
    </blockquote>

    <table id="purchaseList" class="layui-table"  lay-filter="purchases"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="input">
                <i class="layui-icon">&#xe608;</i>
                商品进货
            </a>

        </div>
    </script>
    <!--操作-->
    <script id="purchaseListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="return" lay-event="edit">退货</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/purchase/purchase.js"></script>

</body>
</html>