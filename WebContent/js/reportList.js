/**
 * 
 */
$(function() {
	showInfo()
});
function showInfo() {
	$.ajax({
		type : 'GET',
		url : 'ReportServlet',
		dataType : 'json',
		data : {
			flag : "getList"
		},
		error : function(xhr, err) {
			alert('Error：' + err + '！')
		},
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				var a = "<li><span>" + data[i].safeId+" "+data[i].safeName+"</span><a href=\"/java_test/entryReport.html?id="+data[i].safeId+"\">录入报告</a><br></li>";
				$('#list').append(a);
			}
		}
	});
}