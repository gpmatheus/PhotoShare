# PhotoShare


## Installation

### Prerequisites

java 11

node 18.15.0

maven 3.9.1

docker 20.10.23

### Instructions

Pull database docker image:

docker pull gpmatheus/photoshare-database

create a container from this image:

docker run -d --name pshare-db -p 5000:5432 gpmatheus/photoshare-database

in PhotoShare-BackEnd folder, run the command:

mvn spring-boot:run

now install the angular cli globally:

npm install -g @angular/cli

in the PhotoShare-FrontEnd folder, run the command:

ng serve

in a browser, go to http://localhost:4200
