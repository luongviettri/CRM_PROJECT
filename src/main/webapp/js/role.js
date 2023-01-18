$(document).ready(function () {
    //!.tên_class
    //!#tên_id
    $(".btn-xoa").click(function () {
        const id = this.id;
        const This = this; //! dùng để lưu this này lại
        console.log(id);
        //todo: logic code
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/roles/delete?id=${id}`,
            // data:
        }).done(function (data) {
            if (data.data) {
                This.closest("tr").remove(); //! closet --> tự động tìm thẻ tr gần nhất và xóa.
                console.log("Xoa thanh cong");
            } else {
                console.log("Xoa thất bại");
            }
        })
    })
})