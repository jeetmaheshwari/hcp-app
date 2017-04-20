package com.hcp.services;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.Prediction;
import com.google.gson.Gson;
import com.hcp.businessrules.BasicInvoiceRule;
import com.hcp.businessrules.RuleDescription;
import com.hcp.businessrules.invoices.EqualsRule;
import com.hcp.businessrules.invoices.EqualsRuleMultiple;
import com.hcp.dao.CustomerDAO;
import com.hcp.dao.PaymentDAO;
import com.hcp.entity.CustomerItem;
import com.hcp.entity.CustomerPayment;
import com.hcp.entity.Payment;

@Path("/hcp")
public class HCPService
{
  @Context
  private HttpServletRequest request;
  
  @GET
  @Path("/currentUser")
  @Produces({"text/plain"})
  public Response verifyRESTService()
  {
//    User user = null;
//    try
//    {
//      UserProvider userProvider = UserManagementAccessor.getUserProvider();
//      if (this.request.getUserPrincipal() != null) {
//        user = userProvider.getUser(this.request.getUserPrincipal().getName());
//      }
//    }
//    catch (Exception e)
//    {
//      return Response.status(200).entity("Error getting current user: " + e.getMessage()).build();
//    }
    return Response.status(200).entity("").build();
  }
  
  @GET
  @Path("/Logout")
  @Produces({"text/plain"})
  public Response logout()
  {
    /*LoginContext loginContext = null;
    User user = null;
    URI location = null;
    try
    {
      loginContext = LoginContextFactory.createLoginContext();
      loginContext.logout();
      
      UserProvider userProvider = UserManagementAccessor.getUserProvider();
      if (this.request.getUserPrincipal() != null) {
        user = userProvider.getUser(this.request.getUserPrincipal().getName());
      }
      location = new URI("Logout");
    }
    catch (Exception e)
    {
      return Response.status(200).entity("Logout failed. Reason: " + e.getMessage()).build();
    }*/
    return Response.temporaryRedirect(null).build();
  }
  
  @GET
  @Path("/customers")
  @Produces({"application/json"})
  public Response fetchCustomers()
    throws SQLException
  {
    try
    {
      String jsonString = new CustomerDAO().getCustomerItems();
      return Response.status(Response.Status.OK).entity(jsonString).build();
    }
    catch (Exception e)
    {
      System.out.println("Got error.. : " + e.getMessage());
    }
    return null;
  }
  
  @GET
  @Path("/customer/{custNum}/{docNumber}")
  @Produces({"application/json"})
  public Response getCustomer(@PathParam("custNum") String custNum, @PathParam("docNumber") String docNumber)
    throws Exception
  {
    CustomerItem customerItem = new CustomerDAO().getCustomerItem(custNum, docNumber);
    return Response.status(Response.Status.OK).entity(new Gson().toJson(customerItem)).build();
  }
  
  @GET
  @Path("/payment/{custNum}/{docNumber}")
  @Produces({"application/json"})
  public Response getPayment(@PathParam("custNum") String custNum, @PathParam("docNumber") String docNumber)
    throws Exception
  {
    List<Payment> payment = new PaymentDAO().getPayment(custNum, docNumber);
    return Response.status(Response.Status.OK).entity(new Gson().toJson(payment)).build();
  }
  
  @GET
  @Path("/payments")
  @Produces({"application/json"})
  public Response fetchPayments()
    throws SQLException
  {
    try
    {
      String jsonString = new PaymentDAO().getPayments();
      return Response.status(Response.Status.OK).entity(jsonString).build();
    }
    catch (Exception e)
    {
      System.out.println("Got error.. : " + e.getMessage());
    }
    return null;
  }
  
  @GET
  @Path("/clearedItems/{custNum}/{docNumber}")
  @Produces({"application/json"})
  public Response fetchClearedItems(@PathParam("custNum") String custNum, @PathParam("docNumber") String docNumber)
  {
    try
    {
      List<CustomerItem> items = new ArrayList();
      if (docNumber.contains(","))
      {
        List<String> docNumbers = Arrays.asList(docNumber.split(","));
        for (String number : docNumbers)
        {
          CustomerItem item = new CustomerDAO().getCustomerItem(custNum, number);
          items.add(item);
        }
      }
      else
      {
        CustomerItem item = new CustomerDAO().getCustomerItem(custNum, docNumber);
        items.add(item);
      }
      String json = new Gson().toJson(items);
      return Response.status(Response.Status.OK).entity(json).build();
    }
    catch (Exception e)
    {
      System.out.println("Error fetching items....");
    }
    return Response.status(Response.Status.NO_CONTENT).entity(new Gson().toJson("")).build();
  }
  
