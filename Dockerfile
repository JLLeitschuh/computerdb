from maven:3.5.0-jdk-8-onbuild

COPY . /usr/src/app

RUN ["mvn", "package"]