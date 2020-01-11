<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <head>
        <title>Events</title>
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

            .small {
                font-size: 15px
            }

            .item{
                height: 150px;  
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
                <h4 class="text-center my-auto text-white">PAYMENTS</h4>
            </div>

            <div class="col col-1" onclick="location.href = '<c:url value='/payments/new-${event.id}'/>';" style="cursor: pointer;">

                <i class="material-icons my-auto text-white">add</i>

            </div>

        </div>

    </nav>
    <button type="button" class="btn btn-info btn-lg btn-block" onclick="location.href = '<c:url value='/events/detail-${event.id}'/>';">
        <div class="container-fluid">
            <div class="row">  
                <div class="col align-self-center">
                    <div class="text-right">
                        <i class="material-icons">clear_all</i>
                    </div>
                </div>

                <div class="col align-self-center text-left">
                    ${event.name}
                </div>
            </div>
        </div>
    </button>
</head>

<body>  
    <c:forEach items="${payments}" var="payment">
        <div class="container-fluid">
            <div class="row">        

                <div class="col">

                    <!--Weergave van ��n item  -->
                    <!--Weergave van ��n item  -->
                    <!--Weergave van ��n item  -->
                    <div class="modal-content shadow">

                        <div>
                            <div>
                                <h1 ><c:out value="${payment.name}"/></h1>
                                <h6><c:out value="${payment.price}"/>EURO</h6>

                                <hr>

                                <textarea class="form-control" rows="10" disabled rows="4" cols="50">
                                    ${payment.description}
                                </textarea>
                            </div> 
                        </div>


                        <div class="modal-footer">

                            <!-- CHECK / UNCHECK -->
                            <c:choose>
                                <c:when test="${payment.ack}">
                                    <button onclick="location.href = '<c:url value='/payments/ack-${payment.id}-${event.id}'/>';" type="button" class="btn btn-outline-success">
                                        <i class="material-icons small">check</i>
                                    </button> 
                                </c:when>
                                <c:otherwise>
                                    <button onclick="location.href = '<c:url value='/payments/ack-${payment.id}-${event.id}'/>';" type="button" class="btn btn-outline-danger">
                                        <i class="material-icons small">check</i>
                                    </button> 

                                </c:otherwise>
                            </c:choose>  



                            <button onclick="location.href = '<c:url value='/payments/edit-${payment.id}-${event.id}'/>';"  type="button" class="btn btn-warning">
                                <i class="material-icons small">edit</i>
                            </button> 

                            <button onclick="window.location.href = '<c:url value='/payments/delete-${payment.id}-${event.id}'/>';" type="button" class="btn btn-danger">
                                <i class="material-icons small">clear</i>
                            </button>   
                        </div>
                    </div>

                    <hr>


                </div>     
            </div>
        </div>
    </c:forEach>
</body>
</html>