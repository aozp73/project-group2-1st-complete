<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
<div style="height: 100px;"></div>
    <div class="container py-3 px-3">
        <div class="justify-content-center d-flex my-3 py-3">
            <h2>
                이력서 수정
            </h2>
        </div>
        <div class="my-border-color-default px-3 py-3" style="width: 100%; ">
            <form>
                <input id="id" name="id" value="${resume.id}" type="hidden">
                <div class="px-3">
                    <div class="title column py-3">
                        <input id="title" class="form-control my-border-color-default" type="text"
                            placeholder="제목을 입력해주세요." value="${resume.title}" name="title"> 
                    </div>
                    <div class="form-group">
                        <textarea class="form-control summernote" rows="5" name="content" id="content">${resume.content}</textarea>
                    </div>
                </div>
            </form>
        </div>
        <div class="d-flex justify-content-center">
            <div class="btn-group m-4" role="group" aria-label="Basic example ">
                <button onclick="save(${resume.id})" type="button" class="btn btn-primary my-button-color-default" id="btnSave">완료</button>
            </div>
            <div class="btn-group m-4" role="group" aria-label="Basic example ">
                <button id="btnCancel" type="button" class="btn btn-secondary">취소</button>
            </div>
        </div>
    </div>


    <script>
        $('.summernote').summernote({
            tabsize: 2,
            height: 500
        });
    </script>

    <script>
        function save(id) {
            let data = {
                "id": $("#id").val(),
                "title": $("#title").val(),
                "content": $("#content").val()
            };
            $.ajax({
                type: "post",
                url: "/resume/update/"+id,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(res => {
                console.log(data);
                alert(res.msg);
                location.href = "/resume/list";
            }).fail(err => {
                alert(err.JSON)
            });
        }
    </script>

 <%@ include file="../layout/footer.jsp" %>