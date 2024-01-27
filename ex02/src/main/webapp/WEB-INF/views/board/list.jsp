<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type='button' class='btn btn-xs pull-right'>Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                
                                <c:forEach items="${list }" var="board">
                                	<tr>
                                		<td>
                                			<a href="/board/get?bno=<c:out value="${board.bno }"/>">
                                				<c:out value="${board.bno }"/>
                                			</a>
                                		</td>
                                		<td>
                                			<a class='move' href='<c:out value="${board.bno }"/>' target='_blank'>
                                				<c:out value="${board.title }"/>
                                			</a>
                                		</td>
                                		<td>
                                			<a href="/board/get?bno=<c:out value="${board.bno }"/>">
                                				<c:out value="${board.writer }"/>
                                			</a>
                                		</td>
                                		<td>
                                			<a href="/board/get?bno=<c:out value="${board.bno }"/>">
                                				<fmt:formatDate pattern="yyyy-MM-dd"
                                				value="${board.regdate }"/>
                                			</a>
                                		</td>
                                		<td>
                                			<a href="/board/get?bno=<c:out value="${board.bno }"/>">
                                				<fmt:formatDate pattern="yyyy-MM-dd"
                                					value="${board.updateDate }"/>
                                			</a>
                                		</td>
                                	</tr>
                                </c:forEach>
                            </table> <!-- table end-->
                           
                           <!-- search start-->
                           <div class='row'>
                           	<div class='col-lg-12'>
                           		<form id='searchForm' action='/board/list' method='get'>
                           			<select name='type'>
                           				<option value=""
                           					<c:out value="${pageMaker.cri.type == null ? 'selected' : '' }"/>>--</option>
                           				<option value="T"
                           					<c:out value="${pageMaker.cri.type == 'T' ? 'selected' : '' }"/>>제목</option>
                           				<option value="C"
                           					<c:out value="${pageMaker.cri.type == 'C' ? 'selected' : '' }"/>>내용</option>
                           				<option value="W"
                           					<c:out value="${pageMaker.cri.type == 'W' ? 'selected' : '' }"/>>작성자</option>
                           				<option value="TC"
                           					<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : '' }"/>>제목 or 내용</option>
                           				<option value="TW"
                           					<c:out value="${pageMaker.cri.type eq 'TW' ? 'selected' : '' }"/>>제목 or 작성자</option>
                           				<option value="TWC"
                           					<c:out value="${pageMaker.cri.type eq 'TWC' ? 'selected' : '' }"/>>제목 or 내용 or 작성자</option>
                           			</select>
                           			<input type='text' name='keyword'
                           				value='<c:out value="${pageMaker.cri.keyword }"/>' />
                           			<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum }"/>' />
                           			<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount }"/>' />
                           			<button class='btn btn-default'>Search</button>
                           		</form>
                           	</div>
                          </div>
                          
                           <!-- paging-->
                           <div class='pull-right'>
                           	<ul class="pagination">
                           		<c:if test="${pageMaker.prev }">
                           			<li class="paginate_button previous"><a href="${pageMaker.startPage -1}">Previous</a>
                           			</li>
                           		</c:if>
                           		
                           		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                           			<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active" : "" }"><a href="${num }">${num }</a></li>
                           		</c:forEach>
                           		
                           		<c:if test="${pageMaker.next }">
                           			<li class="paginate_button next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
                           		</c:if>
                           	</ul>
                           </div>
                           <!-- end paging-->
                           
                           <form id="actionForm" action="/board/list" method="get">
                           	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                           	<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                           	<input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type }"/>' />
                           	<input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword }"/>' />
                           </form>
                           
                           <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">
                                            	처리가 완료되었습니다.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
                            
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
// addFlashAttribute()로 저장되었기 때문에 한 번도 사용된 적이 없다면 값을 만들어 내지만,
//사용자가 board/list를 호출하거나 , 새로고침을 통해서 호출하는 경우는 값에 아무것도 없음
//addFlashAttribute()를 이용해서 일회성으로만 데이터를 사용할 수 있으므로
//이를 이용해서 경고창이나 모달창 등을 보여주는 방식으로 처리할 수 있음
	$(document).ready(function(){
		
		var result = '<c:out value="${result}"/>'
		
		
		checkModal(result);
		
		history.replaceState({},null,null);
		
		function checkModal(result){
			if(result == '' || history.state){
				return;
			}
			
			if(parseInt(result) > 0 ){
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.")
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click",function(){
			
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e) {
            e.preventDefault();
            console.log('click');
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });
		
		$(".move").on("click", function(e){

			e.preventDefault();
			
			console.log('move');
			
			// this는 현재 this의 위치에서 해당되는 객체를 가르킴. 현재 여기에서는 actionForm
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action","/board/get");
			actionForm.submit();
			
		});
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e){
			
			if(!searchForm.find("option:selected").val()){
				alert("검색종류를 선택하세요");
				return false;
			}
			
			// 화면에서 키워드가 없다면 검색을 하지 않도록 제어
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요");
				return false;
			}
			
			// 브라우저에서 검색 버튼을 클릭하면 <form>태그의 전송은 막고, 페이지의 번호는 1이 되도록 처리
			$("#searchForm").find("input[name='pageNum']").val("1")
			e.preventDefault();
			
			searchForm.submit();
		});
		
	});
</script>
</html>
