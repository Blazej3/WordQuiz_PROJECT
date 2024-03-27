package exercise.wordquiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import exercise.wordquiz.domain.CategoryRepository;
import exercise.wordquiz.domain.WordSetRepository;

@Controller
public class WordSetController {
    @Autowired
    private WordSetRepository w_repository;

    @Autowired
    private CategoryRepository c_repository;

    // show all WordSets
    @RequestMapping(value = { "/wordlist" })
    public String wordList(Model model) {
        model.addAttribute("wordsets", w_repository.findAll());
        return "wordlist.html";
    }



}
