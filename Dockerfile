FROM mjbelow/openjdk-openjfx

ENV DISPLAY=host.docker.internal:0.0
RUN mkdir -p root
WORKDIR /root
COPY . .
RUN javac -d bin --module-path $PATH_TO_FX --add-modules=javafx.controls src/*.java
CMD java -cp bin --module-path $PATH_TO_FX --add-modules=javafx.controls Game
