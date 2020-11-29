/**
 * 
 */

document.addEventListener("DOMContentLoaded", common_init);

function common_init(){
	console.log('common script');
}


function movePage(formSeq){
	location.href = '/main?formSeq='+formSeq;
}

