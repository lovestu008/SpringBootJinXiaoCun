<!DOCTYPE html>
<html>
<head>
    <title>商品管理</title>
    <#include "../common.ftl">
    <link rel="stylesheet" href="${ctx}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">

        <!-- 商品表 -->
        <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 100%">
            <div class="layui-card">
                <fieldset class="layui-elem-field layui-field-title" >
                    <legend style="font-size: 15px">商品管理</legend>
                </fieldset>
                <div class="layui-card-body">
                    <form class="layui-form" >
                        <blockquote class="layui-elem-quote quoteBox">
                            <form class="layui-form">
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="goodsName"
                                               class="layui-input
					searchVal" placeholder="商品名" />
                                    </div>
                                    <a class="layui-btn search_btn" data-type="reload"><i
                                                class="layui-icon">&#xe615;</i> 搜索</a>
                                </div>
                            </form>
                        </blockquote>

                        <table id="goodsList" class="layui-table"  lay-filter="goods"></table>

                        <script type="text/html" id="toolbarDemo">
                            <div class="layui-btn-container">
                                <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                                    <i class="layui-icon">&#xe608;</i>
                                    添加商品
                                </a>
                                <a class="layui-btn layui-btn-normal addNews_btn" lay-event="delete">
                                    <i class="layui-icon layui-icon-delete"></i>
                                    删除商品
                                </a>
                            </div>
                        </script>
                        <#--操作-->
                        <script id="goodsListBar" type="text/html">
                            <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
                            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                        </script>
                    </form>

                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript" src="${ctx}/js/goods/goods.js"></script>

</body>
</html>