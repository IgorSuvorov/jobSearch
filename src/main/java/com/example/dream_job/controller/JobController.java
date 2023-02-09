package com.example.dream_job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.dream_job.model.Job;

/**
 * @author Igor Suvorov
 */

@Controller
public class JobController {

    private final PostService postService;

    public JobController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts.html";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("posts", new Job(0, "Fill this field"));
        return "addPost";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", postService.findById(id));
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Job post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Job post) {
        postService.savePost(post);
        return "redirect:/posts";
    }
}