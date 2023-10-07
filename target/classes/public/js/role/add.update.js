layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;




    $("#closeBtn").click(function () {
        //先得到当前iframe层的索引
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    form.on("submit(addOrUpdateRole)",function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...", {
            icon: 16, // 图标
            time: false, // 不关闭
            shade: 0.8 // 设置遮罩的透明度
        });
        var url = ctx+"/role/add";

        // 通过获取隐藏域中的ID
        var roleId = $("[name='id']").val();
        // 判断ID是否为空
        if (roleId != null && roleId != '') {
            // 更新操作
            url = ctx + "/role/update";
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
    

});