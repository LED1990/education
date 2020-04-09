$(document).ready(function () {

    $(document).on("click", '#addAction', function () {
        event.preventDefault();
        var data = $('form').serialize();
        data += '&addAction=' + $(this).val();
        sendPost(data);
    });

    $(document).on("click", '#removeAction', function () {
        event.preventDefault();
        var data = $('form').serialize();
        data += '&removeAction=' + $(this).val();
        sendPost(data);
    });

    $(document).on("click", '#addClassification', function () {
        event.preventDefault();
        var data = $('form').serialize();
        data += '&addClassification=' + $(this).val();
        sendPost(data);
    });

    $(document).on("click", '#removeClassification', function () {
        event.preventDefault();
        var data = $('form').serialize();
        console.log('this val ', $(this).val());
        data += '&removeClassification=' + $(this).val();
        sendPost(data);
    });

    $(document).on("click", '#addIndication', function () {
        event.preventDefault();
        var data = $('form').serialize();
        data += '&addIndication=' + $(this).val();
        sendPost(data);
    });

    $(document).on("click", '#removeIndication', function () {
        event.preventDefault();
        var data = $('form').serialize();
        data += '&removeIndication=' + $(this).val();
        sendPost(data);
    });

    function sendPost(data) {
        $.post('update', data, function (response) {
            var $form = $('#evaluationForm');
            $form.replaceWith(response);
        });
    }

});

