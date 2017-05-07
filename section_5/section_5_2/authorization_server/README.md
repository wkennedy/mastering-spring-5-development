
cURL command to get a token using client_credentials grant type

    curl client:secret@localhost:8082/auth/oauth/token -d grant_type=client_credentials -d scope=read -d client_id=client -d client_secret=secret -d username=client -d password=secret
    
    curl -H "Authorization: Bearer 872048f3-8c15-4dd4-a20a-3b6fe5aa2a52" http://localhost:8082/auth/me