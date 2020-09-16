<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Lomikel Feedback -->
<!-- @author Julius.Hrivnac@cern.ch -->

<%@ page errorPage="ExceptionHandler.jsp" %>

<!-- TBD: run from index.jsp -->
<jsp:useBean id="profile" class="com.Lomikel.WebService.Profile" scope="session"/>

<%
  String p = profile.profile();
  %>

<link href="Feedback.css" rel="stylesheet" type="text/css"/>

<div id="feedback" title="operation feedback">
  <button onClick="w2popup.load({url:'Help-Feedback.html', showMax: true})" style="position:absolute; top:0; right:0">
    <img src="images/Help.png" width="10"/>
    </button>
  <p style="color:red;font-size:15px">Select <b>graph server</b> and initial <b>graph</b>,<br/>
                                      then select an <b>element</b> to see possible <b>actions</b>.</p>
  </div>

<script type="text/javascript" src="profiles/<%=p%>.js"></script>
