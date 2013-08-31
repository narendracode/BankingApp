 <html>
 <head>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <script type="text/javascript" src="jquery.validate.min.js"></script>
 <style>
 #register-form .fieldgroup label.error {
    color: #FB3A3A;
    margin: 4px 0 5px 1px;
    padding: 0; 
    width: 220px;
}
</style>
<script type="text/javascript">
(function($,W,D)
{
    var JQUERY4U = {};

    JQUERY4U.UTIL =
    {
        setupFormValidation: function()
        {
            //form validation rules
            $("#register-form").validate({
                rules: {
                    amount: {required: true,
                             number: true},
                    to_accountNumber: "required"
                },
                messages: {
                    amount: "Please enter amount",
                    to_accountNumber: "Please enter the account number"                    
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });

            
        }
    }

    //when the dom has loaded setup form validation rules
    $(D).ready(function($) {
        JQUERY4U.UTIL.setupFormValidation();
    });

})(jQuery, window, document);
</script>
</head>
<body>
<h3>Funds Transfer</h3>
<span class="label label-important">
<% if(request.getAttribute("message")!=null){
out.println((String)request.getAttribute("message"));
}
	%>
	</span>
<div class="row">
<div class="span5">
<!-- deposit start -->
<form id="register-form" action="/Team4bBankApplication/fundsTransfer.iss" method="post" novalidate="novalidate">


<fieldset>

            <div class="fieldgroup">
                <label for="accountNumber">From Account</label>
                <select name="accountNumber">
<c:forEach var="account" items="${sessionScope.accounts}">
  <option value="${account.accountNumber}">${account.accountNumber} </option>
</c:forEach>
</select>
            </div>
            <div class="fieldgroup">
                <label for="to_accountNumber">To Account</label>
                <input type="text" name="to_accountNumber">
            </div>
            <div class="fieldgroup">
                <label for="status">Message</label>
                <input type="text" name="status">
            </div>
            <div class="fieldgroup">
                <label for="amount">Amount</label>
                <input type="text" name="amount">
            </div>
             <div class="fieldgroup">
                <button class="btn btn-primary" type="submit" name="fundsTranfer" value="fundsTransfer">Transfer Fund</button>
            </div>
</fieldset>
</form>

</div>
<div class="span4">
Some information about the Withdraw will be shown here..
</div>
</div>
</body>
</html>
