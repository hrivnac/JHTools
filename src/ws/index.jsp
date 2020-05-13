<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang='en'>

<!-- JHTools JSP -->
<!-- @author Julius.Hrivnac@cern.ch  -->

<!--%@ page errorPage="ExceptionHandler.jsp" %-->

  <head>
    <title>@NAME@</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="GraphView.css"                                       rel="stylesheet" type="text/css"/>
    <link href="HBaseTable.css"                                      rel="stylesheet" type="text/css"/>
    <link href="gridstack-1.1.1/dist/gridstack.min.css"              rel="stylesheet" type="text/css"/>
    <link href="bootstrap-4.4.1/css/bootstrap.min.css"               rel="stylesheet" type="text/css">
    <link href="fontawesome-free-5.13.0-web/css/all.css"             rel="stylesheet" type="text/css">
    <link href="bootstrap-table-1.16.0/dist/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="jquery-ui-1.12.1/jquery-ui.min.css"                  rel="stylesheet" type="text/css"/>
    <link href="daterangepicker-0.1.0/dist/daterangepicker.css"      rel="stylesheet" type="text/css"/>
    <link href="index.css"                                           rel="stylesheet" type="text/css"/>
    </head>
    
  <body>    
    <script type="text/javascript" src="vis-network-7.3.6/standalone/umd/vis-network.min.js"></script> 
    <script type="text/javascript" src="OptionsDefault.js"></script>
    <script type="text/javascript" src="Options.js"></script>
    <script type="text/javascript" src="jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="jquery-ui-1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script type="text/javascript" src="gridstack-1.1.1/dist/gridstack.all.js"></script>
    <script type="text/javascript" src="bootstrap-4.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-table-1.16.0/dist/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="knockout-3.2.0.js"></script>
    <script type="text/javascript" src="moment-2.25.2.js"></script>
    <script type="text/javascript" src="daterangepicker-0.1.0/dist/daterangepicker.js"></script>
    <script type="text/javascript" src="HBaseTable.js"></script>
  
    <div class="row">
      <div class="col-sm-12" style="padding-bottom: 1px;">
        <div style="padding: 1px; border: 1px grey">
          <div class="grid-stack" style="background-color:lightgrey">
          
            <div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="6" data-gs-height="3">
              <div class="grid-stack-item-content" id="commands" title="context-sensitive commands" style="background-color:#ddffdd">
                <%@ include file="Top.jsp"%>
                </div>    
              </div>
       
            <div class="grid-stack-item" data-gs-x="0" data-gs-y="3" data-gs-width="6" data-gs-height="1">
              <div class="grid-stack-item-content" id="feedback" title="operation feedback" style="background-color:#ddddff">
                <p style="color:red;font-size:15px">Select <b>graph server</b> and initial <b>graph</b>,<br/>
                                                    then select an <b>element</b> to see possible actions.</p>
                </div>
              </div>
            
            <div class="grid-stack-item" data-gs-x="0" data-gs-y="4" data-gs-width="6" data-gs-height="12">
              <div class="grid-stack-item-content" id="graph" style="background-color:#ddffdd">
                <%@ include file="GraphView.jsp"%>
                </div>
              </div>
        
            <div class="grid-stack-item" data-gs-x="6" data-gs-y="1" data-gs-width="6" data-gs-height="1">
              <div class="grid-stack-item-content" id="mini" title="main top" style="background-color:#ddffdd">
                <%@include file="TopMini.jsp"%>
                </div>
              </div>
        
            <div class="grid-stack-item" data-gs-x="6" data-gs-y="0" data-gs-width="6" data-gs-height="15">
              <div class="grid-stack-item-content" id="result" title="results" style="background-color:#ddddff">
                <%@include file="Result.jsp"%>
                </div>
              </div>
            
            </div>
          </div>
        </div>
      </div>
    
    <script type="text/javascript" src="StylesheetDefault.js"></script>
    <script type="text/javascript" src="Stylesheet.js"></script>
    <script type="text/javascript" src="GraphView.js"></script>
    <script type="text/javascript">
      var grid = GridStack.init({
        alwaysShowResizeHandle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent),
        resizable: {
          handles: 'e, se, s, sw, w'
          },
        removable: '#trash',
        removeTimeout: 100,
        acceptWidgets: '.newWidget'
        });
      grid.disable();
      </script>
      
    </body>
  
  </html>
