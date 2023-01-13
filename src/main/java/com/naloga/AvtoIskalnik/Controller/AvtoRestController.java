package com.naloga.AvtoIskalnik.Controller;

import com.naloga.AvtoIskalnik.Components.WebCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avtoIskalnik")
public class AvtoRestController {
    @Autowired
    private WebCrawler webCrawler;
    @GetMapping("/endpoint1")
    public boolean startCrawler(){
        webCrawler.crawlAvtoNet();
        return false;
    }

}
