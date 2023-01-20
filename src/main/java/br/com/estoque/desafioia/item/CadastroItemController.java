package br.com.estoque.desafioia.item;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CadastroItemController {

    private final ItemRepository repository;
    private final NomeItemUnicoValidator validator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(validator);
    }

    public CadastroItemController(ItemRepository repository, NomeItemUnicoValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @PostMapping("/itens")
    @Transactional
    public ResponseEntity<ItemResponse> cadastra(@Valid @RequestBody NovoItemRequest request) {

        var novoItem = request.toModel();

        repository.save(novoItem);

        var location = URI.create("http://localhost/itens/" + novoItem.getId());

        return ResponseEntity
                .created(location)
                .body(new ItemResponse(novoItem));

    }
}
