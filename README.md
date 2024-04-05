# IDATT2105-PROJECT-BACKEND
Backend repository for voluntary project in IDATT2105 full-stack application development

# Setup
This is the backend for a fullstack project. The project should be run using the `docker-compose.yml` file, as it requires multiple environment variables, as well as dev-certificates which are currently generated using the `mkcert`-tool. 

This guide requires:
<ul>
  <li>A pc with docker installed</li>
  <li>An environment variable with the name "KEYSTORE_PASSWORD", set to a value of your own choosing</li>
</ul>

Setting an environment variable is done by running `set KEYSTORE_PASSWORD=changeit` (Windows) or by `export KEYSTORE_PASSWORD=changeit` (Unix). If, for whatever reason, you are unable to set variables, you can edit the docker-compose.yml file and replace each occurence of `${KEYSTORE_PASSWORD}` with you password (replace the entire thing). 

After this is done:
<ol>
  <li>Go to an empty folder where you want the project</li>
  <li>Put the docker-compose.yml file in this folder</li>
  <li>Clone the backend project into this folder: https://github.com/1Cezzo/idatt2105-project-backend</li>
  <li>Clone the frontend project as well: https://github.com/1Cezzo/idatt2105-project-frontend</li>
  <li>Run `docker compose up` (this will take several minutes the first time around)</li>
</ol>
The project should now be up and running, and can be accessed from https://localhost:5173 (note that it's https and not http). Your browser will likely alert you that the certificate is not trusted, due to the project using dev-certificates. This can be safely ignored by pressing advanced options and "continue to site", but might required an extra step for chrome browsers: https://superuser.com/questions/772762/how-can-i-disable-security-checks-for-localhost

# API-doc:
For API documentation, download the project, run `mvn clean install` and `mvn spring-boot:run`, and then navigate to `localhost:8443/swagger-ui/index.html` in your browser. Log in with username:`admin` and password: `password` You will land on this page:

![Swagger UI](https://github.com/1Cezzo/idatt2105-project-backend/assets/111747340/36e9da03-c618-436f-b4da-0417027b927a)

