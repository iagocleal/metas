$(document).ready(function(){
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/meta/lstMetas",
		success: function(data){			
			var $trHTML = $("#tblMetas");
			$.each(data, function (i, item) {
				$trHTML.append('<tr><td>' + item.descricao + '</td>'
						+ '<td>' + item.orgao.descricao + '</td>'
						+ '<td>' + item.metaRelacionada.descricao + '</td>'
						+ '<td class="actions"><button id="btnEditar" type="button" class="btn btn-warning btn-xs" onclick="editar('+item.id+')">Editar</button> <a class="btn btn-danger btn-xs"  href="#" data-toggle="modal" data-target="#delete-modal" onclick="excluir('+item.id+')">Excluir</a> <input name="excluirMeta" type="hidden" id="excluirMeta" value="'+item.id+'"></td></tr>');
	        });
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});	
	
});

function editar(id){
	window.location = "cadMetas.html?idMeta="+id;
}

function excluir(id){
	$('#excluirMeta').val(id)
}

function excluirSelecionado(id){
	console.log($('#excluirMeta').val());
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/metas/rest/meta/excluirMeta?idMeta=" + $('#excluirMeta').val(),
		success: function(data){
			if(!data){
				$("#divMensagemVer").attr("hidden", false);
				$("#mensagemVer").html("Impossível excluir, pois a meta selecionada está relacionada a outra meta!");
				$('#delete-modal').modal('hide')
			}else {
				window.location = "verMetas.html";
			}
		},
		error: function(e){
			alert("Erro: " + e);
			console.log(e);
		}
	});
}