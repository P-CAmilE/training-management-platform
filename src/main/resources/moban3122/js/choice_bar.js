$(function () {
    //选中filter下的所有a标签，为其添加hover方法，该方法有两个参数，分别是鼠标移上和移开所执行的函数。
    $("#filter a").hover(
        function () {
            $(this).addClass("seling");
        },
        function () {
            $(this).removeClass("seling");
        }
    );


    //选中filter下所有的dt标签，并且为dt标签后面的第一个dd标签下的a标签添加样式seled。(感叹jquery的强大)
    $("#filter dt+dd a").attr("class", "seled"); /*注意：这儿应该是设置(attr)样式，而不是添加样式(addClass)，
                                                      不然后面通过$("#filter a[class='seled']")访问不到class样式为seled的a标签。*/

    //为filter下的所有a标签添加单击事件
    $("#filter a").click(function () {
        $(this).parents("dl").children("dd").each(function () {
            $(this).children("div").children("a").removeClass("seled");
        });

        $(this).attr("class", "seled");

        alert(RetSelecteds()); //返回选中结果
    });
    alert(RetSelecteds()); //返回选中结果
});

function RetSelecteds() {
    var result = "";
    $("#filter a[class='seled']").each(function () {
        result += $(this).html()+"\n";
    });
    return result;
}
