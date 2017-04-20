oTab2 = new sap.ui.commons.Tab("tab2");
oTab2.setTooltip("Machine API");
oTab2.setTitle(new sap.ui.core.Title("Title2",{text:"Machine API",icon:"images/address.gif"}));

var oLayout2 = new sap.ui.commons.layout.MatrixLayout("Matrix2", {columns: 2, width: "100%"});
oLayout2.setWidths(['150px']);

oTF = new sap.ui.commons.TextField("TextField-MLCustomer", {tooltip: 'Enter Customer No.', editable: true, value: '', width: '10em'});
oLabel = new sap.ui.commons.Label("Label-MLCustomer", {text: 'Customer No.', labelFor: oTF});
oLayout2.createRow(oLabel, oTF);

var submit = new sap.ui.commons.Button("Submit", {
												text: "Submit",
												width:"8em",
												style: sap.ui.commons.ButtonStyle.Accept,
												press: function(oEvent){
													//showCustomerData();
													onCompute();
												}});

oLabel = new sap.ui.commons.Label("Lbl-Submit", {text: '', labelFor: submit});
oLayout2.createRow(oLabel, submit);
												
oTab2.addContent(oLayout2);
oTabStrip.addTab(oTab2);

function getTableForId(tableId) {
	oControl = new sap.ui.commons.CheckBox({checked: false});
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Select"}), template: oControl, hAlign: "Center", autoResizable: true}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Customer #"}),template: new sap.m.Text({text: "{custNumber}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Customer Name"}),template: new sap.m.Text({text: "{custName}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Invoice #"}),template: new sap.m.Text({text: "{invNumber}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Invoice Ref."}),template: new sap.m.Text({text: "{invRef}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Received Amt"}),template: new sap.m.Text({text: "{recvdAmt}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Payment Ref"}),template: new sap.m.Text({text: "{paymentRef}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Total Amt"}),template: new sap.m.Text({text: "{totalAmt}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Bank Ref."}),template: new sap.m.Text({text: "{bankRef}"}), width: "100px", hAlign: "Center"}));
	tableId.addColumn(new sap.ui.table.Column({label: new sap.m.Label({text: "Clearance"}),template: new sap.m.Text({text: "{match}"}), width: "100px", hAlign: "Center"}));
	
	return tableId;
}

var custItemsTable =  new sap.ui.table.Table({ 
    title: "Customer Payments", 
    selectionMode: sap.ui.table.SelectionMode.Single,
	extension: [
		new sap.ui.commons.Button({text: "Compute", press: function() { 
			onCompute();
		}}),
		new sap.ui.commons.Button({text: "Cleared Items", press: function() { 
			alert("Show cleared items !"); 
		}})
	]
}); 

function onCompute() {
	alert("Invoke ML !"); 
	//var data = custItemsTable.getModel().getData();
    //var selected = $.grep(data, function(n) {
      //return (n.selected === true);
    //});
   // console.log(selected);
    sap.m.MessageToast.show('# selected: Test ');
	
	var customer = '50001';
	var custName = 'Walmart';
	var invNumber = '900001501';
	var invRef = 'BELLAIREWAL';
	var recvdAmt = '100';
	var pmntRef = 'WALM900000004';
	var amount = '400';
	var bankRef = '1307';
	var match = 'N';
	var baseUrl = window.location.href;
	var mlUrl = baseUrl+"rest/hcp/predictive/"+customer+"/"+custName+"/"+invNumber+"/"+invRef+"/"+recvdAmt+"/"+pmntRef+"/"+amount+"/"+bankRef;
	$.showLoading();
	$.ajax({
        type: "POST",
        async: true,
        url: mlUrl,
        success: function (data) {
        	$.hideLoading();
        	alert(data);
        }
	});	
}

var custItemContainer = new sap.m.ScrollContainer({
     height: "400px",
     vertical: true,
     horizontal: true,
     focusable: true,
     content: [custItemsTable]
   });

var custTableForm = new sap.ui.layout.form.SimpleForm(
	"custForm",
	{
		layout: sap.ui.layout.form.SimpleFormLayout.ResponsiveGridLayout,
		columnsXL:2,
		editable: true,
		maxContainerCols: 2,
		content:[custItemContainer
			]
	});

function showCustomerData() {
	var custNum = oTF.getValue();
	$.showLoading();
	$.ajax({
        type: "GET",
        async: true,
        url: "/hcp-app/rest/hcp/customerPayments/"+custNum,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
        	$.hideLoading();
        	var oModel = new sap.ui.model.json.JSONModel();
    		oModel.setData(data);
    		custItemsTable = getTableForId(custItemsTable);
    		custItemsTable.setModel(oModel);
    		custItemsTable.bindRows("/");
    		custTableForm.placeAt("custPaymentPos");
       },
       error: function(err) {
    	   $.hideLoading();
    	   sap.ui.commons.MessageBox.alert(err.responseText);
       }});
}

var clearedItemsTable =  new sap.ui.table.Table({ 
    title: "Cleared Payments", 
    selectionMode: sap.ui.table.SelectionMode.Single	
}); 

var clearedItemContainer = new sap.m.ScrollContainer({
     height: "400px",
     vertical: true,
     horizontal: true,
     focusable: true,
     content: [clearedItemsTable]
   });

//create the DataTable control
var clearedTableForm = new sap.ui.layout.form.SimpleForm(
	"clearedForm",
	{
		layout: sap.ui.layout.form.SimpleFormLayout.ResponsiveGridLayout,
		columnsXL:2,
		editable: true,
		maxContainerCols: 2,
		content:[clearedItemContainer]
	});

function showClearedItems() {
	$.showLoading();
	var custNum = oTF.getValue();
	$.ajax({
        type: "GET",
        async: true,
        url: "/hcp-app/rest/hcp/clearedCustPayments/"+customer,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
        	$.hideLoading();
        	var oModel = new sap.ui.model.json.JSONModel();
    		oModel.setData(data);
    		clearedItemsTable = getTableForId(clearedItemsTable);
    		clearedItemsTable.setModel(oModel);
    		clearedItemsTable.bindRows("/");
    		clearedTableForm.placeAt("clearedPaymentPos");
       },
       error: function(err) {
    	   $.hideLoading();
    	   sap.ui.commons.MessageBox.alert(err.responseText);
       }});
}