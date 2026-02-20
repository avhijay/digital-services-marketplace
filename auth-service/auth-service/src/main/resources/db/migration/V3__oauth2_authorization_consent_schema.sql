

    CREATE TABLE oauth2_authorization_consent (



    registered_client_id VARCHAR(100) NOT NULL,
    principal_name VARCHAR(200) NOT NULL,
    authorities VARCHAR(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name),
    CONSTRAINT fk_registered_client_consent
    FOREIGN KEY (registered_client_id)
    REFERENCES oauth2_registered_client (id)




);