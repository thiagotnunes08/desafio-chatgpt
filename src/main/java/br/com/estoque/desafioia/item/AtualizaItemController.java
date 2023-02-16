package br.com.estoque.desafioia.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//quando também mapear o recurso com requestMapping aki ?
@RestController
public class AtualizaItemController {

    private final ItemRepository repository;

    public AtualizaItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @Transactional
    //essa nomeclatura do recurso, ta ok ?
    @PutMapping("item/{id}/atualiza")
    public ResponseEntity<ItemResponse> atualiza(@PathVariable Long id, @RequestBody NovoItemRequest request) {

        var item = repository
                .findById(id)
                .orElseThrow(() ->
                        //qual a melhor response ex nesse caso ? 404,400 ou 422
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Item %s inválido ou não encontrado no sistema!"
                                        .formatted(id)));


        item.logicaAtualiza(request);

        //retonando um 200 por conta do body. mas normalmente seria um 204 ?
        return ResponseEntity.ok(new ItemResponse(item));


    }

}
