package exercise.wordquiz.domain;

import jakarta.persistence.*;





@Entity
public class WordSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String engWord;
    private String finWord;
    private String exampleSentence;
    private String difficultyLevel;
    
   
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

public WordSet(){

}

public WordSet(String engWord, String finWord, String exampleSentence, String difficultyLevel, Category category){
    this.engWord = engWord;
    this.finWord = finWord;
    this.exampleSentence = exampleSentence;
    this.difficultyLevel = difficultyLevel;
    this.category = category;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getEngWord() {
    return engWord;
}

public void setEngWord(String engWord) {
    this.engWord = engWord;
}

public String getFinWord() {
    return finWord;
}

public void setFinWord(String finWord) {
    this.finWord = finWord;
}

public String getExampleSentence() {
    return exampleSentence;
}

public void setExampleSentence(String exampleSentence) {
    this.exampleSentence = exampleSentence;
}

public String getDifficultyLevel() {
    return difficultyLevel;
}

public void setDifficultyLevel(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
}

public Category getCategory() {
    return category;
}

public void setCategory(Category category) {
    this.category = category;
}

@Override
public String toString() {
    return "WordSet [id=" + id + ", engWord=" + engWord + ", finWord=" + finWord + ", exampleSentence="
            + exampleSentence + ", difficultyLevel=" + difficultyLevel + ", category=" + category + "]";
}


}
