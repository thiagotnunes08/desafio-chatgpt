package br.com.estoque.desafioia.compartilhado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.springframework.util.Assert.isTrue;

public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(CampoUnico params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }

        var query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value",value);

        var list = query.getResultList();

        isTrue(list.size() <= 1,"BUG. tem mais de um "+klass+"com o abtributo"+domainAttribute);

        return list.isEmpty();
    }

    
}
