//page refresh moment
$(document).ready(function() 
{
		if ($("#alertSuccess").text().trim() == "") 
		{
			$("#alertSuccess").hide(); 
		}
		$("#alertError").hide();
});		
		
$(document).on("click", "#updateArea", function(event) {
	$("#insertForm").hide();
	$("#formItemClass").show();
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

$(document).on("click", "#insertArea", function(event) {
	$("#formItemClass").hide();
	$("#insertForm").show();
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSubmit", function(event) 
{
		// Clear alerts---------------------
		$("#alertSuccess").text(""); 
		$("#alertSuccess").hide(); 
		$("#alertError").text(""); 
		$("#alertError").hide();
		
		// Form validation-------------------
		var status = validateForm(); 
		if (status != true)
		{
			$("#alertError").text(status); 
			$("#alertError").show(); 
			return;
		}
		
		// If valid------------------------
		var type = ($("#hidRIDSave").val() == "") ? "POST" : "PUT"; 
		
		$.ajax(
		{
		
				url : "ResearcherAPI",
				type : "Post",
				data : $("#formResearcher").serialize(),
				dataType : "text",
				complete : function(response, status) 
				{
					onResearcherSaveComplete(response.responseText, status);
				}
		});
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{
		$("#hidRIDSave").val($(this).data("Rid").find('#hidResearcherIdUpdate').val());
		$("#rName").val($(this).closest("tr").find('td:eq(0)').text()); 
		$("#rPhone").val($(this).closest("tr").find('td:eq(1)').text()); 
		$("#rAddress").val($(this).closest("tr").find('td:eq(2)').text()); 
		$("#rEmail").val($(this).closest("tr").find('td:eq(3)').text());
		$("#projectName").val($(this).closest("tr").find('td:eq(4)').text());
		$("#rCost").val($(this).closest("tr").find('td:eq(5)').text());
});

// CLIENT-MODEL================================================================
function validateResearcherForm() 
{
	 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	 var emailval = $("#email").val();
	 var telNoVal = $("#telephone").val();
	 var telNoReg = /^\d{10}$/;
	 
	// NAME
	if ($("rName").val().trim() == "") {
	return "Insert Researcher name.";
	}
	// PHONE
	if(!telNoReg.test(telNoVal)){
		return "Insert Valid Telephone No";
	}
	// ADDRESS
	if ($("#rAddress").val().trim() == "") {
	return "Insert Researcher postal address.";
	}
	if ($("#email").val().trim() == ""){
		return "Insert Email";
	}
	if (!emailReg.test(emailval)) {
		return "Insert Valid Email";
	}
	// PROJECT NAME-------------------------------
	if ($("#projectName").val().trim() == "") {
	return "Insert project name.";
	}
	// is numerical value
	var rCost = $("#rCost").val().trim(); 
	if (!$.isNumeric(rCost))
	{
		return "Insert a numerical value for project cost.";
	}
	// convert to decimal price
	$("#rCost").val(parseFloat(rCost).toFixed(2));
		
	return true;
}

function validateForm() {

	 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	 var emailval = $("#email2").val();
	 var telNoVal = $("#telephone2").val();
	 var telNoReg = /^\d{10}$/;
	
	if ($("#email2").val().trim() == ""){
		return "Insert Email";
	}
	if ($("#telephone2").val().trim() == ""){
		return "Insert Telephone No";
	}
	if (!emailReg.test(emailval)) {
		return "Insert Valid Email";
	}
	if(!telNoReg.test(telNoVal)){
		return "Insert Valid Telephone No";
	}
	return true;
}

function onResearcherSaveComplete(response, status) 
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divresearcherGrid").html(resultSet.data);
			$("#hidRIDSave").val("");
			$("#formResearcher")[0].reset();
			//$("#hidPaymentIDSave2").val("");
			//$("#formItem2")[0].reset();
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		} else if(resultSet.status.trim() == "AuthError") {
			$("#alertError").text("Authentication Error");
			$("#alertError").show();
		}
	} else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
	} else 
		{
			$("#alertError").text("Unknown Error Occured");
			$("#alertError").show();
		}
				
		$("#hidRIDSave").val(""); 
		$("#formResearcher")[0].reset();	
	
}	

// DELETE==========================================
$(document).on("click", ".btnRemove", function(event) 
{
	$.ajax( 
	{
		url : "ResearcherAPI",
		type : "DELETE",
		data : "RID=" + $(this).data("Rid"),
		dataType : "text",
		complete : function(response, status) 
		{
			onResearcherDeleteComplete(response.responseText, status);
		} 
	});
});

function onResearcherDeleteComplete(response, status) 
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show();
			$("#divresearcherGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{
			
			$("#alertError").text(resultSet.data); 
			$("#alertError").show();
		}
	} else if (status == "error") 
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show(); 
	} else
	{

		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show();
	}
}

				
				
				
				