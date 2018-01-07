FROM java:8-jre
ADD fakeSMTP-2.0.jar /opt
EXPOSE 2525
VOLUME ["/var/mail"]

CMD java -jar /opt/fakeSMTP-2.0.jar -s -b -p 2525 -o /var/mail 