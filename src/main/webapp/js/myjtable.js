$(document).ready(function() {
                $('#UserTableContainer').jtable({
                		paging: true,
                		pageSize: 5,
                        title : 'User Table',
                        actions : {
                                listAction : 'listAction'
                        },
                        fields : {
                                firstName : {
                                        title : 'First Name',
                                        width : '20%',
                                        edit : false
                                },
                                lastName : {
                                    	title : 'Last Name',
                                    	width : '20%',
                                    	edit : false
                            	},
                                userName : {
                                        title : 'User Name',
                                        width : '20%',
                                        edit : false
                                },
                                email : {
                                        title : 'Email',
                                        width : '20%',
                                        key: true,
                                        edit : false
                                },
                                age : {
                                        title : 'Age',
                                        width : '20%',
                                        edit : false
                                }
                        }
                });
                $('#UserTableContainer').jtable('load');
        });