FROM mjbelow/openjdk-openjfx as jre-build

# Copy source files to compile/package application
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY . .

# Compile source to classes and output to bin
RUN javac -d bin --module-path $PATH_TO_FX --add-modules=javafx.controls src/*.java
# Create jar file
RUN jar cfe app.jar Game -C bin .

# Create a custom Java runtime
RUN $JAVA_HOME/bin/jlink \
         --module-path $PATH_TO_FX_JMOD \
         --add-modules java.base,javafx.controls \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Define your base image
FROM debian:buster-slim as base

# Need this library for graphical application
RUN apt update && apt -y install libgtk-3-0

# Copy over Java runtime and add binaries to path
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME

# Display environment variable to be used with local X server
ENV DISPLAY=host.docker.internal:0.0

# Deploy application built from JDK (jre-build)
RUN mkdir /opt/app
COPY --from=jre-build /opt/app/app.jar /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]
