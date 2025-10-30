# Operations Runbook

## Local Dev (Docker Compose)
1) Build services:

```bash
mvn -q -DskipTests package
```

2) Start dependencies:

```bash
docker compose -f docker/docker-compose.yml up -d
```

3) Run services locally (example):

```bash
java -jar auth-gateway/target/auth-gateway-0.1.0-SNAPSHOT.jar
```

## Local Kubernetes
1) Apply dependencies and services:

```bash
kubectl apply -f k8s/dependencies.yaml
kubectl apply -f k8s/
kubectl apply -f k8s/observability.yaml
```

2) Check pods:

```bash
kubectl -n datatrust360 get pods
```

## Logs and Monitoring
- Prometheus: `http://localhost:9090`
- Grafana: `http://localhost:3000`

## Troubleshooting
- Verify Kafka connectivity: check `KAFKA_BOOTSTRAP_SERVERS` env var.
- Verify Kinesis stream exists: run `scripts/init-localstack.ps1`.
- Check storage service logs for persistence errors.
