
$(document).ready(function(){
    if($('.btn-detalhar').length == 0){
        return;
    }
    
    $( ".btn-detalhar" ).each(function() {
    	var btnDetalhar = $(this);
    	btnDetalhar.click(function() {
    		var modalBody = $(".modal-body");
    		modalBody.empty();
    		var table = '<table class="table"><thead><tr><th scope="col">Descricao</th><th scope="col">Quantidade</th></tr></thead><tbody>'
			$.each(btnDetalhar.data( "itens" ), function(index, obj) {
				table += '<tr><td>' + obj.produto.descricao + '</td><td>' + obj.quantidade + '</td></tr>';
			});
    		table += '</tbody></table>';
    		modalBody.append(table);
		})
    })
})