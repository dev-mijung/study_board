<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action="/board/modify" method="post">
                        	
                        		<!-- 추가-->
                        		<!-- modify도 Model에서 Criteria를 사용하기 때문에 추가 해 줘야 함-->
                        		<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum }'/>"/>
                        		<input type="hidden" name="amount" value="<c:out value='${cri.amount }'/>"/>
                        		<input type="hidden" name="keyword" value="<c:out value='${cri.keyword }'/>"/>
                        		<input type="hidden" name="type" value="<c:out value='${cri.type }'/>"/>
                        		
                            	<div class="form-group">
                            		<label>Bno</label> <input class="form-control" name="bno" value='<c:out value="${board.bno }"/>'readonly="readonly"/>
                            	</div>
                            	<div class="form-group">
                            		<label>Title</label> <input class="form-control" name="title" value='<c:out value="${board.title }"/>' />
                            	</div>
                            	<div class="form-group">
                            		<label>Text area</label>
                            		<textarea class="form-control" rows="3" name="content">
                            			<c:out value="${board.content }"/>
                            		</textarea>
                            	</div>
                            	<div class="form-group">
                            		<label>Writer</label>
                            		<input class="form-control" name="writer" value='<c:out value="${board.writer }"/>' readonly="readonly"/>
                            	</div>
                            	<div class="form-group" hidden>
                            		<label>RegDate</label>
                            		<input class="form-control" name="regDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate }"/>' readonly="readonly">
                            	</div>
                            	<div class="form-group" hidden>
                            		<label>Update Date</label>
                            		<input class="form-control" name="updateDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly">
                            	</div>
                            	
                            	<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
                            	<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
                            	<button type="submit" data-oper='list' class="btn btn-info" >List</button>
                        	</form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
            </div>
            <!-- /.row -->
           
        <%@include file="../includes/footer.jsp" %>

</body>

<script type="text/javascript">
	$(document).ready(function(){
		
		var formObj = $("form");
		
		$('button').on("click", function(e){
			
			// form태그의 모든 버튼은 기본적으로 submit으로 처리하기 때문에, 
			// e.preventDefault()로 기본 동작을 막고 마지막에 직접 submit()을 수행 함
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation == 'remove'){
				console.log('remove');
				formObj.attr("action", "/board/remove");
			} else if(operation == 'list'){
				// pageNum, amount 외에 다른 param이 붙어있으면 안되서 pageNum과 amount를 복사해 두고 
				// formObj를 비웠다가 pageNum, amount, keyword, type을 다시 붙임
				formObj.attr("action","/board/list").attr("method","get")
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}
			formObj.submit();
		});
	});
</script>
</html>
