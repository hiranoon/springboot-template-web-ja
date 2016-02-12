$(function() {
    // イベントの登録
    $('#logoutConfirmModalSubmitTrigger').one('click', function(){
        // 非表示の anchor ボタンに遷移する
        location.href = $('#logoutButton').attr("href");
    });
});
