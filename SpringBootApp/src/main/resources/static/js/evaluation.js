$(document).ready(function () {

    jQuery(function($) {
        $('.removeIndication').on('click', function() {
            console.log("[removeIndication] removing indication");
            $(this).parent('div').remove();
        });
    });


    jQuery(function($) {
        $('.addIndication').on('click', function() {
            console.log("[addIndication] adding indication");

            // var Iam = $(this).parent();
            // console.log("[addIndication] this " + Iam.val());
            // console.log("[addIndication] this " + Iam.attr('class'));
            // console.log("[addIndication] this " + Iam.attr('id'));
            // var obj = $(this).parent().find(":input");
            var obj = $(this).parent().find('input[type=hidden]');
            // console.log("[addIndication] obj " + obj.val());
            var className = obj.attr('class');
            // console.log("[addIndication] class name " + className);
            var action_index = obj.attr('class');
            // var indication_index = className.split(';')[1];
            // var count = $(this).parent().children('div').size();
            var indication_index = $(this).parent().children('div').length;
            // console.log("[addIndication] count1 " + count);
            console.log("[addIndication] indication_index " + indication_index);

            console.log("[addIndication] action_index " + action_index);



            $("<div class='col-sm'>\n" +
                "<input type='hidden' id='undesirableActions" + action_index +".indications"+ indication_index + ".id' name='undesirableActions[" + action_index + "].indications["+ indication_index + "].id' value=''>\n" +
                "<div class='input-group input-group-sm mb-3'>\n" +
                "<input id='undesirableActions" + action_index + ".indications"+ indication_index + ".info  type='text' class='form-control' aria-label='Sizing example input'\n" +
                "aria-describedby='inputGroup-sizing-sm' title='Classification code' name='undesirableActions[" + action_index +"].indications["+ indication_index + "].info' value='default info'/>\n" +
                "</div>\n" +
                "<div class='input-group input-group-sm mb-3'>\n" +
                "<input id='undesirableActions" + action_index +".indications"+ indication_index + ".medicineName'  type='text' class='form-control' aria-label='Sizing example input'\n" +
                "aria-describedby='inputGroup-sizing-sm' title='Classification name' name='undesirableActions[" + action_index +"].indications["+ indication_index + "].medicineName' value='default value'/>\n" +
                "</div>\n" +
                "<button type='button' class='removeIndication'>Remove</button>\n" +
                "</div>").appendTo($(this).parent());
        });

        //adding event handler to created element
        $(document).on( "click", '.removeIndication' , function(){
            console.log("[removeIndication] removing indication");
            $(this).parent('div').remove();
        } );
    });

});

