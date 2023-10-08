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
            <input type="hidden" id="providerId" value="${(return.providerId)!}">
            <input type="hidden" id="goodsId" value="${(return.goodsId)!}">
            <div class="layui-inline">
                <div class="layui-input-inline layui-col-md4">
                    <label class="layui-form-label">供应商</label>
                    <div class="layui-input-block">
                        <select name="provider" id="provider">
                            <option value="">请选择供应商</option>
                        </select>
                    </div>
                </div>
                <div class="layui-input-inline  layui-col-md4">
                    <label class="layui-form-label">商品</label>
                    <div class="layui-input-block">
                        <select name="goodsName" id="goodsName">
                            <option value="">请选择商品</option>
                        </select>
                    </div>
                </div>
                &nbsp;&nbsp;
                <a class="layui-btn layui-btn-danger layui-btn-radius search_btn" data-type="reload"><i
                            class="layui-icon layui-btn-md layui-bg-red">&#xe615;</i> 搜索</a>
                <button class="layui-btn layui-btn-warm layui-btn-radius"  type="reset" id="reset"><i
                            class="layui-icon layui-bg-orange">&#xe666;</i> 重置</button>
            </div>
        </form>
    </blockquote>

    <table id="returnList" class="layui-table"  lay-filter="returns"></table>
</form>
<script type="text/javascript" src="${ctx}/js/return/return.js"></script>

</body>
</html>