  @POST
  @Path("/invoiceUpdate/{custNum}/{docNumber}/{rule}/{amount}")
  @Consumes({"application/json"})
  public Response updateInvoiceStatus(@PathParam("custNum") String custNum, @PathParam("docNumber") String numbers, @PathParam("rule") String ruleName, @PathParam("amount") String amount)
  {
    System.out.println("Customer : " + custNum + " Doc : " + numbers);
    List<String> docNumbers = Arrays.asList(numbers.split(","));
    BasicInvoiceRule rule = null;
    if (ruleName.equalsIgnoreCase(RuleDescription.EQUALS.name())) {
      rule = new EqualsRule(custNum);
    } else {
      rule = new EqualsRuleMultiple(custNum, amount);
    }
    rule.setDocNumbers(docNumbers);
    try
    {
      rule.evaluate();
      rule.execute();
    }
    catch (Exception e)
    {
      return Response.status(406).entity(new Gson().toJson("Amount might be higher than the limit. Try raising the limit !")).build();
    }
    if (rule.getUpdated() > 0) {
      return Response.status(200).entity(new Gson().toJson("Payment cleared")).build();
    }
    return Response.status(402).entity(new Gson().toJson("Payment required")).build();
  }
  
  @GET
  @Path("/customerPayments/{custNum}")
  @Produces({"application/json"})
  public Response fetchCustomerPayments(@PathParam("custNum") String custNum)
    throws SQLException
  {
    try
    {
      String jsonString = new PaymentDAO().getCustomerPayments(custNum);
      return Response.status(Response.Status.OK).entity(jsonString).build();
    }
    catch (Exception e)
    {
      System.out.println("Got error.. : " + e.getMessage());
    }
    return null;
  }
  
  @POST
  @Path("/predictive/{custNum}/{custName}/{invNumber}/{invRef}/{recvdAmt}/{pmntRef}/{amount}/{bankRef}")
  @Consumes({"application/json"})
  public Response predictive(@PathParam("custNum") String custNum, @PathParam("custName") String custName, @PathParam("invNumber") String invNumber, @PathParam("invRef") String invRef, @PathParam("recvdAmt") String recvdAmt, @PathParam("pmntRef") String pmntRef, @PathParam("amount") String amount, @PathParam("bankRef") String bankRef)
  {
    Prediction prediction = null;
    try
    {
    	 System.out.println("preditct method");
      CustomerPayment custPmnt = new CustomerPayment();
      custPmnt.setCustNumber(custNum);
      custPmnt.setCustName(custName);
      custPmnt.setInvNumber(invNumber);
      custPmnt.setInvRef(invRef);
      custPmnt.setRecvdAmt(recvdAmt);
      custPmnt.setPaymentRef(pmntRef);
      custPmnt.setTotalAmt(amount);
      custPmnt.setBankRef(bankRef);
      
      prediction = realtimePrediction(custPmnt);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return Response.status(200).entity(new Gson().toJson(prediction)).build();
  }
  
  private Prediction realtimePrediction(CustomerPayment custPmnt)
    throws Exception
  {
    AmazonMachineLearningClient mlClient = getMlClient();
    String modelId = "ml-dCTL62pAfwQ";
    String realtimeMlEndPoint = "https://realtime.machinelearning.us-east-1.amazonaws.com";
    Prediction prediction = null;	
    try
    {
      Map<String, String> map = new HashMap();
      map.put("Customer", custPmnt.getCustName());
      map.put("Invoice Number", custPmnt.getInvNumber());
      map.put("Invoice Ref", custPmnt.getInvRef());
      map.put("Amount", custPmnt.getTotalAmt());
      map.put("Payment Ref", custPmnt.getPaymentRef());
      map.put("Received", custPmnt.getRecvdAmt());
      map.put("Bank Ref", custPmnt.getBankRef());
      
      PredictRequest predictRequest = new PredictRequest()
        .withMLModelId(modelId)
        .withPredictEndpoint(realtimeMlEndPoint)
        .withRecord(map);
      PredictResult predictionResult = mlClient.predict(predictRequest);
      System.out.println(predictionResult.getPrediction().getPredictedLabel());
      prediction = predictionResult.getPrediction();
    }
    catch (AmazonServiceException ase)
    {
      System.out.println("Caught an AmazonServiceException, which means your request made it to AWS, but was rejected with an error response for some reason.");
      
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
    }
    catch (AmazonClientException ace)
    {
      System.out.println("Caught an AmazonClientException, which means the client encountered a serious internal problem while trying to communicate with AWS, such as not being able to access the network.");
      
      System.out.println("Error Message: " + ace.getMessage());
    }
    return prediction;
  }
  
  private AmazonMachineLearningClient getMlClient()
    throws Exception
  {
    AWSCredentials credentials = null;
    try
    {
      //System.setProperty("aws.accessKeyId", "AKIAJEWC7NQXV7VTZE5Q");
    //  System.setProperty("aws.secretKey", "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");
    	System.setProperty("aws.accessKeyId", "AKIAJNW63HLWIE4JC3YQ");
        System.setProperty("aws.secretKey", "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");
      credentials = new SystemPropertiesCredentialsProvider().getCredentials();
    }
    catch (Exception e)
    {
      throw new AmazonClientException("Error in loading credentials", e);
    }
    ClassLoader classLoader = HCPService.class.getClassLoader();
    URL resource = classLoader.getResource("org/apache/http/message/BasicLineFormatter.class");
    System.out.println("Wrong JAR ! : " + resource);
    AmazonMachineLearningClient mlClient = new AmazonMachineLearningClient(credentials);
    mlClient.setRegion(Region.getRegion(Regions.US_EAST_1));
    return mlClient;
  }
}
