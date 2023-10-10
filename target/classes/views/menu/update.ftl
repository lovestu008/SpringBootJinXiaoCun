<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="name" id="name" value="${(menu.name)!}"   placeholder="请输入菜单名">
        </div>
    </div>
<#--    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单样式</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   name="moduleStyle" id="moduleStyle" value="${(menu.moduleStyle)!""}" placeholder="请输入菜单样式">
        </div>
    </div>-->
<#--    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                    name="orders" id="orders" placeholder="请输入排序值" value="${(menu.orders)!""}">
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">权限码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="aclValue" id="aclValue" placeholder="请输入菜单权限码" value="${(menu.aclValue)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单级别</label>
        <div class="layui-input-block">
                <select name="grade" >
                    <option value="-1" <#if menu.grade==-1>selected="selected"</#if> >根菜单</option>
                    <option value="0" <#if menu.grade==0>selected="selected"</#if> >一级菜单</option>
                    <option value="1" <#if menu.grade==1>selected="selected"</#if>>二级菜单</option>
                    <option value="2" <#if menu.grade==2>selected="selected"</#if>>三级菜单</option>
                </select>
        </div>
    </div>

    <#if menu.grade==1>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">菜单url</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName"
                       lay-verify="required" name="url" id="url" placeholder="请输入菜单url" value="${(menu.url)!""}">
            </div>
        </div>
    </#if>
    <#if menu.grade==2>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">菜单url</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName"
                       lay-verify="required" name="url" id="url" placeholder="请输入菜单url" value="${(menu.url)!""}">
            </div>
        </div>
    </#if>

    <!--
       添加根级菜单
    -->
    <input name="parentId" type="hidden" value="${menu.pId}"/>
    <input name="id" type="hidden" value="${menu.id}"/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateModule">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/menu/update.js"></script>
</body>
</html>