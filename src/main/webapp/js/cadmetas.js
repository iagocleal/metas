var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
$(document).ready(function(){
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/orgao/getOrgao",
		success: function(data){
			
			var $el = $("#cadOrgao");
			$.each(data, function(i, item) {
				$el.append($("<option></option>")
						.attr("value", item.id).text(item.descricao));
			});
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/tipoMeta/getTipoMeta",
		success: function(data){
			
			var $el = $("#cadTipo");
			$.each(data, function(i, item) {
				$el.append($("<option></option>")
						.attr("value", item.id).text(item.descricao));
			});
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});		
	
	var idMeta = getUrlParameter('idMeta');
	
	if(idMeta != null){
		buscarMetaById(idMeta);
	}
});

function buscarMetaById(idMeta){
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/meta/getMetaById?idMeta="+idMeta,
		success: function(data){
			
			$.each(data, function(i, item) {
				console.log(data);
				$("#cadTipo").val(item.tipoMeta.id);
				$("#cadOrgao").val(item.orgao.id);
				$("#cadDescricao").val(item.descricao);
				
				changeOrgao();
				
				$("#cadSetor").val(item.setor.id);
				$("#cadMetaRelacionada").val(item.metaRelacionada.id);
			});
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});
}

function changeOrgao(){
	var $elAux = $("#cadOrgao");

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/setor/getSetor?seqOrgao="+$elAux.val(),
		success: function(data){
			
			var $el = $("#cadSetor");
			$el.empty();
			$el.append($("<option></option>")
					.attr("value", "0").text("SELECIONE"));
			$.each(data, function(i, item) {
				$el.append($("<option></option>")
						.attr("value", item.id).text(item.descricao));
			});
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/meta/getMetaRelacionada?seqOrgao="+$elAux.val(),
		success: function(data){
			var $el = $("#cadMetaRelacionada");
			$el.empty();
			$el.append($("<option></option>")
					.attr("value", "0").text("SELECIONE"));
			$.each(data, function(i, item) {
				$el.append($("<option></option>")
						.attr("value", item.id).text(item.descricao));
			});
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});	
}

function salvarForm(){
	
	var idMeta = getUrlParameter('idMeta');
	
	if(idMeta != null){
		var	meta =  {"tipoMeta": { "id": $("#cadTipo").val()},	"metaRelacionada": {"id": $("#cadMetaRelacionada").val()},	"orgao": {"id": $("#cadOrgao").val()},	"setor": {"id": $("#cadSetor").val()}, "id": idMeta, "descricao": $("#cadDescricao").val()};
	}
	else{
		var	meta =  {"tipoMeta": { "id": $("#cadTipo").val()},	"metaRelacionada": {"id": $("#cadMetaRelacionada").val()},	"orgao": {"id": $("#cadOrgao").val()},	"setor": {"id": $("#cadSetor").val()}, "descricao": $("#cadDescricao").val()};	
	}
	
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/metas/rest/meta/salvarMeta",
		contentType: 'application/json',
		data: JSON.stringify(meta),
	    dataType: 'json',
		success: function(data){
			url = "verMetas.html";
			$(location).attr("href", url);
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});
}