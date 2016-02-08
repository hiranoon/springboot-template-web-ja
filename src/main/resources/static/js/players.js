$(function() {
    // 変数宣言
    var currentId;

    // モーダル表示時のイベント処理
    $('#destroyConfirmModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); //モーダルを呼び出すときに使われたボタンを取得
        currentId = button.data('id'); //data-id の値を取得してセットする
    })

    // イベントの登録
    $('#destroyConfirmModalSubmitTrigger').one('click', function(){
        $('#destroySubmit_' + currentId).trigger('click'); // 非表示のsubmitボタンをクリックする
    });
});
