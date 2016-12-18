function save() {
	$.ajax({
		type : 'POST',
		url : 'PowerManage',
		dataType : 'json',
		data : {
			
		},
		error : function(xhr, err) {
			alert('Error：' + err + '！')
		},
		success : function(data) {

		}
	});
}