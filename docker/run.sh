
echo "<< docker-compose -f docker-compose-base.yml  up >>"

docker build ./postgres -t postgres:instaton

docker build ./smtp -t smtp:instaton

docker-compose up