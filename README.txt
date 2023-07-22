Database: PostgreSQL

1. register email
   url: localhost:8080/api/v1/auth/register
   method: POST
   body: {
    	   "firstname": "riko",
    	   "lastname": "test",
    	   "email": "rikokhoirul@gmail.com",
    	   "password": "qwerty"
	 }

2. login dan get token
   url: localhost:8080/api/v1/auth/login
   method: POST
   body: {
    	   "email": "rikokhoirul@gmail.com",
    	   "password": "qwerty"
	 }

3. Get Job List API
   url: localhost:8080/api/v1/getJobList
   method: GET

4. Get Job Detail API
   url: localhost:8080/api/v1/getJobDetail/{id}
   method: GET