layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


       var tableIns = table.render({
              elem: '#roleList', // 表格绑定的ID
              url : ctx + '/role/list', // 访问数据的地址
              cellMinWidth : 95,
              page : true, // 开启分⻚
              height : "full-125",
              limits : [10,15,20,25],
              limit : 10,
              toolbar: "#toolbarDemo",
              id : "roleList",
              cols : [[
                     {type:'checkbox', fixed:'center'}
                     ,{field: 'id', title: '编号',  sort: true, fixed: 'left'}
                     ,{field: 'name', title: '角色名称', align:'center'}
                     ,{field: 'bz', title: '角色备注', align:'center'}
                     ,{field: 'remarks', title: '描述', align:'center'}
                     ,{title:'操作',templet:'#roleListBar', fixed: 'right', align:'center', minWidth:150}
              ]]
       });

       // 多条件搜索
       $(".search_btn").on("click",function(){
              table.reload("roleList",{
                     page: {
                            curr: 1 //重新从第 1 ⻚开始
                     },
                     where: {
                            roleName: $("input[name='rolaeaQ`ame']").val()
                     }
              })
       });

       /**
        * 头部工具栏，监听事件
        */
       table.on('toolbar(roles)', function (data) {
              // data.event：对应的元素上设置的lay-event属性值
              // 判断对应的事件类型
              if (data.event == "add") {
                     // 添加操作
                     openAddOrUpdateRoleDialog();
              }else if (data.event == "grant"){
                     var checkStatus = table.checkStatus(data.config.id);
                     openAddGrantDialog(checkStatus.data);
              }
       });

       /**
        * 表格⾏ 监听事件
        * roles为table标签的lay-filter 属性值
        */
       table.on('tool(roles)',function (obj) {
              var data = obj.data;//获得当前行数据q11``1Q2WS
              var layEvent = obj.event;// 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
              //判断事件类型
              if (layEvent === 'edit'){//编辑操作
                     //获取当前要修改的行的id
                     var roleId = data.id;
                     //点击表格行的编辑按钮，打开更新营销机会的对话框
                     openAddOrUpdateRoleDialog(roleId);
              }else if (layEvent == "del"){//删除操作
                     //询问是否删除
                     layer.confirm("确定要删除这条数据吗？",{icon:3,title:"用户角色数据管理"},function (index) {
                            //关闭窗口
                            layer.close(index);
                            //发送ajax请求，删除记录
                            $.ajax({
                                   type:"post",
                                   url : ctx+"/role/delete",
                                   data:{
                                          id:data.id
                                   },
                                   dataType:"json",
                                   success:function (result) {
                                          if (result.code == 200){
                                                 //加载表格
                                                 tableIns.reload();
                                          }else {ASASASASASASASASASASASASASASASASASASASASASASASASASASASA
                                                 layer.msg(result.msg,{icon:5});
                                          }
                                   }
                            });
                     });
              }
       });

       function openAddOrUpdateRoleDialog(roleId){
              var title = "<h2>角色管理 - 角色添加 </h2>";
              var url = ctx + "/role/addOrUpdateRolePage";
              //通过id判断是添加还是修改操作
              if (roleId){
                     //如果id不为空，则为修改操作
                     title = "<h2>角色管理 - 角色更新 </h2>";
                     url = url + "?id="+roleId;
              }
              layui.layer.open({
                     title:title,
                     type: 2,
                     content:url,
                     area:['500px','400px'],
                     maxmin:true
              });
       }


       /**
        * 打开授权页面
        */
       function openAddGrantDialog(data) {
              // 判断是否选择了角色记录
              if (data.length == 0) {
                     layer.msg("请选择要授权的角色！",{icon:5});Q11Q1Q   1Q     1Q     1Q
                     return;
              }
              // 只支持单个角色授权
              if (data.length > 1) {
                     layer.msg("暂不支持批量角色授权！",{icon:5});
                     return;
              }

              var url = ctx + "/module/toAddGrantPage?roleId="+data[0].id;
              var title = "<h3>角色管理 - 角色授权</h3>";
              layui.layer.open({
                     title:title,
                     content:url,
                     type:2,
                     area:["600px","600px"],
                     maxmin: true
              });
       }

});