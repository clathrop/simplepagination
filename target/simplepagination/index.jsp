<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored ="false" %> 

<html>
<head>
<title>jTable in Struts 2</title>
<!-- jTable Metro theme -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet"
        type="text/css" />

<!-- jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
        $(document).ready(function() {
                $('#UserTableContainer').jtable({
                		paging: true,
                		pageSize: 3,
                        title : 'User Table',
                        actions : {
                                listAction : 'listAction'
                        },
                        fields : {
                                firstName : {
                                        title : 'First Name',
                                        width : '20%',
                                        key : true,
                                        list : true,
                                        create : true
                                },
                                lastName : {
                                    title : 'Last Name',
                                    width : '20%',
                                    key : true,
                                    list : true,
                                    create : true
                            	},
                                userName : {
                                        title : 'User Name',
                                        width : '20%',
                                        edit : false
                                },
                                email : {
                                        title : 'Email',
                                        width : '20%',
                                        edit : true
                                },
                                age : {
                                        title : 'Age',
                                        width : '20%',
                                        edit : true
                                }
                        }
                });
                $('#UserTableContainer').jtable('load');
        });
</script>

</head>
<body>
<div>
        <h3>Pagination prototype build with jTable and Struts2 framework</h3>
            <div id="UserTableContainer"></div>
</div>
</body>
</html>
