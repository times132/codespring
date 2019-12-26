<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>


<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <%@include file="../includes/header.jsp"%>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Tables</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <div class="m-0 font-weight-bold text-primary">Board List Page
                            <button id="regBtn" type="button" class="btn btn-sm float-right">Register New Board</button>
                        </div>

                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>#번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>수정일</th>
                                </tr>
                                </thead>

                                <c:forEach items="${list}" var="board">
                                    <tr>
                                        <td><c:out value="${board.bno}"/></td>
                                        <td>
                                            <a class="move" href='<c:out value="${board.bno}"/>'>
                                            <c:out value="${board.title}"/><b> [<c:out value="${board.replycnt}"/>]</b>
                                            </a></td>
                                        <td><c:out value="${board.writer}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updatedate}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>

                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="searchForm" action="/board/list" method="get">
                                        <select name="type">
                                            <option value=""<c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>--</option>
                                            <option value="T"<c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''}"/>>제목</option>
                                            <option value="C"<c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''}"/>>내용</option>
                                            <option value="W"<c:out value="${pageMaker.cri.type eq 'W' ? 'selected' : ''}"/>>작성자</option>
                                            <option value="TC"<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''}"/>>제목+내용</option>
                                            <option value="TW"<c:out value="${pageMaker.cri.type eq 'TW' ? 'selected' : ''}"/>>제목+작성자</option>
                                            <option value="TWC"<c:out value="${pageMaker.cri.type eq 'TWC' ? 'selected' : ''}"/>>제목+내용+작성자</option>
                                        </select>
                                        <input type="text" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/>">
                                        <input type="hidden" name="pageNum" value="<c:out value="${pageMaker.cri.pageNum}"/>">
                                        <input type="hidden" name="amount" value="<c:out value="${pageMaker.cri.amount}"/>">
                                        <button class="btn btn-default">검색</button>
                                    </form>
                                </div>
                            </div>

                            <div class="page -align-center">
                                <ul class="pagination">
                                    <c:if test="${pageMaker.prev}">
                                        <li class="pagination_button previous"><a class="prev" href="${pageMaker.startPage - 1}">이전</a></li>
                                    </c:if>

                                    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                                        <li class="pagination_button ${pageMaker.cri.pageNum == num ? "active":""}"><a class="num" href="${num}">${num}</a></li>
                                    </c:forEach>

                                    <c:if test="${pageMaker.next}">
                                        <li class="pagination_button next"><a class="next" href="${pageMaker.endPage + 1}">다음</a></li>
                                    </c:if>
                                </ul>
                            </div>

                            <form id="actionForm" action="/board/list" method="get">
                                <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                                <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                                <input type="hidden" name="type" value="${pageMaker.cri.type}">
                                <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                            </form>

                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">처리가 완료되었습니다.</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2019</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        var result = '<c:out value="${result}"/>';

        checkModal(result);

        history.replaceState({}, null, null);

        function checkModal(result) {
            if(result === '' || history.state){
                return;
            }
            if (parseInt(result) > 0){
                $(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
            }
            $("#myModal").modal("show");
        }

        $("#regBtn").on("click", function(){
            self.location = "/board/register";
        });

        var actionForm = $("#actionForm");

        $(".pagination_button a").on("click", function (e) {
            e.preventDefault();
            console.log("click");
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });

        $(".move").on("click", function (e) {
            e.preventDefault();
            actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
            actionForm.attr("action", "/board/get");
            actionForm.submit();
        });
        
        var searchForm = $("#searchForm");
        
        $("#searchForm button").on("click" , function (e) {
            if (!searchForm.find("option:selected").val()){
                alert("검색종류를 선택하세요.");
                return false;
            }
            if (!searchForm.find("input[name='keyword']").val()){
                alert("키워드를 입력하세요.");
                return false;
            }

            searchForm.find("input[name='pageNum']").val("1");
            e.preventDefault();

            searchForm.submit();
        })
    });
</script>
<%@include file="../includes/footer.jsp"%>

</body>
</html>
