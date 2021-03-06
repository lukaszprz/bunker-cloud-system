PGDMP         6                 y            oauth-db %   10.14 (Ubuntu 10.14-0ubuntu0.18.04.1) #   12.5 (Ubuntu 12.5-0ubuntu0.20.04.1)     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16386    oauth-db    DATABASE     |   CREATE DATABASE "oauth-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pl_PL.UTF-8' LC_CTYPE = 'pl_PL.UTF-8';
    DROP DATABASE "oauth-db";
             
   oauth-user    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    16388    flyway_schema_history    TABLE     �  CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);
 )   DROP TABLE public.flyway_schema_history;
       public         
   oauth-user    false    3            �            1259    16421    oauth_access_token    TABLE     (  CREATE TABLE public.oauth_access_token (
    token_id character varying(255),
    token bytea,
    authentication_id character varying(255) NOT NULL,
    user_name character varying(255),
    client_id character varying(255),
    authentication bytea,
    refresh_token character varying(255)
);
 &   DROP TABLE public.oauth_access_token;
       public         
   oauth-user    false    3            �            1259    16469    oauth_client    TABLE     �  CREATE TABLE public.oauth_client (
    id integer NOT NULL,
    access_token_validity integer,
    additional_information character varying(255),
    client_id character varying(255),
    client_secret character varying(255),
    grant_types character varying(255),
    redirect_uris character varying(255),
    refresh_token_validity integer,
    resources character varying(255),
    scopes character varying(255)
);
     DROP TABLE public.oauth_client;
       public         
   oauth-user    false    3            �            1259    16467    oauth_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public.oauth_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.oauth_client_id_seq;
       public       
   oauth-user    false    200    3            �           0    0    oauth_client_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.oauth_client_id_seq OWNED BY public.oauth_client.id;
          public       
   oauth-user    false    199            �            1259    16429    oauth_refresh_token    TABLE     |   CREATE TABLE public.oauth_refresh_token (
    token_id character varying(255),
    token bytea,
    authentication bytea
);
 '   DROP TABLE public.oauth_refresh_token;
       public         
   oauth-user    false    3            �            1259    16480    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    activated boolean,
    activation_key character varying(255),
    authorities character varying(255),
    password character varying(255),
    reset_password_key character varying(255),
    username character varying(255)
);
    DROP TABLE public.users;
       public         
   oauth-user    false    3            �            1259    16478    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       
   oauth-user    false    3    202            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public       
   oauth-user    false    201            �
           2604    16472    oauth_client id    DEFAULT     r   ALTER TABLE ONLY public.oauth_client ALTER COLUMN id SET DEFAULT nextval('public.oauth_client_id_seq'::regclass);
 >   ALTER TABLE public.oauth_client ALTER COLUMN id DROP DEFAULT;
       public       
   oauth-user    false    200    199    200            �
           2604    16483    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       
   oauth-user    false    202    201    202            ~          0    16388    flyway_schema_history 
   TABLE DATA                 public       
   oauth-user    false    196                      0    16421    oauth_access_token 
   TABLE DATA                 public       
   oauth-user    false    197            �          0    16469    oauth_client 
   TABLE DATA                 public       
   oauth-user    false    200            �          0    16429    oauth_refresh_token 
   TABLE DATA                 public       
   oauth-user    false    198            �          0    16480    users 
   TABLE DATA                 public       
   oauth-user    false    202            �           0    0    oauth_client_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.oauth_client_id_seq', 2, true);
          public       
   oauth-user    false    199            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 1, true);
          public       
   oauth-user    false    201            �
           2606    16396 .   flyway_schema_history flyway_schema_history_pk 
   CONSTRAINT     x   ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);
 X   ALTER TABLE ONLY public.flyway_schema_history DROP CONSTRAINT flyway_schema_history_pk;
       public         
   oauth-user    false    196                        2606    16428 *   oauth_access_token oauth_access_token_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.oauth_access_token
    ADD CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id);
 T   ALTER TABLE ONLY public.oauth_access_token DROP CONSTRAINT oauth_access_token_pkey;
       public         
   oauth-user    false    197                       2606    16477    oauth_client oauth_client_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.oauth_client
    ADD CONSTRAINT oauth_client_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.oauth_client DROP CONSTRAINT oauth_client_pkey;
       public         
   oauth-user    false    200                       2606    16488    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         
   oauth-user    false    202            �
           1259    16397    flyway_schema_history_s_idx    INDEX     `   CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);
 /   DROP INDEX public.flyway_schema_history_s_idx;
       public         
   oauth-user    false    196            ~   �  x����N�0F�>���D*������P	����J#�F�bԷ�vC�@�B���ǹ߱}3��_�?����-ym�UYL���G�1�XٗܬJ��fC�׏Wsr�gd��hl�-��֯HQ�v�ϗ�����8,��1[�$�lI�ȉ{����JO�+���6q=R�`ƀ �<G1a8�0;=#�i���h�W�4�G��:��d����+�1[�tT'�şr�
�����:V�������ϋ�n������5�N��MG�u2ʥ�JƆ	�J�OH$!�'+o]PB���}Y��J|�yy���Hsʀ�JL!�K�+ɤ$~tJ�{����]��_��I|�kB� (�`�����J*)�J�}j�[��$�9uH�?� :�{'��� 9�=�X���;���a $RJ9�A�Q�����]���@��G         
   x���          �   �   x���v
Q���W((M��L��O,-ɈO��L�+Qs�	uV�0�Q����IE��ũE�@��Q����JZ�_H���^���~�_�X`pfZj�v�QEU�i^h�����O��������~N�W�7HQjZQjqF|I~vj�NAbqqy~Q�:��PkK3�5��<�t�������Ҽ]���2�S��x����E�)@:31��7@ �+\\ �.vm      �   
   x���          �   �   x���v
Q���W((M��L�+-N-*Vs�	uV�0�Q()*M�Q����QP��q�jRrT�UT���BR�=�J��������3�R�����*�BM�B��=�|RL�|-\�L�s��
���F%��炬U״��� �<+     