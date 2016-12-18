/**
 * 
 */
$(function(){
	srhcompany()
	 $("#checkAll").click(function() {
         $('input[name="subBox"]').attr("checked",this.checked) 
     });
     var $subBox = $("input[name='subBox']")
     $subBox.click(function(){
         $("#checkAll").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false)
     });
});

function srhcompany(){
	var code = $("#code").val()	
	var name = $("#name").val()
	var owner = $("#owner").val()
	var tel = $("#tel").val()
	var address = $("#address").val()
	var page = $.getUrlVar('page')	
	
	if(page==undefined){
		page = 1;
	}
	
	$.ajax({
		type: 'POST',
		url: 'DwServlet',
		dataType: 'json',
		data: {
			flag:"srh",
			code:code,
			name:name ,
			owner:owner,
			tel:tel,
			address:address,
			page: page
		},
		error: function(xhr, err){
			alert('Error:' + err + '!')
		},
		success: function(res){
			
			var companys = res.dws
			if(res.pages == "0"){
				alert(companys)
			}else{
				$("#companytb").empty()
				$("#nav").empty()
				$('#companytb').after('<div id="nav"></div>')
				var rowsShown = 3
				var rowsTotal = res.pages
				var numPages = Math.ceil(rowsTotal/rowsShown)
				
				for(i = 0;i<numPages;i++) {
					var pageNum = i + 1
					$('#nav').append('<a href="company.html?page='+pageNum+'"'+' rel="'+i+'">'+pageNum+'</a> ')
				}
				
				for(var i=0; i < companys.length; i++){
					var company = '<tr><td><input type="checkbox" name="subBox"  id=cb"'+i+'"></td>'+
					'<td>'+(i+1)+'</td>'+
					'<td>'+companys[i]["code"]+'</td>'+
					'<td>'+companys[i]["name"]+'</td>'+
					'<td>'+companys[i]["owner"]+'</td>'+
					'<td>'+companys[i]["tel"]+'</td>'+
					'<td>'+companys[i]["address"]+'</td>'+'</tr>'
					$("#companytb").append(company)
				}
			}
		}
	});
}

function delcompany(){
	var checked = $("input[type='checkbox'][name='subBox']")
	var ischecked = false;
	$(checked).each(function(){
		if($(this).attr("checked")==true) 
		{
			ischecked = true
			var item = $(this).parent().next().next()[0].innerText
			if (item=="") {
				alert("位号不能为空!")
				return;
			}
			var del = $(this).parent().parent()
			$.ajax({
				type: 'POST',
				url :'DwServlet',
				dataType:'json',
				data:{
					flag:"delete",
					code:item,
				},
				error: function(xhr,err){
					alert('request failed:'+err+'!')
				},
				success: function(data){
					del.remove()
				}
			});
		}
	 });
	if(!ischecked){
		alert("请勾选要删除的项!");
		return;
	}
}

function updcompany(){
	var checked = $("input[type='checkbox'][name='subBox']");
	var ischecked = false;
	$(checked).each(function(){
		if($(this).attr("checked")==true) 
		{
			ischecked = true
			var company= $(this).parent().nextAll();
			var code = company[1].innerText;
			var name = company[2].innerText;
			var owner = company[3].innerText;
			var tel = company[4].innerText;
			var address = company[5].innerText;


			var url = 'companyedit.html?code='+code+'&name='+encodeURIComponent(name)+
			'&owner='+encodeURIComponent(owner)+'&tel='+encodeURIComponent(tel)+
			'&address='+encodeURIComponent(address)
			window.open(url)
		}
	 });
	if(!ischecked){
		alert("请勾选要更新的项!");
		return;
	}
}
$.extend({
	  getUrlVars: function(){
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	      hash = hashes[i].split('=');
	      vars.push(hash[0]);
	      vars[hash[0]] = hash[1];
	    }
	    return vars;
	  },
	  getUrlVar: function(name){
	    return $.getUrlVars()[name];
	  }
	});