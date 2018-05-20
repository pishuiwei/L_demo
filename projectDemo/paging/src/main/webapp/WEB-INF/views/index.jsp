<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/paging.css" rel="stylesheet" type="text/css"/>
<title>PagingDemo</title>
</head>
<body>
	<table id="tablePage">
		<tr><td>序号</td><td>姓名</td><td>年龄</td><td align='center'>操作</td></tr>
	</table>
</body>
<script type="text/javascript">
	$(function(){
		paging();
	});
	function paging(currentPage,pageSize,deleteDataId,userName,userAge,id){
		if(typeof currentPage == "undefined" ){
			currentPage="1";
		}
		if(typeof pageSize == "undefined"){
			pageSize = "5";
		}
		if(typeof deleteDataId == "undefined"){
			deleteDataId = 0;
		}
		if(typeof userName == "undefined"){
			userName = "";
		}
		if(typeof userAge == "undefined"){
			userAge = "";
		}
		if(typeof id == "undefined"){
			id = 0;
		}
		var uiHTML = "";
		var page = "";
		$.ajax({
			type:"get",
			data:{"currentPage":currentPage,"pageSize":pageSize,"deleteDataId":deleteDataId,"userName":userName,"userAge":userAge,"id":id},
			url:"${pageContext.request.contextPath}/paging",
			dataType:"json",
			success:function(value){
				$.each(value.data.records,function(i,n){
					uiHTML += "<tr onmouseover='pageClick(this)' onmouseout='pagemouseout(this)'><td>"+(i+1)+
					"</td>"+"<td>"+n.userName+"</td>"+"<td>"+n.userAge+"</td>"+
					"<td align='center'> <span class='delete'>删除</span> <span class='update'>修改</span> "+
					"<input class='dataId' type='hidden' value='"+(n.id)+"'> </td>"+
					"</tr>"; 
				});
				$("#tablePage tr:first").after(uiHTML);
				if((value.data.totalPage)>1){
					page = " <tr><td colspan='5' align='center'> "+
					" <span>共"+value.data.totalCount+"条数据</span> "+
					" <span class='up'>上一页</span> "+
					" <span>第"+value.data.currentPage+"/"+value.data.totalPage+"页</span> "+
					" <span class='next'>下一页</span> "+
					" <input type='text' class='input' style='width: 20px;'><span class='skip'>跳转</span> "+
					" </td> "+
					" <td></td><td></td><td></td></tr> ";
					$("#tablePage tr:last").after(page);					
				}
				/* 删除功能start */
				$(".delete").click(function(){
					var deleteDataId =$(this).siblings("input").val();
					var con = confirm("你确定要删除此数据吗?");
					if(con==true){
						$("#tablePage tr:not(:first)").remove();
						paging(value.data.currentPage, value.data.pageSize, deleteDataId);
					}
				});
				/* 删除功能end */
				/* 修改功能start */
				$(".update").click(function(){
					var id =$(this).siblings("input").val();
					var userName,userAge;
					userName = prompt("新的名字");
					userAge = prompt("新的年龄");
					$("#tablePage tr:not(:first)").remove();
					paging(value.data.currentPage, value.data.pageSize, 0, userName, userAge, id);
				});
				/* 修改功能end */
								
				/* 下一页功能start */
				$(".next").click(function(){
					var currentPage = (value.data.currentPage)+1;
					var totalPage = value.data.totalPage;
					if(currentPage <= totalPage){
						$("#tablePage tr:not(:first)").remove();
						paging(currentPage);
					}else{
						alert("已经是最后一页了");
					}
				});
				/* 下一页功能end */
				/* 上一页功能start  */
				$(".up").click(function(){
					var currentPage = (value.data.currentPage)-1;
					if(currentPage >= 1){
						$("#tablePage tr:not(:first)").remove();
						paging(currentPage);
					}else{
						alert("已经是第一页了");
					}
				});
				/* 上一页功能end */
				/* 跳转功能start */
				$(".skip:not('.input')").click(function(){
					var currentPage = $(".input").val();
					var totalPage = value.data.totalPage;
					if(currentPage >= 1 && currentPage <= totalPage){
						$("#tablePage tr:not(:first)").remove();
						paging(currentPage);						
					}else{
						alert("没有此页")
					}
				})
				/* 跳转功能结束  */
				
			},
			error:function(){
				alert("请求分页数据失败！")
			}
		});
	}
	/* 鼠标移入tr改变背景颜色 */
	function pageClick(data){
		$(data).toggleClass("tr");
		$(data).siblings().removeClass("tr");
	}
	/* 鼠标移出 */
	function pagemouseout(data){
		$(data).removeClass("tr");
	}
</script>
</html>



