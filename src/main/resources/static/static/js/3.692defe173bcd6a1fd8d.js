webpackJsonp([3],{"7CS/":function(t,e){},poSA:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:function(){return{userLoginName:"",userAuth:""}},mounted:function(){this.userLoginName=JSON.parse(sessionStorage.getItem("UN")),this.userAuth=JSON.parse(sessionStorage.getItem("auth")),console.log("权限",this.userAuth),console.log("UN",this.userLoginName)},methods:{quitBtn:function(){this.$router.push({name:"login"}),sessionStorage.clear()}}},a={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-container",{staticStyle:{height:"100%",border:"1px solid #eee"}},[1==t.userAuth?s("el-aside",{staticStyle:{"background-color":"#b3c0d1"},attrs:{width:"200px"}},[s("div",{staticClass:"sysTitle"},[t._v("生产管理系统")]),t._v(" "),s("div",{staticClass:"userInfo"},[s("div",{staticClass:"icon"},[s("i",{staticClass:"el-icon-s-custom"})]),t._v(" "),s("div",{staticClass:"userRole"},[t._v("超级管理员")])]),t._v(" "),s("el-menu",{attrs:{"default-openeds":["6","9"]}},[s("el-submenu",{attrs:{index:"6"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-user"}),t._v("用户管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"6-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"6-2"}},[t._v("选项2")])],2),t._v(" "),s("el-submenu",{attrs:{index:"7"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-menu"}),t._v("计划管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"7-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"7-2"}},[t._v("选项2")])],2),t._v(" "),s("el-submenu",{attrs:{index:"8"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-s-order"}),t._v("任务管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"8-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"8-2"}},[t._v("选项2")])],2),t._v(" "),s("el-submenu",{attrs:{index:"9"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-files"}),t._v("检验管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"9-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"9-2"}},[t._v("选项2")])],2)],1)],1):t._e(),t._v(" "),2==t.userAuth?s("el-aside",{staticStyle:{"background-color":"#b3c0d1"},attrs:{width:"200px"}},[s("div",{staticClass:"sysTitle"},[t._v("生产管理系统")]),t._v(" "),s("div",{staticClass:"userInfo"},[s("div",{staticClass:"icon"},[s("i",{staticClass:"el-icon-s-custom"})]),t._v(" "),s("div",{staticClass:"userRole"},[t._v("计划管理员")])]),t._v(" "),s("el-menu",[s("el-submenu",{attrs:{index:"2"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-menu"}),t._v("计划管理")]),t._v(" "),s("el-menu-item-group",[s("el-menu-item",{attrs:{index:"2-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"2-2"}},[t._v("选项2")])],1)],2)],1)],1):t._e(),t._v(" "),3==t.userAuth?s("el-aside",{staticStyle:{"background-color":"#b3c0d1"},attrs:{width:"200px"}},[s("div",{staticClass:"sysTitle"},[t._v("生产管理系统")]),t._v(" "),s("div",{staticClass:"userInfo"},[s("div",{staticClass:"icon"},[s("i",{staticClass:"el-icon-s-custom"})]),t._v(" "),s("div",{staticClass:"userRole"},[t._v("操作员")])]),t._v(" "),s("el-menu",[s("el-submenu",{attrs:{index:"3"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-s-order"}),t._v("任务管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"3-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"3-2"}},[t._v("选项2")])],2)],1)],1):t._e(),t._v(" "),4==t.userAuth?s("el-aside",{staticStyle:{"background-color":"#b3c0d1"},attrs:{width:"200px"}},[s("div",{staticClass:"sysTitle"},[t._v("生产管理系统")]),t._v(" "),s("div",{staticClass:"userInfo"},[s("div",{staticClass:"icon"},[s("i",{staticClass:"el-icon-s-custom"})]),t._v(" "),s("div",{staticClass:"userRole"},[t._v("三方检验员")])]),t._v(" "),s("el-menu",[s("el-submenu",{attrs:{index:"4"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-files"}),t._v("检验管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"4-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"4-2"}},[t._v("选项2")])],2)],1)],1):t._e(),t._v(" "),5==t.userAuth?s("el-aside",{staticStyle:{"background-color":"#b3c0d1"},attrs:{width:"200px"}},[s("div",{staticClass:"sysTitle"},[t._v("生产管理系统")]),t._v(" "),s("div",{staticClass:"userInfo"},[s("div",{staticClass:"icon"},[s("i",{staticClass:"el-icon-s-custom"})]),t._v(" "),s("div",{staticClass:"userRole"},[t._v("操作员")])]),t._v(" "),s("el-menu",[s("el-submenu",{attrs:{index:"3"}},[s("template",{slot:"title"},[s("i",{staticClass:"el-icon-s-order"}),t._v("任务管理")]),t._v(" "),s("el-menu-item",{attrs:{index:"3-1"}},[t._v("选项1")]),t._v(" "),s("el-menu-item",{attrs:{index:"3-2"}},[t._v("选项2")])],2)],1)],1):t._e(),t._v(" "),s("el-container",[s("el-header",{staticStyle:{"text-align":"right","font-size":"12px"}},[s("span",{staticClass:"loginUser"},[t._v(t._s(t.userLoginName))]),t._v(" "),s("el-button",{attrs:{type:"warning"},on:{click:t.quitBtn}},[t._v("退出登录")])],1),t._v(" "),s("el-main",[s("router-view")],1)],1)],1)},staticRenderFns:[]};var l=s("VU/8")(i,a,!1,function(t){s("7CS/")},"data-v-78c3eaac",null);e.default=l.exports}});
//# sourceMappingURL=3.692defe173bcd6a1fd8d.js.map