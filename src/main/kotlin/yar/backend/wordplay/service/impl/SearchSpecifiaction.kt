package yar.backend.wordplay.service.impl

import org.springframework.data.jpa.domain.Specification
import yar.backend.wordplay.entity.BookEntity

class SearchSpecifiaction {
    companion object {
        fun searchByFields(searchText: String): Specification<BookEntity> {
            return Specification { root, _, criteriaBuilder ->
                criteriaBuilder.or(
                    criteriaBuilder.like(root["field1"], "%$searchText%"),
                    criteriaBuilder.like(root["field2"], "%$searchText%"),
                    criteriaBuilder.like(root["field3"], "%$searchText%")
                )
            }
        }
    }
}
