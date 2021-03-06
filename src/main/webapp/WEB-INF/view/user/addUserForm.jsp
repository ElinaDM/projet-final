<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create account </title>

	 <!-- Bootstrap core CSS -->
     <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

     <!-- Custom styles for this template -->
     <link href="/resources/css/signin.css" rel="stylesheet">
     
  </head>

  <body>
    <div class="container">

           <form class="form-signin" action="/admin/user/add">
               <fieldset>

               <!-- Form Name -->
               <legend>Créer un compte</legend>

               <!-- Text input-->
               <div class="form-group">
                 <div class="col-md-12">
                 <input id="surname" name="surname" type="text" placeholder="Prénom" class="form-control input-md" required="">

                 </div>
               </div>

               <!-- Text input-->
               <div class="form-group">
                <div class="col-md-12">
                 <input id="name" name="name" type="text" placeholder="Nom" class="form-control input-md" required="">

                 </div>
               </div>

               <!-- Text input-->
               <div class="form-group">
               <div class="col-md-12">
                 <input id="email" name="email" type="text" placeholder="Adresse email" class="form-control input-md" required="">
                 </div>
               </div>

               <!-- Password input-->
               <div class="form-group">
                <div class="col-md-12">
                   <input id="password" name="password" type="password" placeholder="Mot de passe" class="form-control input-md" required="">
                 </div>
               </div>

               <!-- Select Basic -->
               <div class="form-group">
                 <label class="col-md-4 control-label" for="role"></label>
                 <div class="col-md-12">
                   <select id="role" name="role" class="form-control">
                     <option value="USER">USER</option>
                     <option value="ADMIN">ADMIN</option>
                   </select>
                 </div>
               </div>

               <!-- Button -->
               <div class="form-group">
                 <label class="col-md-12 control-label" for="submit"></label>
                 <div class="col-md-12">
                   <button id="submit" name="submit" class="btn btn-lg btn-success btn-block">Ajouter</button>
                 </div>
               </div>

               <!-- Select Basic -->

               </fieldset>
               </form>
        </div> <!-- /container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>
