# Docker Setup for agentGeneric

This guide explains how to run the agentGeneric application using Docker.

## Prerequisites

- Docker (version 20.10 or higher)
- Docker Compose (version 1.29 or higher)

## Quick Start with Docker Compose

The easiest way to run the entire stack (App + MySQL + Ollama) is using Docker Compose:

```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

The application will be available at `http://localhost:8080`

## Building the Docker Image

To build just the application image:

```bash
docker build -t agengeneric:latest .
```

## Running the Application Container Only

If you already have MySQL and Ollama running, you can run just the app:

```bash
docker run -d \
  --name agentGeneric-app \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/company_db \
  -e SPRING_DATASOURCE_USERNAME=user \
  -e SPRING_DATASOURCE_PASSWORD=pass \
  -e SPRING_AI_OLLAMA_BASE_URL=http://host.docker.internal:11434 \
  agentgeneric:latest
```

## Environment Variables

The following environment variables can be configured:

- `SPRING_DATASOURCE_URL` - MySQL connection URL (default: jdbc:mysql://mysql:3306/company_db)
- `SPRING_DATASOURCE_USERNAME` - MySQL username (default: user)
- `SPRING_DATASOURCE_PASSWORD` - MySQL password (default: pass)
- `SPRING_AI_OLLAMA_BASE_URL` - Ollama API URL (default: http://ollama:11434)

## Checking the Application Status

```bash
# Check if the app is running
docker-compose ps

# View application logs
docker-compose logs app

# View specific number of log lines
docker-compose logs app --tail=100

# Follow logs in real-time
docker-compose logs -f app
```

## Common Issues

### MySQL Connection Refused
- Ensure the MySQL service is healthy: `docker-compose ps`
- Wait for MySQL to be ready (check the health status)
- Verify the connection string matches the service name in docker-compose.yml

### Ollama Not Responding
- Ensure the Ollama service is running: `docker-compose ps ollama`
- Verify it's listening on port 11434: `docker-compose logs ollama`
- Pull a model if needed (inside the Ollama container): `docker exec agentGeneric-ollama ollama pull llama3.2`

### Port Already in Use
If ports 8080, 3306, or 11434 are already in use, modify the port mappings in `docker-compose.yml`:

```yaml
ports:
  - "8081:8080"  # Host:Container port mapping
```

## Rebuilding After Code Changes

```bash
# Rebuild the image and restart services
docker-compose up -d --build
```

## Production Considerations

For production deployment:

1. Use a robust container orchestration platform (Kubernetes, Docker Swarm, etc.)
2. Use external database services (AWS RDS, Azure Database, etc.)
3. Implement proper secret management instead of environment variables
4. Set resource limits (CPU, memory) in docker-compose.yml or deployment configs
5. Use health checks and restart policies
6. Consider using a reverse proxy (Nginx, Traefik) in front of the app
7. Configure proper logging and monitoring
8. Use version tags for images instead of `latest`

## Cleanup

```bash
# Remove all containers and volumes
docker-compose down -v

# Remove the image
docker rmi agentgeneric:latest
```

