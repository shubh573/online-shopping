
$(function(){
	//solving the active menu problem
	
	if(menu == "About")
		$('#about').addClass('active');
	else if(menu == "Contact")
		$('#contact').addClass('active');
	else if(menu == "Products")
		$('#products').addClass('active');
	else if(menu == "Manage Products")
		$('#manageproducts').addClass('active');	
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
	
	//dismiss alert after 3 second
	var $alert = $('.alert');
	
	if($alert.length) {
		
		setTimeout(function() {
			$alert.fadeOut("slow");
		},3000);
	}
	
	//------------------------------
	
	
	
	//------------------------------
	// data table for admin
	//------------------------------
	
// code for jquery dataTable
	
	var $adminProductsTable = $('#adminProductsTable');
	
	if($adminProductsTable.length) {
		
		var jsonUrl = window.contextRoot +'/data/admin/all/products';
		
		
		$adminProductsTable.DataTable( {
			
			lengthMenu: [[10,30,50,-1],['10 Records','30 Records','50 Records','All']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},		
			columns: [
				{
					data: 'id'	
				},
				{
					data: 'code',
					mRender: function(data, type, row) {
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>'
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
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
					data: 'unitPrice',
					mRender: function(data, type, row) {
						return '&#8377; ' + data
					}	
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data, type, row) {
						
						var str = '';
						
						str += '<label class="switch">';
						if(data) {
							str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}	
						else{
							str +=	'<input type="checkbox" value="'+row.id+'"/>';
						}
						str += '<div class="slider"></div></label>';
						
							return str;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row) {

						var str = '';
						
						str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str += '<span class="fa fa-pencil"></span></a>';
							
							return str;
					}
				}
					
			],
			
			initComplete: function() {
				
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {
					
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)?	'You want to activate the product?':
											'You want to deactivate the product?';
					var value = checkbox.prop('value');
					bootbox.confirm({
						size: 'medium',
						title: 'Product Activation & Deactivation',
						message: dMsg,
						callback: function(confirmed) {
							
							if(confirmed) {
								console.log(value);
								
								var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
								
								$.post(activationUrl, function(data) {
									bootbox.alert({
										 size: 'medium',
										 title: 'information',
										 message: data
									});
								});
								
								
							}
							else{
								checkbox.prop('checked', !checked);
							}
						}	
				});
				
				});	
			}
		
		});
			
	}
	
	
	//------------------------------
	// Validation code for category
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({
			
			rules : {
				
				name : {
					
					required: true,
					minlength: 2
				},
				
				description: {
					
					required: true 
				}
			},
			
			messages : {
				
				name : {
					
					required: 'Please add the category name!',
					minlength: 'The category name should not be less than 2  characters'
				},
				
				description: {
					
					required: 'Please add description for this category!'
				}
			},
			errorElement: 'em',
			errorPlacement: function(error, element) {
				//add the class of help-block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
		});
	}
});

