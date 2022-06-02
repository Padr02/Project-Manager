
# PCS app
This Spring and Vaadin-based web application was built as a student project in April 2022.

##Screenshot
![pcs](https://user-images.githubusercontent.com/82325129/171657849-911d11d4-c92e-4d3b-b9dd-3db2dc88d0b2.png)

## Running the application
The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

The project can also be imported to your IDE of choice as you would with any
Maven project. Read more on [how to import Vaadin projects to different 
IDEs](https://vaadin.com/docs/latest/flow/guide/step-by-step/importing) (Eclipse, IntelliJ IDEA, NetBeans, and VS Code).

## Deploying to Production
To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/myapp-1.0-SNAPSHOT.jar`

## Controllers and DTOs
This Spring application makes neither use of separate controllers nor DTOs. However, these files remain in the catalogue structure for future reference.

## Room for improvement
As of now, a session is still valid when a user presses the back button. It is also still valid when a user closes the browser, opens a new browser and writes the URL with an endpoint.
This means that the invalidation of session relies on the user to log out. This is widely practiced by companies. The exception would be banks, who do not want to allow too excessive access.

After a long discussion, our group opted to allow the session to persist and rely on the user to log out. This is, on the other hand, something that can be improved with logic that manages the use of the application's client-side.

## Authors
Pavel Drepalov, github: padr02
Sebastian Persson, github: Swesebbe91
Connie Tran Hedberg, github: contranhed


