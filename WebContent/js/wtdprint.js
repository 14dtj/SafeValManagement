$(function() {
	showInfo()
});
function showInfo() {
	var str = window.location.search;
	var index = str.indexOf("=");
	var id = str.substring(index + 1);
	$.ajax({
		type : 'POST',
		url : 'PrintWtdServlet',
		dataType : 'json',
		data : {
			flag : "getspecific",
			id : id
		},
		error : function(xhr, err) {
			alert('Error：' + err + '！')
		},
		success : function(data) {
			$('#reportNum').html("委托单编号："+id);
			$('#unit').html(data.unit);
			$('#address').html(data.address);
			$('#contactPerson').html(data.contact);
			$('#tel').html(data.tel);
			var list = data.reportList;
			var feeTotal = 0;
			for(var i =0;i<list.length;i++){
				var temp = '<tr>'+
				'<td>'+(i+1)+'</td>'+
				'<td>'+list[i].version+'</td>'+
				'<td>'+list[i].tj+'</td>'+
				'<td>'+list[i].medium+'</td>'+
				'<td>'+list[i].workPressure+'</td>'+
				'<td>'+list[i].intePressure+'</td>'+
				'<td>'+list[i].attachEquip+'</td>'+
				'<td>'+list[i].location+'</td>'+
				'<td>'+list[i].reportId+'</td>'+
				'<td>'+list[i].fee+'</td>'+
				'</tr>'
				$("#checkReport").append(temp)
				feeTotal+=parseInt(list[i].fee)
			}
			$('#numCount').html(list.length);
			$('#feeCount').html(feeTotal);
		}
	});
}