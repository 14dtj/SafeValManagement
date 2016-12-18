/**
 * 
 */
$(function() {
	//showInfo()
});
function showInfo() {
	$('#list').empty();
	$
			.ajax({
				type : 'GET',
				url : 'PrintReportServlet',
				dataType : 'json',
				data : {
					flag : "getList"
				},
				error : function(xhr, err) {
					alert('Error：' + err + '！')
				},
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var a = "<li><span>"
								+ data[i].reportNum
								+ " "
								+ data[i].checkUser
								+ " "
								+ data[i].checkDate
								+ "</span><a href=\"/java_test/specificPrintReport.html?id="
								+ data[i].reportNum + "\">打印预览</a><br></li>";
						$('#list').append(a);
					}
				}
			});
}
function search() {
	var checkUser = $('#checkUser').val();
	var reportNum = $('#reportNum').val();
	var checkDate = $('#checkDate').val();
	if(checkUser=="" && reportNum=="" && checkDate==""){
		showInfo();
		return;
	}
	$('#list').empty(); 
	$
			.ajax({
				type : 'GET',
				url : 'PrintReportServlet',
				dataType : 'json',
				data : {
					flag : "getListBySearch",
					checkUser : checkUser,
					reportNum : reportNum,
					checkDate : checkDate
				},
				error : function(xhr, err) {
					alert('Error：' + err + '！')
				},
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var a = "<li><span>"
								+ data[i].reportNum
								+ " "
								+ data[i].checkUser
								+ " "
								+ data[i].checkDate
								+ "</span><a href=\"/java_test/specificPrintReport.html?id="
								+ data[i].reportNum + "\">打印预览</a><br></li>";
						$('#list').append(a);
					}
				}
			});
}
