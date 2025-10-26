# Creates required Kinesis streams in LocalStack.
# Requires aws CLI configured to talk to LocalStack.

$env:AWS_ACCESS_KEY_ID = "test"
$env:AWS_SECRET_ACCESS_KEY = "test"
$env:AWS_DEFAULT_REGION = "us-east-1"

aws --endpoint-url=http://localhost:4566 kinesis create-stream --stream-name dt360-partner-ingest --shard-count 1
