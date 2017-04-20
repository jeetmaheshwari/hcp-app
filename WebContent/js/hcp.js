// Create a TabStrip instance
var oTabStrip = new sap.ui.commons.TabStrip("TabStrip1");
oTabStrip.setWidth("600px");
oTabStrip.setHeight("200px");
oTabStrip.setEnableTabReordering(true);
oTabStrip.attachClose( function (oEvent) {
	var oTabStrip1 = oEvent.oSource;
	oTabStrip1.closeTab(oEvent.getParameter("index"));
});

var oDropdownBox = new sap.ui.commons.DropdownBox("DropdownBox1");
oDropdownBox.setWidth("120px");
oDropdownBox.setTooltip("Select Rule");
var oItem = new sap.ui.core.ListItem("Rule-1");
oItem.setText("Equals");
oDropdownBox.addItem(oItem);
oItem = new sap.ui.core.ListItem("Rule-2");
oItem.setText("Equal To");
oDropdownBox.addItem(oItem);
var inputField = new sap.ui.commons.TextField("pmntVal", {value:"",width:"5em"});
inputField.setEnabled(false);
inputField.setVisible(false);

oDropdownBox.attachChange(
	function(){ 
		if(this.getValue() != 'Equals') {
			inputField.setEnabled(true); 
			inputField.setVisible(true);
		}
		else {
			inputField.setValue(0);
			inputField.setEnabled(false);
			inputField.setVisible(false);
		}			
	}
);

var submitButton = new sap.ui.commons.Button("SubmitButton", {
												text: "Submit",
												width:"8em",
												style: sap.ui.commons.ButtonStyle.Accept,
												press: function(oEvent){
													updateContent();
												}});
												
var oLayout1 = new sap.ui.commons.layout.MatrixLayout("Matrix1", {columns: 2, width: "100%"});
oLayout1.setWidths(['150px']);
oTF1 = new sap.ui.commons.TextField("TextField-Customer", {tooltip: 'Enter Customer No.', editable: true, value: '', width: '10em'});
oLabel = new sap.ui.commons.Label("Label-Customer", {text: 'Customer No.', labelFor: oTF1});
oLayout1.createRow(oLabel, oTF1);

oTF2 = new sap.ui.commons.TextField("TextField-Document", {tooltip: 'Enter Document No.', editable: true, value: '', width: '10em'});
oLabel = new sap.ui.commons.Label("Label-Document", {text: 'Document No.', labelFor: oTF2});
oLayout1.createRow(oLabel, oTF2);

oLabel = new sap.ui.commons.Label("Label-Rule", {text: 'Rule', labelFor: oDropdownBox});
oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: [oDropdownBox, inputField]});
oLayout1.createRow(oLabel, oCell);

oLabel = new sap.ui.commons.Label("Label-Submit", {text: '', labelFor: submitButton});
oLayout1.createRow(oLabel, submitButton);

oTabStrip.createTab("Custom Rules",oLayout1);
oTabStrip.placeAt("formFields");

$.showLoading({
  allowHide: true
});  

var oTable =  new sap.ui.table.Table({ 
    title: "Customer Items", 
    selectionMode: sap.ui.table.SelectionMode.Single,
	extension: [
		new sap.ui.commons.Button({text: "Export", press: function() { alert("Button pressed!"); }})
	]
}); 

var oScrollContainer = new sap.m.ScrollContainer({
     height: "400px",
     vertical: true,
     horizontal: true,
     focusable: true,
     content: [oTable]
   });

// create the DataTable control
var oSimpleTableForm = new sap.ui.layout.form.SimpleForm(
	"sf2",
	{
		layout: sap.ui.layout.form.SimpleFormLayout.ResponsiveGridLayout,
		columnsXL:2,
		editable: true,
		maxContainerCols: 2,
		content:[oScrollContainer
			]
	});

