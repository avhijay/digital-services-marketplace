

INSERT INTO oauth2_registered_client (
id,
client_id,
client_secret,
client_name,
client_authentication_methods,
authorization_grant_types,
scopes,
client_settings,
token_settings

)
VALUES(
'api-gateway-id',
'api-gateway',
'',
'API Gateway',
'client_secret_basic',
'client_credentials,refresh_token',
'payment.read,payment.write',
'{}',
'{}'


);