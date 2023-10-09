layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    //用户密码修改，表单提交
    form.on("submit(saveBtn)",function (data) {
        //获取表单元素的内容
        var fieldData = data.field;
        //获取隐藏作用域中的userId
        var userId = $("#userId").val();
        console.log(userId);
        //发送ajax请求
        $.ajax({
            type:"post",
            url: ctx+"/user/updatePwd",
            data:{
                userId:userId,
                oldPassword:fieldData.old_password,
                newPassword:fieldData.new_password,
                repeatPassword:fieldData.again_password
            },
            dataType:"json",
            success:function (data) {
                //判断是否成功
                if (data.code == 200){
                    //修改成功后,用户自动退出系统
                    layer.msg("用户名密码修改成功，系统在3秒后退出。。。",function () {
                        //退出系统后，删除对应的cookie
                        $.removeCookie("userIdStr",{domin:"localhost",path:"/supermarket"});
                        $.removeCookie("userName",{domin: "localhost",path: "/supermarket"});
                        $.removeCookie("trueName",{domin: "localhost",path: "/supermarket"});

                        //跳转到登录页面（父窗口跳转）
                        window.parent.parent.location.href = ctx + "/index";
                    });
                }else if (data.code == 201){
                    layer.msg("用户名密码修改成功",function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    });
});