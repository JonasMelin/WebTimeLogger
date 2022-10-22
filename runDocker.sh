docker kill webtimelogger || true
sleep 2
docker run -d --rm --network=host --name=webtimelogger webtimelogger:latest

