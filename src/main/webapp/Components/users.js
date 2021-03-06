$(document).ready(function()
{ 
 	if ($("#alertSuccess").text().trim() == "") 
 	{ 
 		$("#alertSuccess").hide(); 
 	} 
 	$("#alertError").hide(); 
 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear status msges-------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation----------------
	var status = validateItemForm();
	// If not valid-------------------
	
	if (status != true)
	{	
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid-----------------------
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "UsersAPI",
		type : type,
		data : $("#formUserID").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Registered.");
			$("#alertSuccess").show();
			
			$("#divUsersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error")
	{
		$("#alertError").text("Error while Registering.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formUser")[0].reset();
}



//form fill 
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).data("userid"));
	$("#UserNameId").val($(this).closest("tr").find('td:eq(1)').text());
	$("#UserEmailId").val($(this).closest("tr").find('td:eq(2)').text());
	$("#UserPWDId").val($(this).closest("tr").find('td:eq(3)').text());
	$("#UserContactId").val($(this).closest("tr").find('td:eq(4)').text());
	$("#UserRoleId").val($(this).closest("tr").find('td:eq(5)').text());
});


// CLIENT-MODEL================================================================
function validateItemForm()
{
	
	// CODE
	if ($("#UserNameId").val().trim() == "")
	{
		return "Insert User Name.";
	}
	
	
	// NAME
	if ($("#UserEmailId").val().trim() == "")
	{
		return "Insert Email Address.";
	}
	
	// PRICE-------------------------------
	if ($("#UserPWDId").val().trim() == "")
	{
		return "Insert Password.";
	}
	
	
	// Role Number-------------------------------
	if ($("#UserRoleId").val().trim() == "")
	{
		return "Insert Role ID.";
	}
	
	
	
	// User Contact Number------------------------
	if ($("#UserContactId").val().trim() == "")
	{
		return "Insert Contact Number.";
	}

	// is numerical value
	var tmpContact = $("#UserContactId").val().trim();
	if (!$.isNumeric(tmpContact))
	{
		return "Insert a numerical value for Contact Number.";
	}
	
	
return true;
}


//DELETE
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "UsersAPI",
		type : "DELETE",
		data : "UserID=" + $(this).data("userid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divUsersGrid").html(resultSet.data);
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
