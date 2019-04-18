FROM java:8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ./target/huahuadai-api.jar /app/huahuadai-api.jar
WORKDIR /app
RUN bash -c 'touch /huahuadai-api.jar'
#EXPOSE 8089
ENV JAVA_OPTS="-Dlogback.path=/opt/logs/huahuadai/api -Dspring.profiles.active=dev -Xms128M -Xmx128M"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar huahuadai-api.jar" ]