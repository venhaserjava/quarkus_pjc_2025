package unidade.validators;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUnidadeFieldValidator implements ConstraintValidator<UniqueUnidadeField, String> {

    @Inject
    EntityManager em;

    private String fieldName;

    @Override
    public void initialize(UniqueUnidadeField constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Não valida campos vazios, apenas duplicidade
        }

        String jpql = "SELECT COUNT(u) FROM Unidade u WHERE u." + fieldName + " = :value";
        Query query = em.createQuery(jpql);
        query.setParameter("value", value);
        Long count = (Long) query.getSingleResult();

        return count == 0;
    }
}
