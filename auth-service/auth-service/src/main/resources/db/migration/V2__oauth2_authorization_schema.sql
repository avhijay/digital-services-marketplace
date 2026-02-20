    CREATE TABLE oauth2_authorization (

    id VARCHAR(100) PRIMARY KEY,
    registered_client_id VARCHAR(100) NOT NULL,
    principal_name VARCHAR(200) NOT NULL,
    authorization_grant_type VARCHAR(100) NOT NULL,
    attributes BLOB,
    state VARCHAR(500),
    authorization_code_value BLOB,
    authorization_code_issued_at TIMESTAMP,
    authorization_code_expires_at TIMESTAMP,
    authorization_code_metadata BLOB,
    access_token_value BLOB,
    access_token_issued_at TIMESTAMP,
    access_token_expires_at TIMESTAMP,
    access_token_metadata BLOB,
    access_token_type VARCHAR(100),
    access_token_scopes VARCHAR(1000),
    refresh_token_value BLOB,
    refresh_token_issued_at TIMESTAMP,
    refresh_token_expires_at TIMESTAMP,
    refresh_token_metadata BLOB,

    CONSTRAINT fk_registered_client FOREIGN KEY (registered_client_id)  REFERENCES oauth2_registered_client (id)








);