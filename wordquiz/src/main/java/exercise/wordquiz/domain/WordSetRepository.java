package exercise.wordquiz.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WordSetRepository extends CrudRepository <WordSet, Long>{

    List<WordSet> findByCategoryCategoryId(Long categoryId);

    List<WordSet> findByEngWord(String engWord);

}

