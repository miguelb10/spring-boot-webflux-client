package com.bolsadeideas.springboot.webflux.client.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bolsadeideas.springboot.webflux.client.app.models.Producto;
import com.bolsadeideas.springboot.webflux.client.app.models.services.ProductoService;

import reactor.core.publisher.Mono;

@Component
public class ProductoHandler {

	@Autowired
	private ProductoService service;

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(service.findAll(), Producto.class);
	}

	public Mono<ServerResponse> ver(ServerRequest request){
		String id = request.pathVariable("id");
		return service.findById(id).flatMap(p -> ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.bodyValue(p)
				.switchIfEmpty(ServerResponse.notFound().build()));
	}
}
