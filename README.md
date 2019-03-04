# imperative:
	docker run --name server -p 8080:8080 -d masters.imperative.server
	docker run --name client --link server:server masters.imperative.client server 8080 5
	
	
# reactive:
	docker run --name server -p 8081:8081 -d masters.reactive.server
	docker run --name client --link server:server masters.reactive.client server 8081 5

