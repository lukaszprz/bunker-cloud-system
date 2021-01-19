-- public.flyway_schema_history definition

-- Drop table

-- DROP TABLE public.flyway_schema_history;

CREATE TABLE public.flyway_schema_history (
    installed_rank int4 NOT NULL,
    "version" varchar(50) NULL,
    description varchar(200) NOT NULL,
    "type" varchar(20) NOT NULL,
    script varchar(1000) NOT NULL,
    checksum int4 NULL,
    installed_by varchar(100) NOT NULL,
    installed_on timestamp NOT NULL DEFAULT now(),
    execution_time int4 NOT NULL,
    success bool NOT NULL,
    CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);
CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);

-- Permissions

ALTER TABLE public.flyway_schema_history OWNER TO "oauth-user";
GRANT ALL ON TABLE public.flyway_schema_history TO "oauth-user";


-- public.oauth_access_token definition

-- Drop table

-- DROP TABLE public.oauth_access_token;

CREATE TABLE public.oauth_access_token (
    token_id varchar(255) NULL,
    "token" bytea NULL,
    authentication_id varchar(255) NOT NULL,
    user_name varchar(255) NULL,
    client_id varchar(255) NULL,
    authentication bytea NULL,
    refresh_token varchar(255) NULL,
    CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
);

-- Permissions

ALTER TABLE public.oauth_access_token OWNER TO "oauth-user";
GRANT ALL ON TABLE public.oauth_access_token TO "oauth-user";


-- public.oauth_client definition

-- Drop table

-- DROP TABLE public.oauth_client;

CREATE TABLE public.oauth_client (
    id serial NOT NULL,
    access_token_validity int4 NULL,
    additional_information varchar(255) NULL,
    client_id varchar(255) NULL,
    client_secret varchar(255) NULL,
    grant_types varchar(255) NULL,
    redirect_uris varchar(255) NULL,
    refresh_token_validity int4 NULL,
    resources varchar(255) NULL,
    scopes varchar(255) NULL,
    CONSTRAINT oauth_client_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.oauth_client OWNER TO "oauth-user";
GRANT ALL ON TABLE public.oauth_client TO "oauth-user";


-- public.oauth_refresh_token definition

-- Drop table

-- DROP TABLE public.oauth_refresh_token;

CREATE TABLE public.oauth_refresh_token (
    token_id varchar(255) NULL,
    "token" bytea NULL,
    authentication bytea NULL
);

-- Permissions

ALTER TABLE public.oauth_refresh_token OWNER TO "oauth-user";
GRANT ALL ON TABLE public.oauth_refresh_token TO "oauth-user";


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
    id serial NOT NULL,
    activated bool NULL,
    activation_key varchar(255) NULL,
    authorities varchar(255) NULL,
    "password" varchar(255) NULL,
    reset_password_key varchar(255) NULL,
    username varchar(255) NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.users OWNER TO "oauth-user";
GRANT ALL ON TABLE public.users TO "oauth-user";