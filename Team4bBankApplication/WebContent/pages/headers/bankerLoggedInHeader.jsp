  <%@ page import="dto.CurrentUser" %>
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
             <!--   <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn">Sign in</button> -->
              <ul class="nav">
              <li class="active">
              <%if(session.getAttribute("currentUser")!=null){
            		CurrentUser currentUser = (CurrentUser)session.getAttribute("currentUser");
            	  out.println("Hi "+currentUser.getFirstName()+" "+currentUser.getLastName()); 
              }%>
              
            <a href="/Team4bBankApplication/logout.iss">logout</a>
              </li>
        
            </ul>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>