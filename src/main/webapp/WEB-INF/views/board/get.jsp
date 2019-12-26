<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>

<body id="page-top">
    <div id="wrapper">
        <%@include file="../includes/header.jsp"%>
        <div class="container-fluid">
            <div class='bigPictureWrapper'>
                <div class='bigPicture'>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>

            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card panel-default">
                        <div class="card-header">Board Read Page</div>

                        <div class="card-body">
                            <div class="form-group">
                                <label>Bno</label>
                                <input class="form-control" name="bno" value="<c:out value="${board.bno}"/>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Title</label>
                                <input class="form-control" name="title" value="<c:out value="${board.title}"/>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Text area</label>
                                <input class="form-control" name="content" value="<c:out value="${board.content}"/>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Writer</label>
                                <input class="form-control" name="writer" value="<c:out value="${board.writer}"/>" readonly="readonly">
                            </div>
                            <sec:authentication property="principal" var="pinfo"/>
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${pinfo.username eq board.writer}">
                                    <button data-oper="modify" class="btn btn-default">Modify</button>
                                </c:if>
                            </sec:authorize>

                            <button data-oper="list" class="btn btn-info">List</button>

                            <form id="operForm" action="/board/modify" method="get">
                                <input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}"/>">
                                <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
                                <input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
                                <input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
                                <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">Files</div>

                        <div class="card-body">
                            <div class="uploadResult">
                                <ul>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-comment fa-fw"></i>Reply
                            <sec:authorize access="isAuthenticated()">
                                <button id="addReplyBtn" class="btn btn-primary btn-xs float-right">New Reply</button>
                            </sec:authorize>
                        </div>

                        <div class="card-body">
                            <ul class="chat">

                            </ul>
                        </div>

                        <div class="card-footer">

                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Reply</label>
                        <input class="form-control" name="reply" value="New Reply!!!">
                    </div>
                    <div class="form-group">
                        <label>Replyer</label>
                        <input class="form-control" name="replyer">
                    </div>
                    <div class="form-group">
                        <label>Reply Date</label>
                        <input class="form-control" name="replyDate" value="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
                    <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
                    <button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
                    <button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/resources/js/reply.js"></script>

    <script>
        $(document).ready(function () {
            var bnoValue = "<c:out value="${board.bno}"/>";
            var replyUL = $(".chat");

            showList(1);

            function showList(page) {
                replyService.getList({bno: bnoValue, page: page || 1}, function (replyCnt, list) {

                    if (page == -1){
                        pageNum = Math.ceil(replyCnt/10.0);
                        showList(pageNum);
                        return;
                    }

                    var str = "";

                    if (list == null || list.length == 0){
                        return;
                    }
                    for (var i = 0, len = list.length || 0; i < len; i++){
                        str += "<li class='left clearfix' data-rno='" + list[i].rno+"'>";
                        str += "<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
                        str += "<small class='float-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>"
                        str += "<p>"+ list[i].reply + "</p></div></li>";
                    }
                    replyUL.html(str);

                    showReplyPage(replyCnt);
                });
            }

            var pageNum = 1;
            var replyPageFooter = $(".card-footer");

            function showReplyPage(replyCnt) {
                var endNum = Math.ceil(pageNum/10.0) * 10;
                var startNum = endNum - 9;

                var prev = startNum != 1;
                var next = false;

                if (endNum * 10 >= replyCnt){
                    endNum = Math.ceil(replyCnt/10.0);
                }

                if (endNum * 10 < replyCnt){
                    next = true;
                }

                var str = "<ul class='pagination float-right'>";

                if (prev){
                    str += "<li class='page-item'><a class='page-link' href='" + (startNum-1) + "'>이전</a></li>";
                }

                for (var i = startNum; i <= endNum; i++){
                    var active = pageNum == i ? "active" : "";
                    str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
                }

                if (next){
                    str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>다음</a></li>";
                }

                str += "</ul></div>";

                console.log(str);

                replyPageFooter.html(str);
            }

            replyPageFooter.on("click", "li a", function (e) {
                e.preventDefault();
                console.log("page click");

                var targetPageNum = $(this).attr("href");

                pageNum = targetPageNum;

                showList(pageNum);
            });

            var modal = $(".modal");
            var modalInputReply = modal.find("input[name='reply']");
            var modalInputReplyer = modal.find("input[name='replyer']");
            var modalInputReplyDate = modal.find("input[name='replyDate']");

            var modalModBtn = $("#modalModBtn");
            var modalRemoveBtn = $("#modalRemoveBtn");
            var modalRegisterBtn = $("#modalRegisterBtn");

            var replyer = null;
            <sec:authorize access="isAuthenticated()">
                replyer = '<sec:authentication property="principal.username"/>';
            </sec:authorize>

            var csrfHeaderName ="${_csrf.headerName}";
            var csrfTokenValue="${_csrf.token}";

            $("#modalCloseBtn").on("click", function (e) {
                modal.modal("hide");
            });

            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
            });

            $("#addReplyBtn").on("click", function (e) {
                modal.find("input").val("");
                modal.find("input[name='replyer']").val(replyer);
                modalInputReplyDate.closest("div").hide();
                modal.find("button[id != 'modalCloseBtn']").hide();

                modalRegisterBtn.show();

                $(".modal").modal("show");
            });

            modalRegisterBtn.on("click", function (e) {
                var reply = {
                    reply: modalInputReply.val(),
                    replyer: modalInputReplyer.val(),
                    bno: bnoValue
                };
                replyService.add(reply, function (result) {
                    alert(result);

                    modal.find("input").val("");
                    modal.modal("hide");

                    showList(-1);
                });
            });

            $(".chat").on("click", "li", function () {
                var rno = $(this).data("rno");

                replyService.get(rno, function (reply) {
                    modalInputReply.val(reply.reply);
                    modalInputReplyer.val(reply.replyer);
                    modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
                    modal.data("rno", reply.rno);

                    modal.find("button[id != 'modalCloseBtn']").hide();
                    modalModBtn.show();
                    modalRemoveBtn.show();

                    $(".modal").modal("show");
                });
            });

            modalModBtn.on("click", function (e) {
                var reply = {
                    rno: modal.data("rno"),
                    reply: modalInputReply.val()
                };

                replyService.update(reply, function (result) {
                    alert(result);
                    modal.modal("hide");
                    showList(pageNum);
                });
            });

            modalRemoveBtn.on("click", function (e) {
                var rno = modal.data("rno");

                if (!replyer){
                    alert("로그인후 삭제가 가능합니다.");
                    modal.modal("hide");
                    return;
                }

                var originalReplyer = modalInputReplyer.val();

                console.log("Original Replyer: " + originalReplyer);

                if (replyer != originalReplyer){
                    alert("자신의 작성한 댓글만 삭제가 가능합니다.");
                    modal.modal("hide");
                    return;
                }

                replyService.remove(rno, originalReplyer, function (result) {
                    alert(result);
                    modal.modal("hide");
                    showList(pageNum);
                });
            });

            var bno = '<c:out value="${board.bno}"/>';

            $.getJSON("/board/getAttachList", {bno: bno}, function (arr) {
                console.log(arr);

                var str = "";

                $(arr).each(function (i, attach) {
                    if (attach.fileType){
                        var fileCallPath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);

                        str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid +
                            "'data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
                        str += "<img src='/display?fileName=" + fileCallPath + "'>";
                        str += "</div></li>"
                    } else{
                        str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid +
                            "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
                        str += "<span>" + attach.fileName + "</span><br/>";
                        str += "<img src='/resources/img/attach.png'>";
                        str += "</div></li>";
                    }
                });
                $(".uploadResult ul").html(str);
            });

            $(".uploadResult").on("click", "li", function (e) {
                console.log("view image");

                var liObj = $(this);

                var path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_" + liObj.data("filename"));

                if (liObj.data("type")){
                    showImage(path.replace(new RegExp(/\\/g), "/"));
                } else{
                    self.location = "/download?fileName=" + path;
                }
            });

            function showImage(fileCallPath) {
                alert(fileCallPath);

                $(".bigPictureWrapper").css("display", "flex").show();

                $(".bigPicture")
                    .html("<img src='/display?fileName=" + fileCallPath + "'>")
                    .animate({width: '100%', height: '100%'}, 1000);
            }

            $(".bigPictureWrapper").on("click", function (e) {
                $(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
                setTimeout(function () {
                    $(".bigPictureWrapper").hide();
                }, 1000);
            })

            var operForm = $("#operForm");

            $("button[data-oper='modify']").on("click", function (e) {
                operForm.attr("action", "/board/modify").submit();
            });

            $("button[data-oper='list']").on("click", function (e) {
                operForm.find("#bno").remove();
                operForm.attr("action", "/board/list");
                operForm.submit();
            });
        });
    </script>
<%@include file="../includes/footer.jsp"%>
</body>
</html>
