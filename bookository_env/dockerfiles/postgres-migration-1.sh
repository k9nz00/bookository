pg_ctl -D "$PGDATA"	-w stop
pg_ctl -D "$PGDATA" -o "-c listen_addresses='0.0.0.0'" -w start
java -DdriverClassName=org.postgresql.Driver -DjdbcUrl=jdbc:postgresql://0.0.0.0:5432/bookository -Ddatabase=$POSTGRES_DB -Dusername=$POSTGRES_USER -Dpassword=$POSTGRES_PASSWORD -jar /opt/migration.jar
