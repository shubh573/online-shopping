
window.menu = '${title}';

$(function(){
	//solving the active menu problem
	

	
	switch(menu) {}
	
		case 'About':
			$('#about').addClass('active');
			break;
		case 'Contact':
			$('#contact').addClass('active');
			break;
		case 'Products':
			$('#products').addClass('active');
			break;
		default:
			if(menu	== "Home") break;
			$('#products').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}

	// code for jquery datatable
	// create a dataset
	var products = [
	
		['1', 'ABC'],
		['2', 'BDJ'],
		['3', 'UDT'],
		['4', 'MFG'],
		['5', 'XHT'],
		['6', 'PWS'],
		['7', 'WBJ'],
		['8', 'LSA'],
		
		]

	var $table = $('productListTable');

	if($table.length) {
		console.log('Inside the table');
	}

});