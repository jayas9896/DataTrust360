# OpenAI Insights Flow

1) `processing-worker` evaluates anomaly score from payload.
2) If threshold exceeded, it sends a summary to OpenAI.
3) OpenAI returns natural language insights for analysts.
4) Insights are stored in SQL audit logs or NoSQL metadata.

## Prompt Sketch

System: You are a security analyst.
User: Summarize anomalies for tenant {{tenantId}} from {{source}}. Event: {{payload}}

## Integration Notes

- Use a low-latency model for near-real-time insights.
- Cache previous summaries to reduce cost.
- Store responses in `audit_log` as `action = 'INSIGHT'`.
