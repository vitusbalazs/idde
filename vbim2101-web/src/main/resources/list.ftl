<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>vbim2101 - Lakáshírdetések</title>

        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <h2>Hello! Here you can find all of our available advertisements:</h2>
        <ul class="list-group">
            <#list advertisements as adv>
                <li class="list-group-item">
                    <span class="adv-title">Advertisement no. ${adv.id}</span>
                    <br>
                    Title: ${adv.title}
                    <br>
                    Address: ${adv.address}
                    <br>
                    Price: ${adv.price}
                    <br>
                    Surface area: ${adv.surfaceArea}
                    <br>
                    Rooms: ${adv.rooms}
                </li>
            </#list>
        </ul>
        <div class="control-btn-group">
            <form method="POST" action="/vbim2101-web/logout">
                <!-- <button type="button" class="btn btn-outline-warning">Warning</button> -->
                <button type="submit" class="btn btn-warning">Logout</button>
            </form>
        </div>
    </body>
</html>