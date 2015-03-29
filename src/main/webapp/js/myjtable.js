

$(document).ready(function() {
                $('#UserTableContainer').jtable({
                		paging: true,
                		pageSize: 5,
                        title : 'User Table',
                        actions : {
                                listAction : 'listAction',
                                createAction : 'createAction'
                        },
                        fields : {
                        		userId : {
                        				title : 'User Id',
                        				width : '10%',
                        				edit  : false,
                        				key   : true
                        		},
                                firstName : {
                                        title : 'First Name',
                                        width : '20%',
                                        edit : false,
                                        inputClass: 'validate[required]'
                                },
                                lastName : {
                                    	title : 'Last Name',
                                    	width : '20%',
                                    	edit : false,
                                    	inputClass: 'validate[required]'
                            	},
                                userName : {
                                        title : 'User Name',
                                        width : '20%',
                                        edit : false,
                                        inputClass: 'validate[required]'
                                },
                                email : {
                                        title : 'Email',
                                        width : '20%',
                                        edit : false,
                                        inputClass: 'validate[required, custom[email]]'
                                },
                                age : {
                                        title : 'Age',
                                        width : '20%',
                                        edit : false,
                                        inputClass: 'validate[required]'
                                }
                        },
                        //Initialize validation logic when a form is created
                        formCreated: function (event, data) {
                            data.form.validationEngine();
                        },
                        //Validate form when it is being submitted
                        formSubmitting: function (event, data) {
                            return data.form.validationEngine('validate');
                        },
                        //Dispose validation logic when form is closed
                        formClosed: function (event, data) {
                            data.form.validationEngine('hide');
                            data.form.validationEngine('detach');
                        }
                });
                $('#UserTableContainer').jtable('load');
        });