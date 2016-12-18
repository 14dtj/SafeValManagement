function save(){
	var code = $("#code").val()
	var owner = $("#owner").val()
	var name = $("#name").val()
	var tel = $("#tel").val()
	var address = $("#address").val()


	$.ajax({
		type: 'POST',
		url: 'DwServlet',
		dataType: 'json',
		data: {
			flag:"add",
			code:code,
			owner:owner,
			name:name,
			tel:tel,
			address:address
		},
		error: function(xhr, err){
			alert('Error：' + err + '！')
		},
		success: function(data){
			alert(data.msg);	
		}
	});
}

function onload(){
	var code = decodeURIComponent($.getUrlVar('code'))
	var name = decodeURIComponent($.getUrlVar('name'))
	var owner = decodeURIComponent($.getUrlVar('owner'))
	var tel = decodeURIComponent($.getUrlVar('tel'))
	var address = decodeURIComponent($.getUrlVar('address'))

	if( code != "undefined"){
		$("title")[0].innerHTML = "单位修改"
		$("#topic")[0].innerHTML = "<B>单位修改</B>"
		$("#code").val(code);
		$("#owner").val(owner);
		$("#name").val(name);
		$("#tel").val(tel);
		$("#address").val(address);
	}
	$.ajax({
		type: 'POST',
		url :'DwServlet',
		dataType:'json',
		data:"flag=checkLogin",
		error: function(xhr,err){
		},
		success: function(data){
			if(data.msg=="NOLOGIN"){
				alert("please login.");
				window.location.href="login.html";
			}
			
			
		}
	});
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
