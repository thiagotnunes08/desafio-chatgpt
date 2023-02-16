package br.com.estoque.desafioia.item;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AtualizaItemController {

    private final ItemRepository repository;

    public AtualizaItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PutMapping("item/{id}")
    public ResponseEntity<ItemResponse> atualiza(@PathVariable Long id, @RequestBody @Valid AtualizaItemRequest request) {

        var item = repository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Item %s inválido ou não encontrado no sistema!"
                                        .formatted(id)));


        item.atualizaItemCompleto(request);

        return ResponseEntity.ok(new ItemResponse(item));


    }

}
