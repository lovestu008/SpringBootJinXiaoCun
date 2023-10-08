layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#userList', // 表格绑定的ID
        url : ctx + '/user/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分⻚
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'userName', title: '用户名', align:'center'},
            {field: 'phone', title: '电话号码', align:'center'},
            {field: 'email', title: '邮箱', align:'center'},
            {field: 'password', title: '用户密码', align:'center'},
            {field: 'trueName', title: '真实姓名', align:'center'},
            {field: 'remarks', title: '备注', align:'center'},
            {field: 'createtime', title: '创建时间',align:"center"},
            {field: 'updatetime', title: '更新时间',align:"center"},
            {title: '操作', templet:'#userListBar',fixed:"right",align:"center",
                minWidth:150}
        ]]
    });

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: { //设定异步数据接⼝的额外参数，任意设
                userName: $("[name='userName']").val(), // 用户名
                email: $("[name='email']").val(), // 邮箱
                phone: $("[name='phone']").val() // 电话
            }
            ,page: {
                curr: 1 // 重新从第 1 ⻚开始
            }
        }); // 只重载数据
    });

    /**
     * 头部工具栏，监听事件
     */
    table.on('toolbar(users)', function (data) {
        // data.event：对应的元素上设置的lay-event属性值
        // console.log(data);
        // 判断对应的事件类型
        if (data.event == "add") {
            // 添加操作
            openAddOrUpdateUserDialog();

        } else if (data.event == "del") {
            // 删除操作
            var checkStatus = table.checkStatus(data.config.id);
            deleteUser(checkStatus);
        }
    });
    /**
     * 打开⽤户添加或更新对话框
     */
    function openAddOrUpdateUserDialog(userId) {
        var url = ctx + "/user/addOrUpdateUserPage";
        var title = "⽤户管理-⽤户添加";
        if(userId){
            url = url + "?id="+userId;
            title = "⽤户管理-⽤户更新";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["650px","400px"],
            maxmin:true,
            content : url
        });
    }

    /**
     * 表格⾏ 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(users)',function (obj) {
        var data = obj.data;//获得当前行数据
        var layEvent = obj.event;// 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        //判断事件类型
        if (layEvent === 'edit'){//编辑操作
            //获取当前要修改的行的id
            var userId = data.id;
            //点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateUserDialog(userId);
        }else if (layEvent == "del"){//删除操作
            //询问是否删除
            layer.confirm("确定要删除这条数据吗？",{icon:3,title:"用户数据管理"},function (index) {
                //关闭窗口
                layer.close(index);
                //发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url : ctx+"/user/delete",
                    data:{
                        ids:data.id
                    },
                    dataType:"json",
                    success:function (result) {
                        if (result.code == 200){
                            //加载表格
                            tableIns.reload();
                        }else {
                            layer.msg(result.msg,{icon:5});
                        }
                    }
                });
            });
        }
    });


    function deleteUser(data){
        // 获取所有被选中的记录对应的数据
        var userData = data.data;
        // 判断用户是否选择的记录 (选中行的数量大于0)
        if(userData.length < 1){
            layer.msg("请选择要删除的记录");
            return;
        }
        //询问用户是否删除
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭确认框
            layer.close(index);
            var ids = "ids=";
            //遍历获取对应的id
            for (var i = 0;i<userData.length;i++){
                if (i<userData.length-1){
                    ids = ids+userData[i].id + "&ids=";
                }else {
                    ids = ids +userData[i].id;
                }
            }
            //发送ajax请求删除记录
            $.ajax({
                type:"post",
                url:ctx+"/user/delete",
                data:ids,
                dataType: "json",
                success:function (result) {
                    if (result.code == 200) {
                        // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }





});