layui.use(['form', 'layer' ,'formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
        // 引⼊ formSelects 模块
        formSelects = layui.formSelects;
    /**
     * 监听submit事件
     * 实现营销机会的添加与更新
     */
    form.on("submit(addOrUpdateUser)", function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...", {
            icon: 16, // 图标
            time: false, // 不关闭
            shade: 0.8 // 设置遮罩的透明度
        });
        // 请求的地址 添加操作
        var url = ctx + "/user/add";

        // 通过营销机会的ID来判断当前需要执行添加操作还是修改操作
        // 如果营销机会的ID为空，则表示执行添加操作；如果ID不为空，则表示执行更新操作
        // 通过获取隐藏域中的ID
        var userId = $("[name='id']").val();
        // 判断ID是否为空
        if (userId != null && userId != '') {
            // 更新操作
            url = ctx + "/user/update";
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
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    /**
     * 加载下拉框
     */
    formSelects.config('selectId',{
        type:"post",
        searchUrl :ctx+"/role/queryAllRoles",
        //⾃定义返回数据中name的key, 默认 name
        keyName: 'roleName',
        //⾃定义返回数据中value的key, 默认 value
        keyVal: 'id'
    },true);



    //查询⻆⾊记录时传⼊⽤⼾id
    var userId = $("#id").val();
    formSelects.config('selectId',{
        type:"post",
        searchUrl:ctx+"/role/queryAllRoles?userId="+userId,
        keyName: 'roleName', //⾃定义返回数据中name的key, 默认 name
        keyVal: 'id' //⾃定义返回数据中value的key, 默认 value
    },true);

});