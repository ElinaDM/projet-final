<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation search criteria</title>
    <!-- Bootstrap Date-Picker Plugin -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <!-- Bootstrap core CSS -->
    <!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<div class="bootstrap-iso">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-sm-6 col-xs-12">
            <div class="alert alert-info">
                <p>Cherchez les ressources en fonction de la date</p>
            </div>
            <form class="form-inline" method="post">
                <div class="form-group"> <!-- Date input -->
                    <label class="control-label" for="date">Date de réservation</label>
                    <input class="form-control" id="dateBooking" name="dateBooking" placeholder="DD/MM/YYY" type="text"/>
                </div>
                <select class="carousel form-control" id="startTime" name="startTime">
                    <option>Heure début</option>
                    <option>09:00</option>
                    <option>10:00</option>
                    <option>11:00</option>
                    <option>12:00</option>
                    <option>13:00</option>
                    <option>14:00</option>
                    <option>15:00</option>
                    <option>16:00</option>
                    <option>17:00</option>
                    <option>18:00</option>
                    <option>19:00</option>
                </select>
                <select class="carousel form-control" id="endTime" name="endTime">
                    <option>Heure début</option>
                    <option>09:00</option>
                    <option>10:00</option>
                    <option>11:00</option>
                    <option>12:00</option>
                    <option>13:00</option>
                    <option>14:00</option>
                    <option>15:00</option>
                    <option>16:00</option>
                    <option>17:00</option>
                    <option>18:00</option>
                    <option>19:00</option>
                </select>
                <div class="form-group"> <!-- Submit button -->
                    <button class="btn btn-primary " name="submit" type="submit">Rechercher</button>
                </div>
            </form>

        </div>
    </div>
</div>
</div>
<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        var options={
            format: 'dd/mm/yyyy',
            startDate: "new Date()",
            todayBtn: "linked",
            daysOfWeekDisabled: "0,6",
            container: container,
            todayHighlight: true,
            autoclose: true,
        };
        date_input.datepicker(options);
    })
</script>
</body>
</html>
