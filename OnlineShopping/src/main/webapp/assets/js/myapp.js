

$(function(){
	//solving the active menu problem
	
	if(menu == "About")
		$('#about').addClass('active');
	else if(menu == "Contact")
		$('#contact').addClass('active');
	else if(menu == "Products")
		$('#products').addClass('active');
	else
		$('#home').addClass('active');
		$('#a_'+menu).addClass('active');
//	switch(menu) {
//	
//		case "About":
//			$('#about').addClass('active');
//			break;
//		case "Contact":
//			$('#contact').addClass('active');
//			break;
//		case "Products":
//			$('#products').addClass('active');
//			break;
//		default:
//			$('#home').addClass('active');
//			$('#a_'+menu).addClass('active');
//			break;
//	}

	// code for jquery dataTable
	
	
	var $table = $('#productListTable');
	
	if($table.length) {
		
		var jsonUrl = '';
		if(window.categoryID == '') {
			jsonUrl = window.contextRoot + '/data/all/products';
		}
		else{
			jsonUrl = window.contextRoot + '/data/category/'+ window.categoryID +'/products';
		}
		
		$table.DataTable( {
			
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records','All']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},		
			columns: [
				{
					data: 'code',
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>'
					}
				},
				
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data
					}	
				},
				{
					data: 'quantity',
					mRender: function(data, type, row) {
						
						if(data < 1) {
							return '<span>Out of Stock!</span>';	
						}
						
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {
						
						var str = '';
						str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-info"><span class="fa fa-eye"></span></a> &#160;';
						
						if(row.quantity < 1) {
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="fa fa-shopping-cart"></span></a>';
						}
						else {
							str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><span class="fa fa-shopping-cart"></span></a>';
						}
						
						return str;
					}
				}
					
			]
			
			
		});
			
	}
	

});

