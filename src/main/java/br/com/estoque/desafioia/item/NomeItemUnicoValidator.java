package br.com.estoque.desafioia.item;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NomeItemUnicoValidator implements Validator {

    private final ItemRepository repository;

    public NomeItemUnicoValidator(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoItemRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        var request = (NovoItemRequest) target;

        if (repository.existsByNome(request.nome())) {
            errors.rejectValue("nome", null, "nome de item já cadastrado no sistema!");
        }

    }
}
