$(document).ready(function () {

    $("#myForm").submit(function (e) {
        e.preventDefault();
        var values = {};
        $.each($('#myForm').serializeArray(), function (i, field) {
            values[field.name] = field.value;
        });
        console.log(values);
        $.ajax({
            method: "POST",
            url: `http://localhost:8080/api/roles/add`,
            data: {
                name: values.tenQuyen,
                desc: values.moTa
            }
        }).done(function (data) {
            if (data.data) {
                // This.closest("tr").remove(); //! closet --> tự động tìm thẻ tr gần nhất và xóa.
                console.log("Them thanh cong roi na");
                window.location.replace("http://localhost:8080/roles");
            } else {
                console.log("Xoa thất bại");
            }
        })

    })

    $("")

})