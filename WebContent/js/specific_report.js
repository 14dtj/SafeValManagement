/**
 * 
 */
$(function() {
	showInfo()
});
function showInfo() {
	var str = window.location.search;
	var index = str.indexOf("=");
	var id = str.substring(index + 1);
	$.ajax({
		type : 'POST',
		url : 'ReportServlet',
		dataType : 'json',
		data : {
			flag : "getspecific",
			id : id
		},
		error : function(xhr, err) {
			alert('Error：' + err + '！')
		},
		success : function(data) {
			$('#dw').val(data.dw);
			$('#productId').val(data.fsdm);
			$('#intePressure').val(data.zdyl);
			$('#medium').val(data.gzjz);
			$('#typeId').val(data.xh);
			$('#tj').val(data.tj);
			$('#diameter').val(data.ldzj);
			$('#manufactUnit').val(data.zzdw);
			$('#installLoc').val(data.azwz);
			$('#productPermitId').val(data.xkbh);
			$('#pressureRange').val(data.ylfw);
			$('#productWh').val(data.wh);
			$('#productDate').val(data.ccrq);
		}
	});
}