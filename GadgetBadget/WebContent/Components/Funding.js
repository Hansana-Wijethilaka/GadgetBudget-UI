//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#fundID").val("");
	$("#FUND")[0].reset();
});

$(document).on("click", "#clear", function(event) {

	$("#FUND")[0].reset();
	$("#alertError").hide();
});

$(document).on("click", "#save_fund", function(event) {

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#fundID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url: "FundingBodiesAPI",
		type: type,
		data: $("#FUND").serialize(),
		dataType: "text",
		complete: function(response, status) {
			onItemSaveComplete(response.responseText, status);

			$("#alertSuccess").fadeTo(2000, 500).slideUp(500, function() {
				$("#alertSuccess").slideUp(500);
			});
		}
	});

});

function onItemSaveComplete(response, status) {

	if (status == "success") {

		//console.log(response);
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Fund Details Successfully saved.");
			$("#alertSuccess").show();
			$("#FundGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error") {

		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#fundID").val("");
	$("#FUND")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidFundIDSave").val($(this).data("fundID"));
	$("#fundID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#fundOrganizationName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#fundDescrption").val($(this).closest("tr").find('td:eq(2)').text());
	$("#fundCountry").val($(this).closest("tr").find('td:eq(3)').text());
	$("#fundAmount").val($(this).closest("tr").find('td:eq(4)').text());
	

});

$(document).on("click", ".btnRemove", function(event) {


	$.ajax({
		url: "FundingBodiesAPI",
		type: "DELETE",
		data: "fundID=" + event.target.value,
		dataType: "text",
		complete: function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			//window.location.reload(true);
			$("#alertSuccess").fadeTo(2000, 500).slideUp(500, function() {
				$("#alertSuccess").slideUp(500);
			});

		}
	});
});

function onItemDeleteComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Fund Details Successfully deleted.");
			$("#alertSuccess").show();
			$("#FundGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {

		$("#alertError").text("Error while deleting.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function validateItemForm() {


	if ($("#fundOrganizationName").val().trim() == "") {
		return "Enter fundOrganization name.";
	}

	if ($("#fundDescrption").val().trim() == "") {
		return "Enter Your Description.";
	}
	
	

	if ($("#fundCountry").val().trim() == "") {
		return "Enter Country.";
	}

	if ($("#fundAmount").val().trim() == "") {
		return "Enter Fund Amount.";
	}

	
	return true;
}