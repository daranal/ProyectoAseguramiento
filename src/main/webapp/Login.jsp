<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Programación 3 | Proyecto</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-lg-offset-3 col-md-offset-3 col-offset-3 col-xs-offset-3" style="border: 1px solid; margin-top: 10%;" align="center">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xd-12">
                        <h3 align="center">Login</h3>
                    </div>
                </div>
                <br><br>
                <form action="/ProyectoFaseI/ControllerLogin" method="post" autocomplete="off">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-lg-offset-0 col-md-offset-0 col-offset-0 col-xs-offset-0">
                            <label>Usuario</label>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <input type="text" class="form-control" name="user" required>
                        </div>
                    </div>
                    <br><br>
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 col-lg-offset-0 col-md-offset-0 col-offset-0 col-xs-offset-0">
                            <label>Contraseña</label>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <input type="password" class="form-control" name="password" required>
                        </div>
                    </div>
                    <br><br>
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <button class="btn btn-primary btn-lg" align="center" name="accion" value="Login" type="submit">Login</button>
                        </div>
                    </div>
                    <br><br>
                </form>
            </div>     
        </div>
    </body>
</html>