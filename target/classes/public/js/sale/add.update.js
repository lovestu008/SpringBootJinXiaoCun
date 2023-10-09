layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;




    $("#closeBtn").click(function (){console.log(1)
       //先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
        //再执行关闭
        parent.layer.close(index);
    });



    /**
     * 监听submit事件
     * 实现出库的添加与更新
     */
    form.on("submit(addOrUpdateSale)", function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...", {
            icon: 16, // 图标
            time: false, // 不关闭
            shade: 0.8 // 设置遮罩的透明度
        });
        // 请求的地址 添加操作
        var url = ctx + "/sale/add";

        // 通过销售单的ID来判断当前需要执行添加操作还是修改操作
        // 如果销售单的ID为空，则表示执行添加操作；如果ID不为空，则表示执行更新操作
        // 通过获取隐藏域中的ID
        var saleId = $("[name='id']").val();
        console.log(saleId);
        // 判断ID是否为空
        if (saleId != null && saleId != '') {
            // 更新操作
            url = ctx + "/sale/update";
        }

        // 发送ajax请求
        $.post(url, data.field, function (result) {
            // 操作成功
            if (result.code == 200) {
                // 提示成功layer.msg("操作成功！");
                // 关闭加载层
                layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新⽗⻚⾯，重新渲染表格数据
                parent.location.reload();
            } else {
                layer.msg(result.msg, {icon:5});
            }
        });
        return false; // 阻⽌表单提交
    });

    /**
     * 加载下拉框
     */
    $.ajax({
        type:"post",
        url:ctx+"/customer/allCustomers",
        success:function (data){
            if (data!== null) {//遍历返回的数据
                $.each(data, function(index, item) {//将下拉选项设置到下拉框中
                    $("#customerId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                });
            }
            //重新渲染
            form.render("select")
        }
    })

    form.on('select(goodsByName)',function (data){
        var elem = data.elem; // 获得 select 原始 DOM 对象
        var value = data.value; // 获得被选中的值
        var othis = data.othis; // 获得 select 元素被替换后的 jQuery 对象

        $.post(ctx+"/goods/queryAllGoods",function (data){
            if (data!== null) {//遍历返回的数据
                $.each(data, function(index, item) {//将下拉选项设置到下拉框中
                    $("#goodsName").append("<option value='"+item.id+"' >"+item.name+"</option>");
                });
            }
            //重新渲染
            form.render("select")
        });

        $.post(ctx+"/goods/queryGoodsByName",function (data){
            parent.location.reload();
        })


    })

});