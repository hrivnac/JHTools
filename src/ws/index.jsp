<!DOCTYPE html>
<html>

  <head>
    <title>@NAME@</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="GraphView.css"                                       rel="stylesheet" type="text/css"/>
    <link href="HBaseTable.css"                                      rel="stylesheet" type="text/css"/>
    <link href="bootstrap-4.4.1/css/bootstrap.min.css"               rel="stylesheet" type="text/css">
    <link href="fontawesome-free-5.13.0-web/css/all.css"             rel="stylesheet" type="text/css">
    <link href="bootstrap-table-1.16.0/dist/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="jquery-ui-1.12.1/jquery-ui.min.css"                  rel="stylesheet" type="text/css"/>
    <link href="daterangepicker-0.1.0/dist/daterangepicker.css"      rel="stylesheet" type="text/css"/>
    <link href="index.css"                                           rel="stylesheet" type="text/css"/>
    <link href="w2ui-1.5.rc1/w2ui-1.5.rc1.min.css"                   rel="stylesheet" type="text/css" />
    </head>
  
  <body>
  
    <script>
      var div = document.createElement("div");
      div.style.width = "100%";
      div.style.height = window.innerHeight + "px";
      div.id = "layout";
      document.body.appendChild(div);
      </script>
  
    <script type="text/javascript" src="vis-network-7.3.6/standalone/umd/vis-network.min.js"></script> 
    <script type="text/javascript" src="OptionsDefault.js"></script>
    <script type="text/javascript" src="Options.js"></script>
    <script type="text/javascript" src="jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="jquery-ui-1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script type="text/javascript" src="bootstrap-4.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-table-1.16.0/dist/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="knockout-3.2.0.js"></script>
    <script type="text/javascript" src="moment-2.25.2.js"></script>
    <script type="text/javascript" src="daterangepicker-0.1.0/dist/daterangepicker.js"></script>
    <script type="text/javascript" src="HBaseTable.js"></script>
    <script type="text/javascript" src="w2ui-1.5.rc1/w2ui-1.5.rc1.min.js"></script>
    
    <script type="text/javascript">
      $(function () {
        var greenstyle = 'border: 1px solid #dfdfdf; padding: 5px; background-color: #ddffdd';
        var bluestyle  = 'border: 1px solid #dfdfdf; padding: 5px; background-color: #ddddff';
        $('#layout').w2layout({
          name:'layout',
          panels:[
            {type:'left', size:'50%', resizable:true},
            {type:'main', size:'50%', resizable:true}
            ]
          });
        $().w2layout({
          name: 'layoutLeft',
          panels: [
            {type:'top',  size:'30%', resizable:true, style:greenstyle},
            {type:'main', size:'70%', resizable:true, style:greenstyle}
            ]
          });
        $().w2layout({
          name: 'layoutMain',
          panels: [
            {type:'top',    size:'05%', resizable:true, style:greenstyle},
            {type:'main',   size:'75%', resizable:true, style:bluestyle},
            {type:'bottom', size:'10%', resizable:true, style:bluestyle}
            ]
          });
        
        w2ui['layout'].html('left', w2ui['layoutLeft']);
        w2ui['layout'].html('main', w2ui['layoutMain']);
        w2ui['layoutLeft'].load('top', 'Top.jsp');
        w2ui['layoutLeft'].load('main', 'GraphView.jsp');
        w2ui['layoutMain'].load('top', 'TopMini.jsp');
        w2ui['layoutMain'].load('main', 'Result.jsp');
        w2ui['layoutMain'].load('bottom', 'Feedback.jsp');
    
        });
      </script>
    
    </body>
    
  </html>
