$(function() {
	$('.update-isStopped-button').on('click', function() {
		if (confirm($(this).parents('.update-isStopped').find('input[name="userName"]').val() + "を" + $(this).parents('.update-isStopped').find('option:selected').text() + "状態にします。よろしいですか？")) {
			return true;
		}
		return false;
	});
});