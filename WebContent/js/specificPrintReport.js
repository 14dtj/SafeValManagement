/**
 * 
 */
$(function() {
	showInfo();
	$("#btnPrint").click(function() {
		$("#printContent").printArea();
	});
});
function showInfo() {
	var str = window.location.search;
	var index = str.indexOf("=");
	var id = str.substring(index + 1);
	$.ajax({
		type : 'POST',
		url : 'PrintReportServlet',
		dataType : 'json',
		data : {
			flag : "getspecific",
			id : id
		},
		error : function(xhr, err) {
			alert('Error：' + err + '！')
		},
		success : function(data) {
			$('#reportNum').html("报告编号："+data.reportNum);
			$('#dw').html(data.dw);
			$('#address').html(data.address);
			$('#tel').html(data.tel);
			$('#dm').html(data.fsdm);
			$('#azwz').html(data.azwz);
			$('#lx').html();
			$('#safeValId').html(data.id);
			$('#tj').html(data.tj);
			$('#manufactUnit').html(data.zzdw);
			$('#workPressure').html(data.gzyl);
			$('#workMedium').html(data.gzjz);
			$('#intePressure').html(data.zdyl);
			$('#operateNorm').html(data.operateNorm);
			$('#verifyMode').html(data.verifyMode);
			$('#verifyMedium').html(data.verifyMedium);
			var first = parseInt(data.firstRealPressure);
			var second = parseInt(data.secondRealPressure);
			var third = parseInt(data.thirdRealPressure);
			$('#realPressure').html((first + second + third) / 3);
			var first1 = parseInt(data.firstTrailPressure);
			var second1 = parseInt(data.secondTrailPressure);
			var third1 = parseInt(data.thirdTrailPressure);
			$('#trailPressure').html((first1 + second1 + third1) / 3);
			$('#result').html(data.verifyResult);
			$('#fixStatement').html(data.record);
			$('#verifyDate').html(data.verifyDate);
			$('#nextVerifyDate').html(data.validYear);
			$('#verifyUser').html(data.verifyUser);
			$('#checkUser').html(data.checkUser);
		}
	});
}
