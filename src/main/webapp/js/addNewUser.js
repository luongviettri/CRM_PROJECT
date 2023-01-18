$(document).ready(function () {
    $("#myForm").submit(function (e) {
        e.preventDefault();
        var values = {};
        $.each($('#myForm').serializeArray(), function (i, field) {
            values[field.name] = field.value;
        });
        console.log(values);
        const country = $('#country').find(":selected").text();
        console.log(country)

        $.ajax({
            method: "POST",
            url: `http://localhost:8080/api/users`,
            data: {
                fullName: values.fullName,
                email: values.email,
                password: values.password,
                phone: values.phone,
                country: country
            }

        }).done(function (data) {
            if (data.data) {
                // This.closest("tr").remove(); //! closet --> tự động tìm thẻ tr gần nhất và xóa.
                console.log("Them thanh cong roi na");
                window.location.replace("http://localhost:8080/user-table.jsp");
            } else {
                console.log("Thêm thất bại");
            }
        })

    })

})