# IDATT2105-PROJECT-BACKEND
Backend repository for voluntary project in IDATT2105 full-stack application development

# Setup
This is the backend for a fullstack project. The project should be run using the `docker-compose.yml` file, as it requires multiple environment variables, as well as dev-certificates which are currently generated using the `mkcert`-tool. 

This guide requires:
<ul>
  <li>A pc with docker installed</li>
  <li>The ability to set environment variables</li>
</ul>

Setting an environment variable is done by running `set KEYSTORE_PASSWORD=changeit` (Windows) or by `export KEYSTORE_PASSWORD=changeit` (Unix). If, for whatever reason, you are unable to set variables, you can edit the docker-compose.yml file and replace each occurence of `${KEYSTORE_PASSWORD}` with your password (replace the entire thing). 

The following environment variables are needed:
<ul>
  <li>KEYSTORE_PASSWORD - Whatever value you want. THIS IS REQUIRED FOR THE PROJECT TO RUN</li>
  <li>SMTP_PASSWORD</li>
  <li>SMTP_EMAIL</li>
  <li>SMTP_HOST</li>
  <li>ENDPOINT_URL</li>
  <li>ACCESS_KEY</li>
  <li>SECRET_KEY</li>
  <li>BUCKET_NAME</li>
</ul>

After this is done:
<ol>
  <li>Go to an empty folder where you want the project</li>
  <li>Put the docker-compose.yml file in this folder</li>
  <li>Clone the backend project into this folder: https://github.com/1Cezzo/idatt2105-project-backend</li>
  <li>Clone the frontend project as well: https://github.com/1Cezzo/idatt2105-project-frontend</li>
  <li>Run `docker compose up` (this will take several minutes the first time around)</li>
</ol>
The project should now be up and running, and can be accessed from https://localhost:5173 (note that it's https and not http). Your browser will likely alert you that the certificate is not trusted, due to the project using dev-certificates. This can be safely ignored by pressing advanced options and "continue to site", but you will have to allow insecure localhost connections as well to communicate with the backend:
<ul>
  <li>Edge - Go to <code>edge://flags/#allow-insecure-localhost</code> and set the highligthed option to enabled</li>
  <li>Chrome - Go to <code>chrome://flags/#allow-insecure-localhost</code> and set the highlighted option to enabled</li>
  <li>Opera GX - From the terminal, run: <code>"C:\path\to\opergx\launcher.exe" --allow-insecure-localhost</code></li>
  <li>Firefox - There seems to be a lot of problems with how firefox handles self-signed certificates, so we do not recommend using firefox. Going to localhost:5173 and accepting the warning, then going to localhost:8443/login and accepting the warning, will get you to the site, but there might be problems loading images and such. </li>
</ul>

## Notes
The backend is set up to fill the database with placeholder quizzes when it runs, this happens in the `util/Dataloader.java` class. Comment this class out if this is not desired. 
# API-doc:
For API documentation, run the project using the above guide, and then navigate to `localhost:8443/swagger-ui/index.html` in your browser. Log in with username:`admin` and password: `password` You will land on this page:

![Swagger UI](https://github.com/1Cezzo/idatt2105-project-backend/assets/111747340/36e9da03-c618-436f-b4da-0417027b927a)

# Testing
The project uses JUnit tests, which can be run with Maven from the commandline with `mvn clean test`
