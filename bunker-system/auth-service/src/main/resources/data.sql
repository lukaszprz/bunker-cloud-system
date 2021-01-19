INSERT INTO public.flyway_schema_history (installed_rank,"version",description,"type",script,checksum,installed_by,installed_on,execution_time,success) VALUES
(2,'1.1','create oauth client table','SQL','V1_1__create_oauth_client_table.sql',-26078976,'oauth-user','2020-12-31 22:52:25.301',9,true)
,(3,'1.2','insert browser client','SQL','V1_2__insert_browser_client.sql',942872658,'oauth-user','2020-12-31 22:52:25.318',2,true)
,(4,'1.3','insert account service client','SQL','V1_3__insert_account_service_client.sql',-2104679633,'oauth-user','2020-12-31 22:52:25.328',2,true)
,(5,'1.4','insert user test authentication','SQL','V1_4__insert_user_test_authentication.sql',984031343,'oauth-user','2020-12-31 22:52:25.337',1,true)
,(6,'1.5','create oauth access token table','SQL','V1_5__create_oauth_access_token_table.sql',897217571,'oauth-user','2020-12-31 22:52:25.344',7,true)
,(7,'1.6','create oauth refresh token table','SQL','V1_6__create_oauth_refresh_token_table.sql',26055161,'oauth-user','2020-12-31 22:52:25.357',6,true)
,(1,'1.0','create users table','SQL','V1_0__create_users_table.sql',-1562000417,'oauth-user','2020-12-31 22:52:25.268',18,true);

INSERT INTO public.oauth_client (id,access_token_validity,additional_information,client_id,client_secret,grant_types,redirect_uris,refresh_token_validity,resources,scopes) VALUES
(1,NULL,NULL,'browser','$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK','refresh_token,password',NULL,NULL,NULL,'ui')
,(2,NULL,NULL,'account-service','$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK','refresh_token,client_credentials',NULL,NULL,NULL,'server');

INSERT INTO public.users (id,activated,activation_key,authorities,"password",reset_password_key,username) VALUES
(1,true,NULL,'ROLE_USER','$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK',NULL,'randomuser');