<%@ page import="dto.CurrentUser" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
  <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Bank</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">LoggedLink1</a></li>
              <li class="active"><a href="#about">LoggedLink1</a></li>
              <li class="active"><a href="#contact">LoggedLink1</a></li>
        
            </ul>
              <form class="navbar-form pull-right">    
               <ul class="nav">          
                <li class="active" >              
                <c:if test="${sessionScope.currentUser!=null}">
              <strong> <font color="orange"> Hi ${sessionScope.currentUser.firstName}</font> </strong>
                </c:if></li>
                  
           <li class="active"> <a href="/Team4bBankApplication/logout.iss">logout</a>
              </li>       
            </ul>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>