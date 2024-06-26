package exercise.wordquiz.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import exercise.wordquiz.domain.CategoryRepository;
import exercise.wordquiz.domain.WordSetRepository;
import exercise.wordquiz.domain.WordSet;

@Controller
public class WordSetController {
    @Autowired
    private WordSetRepository w_repository;

    @Autowired
    private CategoryRepository c_repository;

    // login page
    @RequestMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    // show all WordSets
    @RequestMapping(value = { "/home" })
    public String wordList(Model model) {
        model.addAttribute("categories", c_repository.findAll());
        return "home.html";
    }

    @GetMapping("/categorylist/{categoryId}")
    public String displayWordsByCategory(@PathVariable Long categoryId, Model model) {
        List<WordSet> words = w_repository.findByCategoryCategoryId(categoryId);
        model.addAttribute("words", words);
        model.addAttribute("categoryId", categoryId);
        return "wordlist.html";
    }

    @GetMapping(value = "/editwordset/{id}")
    public String editWordSetForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("word", w_repository.findById(id));
        model.addAttribute("categories", c_repository.findAll());
        return "editwordset.html";
    }

    @GetMapping(value = "/addwordset/{categoryId}")
    public String addWordSet(@PathVariable("categoryId") Long categoryId, Model model) {
        WordSet newwordSet = new WordSet();
        newwordSet.setCategory(c_repository.findById(categoryId).orElse(null));
        model.addAttribute("word", newwordSet);

        return "addwordset.html";
    }

    @GetMapping(value = "/deletewordset/{id}")
    public String deleteWordSet(@PathVariable("id") Long id, Model model) {
        Long categoryId = w_repository.findById(id).get().getCategory().getCategoryId();
        w_repository.deleteById(id);
        return "redirect:/categorylist/" + categoryId;
    }

    @PostMapping(value = "/savewordset")
    public String save(WordSet wordSet) {
        w_repository.save(wordSet);
        return "redirect:/categorylist/" + wordSet.getCategory().getCategoryId();
    }

    @GetMapping("/quiz/{categoryId}/{wordIndex}")
    public String quizWordsByCategory(@PathVariable Long categoryId,
            @PathVariable int wordIndex,
            Model model) {
        List<WordSet> words = w_repository.findByCategoryCategoryId(categoryId);

        // Check if the index is within the bounds of the list
        if (wordIndex >= 0 && wordIndex < words.size()) {
            WordSet word = words.get(wordIndex);

            model.addAttribute("wordIndex", wordIndex);
            model.addAttribute("word", word);
            model.addAttribute("result", "");
        } else {
            // Handle invalid index
            model.addAttribute("error", "Invalid word index.");
        }

        return "quiz.html";
    }

    

    @PostMapping("/submit-quiz")
    public String submitQuiz(@RequestParam("wordId") Long wordId,
            @RequestParam("userFinWord") String userFinWord,
            @RequestParam("wordIndex") int wordIndex,
            Model model) {
        WordSet word = w_repository.findById(wordId).orElse(null);

        if (word == null) {
            model.addAttribute("result", "Word not found!");
        }else if (userFinWord.equalsIgnoreCase(word.getFinWord())) {
            model.addAttribute("category", word.getCategory());
            model.addAttribute("result", "Correct!");
            model.addAttribute("correctanswer", word.getEngWord() + " = " + word.getFinWord());
            model.addAttribute("exampleSentence", word.getExampleSentence());
            List<WordSet> words = w_repository.findByCategoryCategoryId(word.getCategory().getCategoryId());

            if (wordIndex < words.size() - 1) {

                model.addAttribute("wordIndex", wordIndex + 1);

            } else {
                // Quiz completed message
                model.addAttribute("result", "Correct! Quiz completed.");
            }
        } else {
            model.addAttribute("result", "Incorrect! Try again.");
            model.addAttribute("wordIndex", wordIndex);

        }

        // Always add the current word to the model
        model.addAttribute("word", word);

        return "quiz.html";
    }

}
