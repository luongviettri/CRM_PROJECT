$(document).ready(function () {
    getAllUsers();

    deleteUser();

})
$(".button-xoa").click(function () {
    console.log("vo day roi ne")
})
const deleteUser = () => {
    $('body').on('click', 'a.button-xoa', function () {
        const id = this.id;
        const This = this;
        $.ajax({
            method: "DELETE",
            url: `http://localhost:8080/api/user/delete?userID=${id}`
        }).done(function (data) {
            This.closest("tr").remove();
            console.log(("Xóa user thành công rồi nà"))
        });

    });
}

const getAllUsers = () => {
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/api/users`,
        // data:
    }).done(function (data) {
        if (data.data) {
            const userArray = data.data;
            let myBigHtml = ``;
            userArray.forEach((user, index) => {
                const html = `   
         <tr>
            <td>  ${user.id} </td>
            <td> ${user.firstName} </td>
            <td> ${user.lastName} </td>
            <td> ${user.userName} </td>
            <td> ${user.role} </td>
            <td>
                <a href="#" class="btn btn-sm btn-primary">Sửa</a>
                <a href="#" id="${user.id}" class="btn btn-sm btn-danger button-xoa">Xóa</a>
                <a href="user-details.html" class="btn btn-sm btn-info">Xem</a>
            </td>
        </tr>`
                myBigHtml += html;

            })
            //! tìm đến table và thêm thôi
            $("#myTbody").append(myBigHtml);

        } else {
            $("#myTbody").append(`<tr><td> something wrong</td>  </tr>`);
        }
    })

}


