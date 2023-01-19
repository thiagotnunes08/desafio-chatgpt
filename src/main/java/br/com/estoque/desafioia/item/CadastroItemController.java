package br.com.estoque.desafioia.item;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CadastroItemController {

    @Autowired
    private final ItemRepository repository;

    public CadastroItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/itens")
    public ResponseEntity<ItemResponse> cadastra(@Valid @RequestBody NovoItemRequest request, UriComponentsBuilder uriBuilder) {

        var novoItem = request.toModel();

        repository.save(novoItem);

        var location = uriBuilder
                .path("itens/{id}")
                .buildAndExpand(novoItem.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new ItemResponse(novoItem));

    }
}
