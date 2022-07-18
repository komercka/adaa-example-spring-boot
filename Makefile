build: export COMPOSE_DOCKER_CLI_BUILD=1
build: export DOCKER_BUILDKIT=1
build:
	docker build -t adaa --file .docker/adaa/Dockerfile .
start:
	docker-compose --file docker-compose.yml up
