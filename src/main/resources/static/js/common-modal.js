/**
 * 
 */
var defaultModalID = 'modalFrame';
var defaultFormID = 'formFrame';
var defaultAlertID = 'alertFrame';
var defaultModalObj = $('#' + defaultModalID);
var defaultFormObj = $('#' + defaultFormID);
var defaultAlertObj = $('#' + defaultAlertID);

function openLayer(title, layerId, onConfirm, onClose){
	
	layerObj = $('#'+layerId);
	if(layerObj.length == 0) return false;
	
	tempModalObj = $(defaultModalObj);
	tempModalObj.find('.modal-title').text(title);
	tempModalObj.find('.modal-body').html(layerObj.html());
	
	if(onConfirm != null){
		
		// function 이 아닌 문자열인경우 미리 정의해놓은 기능 동작
		resultCallback = null;
		if(onConfirm === 'reload'){
			resultCallback = function(){ location.reload(); };
		}else{
			resultCallback = onConfirm;
		}
		
		tempModalObj.find('.modal-confirm').off('click').click(resultCallback);
	}
	
	if(onClose != null){
		// function 이 아닌 문자열인경우 미리 정의해놓은 기능 동작
		resultCallback = null;
		if(onClose === 'reload'){
			resultCallback = function(){ location.reload(); };
		}else{
			resultCallback = onClose;
		}
		
		tempModalObj.find('.modal-close').off('click').click(resultCallback);
	}
	
	tempModalObj.modal('show');
}

function openLayerForm(title, layerId, formData){
	
	layerObj = $('#'+layerId);
	if(layerObj.length == 0) return false;
		
	tempFormObj = $(defaultFormObj);
	tempFormObj.find('.modal-title').text(title);
	tempFormObj.find('.modal-body').html(layerObj.html());
	
	tempFormId = 'frm' + new Date().getTime();
	tempFormObj.find(".temp-form").attr("id", tempFormId);
	tempFormObj.find('.temp-form').attr("action", formData.action);
	tempFormObj.find('.temp-form').attr("method", formData.method);
	tempFormObj.find('.temp-form').attr("target", formData.target);
	
	$('#' + tempFormId).parsley();
	
	if(formData.submitText != ''){
		tempFormObj.find('.modal-confirm').text(formData.submitText);
	} 
	tempFormObj.modal('show');
}

function openDialog(title, msg, onConfirm, onClose){
	
	tempModalObj = $(defaultModalObj);
	tempModalObj.find('.modal-title').text(title);
	tempModalObj.find('.modal-body').text(msg);
	
	if(onConfirm != null){
		// function 이 아닌 문자열인경우 미리 정의해놓은 기능 동작
		resultCallback = null;
		if(onConfirm === 'submit'){
			resultCallback = function(){ layerObj.submit(); };
		}else if(onConfirm === 'reload'){
			resultCallback = function(){ location.reload(); };
		}else{
			resultCallback = onConfirm;
		}
		
		tempModalObj.find('.modal-confirm').click(resultCallback);
	}
	
	if(onClose != null){
		// function 이 아닌 문자열인경우 미리 정의해놓은 기능 동작
		resultCallback = null;
		if(onClose === 'reload'){
			resultCallback = function(){ location.reload(); };
		}else{
			resultCallback = onClose;
		}
		
		tempModalObj.find('.modal-close').click(resultCallback);
	}
	
	tempModalObj.modal('show');
}

function openAlert(msg, onClose){
	
	tempModalObj = $(defaultAlertObj);
	tempModalObj.find('.modal-body').text(msg);
	tempModalObj.find('.modal-confirm').hide();
	
	if(onClose != null){
		// function 이 아닌 문자열인경우 미리 정의해놓은 기능 동작
		resultCallback = null;
		if(onClose === 'reload'){
			resultCallback = function(){ location.reload(); };
		}else{
			resultCallback = onClose;
		}
		
		tempModalObj.find('.modal-close').click(resultCallback);
	}
	
	tempModalObj.modal('show');
}
