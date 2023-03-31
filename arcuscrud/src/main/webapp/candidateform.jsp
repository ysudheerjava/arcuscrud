<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Arcus - Candidate Management System</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header class="header_3 gradient-bg">
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #1E2C76">
                   <div class="navbar-brand">
                   
                       Arcus Candidate Management System 
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Candidates List</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                    
                    
                        <c:if test="${candidate != null}">
                            <form action="update" method="post">
                        </c:if>
                        
                        <c:if test="${candidate == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${candidate != null}">
                                    Edit Candidate
                                </c:if>
                                <c:if test="${candidate == null}">
                                    Add New Candidate
                                </c:if>
                            </h2>
                        </caption>


                        <c:if test="${candidate != null}">
                            <input type="hidden" name="id" value="<c:out value='${candidate.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Name</label> <input type="text" value="<c:out value='${candidate.name}' />" class="form-control" name="name" required="required">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Gender</label> <input type="text" value="<c:out value='${candidate.gender}' />" class="form-control" name="gender">
                        </fieldset>
                        

                        <fieldset class="form-group">
                            <label>Email</label> <input type="text" value="<c:out value='${candidate.email}' />" class="form-control" name="email">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>Qualification</label> <input type="text" value="<c:out value='${candidate.qualification}' />" class="form-control" name="qualification">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>State</label> <input type="text" value="<c:out value='${candidate.state}' />" class="form-control" name="state">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>