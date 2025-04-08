# Elasticsearch and Kibana Docker Setup

This repository contains a Docker Compose configuration for running Elasticsearch and Kibana with custom credentials.

---

## Prerequisites

- Docker
- Docker Compose

---

## Setup Instructions

1. **Clone this repository**

3. **Start the services:**

   ```bash
   docker compose up -d
   ```

4. Wait for all services to start (this may take a few minutes)


## Resetting a Password



2. Open a terminal inside the Elasticsearch container:

   ```bash
   docker exec -it <elasticsearch_container_name> bash
   ```

   Replace `<elasticsearch_container_name>` with your actual container name. Use `docker ps` to find it.

3. Run the following command to reset the password interactively:

   ```bash
   /usr/share/elasticsearch/bin/elasticsearch-reset-password -i -u kibana_system
   ```

4. You will see a prompt like this:

   ```
   This tool will reset the password of the [kibana_system] user.
   You will be prompted to enter a new password.
   Do you want to continue [y/N]
   ```

5. Type `y` and press **Enter**, then enter the new password when prompted.

6. Ensure that the password you enter matches the one in your `.env` file.

   ```env
   KIBANA_PASSWORD=new_secure_password_here
   ```
7. Restart the services for the new password to take effect:

   ```bash
   docker compose down
   docker compose up -d
   ```
---

## Accessing the Services

### Elasticsearch

- **URL:** [http://localhost:9200](http://localhost:9200)
- **Username:** `elastic`
- **Password:** (value of `ELASTIC_PASSWORD` in `.env`)

### Kibana

- **URL:** [http://localhost:5601](http://localhost:5601)
- **Username:** `elastic`
- **Password:** (value of `ELASTIC_PASSWORD` in `.env`)

---

## Verifying the Setup

To check the health of the Elasticsearch cluster:

```bash
curl -u elastic:your_elastic_password http://localhost:9200/_cluster/health
```

To access Kibana, open [http://localhost:5601](http://localhost:5601) and log in with the `elastic` user credentials.

