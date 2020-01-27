<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <title>New Artist</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <style>
            .glyphicon {
                font-size: 30px;
            }

            /* Grootte van de icons*/
            .material-icons {
                font-size: 30px
            }

            .item{
                height: 150px;  
            }

            h6 {
                display: inline-block;
            }

            .inline-form {
                display: inline-block;
                max-width: 30px;
                width: auto;
            }

        </style>

           <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="row justify-content-center">

                <div class="col-1" onclick="location.href = '<c:url value='/'/>';" style="cursor: pointer;">
                    <i class="material-icons my-auto text-white">home</i>
                </div>

            </div>

            <div class="col-8">
                <h4 class="text-center my-auto text-white">EDIT ARTIST</h4>
            </div>

            <div class="col col-1" onclick="location.href = '<c:url value='/artists/new'/>';" style="cursor: pointer;">
 

            </div>
        </div>
    </nav>


    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<c:url value='/'/>">Home</a></li>
        <li class="breadcrumb-item"><a href="<c:url value='/artists'/>">Artists</a></li>
        <li class="breadcrumb-item active">new Artist</li>
    </ol>
</head>


<body>



</body>
<div class="container-fluid">
    <div class="row">
 

        <div class="col-lg">

            <form:form method="POST" modelAttribute="artist">
                <form:input type="hidden" path="id" id="id"/>
                <fieldset>    

                    <!--ARTIST NAME-->
                    <!--ARTIST NAME-->
                    <!--ARTIST NAME-->
                    <div class="form-group">
                        <label for="exampleInputEmail1">Artist Name</label>
                        <form:input path="name" id="name" class="form-control" placeholder="Artist Name"/>
                    </div>   

                    <!-- BESCHRIJVING -->
                    <!-- BESCHRIJVING -->
                    <!-- BESCHRIJVING -->
                    <div class="form-group">
                        <label for="exampleInputEmail1">Description</label>
                        <form:input path="description" id="description" class="form-control" placeholder="Description"/>
                    </div>  

                    <hr>

                    <!-- Logo Url -->
                    <!-- Logo Url -->
                    <!-- Logo Url -->
                    <div class="form-group">
                        <label for="exampleInputEmail1">Logo Url</label>
                        <form:input path="logoUrl" id="logoUrl" class="form-control" placeholder="Logo Url"/>
                    </div>  

                    <!-- SUBMIT BUTTON -->
                    <!-- SUBMIT BUTTON -->
                    <!-- SUBMIT BUTTON -->
                    <c:choose>
                        <c:when test="${edit}">
                            <button type="submit" class="btn btn-primary">Update Artist</button>

                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-primary btn-block">Add Artist</button>

                        </c:otherwise>
                    </c:choose>                

                </fieldset>
            </form:form>

            <hr>

        </div>
        <div class="col-lg">
            <c:choose>
                <c:when test="${users.size() <= 0}">
                    <h4>No linked users.</h4>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="user">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col">
                                    <h6><c:out value="${user.name}"/></h6>
                                    <form:form method="POST" modelAttribute="user" class="inline-form">
                                        <fieldset> 
                                            <button type="submit" class="btn btn-danger btn-xs" name="removeUser" value=${user.id}><i class="material-icons small">clear</i></button>             
                                        </fieldset>
                                    </form:form>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <hr>
            
            <form:form method="POST" modelAttribute="user">
                <fieldset>
                    <div class="form-group">
                        <label>User email</label>
                        <form:input path="email" id="email" class="form-control" placeholder="Type here the users email."/>
                    </div>  

                    <!-- SUBMIT BUTTON -->
                    <!-- SUBMIT BUTTON -->
                    <!-- SUBMIT BUTTON -->
                    <button type="submit" class="btn btn-primary btn-block" name="addUser">Add User</button>
                </fieldset>
            </form:form>
        </div>
  </div>
</html>