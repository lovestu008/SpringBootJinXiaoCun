layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    $("#closeBtn").click(function (){
       //先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
        //再执行关闭
        parent.layer.close(index);
    });



    /**
     * 监听submit事件
     * 实现营销机会的添加与更新
     */
    form.on("submit(addOrUpdateSaleChance)", function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...", {
            icon: 16, // 图标
            time: false, // 不关闭
            shade: 0.8 // 设置遮罩的透明度
        });
        // 请求的地址 添加操作
        var url = ctx + "/sale_chance/add";

        // 通过营销机会的ID来判断当前需要执行添加操作还是修改操作
        // 如果营销机会的ID为空，则表示执行添加操作；如果ID不为空，则表示执行更新操作
        // 通过获取隐藏域中的ID
        var saleChanceId = $("[name='id']").val();
        console.log(saleChanceId);
        // 判断ID是否为空
        if (saleChanceId != null && saleChanceId != '') {
            // 更新操作
            url = ctx + "/sale_chance/update";
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
    $.post(ctx+"/user/queryAllSales",function (data) {

        if (data!=null){
            //如果是修改操作，判断当前修改记录的指派人的值
            var assignManId = $("#assignMan").val();
            //遍历返回的数据
            for (var i = 0;i<data.length;i++){
                var opt = "";
                //如果循环得到的ID与隐藏域相等，则表示被选中
                if(assignManId == data[i].id){
                    //设置下拉框选项选中
                    opt = "<option value='"+data[i].id+"' selected>"+data[i].uname+"</option>";
                }else {
                    //设置下拉选项
                    opt = "<option value='"+data[i].id+"'>"+data[i].uname+"</option>";
                }
                //将下拉选项设置到下拉框中
                $("#assignMan").append(opt);
            }
        }
        layui.form.render("select");
    });

});