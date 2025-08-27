# ------------------------------------------------------------------------
#
# Copyright 2023 Espire
#
# ------------------------------------------------------------------------

FROM ubuntu:22.04
MAINTAINER Deebendu Kumar, deebendu.kumar@gmail.com
ENV TZ=Asia/Kolkata

RUN \
    apt-get -q update && apt-get install -y openssh-server software-properties-common git ant curl zip unzip xvfb \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && mkdir /var/run/sshd

RUN \
    mkdir -p /build/software/maven \
    && wget -P /build/software/maven https://archive.apache.org/dist/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.tar.gz \
    && tar -xvzf /build/software/maven/apache-maven-3.0.5-bin.tar.gz --directory /build/software/maven \
    && rm /build/software/maven/apache-maven-3.0.5-bin.tar.gz

RUN mkdir -p /build/software/java
RUN apt-get install openjdk-11-jre-headless

WORKDIR /opt/service
RUN mkdir -p /opt/service
RUN mkdir -p /opt/service/config
RUN mkdir -p /opt/service/lib
RUN mkdir -p /opt/service/trace

COPY target /opt/service/lib
COPY src/main/resources /opt/service/config

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Djava.library.path=/opt/service/lib", "-Dspring.config.location=/opt/service/config/application.properties", "-Dlog4j.configuration=file:////opt/service/config/log4j.properties", "/opt/service/lib/ejf-api-discovery-1.0.0.jar"]
