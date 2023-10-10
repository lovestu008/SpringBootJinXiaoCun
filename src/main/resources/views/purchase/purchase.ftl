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
            <input type="hidden" id="id" value="${(purchase.id)!}">
            <div class="layui-inline">
                <div class="layui-input-inline layui-col-md4">
                    <label class="layui-form-label">供应商</label>
                    <div class="layui-input-block">
                        <select name="provider" id="provider">
                            <option value="请选择供应商"></option>
                        </select>
                    </div>
                </div>
                <div class="layui-input-inline  layui-col-md4">
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-block">
                        <select name="goodsName" id="goodsName">
                            <option value="请选择商品"></option>
                        </select>
                    </div>
                </div>
                &nbsp;&nbsp;
                <a class="layui-btn layui-btn-danger layui-btn-radius search_btn" data-type="reload"><i
                            class="layui-icon layui-btn-md layui-bg-red">&#xe615;</i> 搜索</a>
                <button class="layui-btn layui-btn-warm layui-btn-radius"   id="reset"><i
                            class="layui-icon layui-bg-orange">&#xe666;</i> 重置</button>
            </div>
        </form>
    </blockquote>

    <table id="purchaseList" class="layui-table"  lay-filter="purchases"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal layui-bg-red addNews_btn" lay-event="input">
                <i class="layui-icon">&#xe654;</i>
                商品进货
            </a>

        </div>
    </script>
    <!--操作-->
    <script id="purchaseListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-bg-red" id="edit" lay-event="edit"><i
                    class="layui-icon">&#xe642;</i> 修改</a>
        <a class="layui-btn layui-btn-xs layui-bg-red" id="return" lay-event="return"><i
                    class="layui-icon">&#xe67e;</i> 退货</a>

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/purchase/purchase.js"></script>

</body>
</html>