// define the Table columns
var oControl = new sap.m.Text({text:"{mandt}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Client"}), template: oControl, sortProperty: "mandt", filterProperty: "mandt", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{bukrs}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Company Code"}), template: oControl, sortProperty: "bukrs", filterProperty: "bukrs", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{kunnr}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Customer"}), template: oControl, sortProperty: "kunnr", filterProperty: "kunnr", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{umsks}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Trans.type"}), template: oControl, sortProperty: "umsks", filterProperty: "umsks", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{umskz}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "SG"}), template: oControl, sortProperty: "umskz", filterProperty: "umskz", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{augdt}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Clearing"}), template: oControl, sortProperty: "augdt", filterProperty: "augdt", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{augbl}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Clrng doc."}), template: oControl, sortProperty: "augbl", filterProperty: "augbl", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{zuonr}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Assignment"}), template: oControl, sortProperty: "zuonr", filterProperty: "zuonr", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{gjahr}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Year"}), template: oControl, sortProperty: "gjahr", filterProperty: "gjahr", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{belnr}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "DocumentNo"}), template: oControl, sortProperty: "belnr", filterProperty: "belnr", width: "100px", hAlign: "Center"}));
oControl = new sap.m.Text({text:"{buzei}"});
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Itm"}), template: oControl, sortProperty: "buzei", filterProperty: "buzei", width: "100px", hAlign: "Center"}));

oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Pstng Date"}),template: new sap.m.Text({text: "{budat}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Doc. Date"}),template: new sap.m.Text({text: "{bldat}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Entry Dte"}),template: new sap.m.Text({text: "{cpudt}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Loc.curr.amount"}),template: new sap.m.Text({text: "{dmbtr}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Amount"}),template: new sap.m.Text({text: "{wrbtr}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Crcy"}),template: new sap.m.Text({text: "{waers}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Reference"}),template: new sap.m.Text({text: "{xblnr}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Doc. Type"}),template: new sap.m.Text({text: "{blart}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Period"}),template: new sap.m.Text({text: "{monat}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "PK"}),template: new sap.m.Text({text: "{bschl}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Trg.sp.G/L"}),template: new sap.m.Text({text: "{zumsk}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "D/C"}),template: new sap.m.Text({text: "{shkzg}"}), width: "100px", hAlign: "Center"}));
oTable.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "BusA"}),template: new sap.m.Text({text: "{gsber}"}), width: "100px", hAlign: "Center"})); 

function hideDiv() {
	$.ajax({
        type: "GET",
        async: true,
        url: "/hcp-app/rest/hcp/currentUser",
        success: function (data) {
        	$("<p style=\'color:white\'>"+data+"</p>").insertAfter("#__spacer1");
        }
	});
	
	$("#sap-ui-preserve")[0].style = '';
	$("#sap-ui-preserve")[0].className = '';
	if(undefined != $("#sap-ui-preserve")[0].attributes[3])
		$("#sap-ui-preserve")[0].attributes[3].nodeValue="";
}

function logout() {
	window.location.href = "rest/hcp/Logout";
}

function updateContent() {
	var viewData = [];
	//var content = oSimpleForm.getContent();
	var customer = oTF1.getValue();
	var docNumber = oTF2.getValue();
	var rule = oDropdownBox.getValue();
	var amount = content[7].getValue();
	var formData = { 
            'Customer':  customer, 
            'DocNumber': docNumber,
            'Rule': rule,
			'Amount': amount
        };
	
	//$("#ajaxSpinnerImage").show();
	var updateUrl = "/hcp-app/rest/hcp/invoiceUpdate/"+customer+"/"+docNumber+"/"+rule+"/"+"100";
	if(null != amount && amount.length > 0)
		updateUrl = "/hcp-app/rest/hcp/invoiceUpdate/"+customer+"/"+docNumber+"/"+rule+"/"+amount;
	$.showLoading();
	$.ajax({
        type: "POST",
        async: true,
        url: updateUrl,
        success: function (data) {
        	$.ajax({
	            type: "GET",
	            async: true,
	            url: "/hcp-app/rest/hcp/clearedItems/"+customer+"/"+docNumber,
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            success: function (data) {
	            	$.hideLoading();
	            	var oModel = new sap.ui.model.json.JSONModel();
            		oModel.setData(data);
            		oTable.setModel(oModel);
            		oTable.bindRows("/");
            		oSimpleTableForm.placeAt("tablePos");
	           },
	           error: function(err) {
	        	   $.hideLoading();
	        	   sap.ui.commons.MessageBox.alert(err.responseText);
	           }});
        },
        error: function(err) {
        	$.hideLoading();
     	   sap.ui.commons.MessageBox.alert(err.responseText);
        }});
}