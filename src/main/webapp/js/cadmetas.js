var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
};

$(document).ready(
		function() {

			$.ajax({
				type : "GET",
				url : "http://localhost:8080/metas/rest/orgao/orgao",
				success : function(data) {

					var $el = $("#cadOrgao");
					$.each(data, function(i, item) {
						$el.append($("<option></option>")
								.attr("value", item.id).text(item.descricao));
					});
				},
				error : function(e) {
					alert("Erro: " + e);
					console.log(e);
				}
			});

			$.ajax({
				type : "GET",
				url : "http://localhost:8080/metas/rest/tipoMeta/tipoMeta",
				success : function(data) {

					var $el = $("#cadTipo");
					$.each(data, function(i, item) {
						$el.append($("<option></option>")
								.attr("value", item.id).text(item.descricao));
					});
				},
				error : function(e) {
					alert("Erro: " + e);
					console.log(e);
				}
			});

			var idMeta = getUrlParameter('idMeta');

			if (idMeta != null) {
				buscarMetaById(idMeta);
			}
		});

function buscarMetaById(idMeta) {
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/metas/rest/meta/metaById?idMeta="
				+ idMeta,
		success : function(data) {

			$.each(data, function(i, item) {
				$("#cadTipo").val(item.tipoMeta.id);
				$("#cadOrgao").val(item.orgao.id);
				$("#cadDescricao").val(item.descricao);

				popularSetorMeta(item.setor.id, item.metaRelacionada.id);

				$("#cadSetor").val(item.setor.id);
				$("#cadMetaRelacionada").val(item.metaRelacionada.id);

			});
		},
		error : function(e) {
			alert("Erro: " + e);
			console.log(e);
		}
	});
}

function popularSetorMeta(idSetor, idMetaRelacionada) {
	var $elAux = $("#cadOrgao");

	$.ajax({
		type : "GET",
		url : "http://localhost:8080/metas/rest/setor/setor?seqOrgao="
				+ $elAux.val(),
		success : function(data) {

			var $el = $("#cadSetor");
			$el.empty();
			$el.append($("<option></option>").attr("value", "0").text(
					"SELECIONE"));
			$.each(data, function(i, item) {
				$el.append($("<option></option>").attr("value", item.id).text(
						item.descricao));
			});

			$el.val(idSetor);
		},
		error : function(e) {
			alert("Erro: " + e);
			console.log(e);
		}
	});

	$
			.ajax({
				type : "GET",
				url : "http://localhost:8080/metas/rest/meta/metaRelacionada?seqOrgao="
						+ $elAux.val(),
				success : function(data) {
					var $el = $("#cadMetaRelacionada");
					$el.empty();
					$el.append($("<option></option>").attr("value", "0").text(
							"SELECIONE"));
					$.each(data, function(i, item) {
						$el.append($("<option></option>")
								.attr("value", item.id).text(item.descricao));
					});

					if (idMetaRelacionada != null) {
						$el.val(idMetaRelacionada);
					}
				},
				error : function(e) {
					alert("Erro: " + e);
					console.log(e);
				}
			});
}

function changeOrgao() {
	var $elAux = $("#cadOrgao");

	$.ajax({
		type : "GET",
		url : "http://localhost:8080/metas/rest/setor/setor?seqOrgao="
				+ $elAux.val(),
		success : function(data) {

			var $el = $("#cadSetor");
			$el.empty();
			$el.append($("<option></option>").attr("value", "0").text(
					"SELECIONE"));
			$.each(data, function(i, item) {
				$el.append($("<option></option>").attr("value", item.id).text(
						item.descricao));
			});
		},
		error : function(e) {
			alert("Erro: " + e);
			console.log(e);
		}
	});

	$
			.ajax({
				type : "GET",
				url : "http://localhost:8080/metas/rest/meta/metaRelacionada?seqOrgao="
						+ $elAux.val(),
				success : function(data) {
					var $el = $("#cadMetaRelacionada");
					$el.empty();
					$el.append($("<option></option>").attr("value", "0").text(
							"SELECIONE"));
					$.each(data, function(i, item) {
						$el.append($("<option></option>")
								.attr("value", item.id).text(item.descricao));
					});
				},
				error : function(e) {
					alert("Erro: " + e);
					console.log(e);
				}
			});
}

function prepararMensagem(){
	var msgAdvertencia = "";
	

	if ($("#cadTipo").val() == 0) {
		msgAdvertencia += "Tipo da meta é obrigatório<br />";
	}
	if ($("#cadOrgao").val() == 0) {
		msgAdvertencia += "Orgão é obrigatório<br />";
	}
	if ($("#cadSetor").val() == 0) {
		msgAdvertencia += "Setor é obrigatório<br />";
	}
	if ($("#cadDescricao").val() == "") {
		msgAdvertencia += "Descrião é obrigatório<br />";
	}
	
	$("#mensagem").html(msgAdvertencia);
}

$(function() {
$("#btnSalvar").click(function() {
	
	if ($("#cadTipo").val() == 0 
			|| $("#cadOrgao").val() == 0
			|| $("#cadSetor").val() == 0 
			|| $("#cadDescricao").val() == "") {
		$("#divMensagem").attr("hidden", false);
		prepararMensagem();
	} else {
		var idMeta = getUrlParameter('idMeta');

		if (idMeta != null) {
			var meta = {
				"tipoMeta" : {
					"id" : $("#cadTipo").val()
				},
				"metaRelacionada" : {
					"id" : $("#cadMetaRelacionada").val()
				},
				"orgao" : {
					"id" : $("#cadOrgao").val()
				},
				"setor" : {
					"id" : $("#cadSetor").val()
				},
				"id" : idMeta,
				"descricao" : $("#cadDescricao").val()
			};
		} else {
			var meta = {
				"tipoMeta" : {
					"id" : $("#cadTipo").val()
				},
				"metaRelacionada" : {
					"id" : $("#cadMetaRelacionada").val()
				},
				"orgao" : {
					"id" : $("#cadOrgao").val()
				},
				"setor" : {
					"id" : $("#cadSetor").val()
				},
				"descricao" : $("#cadDescricao").val()
			};
		}

		$.ajax({
			type : "POST",
			url : "http://localhost:8080/metas/rest/meta/salvarMeta",
			contentType : 'application/json',
			data : JSON.stringify(meta),
			dataType : 'json',
			success : function(data) {
				url = "verMetas.html";
				$(location).attr("href", url);
			},
			error : function(e) {
				alert("Erro: " + e);
				console.log(e);
			}
		});
	}
});

	$("#fechar").click(function() {
		$("#divMensagem").attr("hidden", true);
	});
	
})