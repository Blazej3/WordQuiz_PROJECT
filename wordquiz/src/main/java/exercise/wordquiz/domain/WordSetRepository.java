package exercise.wordquiz.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WordSetRepository extends CrudRepository <WordSet, Long>{

List<WordSet> findByEngWord(String engWord);

}

