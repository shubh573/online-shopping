
window.menu = '${title}';

$(function(){
	//solving the active menu problem
	
	window.menu = '${title}';
	
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
			$('#products').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
});